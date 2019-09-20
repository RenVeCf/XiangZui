package com.ipd.xiangzui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.SelectAddressAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.AddAddressBean;
import com.ipd.xiangzui.bean.AddressListBean;
import com.ipd.xiangzui.bean.DelAddressBean;
import com.ipd.xiangzui.bean.ModifyAddressBean;
import com.ipd.xiangzui.common.view.SpacesItemDecoration;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.AddressContract;
import com.ipd.xiangzui.presenter.AddressPresenter;
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

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_99;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;

/**
 * Description ：选择地址
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/9.
 */
public class SelectAddressActivity extends BaseActivity<AddressContract.View, AddressContract.Presenter> implements AddressContract.View {

    @BindView(R.id.tv_select_address)
    TopView tvSelectAddress;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rv_select_address)
    RecyclerView rvSelectAddress;
    @BindView(R.id.srl_select_address)
    SwipeRefreshLayout srlSelectAddress;

    private static Handler handler = new Handler();
    private List<AddressListBean.DataBean.AddrListBean> addrList = new ArrayList<>();
    private SelectAddressAdapter selectAddressAdapter;
    private int pageNum = 1;//页数
    private int removePosition;//删除时用到的下标
    private int addressType;//1: 选择地址, 2: 地址管理

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_address;
    }

    @Override
    public AddressContract.Presenter createPresenter() {
        return new AddressPresenter(this);
    }

    @Override
    public AddressContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSelectAddress);

        addressType = getIntent().getIntExtra("address_type", 0);
        tvTopTitle.setText(addressType == 1 ? "选择地址" : "地址管理");

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvSelectAddress.setLayoutManager(layoutManager);
        rvSelectAddress.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvSelectAddress.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvSelectAddress.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlSelectAddress.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        TreeMap<String, String> modifyUserInfoMap = new TreeMap<>();
        modifyUserInfoMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        modifyUserInfoMap.put("pageNum", pageNum + "");
        modifyUserInfoMap.put("pageSize", "10");
        modifyUserInfoMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyUserInfoMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getAddressList(modifyUserInfoMap, false, false);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlSelectAddress.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlSelectAddress.setRefreshing(false);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_99:
                    if (data.getIntExtra("del_address", 0) != 0) {
                        addrList.remove(removePosition);
                        selectAddressAdapter.notifyDataSetChanged();
                        selectAddressAdapter.setEmptyView(R.layout.null_data, rvSelectAddress);
                    } else {
                        pageNum = 1;
                        initData();
                    }
                    break;
            }
        }
    }

    @OnClick(R.id.sb_add_address)
    public void onViewClicked() {
        startActivityForResult(new Intent(SelectAddressActivity.this, ModifyAddressActivity.class).putExtra("addressType", 1), REQUEST_CODE_99);
    }

    @Override
    public void resultAddressList(AddressListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        addrList.clear();
                        addrList.addAll(data.getData().getAddrList());
                        selectAddressAdapter = new SelectAddressAdapter(addrList, addressType);
                        rvSelectAddress.setAdapter(selectAddressAdapter);
                        selectAddressAdapter.bindToRecyclerView(rvSelectAddress);
                        selectAddressAdapter.setEnableLoadMore(true);
                        selectAddressAdapter.openLoadAnimation();
                        selectAddressAdapter.disableLoadMoreIfNotFullPage();

                        selectAddressAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.stv_select_address_item: //选中地址
                                        if (addressType == 1) {
                                            new Thread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    // 使用postDelayed方式修改UI组件tvMessage的Text属性值
                                                    // 并且延迟执行
                                                    handler.postDelayed(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            setResult(RESULT_OK, new Intent().putExtra("prov", addrList.get(position).getProv()).putExtra("city", addrList.get(position).getCity()).putExtra("dist", addrList.get(position).getDist()).putExtra("address", addrList.get(position).getDetail()));
                                                            finish();
                                                        }
                                                    }, 500);
                                                }
                                            }).start();
                                        } else {
//                                            for (int i = 0; i < addrList.size(); i++) {
//                                                addrList.get(i).setShow(false);
//                                            }
//                                            addrList.get(position).setShow(true);
//                                            selectAddressAdapter.notifyDataSetChanged();
                                        }
                                        break;
                                    case R.id.ib_edit_address: //编辑地址
                                        removePosition = position;
                                        startActivityForResult(new Intent(SelectAddressActivity.this, ModifyAddressActivity.class).putExtra("addressType", 2).putExtra("prov", addrList.get(position).getProv()).putExtra("city", addrList.get(position).getCity()).putExtra("dist", addrList.get(position).getDist()).putExtra("address", addrList.get(position).getDetail()).putExtra("defaultType", addrList.get(position).getDefaultAddress()).putExtra("addressId", addrList.get(position).getAddressId()), REQUEST_CODE_99);
                                        break;
                                }
                            }
                        });

                        //上拉加载
                        selectAddressAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvSelectAddress.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvSelectAddress);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            selectAddressAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            selectAddressAdapter.addData(addrList);
                            selectAddressAdapter.loadMoreComplete(); //完成本次
                        } else {
                            selectAddressAdapter.addData(addrList);
                            selectAddressAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    addrList.clear();
                    selectAddressAdapter = new SelectAddressAdapter(addrList, addressType);
                    rvSelectAddress.setAdapter(selectAddressAdapter);
                    selectAddressAdapter.loadMoreEnd(); //完成所有加载
                    selectAddressAdapter.setEmptyView(R.layout.null_data, rvSelectAddress);
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
    public void resultAddAddress(AddAddressBean data) {

    }

    @Override
    public void resultModifyAddress(ModifyAddressBean data) {

    }

    @Override
    public void resultDelAddress(DelAddressBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
