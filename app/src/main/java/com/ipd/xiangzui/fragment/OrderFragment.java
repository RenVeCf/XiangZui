package com.ipd.xiangzui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.activity.CaptchaLoginActivity;
import com.ipd.xiangzui.activity.MainActivity;
import com.ipd.xiangzui.activity.ModifyMedicalRecordActivity;
import com.ipd.xiangzui.activity.OrderDetailsActivity;
import com.ipd.xiangzui.activity.SendOrderSurgicalInfoActivity;
import com.ipd.xiangzui.adapter.MainOrderAdapter;
import com.ipd.xiangzui.base.BaseFragment;
import com.ipd.xiangzui.bean.AddFeeBean;
import com.ipd.xiangzui.bean.CancelIsOrderBean;
import com.ipd.xiangzui.bean.CancelOrderBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.OrderIsOrverBean;
import com.ipd.xiangzui.bean.OrderListBean;
import com.ipd.xiangzui.bean.OrderQuickBean;
import com.ipd.xiangzui.bean.SelectFeeBean;
import com.ipd.xiangzui.common.view.EditDialog;
import com.ipd.xiangzui.common.view.SpacesItemDecoration;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.contract.OrderContract;
import com.ipd.xiangzui.presenter.OrderPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_111;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：订单
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class OrderFragment extends BaseFragment<OrderContract.View, OrderContract.Presenter> implements OrderContract.View {
    @BindView(R.id.stv_order_time)
    SuperTextView stvOrderTime;
    @BindView(R.id.stv_order_region)
    SuperTextView stvOrderRegion;
    @BindView(R.id.stv_order_money)
    SuperTextView stvOrderMoney;
    @BindView(R.id.ll_order)
    LinearLayoutCompat llOrder;
    @BindView(R.id.rv_order)
    RecyclerView rvOrder;
    @BindView(R.id.srl_order)
    SwipeRefreshLayout srlOrder;

    private List<OrderListBean.DataBean.OrderListsBean> orderList = new ArrayList<>();
    private MainOrderAdapter mainOrderAdapter;
    private int pageNum = 1;//页数
    private String orderType;//订单状态 0:待接单， 1:已接单， 2:进行中， 3:已完成
    private int removePosition;
    private int isModify;//1:修改订单，2：补充病历

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public OrderContract.Presenter createPresenter() {
        return new OrderPresenter(mContext);
    }

    @Override
    public OrderContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(View view) {
        Bundle args = getArguments();
        if (args != null) {
            orderType = args.getString("order_type");
        }
        if ("0".equals(orderType))
            llOrder.setVisibility(View.VISIBLE);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvOrder.setLayoutManager(layoutManager);
        rvOrder.setNestedScrollingEnabled(false);
        rvOrder.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvOrder.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvOrder.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlOrder.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlOrder.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {
        orderList("", "", "");
    }

    private void orderList(String orderByColumn, String isAsc, String dist) {
        TreeMap<String, String> orderListMap = new TreeMap<>();
        orderListMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        orderListMap.put("status", (Integer.parseInt(orderType) + 1) + "");
        orderListMap.put("pageNum", pageNum + "");
        orderListMap.put("pageSize", "10");
        orderListMap.put("orderByColumn", orderByColumn);
        orderListMap.put("isAsc", isAsc);
        orderListMap.put("dist", dist);
        orderListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getOrderList(orderListMap, false, false);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_111:
                    initData();
                    break;
            }
        }
    }

    @OnClick({R.id.stv_order_time, R.id.stv_order_region, R.id.stv_order_money})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_order_time:
                break;
            case R.id.stv_order_region:
                break;
            case R.id.stv_order_money:
                break;
        }
    }

    @Override
    public void resultOrderList(OrderListBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        orderList.clear();
                        orderList.addAll(data.getData().getOrderList());
                        mainOrderAdapter = new MainOrderAdapter(orderList);
                        rvOrder.setAdapter(mainOrderAdapter);
                        mainOrderAdapter.bindToRecyclerView(rvOrder);
                        mainOrderAdapter.setEnableLoadMore(true);
                        mainOrderAdapter.openLoadAnimation();
                        mainOrderAdapter.disableLoadMoreIfNotFullPage();

                        mainOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                switch (view.getId()) {
                                    case R.id.cv_order_item:
                                    case R.id.stv_start_time:
                                    case R.id.stv_fee:
                                    case R.id.stv_name:
                                    case R.id.stv_address:
                                        if (isFastClick())
                                            startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getOrderList().get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                        break;
                                    case R.id.bt_first:
                                        switch (orderList.get(position).getStatus()) {
                                            case "1"://取消订单
                                                if (isFastClick())
                                                    new TwoBtDialog(getActivity(), "确认取消订单？", "确认") {
                                                        @Override
                                                        public void confirm() {
                                                            removePosition = position;
                                                            TreeMap<String, String> cancelOrderMap = new TreeMap<>();
                                                            cancelOrderMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                            cancelOrderMap.put("orderId", orderList.get(position).getOrderId() + "");
                                                            cancelOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(cancelOrderMap.toString().replaceAll(" ", "") + SIGN)));
                                                            getPresenter().getCancelOrder(cancelOrderMap, false, false);
                                                        }
                                                    }.show();
                                                break;
                                            case "8":
                                            case "2"://取消订单
                                                if (isFastClick()) {
                                                    removePosition = position;

                                                    TreeMap<String, String> selectFeeMap = new TreeMap<>();
                                                    selectFeeMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                    selectFeeMap.put("type", "2");
                                                    selectFeeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(selectFeeMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getSelectFee(selectFeeMap, false, false);
                                                }
                                                break;
                                            case "3"://查看详情
                                            case "4":
                                            case "5":
                                            case "6":
                                            case "7":
                                                if (isFastClick())
                                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getOrderList().get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                                break;
                                        }
                                        break;
                                    case R.id.bt_second:
                                        switch (orderList.get(position).getStatus()) {
                                            case "1"://修改订单
                                                if (isFastClick()) {
                                                    isModify = 1;

                                                    TreeMap<String, String> orderDetailsMap = new TreeMap<>();
                                                    orderDetailsMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                    orderDetailsMap.put("orderId", orderList.get(position).getOrderId() + "");
                                                    orderDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderDetailsMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getOrderDetails(orderDetailsMap, false, false);
                                                }
                                                break;
                                            case "8":
                                            case "2"://补充病历
                                                if (isFastClick()) {
                                                    isModify = 2;

                                                    TreeMap<String, String> orderDetailsMap1 = new TreeMap<>();
                                                    orderDetailsMap1.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                    orderDetailsMap1.put("orderId", orderList.get(position).getOrderId() + "");
                                                    orderDetailsMap1.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderDetailsMap1.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getOrderDetails(orderDetailsMap1, false, false);
                                                }
                                                break;
                                            case "3":
                                                break;
                                            case "4"://异议
                                                if (isFastClick())
                                                    new TwoBtDialog(getActivity(), "对此订单有异议，是否进行电话咨询?", "确认") {
                                                        @Override
                                                        public void confirm() {

                                                        }
                                                    }.show();
                                                break;
                                            case "5":
                                                break;
                                            case "6":
                                                break;
                                            case "7":
                                                break;
                                        }
                                        break;
                                    case R.id.bt_third:
                                        switch (orderList.get(position).getStatus()) {
                                            case "1"://加价
                                                if (isFastClick())
                                                    new EditDialog(getActivity()) {
                                                        @Override
                                                        public void confirm(String content) {
                                                            TreeMap<String, String> addFeeMap = new TreeMap<>();
                                                            addFeeMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                            addFeeMap.put("orderId", orderList.get(position).getOrderId() + "");
                                                            addFeeMap.put("premiumMoney", content);
                                                            addFeeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(addFeeMap.toString().replaceAll(" ", "") + SIGN)));
                                                            getPresenter().getAddFee(addFeeMap, false, false);
                                                        }
                                                    }.show();
                                                break;
                                            case "8":
                                            case "2"://联系医生
                                                break;
                                            case "3":
                                                break;
                                            case "4"://确认
                                                break;
                                            case "5":
                                                break;
                                            case "6":
                                                break;
                                            case "7":
                                                break;
                                        }
                                        break;
                                }
                            }
                        });

                        //上拉加载
                        mainOrderAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvOrder.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvOrder);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            mainOrderAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            mainOrderAdapter.addData(data.getData().getOrderList());
                            mainOrderAdapter.loadMoreComplete(); //完成本次
                        } else {
                            mainOrderAdapter.addData(data.getData().getOrderList());
                            mainOrderAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    orderList.clear();
                    mainOrderAdapter = new MainOrderAdapter(orderList);
                    rvOrder.setAdapter(mainOrderAdapter);
                    mainOrderAdapter.loadMoreEnd(); //完成所有加载
                    mainOrderAdapter.setEmptyView(R.layout.null_data, rvOrder);
                }
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultOrderDetails(OrderDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                if (isModify == 1)
                    startActivity(new Intent(getContext(), SendOrderSurgicalInfoActivity.class).putExtra("orderDetails", data.getData().getOrder()).putParcelableArrayListExtra("orderDetailsList", (ArrayList<? extends Parcelable>) data.getData().getOrderDetail()));
                else if (isModify == 2)
                    startActivityForResult(new Intent(getContext(), ModifyMedicalRecordActivity.class).putExtra("orderDetails", data.getData().getOrder()).putParcelableArrayListExtra("orderDetailsList", (ArrayList<? extends Parcelable>) data.getData().getOrderDetail()), REQUEST_CODE_111);
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultCancelOrder(CancelOrderBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                orderList.remove(removePosition);
                mainOrderAdapter.notifyDataSetChanged();
                mainOrderAdapter.setEmptyView(R.layout.null_data, rvOrder);
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultCancelIsOrder(CancelIsOrderBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                orderList.remove(removePosition);
                mainOrderAdapter.notifyDataSetChanged();
                mainOrderAdapter.setEmptyView(R.layout.null_data, rvOrder);
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultAddFee(AddFeeBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                initData();
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultOrderQuick(OrderQuickBean data) {

    }

    @Override
    public void resultSelectFee(SelectFeeBean data) {
        switch (data.getCode()) {
            case 200:
                new TwoBtDialog(getActivity(), "手术开始前24小时取消免费，之后扣除0.2手续费", "确认") {
                    @Override
                    public void confirm() {
                        TreeMap<String, String> cancelIsOrderMap = new TreeMap<>();
                        cancelIsOrderMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                        cancelIsOrderMap.put("orderId", orderList.get(removePosition).getOrderId() + "");
                        cancelIsOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(cancelIsOrderMap.toString().replaceAll(" ", "") + SIGN)));
                        getPresenter().getCancelIsOrder(cancelIsOrderMap, false, false);
                    }
                }.show();
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultOrderIsOrver(OrderIsOrverBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
