package com.ipd.xiangzui.activity;

import android.content.Intent;
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
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.ipd.xiangzui.common.view.SpacesItemDecoration;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_102;

/**
 * Description ：对公账户
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/29.
 */
public class AccountActivity extends BaseActivity {

    @BindView(R.id.tv_account)
    TopView tvAccount;
    @BindView(R.id.rv_account)
    RecyclerView rvAccount;
    @BindView(R.id.srl_account)
    SwipeRefreshLayout srlAccount;

    private List<TestMultiItemEntityBean> list = new ArrayList<>();
    private AccountAdapter accountAdapter;
    private int pageNum = 1;//页数
    private int removePosition;//删除时用到的下标

    @Override
    public int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAccount);

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
        if (5 > 0) {//TODO 有接口后,5替换为总条数
            if (pageNum == 1) {
                list.clear();
                for (int i = 0; i < 5; i++) {//TODO 假数据
                    list.add(new TestMultiItemEntityBean());
                }
//                list.addAll(data.getData().getRoomList()); //TODO 暂用假数据代替
                accountAdapter = new AccountAdapter(list);
                rvAccount.setAdapter(accountAdapter);
                accountAdapter.bindToRecyclerView(rvAccount);
                accountAdapter.setEnableLoadMore(true);
                accountAdapter.openLoadAnimation();
                accountAdapter.disableLoadMoreIfNotFullPage();

                accountAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.ib_edit_account: //编辑账户
                                removePosition = position;
                                startActivityForResult(new Intent(AccountActivity.this, ModifyAccountActivity.class).putExtra("accountType", 2), REQUEST_CODE_102);
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

                if (5 > 10) {//TODO 有接口后,5替换为总条数
                    pageNum += 1;
                } else {
                    accountAdapter.loadMoreEnd();
                }
            } else {
                for (int i = 0; i < 5; i++) {//TODO 假数据
                    list.add(new TestMultiItemEntityBean());
                }
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后,5替换为总条数
                    pageNum += 1;
                    accountAdapter.addData(list);
                    accountAdapter.loadMoreComplete(); //完成本次
                } else {
                    accountAdapter.addData(list);
                    accountAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            list.clear();
            accountAdapter = new AccountAdapter(list);
            rvAccount.setAdapter(accountAdapter);
            accountAdapter.loadMoreEnd(); //完成所有加载
            accountAdapter.setEmptyView(R.layout.null_data, rvAccount);
        }
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
                        list.remove(removePosition);
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
        startActivityForResult(new Intent(this, ModifyAccountActivity.class).putExtra("accountType", 1), REQUEST_CODE_102);
    }
}
