package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.AccountAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.AccountListBean;
import com.ipd.xiangzui.bean.AddAccountBean;
import com.ipd.xiangzui.bean.DelAccountBean;
import com.ipd.xiangzui.bean.ModifyAccountBean;
import com.ipd.xiangzui.common.view.SpacesItemDecoration;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.AccountContract;
import com.ipd.xiangzui.presenter.AccountPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_102;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：对公账户
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/29.
 */
public class AccountActivity extends BaseActivity<AccountContract.View, AccountContract.Presenter> implements AccountContract.View {

    @BindView(R.id.tv_account)
    TopView tvAccount;
    @BindView(R.id.rv_account)
    RecyclerView rvAccount;
    @BindView(R.id.srl_account)
    SwipeRefreshLayout srlAccount;

    private static Handler handler = new Handler();
    private List<AccountListBean.DataBean.BroughtListBean> broughtList = new ArrayList<>();
    private AccountAdapter accountAdapter;
    private int pageNum = 1;//页数
    private int removePosition;//删除时用到的下标
    private int accountType;//1: 可以选择, 2: 不可以选择

    @Override
    public int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public AccountContract.Presenter createPresenter() {
        return new AccountPresenter(this);
    }

    @Override
    public AccountContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAccount);

        accountType = getIntent().getIntExtra("account_type", 2);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvAccount.setLayoutManager(layoutManager);
        rvAccount.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvAccount.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvAccount.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlAccount.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        TreeMap<String, String> accountListMap = new TreeMap<>();
        accountListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        accountListMap.put("pageNum", pageNum + "");
        accountListMap.put("pageSize", "10");
        accountListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(accountListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getAccountList(accountListMap, false, false);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlAccount.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlAccount.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_102:
                    if (data.getIntExtra("del_account", 0) != 0) {
                        broughtList.remove(removePosition);
                        accountAdapter.notifyDataSetChanged();
                        accountAdapter.setEmptyView(R.layout.null_data, rvAccount);
                    } else {
                        pageNum = 1;
                        initData();
                    }
                    break;
            }
        }
    }

    @OnClick(R.id.bt_add_account)
    public void onViewClicked() {
        if (isFastClick())
            startActivityForResult(new Intent(this, ModifyAccountActivity.class).putExtra("accountType", 1), REQUEST_CODE_102);
    }

    @Override
    public void resultAccountList(AccountListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        broughtList.clear();
                        broughtList.addAll(data.getData().getBroughtList());
                        accountAdapter = new AccountAdapter(broughtList);
                        rvAccount.setAdapter(accountAdapter);
                        accountAdapter.bindToRecyclerView(rvAccount);
                        accountAdapter.setEnableLoadMore(true);
                        accountAdapter.openLoadAnimation();
                        accountAdapter.disableLoadMoreIfNotFullPage();

                        accountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.stv_account_item: //选中账户
                                        if (accountType == 1) {
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // 使用postDelayed方式修改UI组件tvMessage的Text属性值
                                                    // 并且延迟执行
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            setResult(RESULT_OK, new Intent().putExtra("open_bank", broughtList.get(position).getOpenBank()).putExtra("bank_code", broughtList.get(position).getBankAccount()).putExtra("broughtId", broughtList.get(position).getBroughtId()));
                                                            finish();
                                                        }
                                                    }, 500);
                                                }
                                            }).start();
                                        }
                                        break;
                                    case R.id.ib_edit_account: //编辑账户
                                        removePosition = position;
                                        startActivityForResult(new Intent(AccountActivity.this, ModifyAccountActivity.class).putExtra("accountType", 2).putExtra("companyName", broughtList.get(position).getCompanyName()).putExtra("openBank", broughtList.get(position).getOpenBank()).putExtra("bankAccount", broughtList.get(position).getBankAccount()).putExtra("broughtId", broughtList.get(position).getBroughtId()), REQUEST_CODE_102);
                                        break;
                                }
                            }
                        });

                        //上拉加载
                        accountAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvAccount.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvAccount);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            accountAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            accountAdapter.addData(broughtList);
                            accountAdapter.loadMoreComplete(); //完成本次
                        } else {
                            accountAdapter.addData(broughtList);
                            accountAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    broughtList.clear();
                    accountAdapter = new AccountAdapter(broughtList);
                    rvAccount.setAdapter(accountAdapter);
                    accountAdapter.loadMoreEnd(); //完成所有加载
                    accountAdapter.setEmptyView(R.layout.null_data, rvAccount);
                }
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultAddAccount(AddAccountBean data) {

    }

    @Override
    public void resultModifyAccount(ModifyAccountBean data) {

    }

    @Override
    public void resultDelAccount(DelAccountBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
