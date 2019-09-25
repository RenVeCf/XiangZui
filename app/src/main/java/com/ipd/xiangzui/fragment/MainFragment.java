package com.ipd.xiangzui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Parcelable;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.activity.AuthenticationActivity;
import com.ipd.xiangzui.activity.CaptchaLoginActivity;
import com.ipd.xiangzui.activity.MainActivity;
import com.ipd.xiangzui.activity.OrderActivity;
import com.ipd.xiangzui.activity.OrderDetailsActivity;
import com.ipd.xiangzui.activity.SendOrderActivity;
import com.ipd.xiangzui.activity.SendOrderSurgicalInfoActivity;
import com.ipd.xiangzui.activity.VipActivity;
import com.ipd.xiangzui.activity.WalletActivity;
import com.ipd.xiangzui.activity.WebViewActivity;
import com.ipd.xiangzui.adapter.MainGridAdapter;
import com.ipd.xiangzui.adapter.MainOrderAdapter;
import com.ipd.xiangzui.adapter.RecyclerViewBannerAdapter;
import com.ipd.xiangzui.base.BaseFragment;
import com.ipd.xiangzui.bean.AddFeeBean;
import com.ipd.xiangzui.bean.CancelIsOrderBean;
import com.ipd.xiangzui.bean.CancelOrderBean;
import com.ipd.xiangzui.bean.HomeBean;
import com.ipd.xiangzui.bean.HospitalNameBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.OrderQuickBean;
import com.ipd.xiangzui.bean.SendOrderBean;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.bean.VerifiedTypeBean;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzui.common.view.EditDialog;
import com.ipd.xiangzui.common.view.GridSpacingItemDecoration;
import com.ipd.xiangzui.common.view.SimpleNoticeMFs;
import com.ipd.xiangzui.common.view.SpacesItemDecoration;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.contract.HomeContract;
import com.ipd.xiangzui.presenter.HomePresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeFactory;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.ADDRESS;
import static com.ipd.xiangzui.common.config.IConstants.CITY;
import static com.ipd.xiangzui.common.config.IConstants.DIST;
import static com.ipd.xiangzui.common.config.IConstants.HOSPTIAL_NAME;
import static com.ipd.xiangzui.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzui.common.config.IConstants.PROV;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：首页
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/1.
 */
public class MainFragment extends BaseFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {
    @BindView(R.id.tv_main)
    TopView tvMain;
    @BindView(R.id.bl_banner)
    BannerLayout blBanner;
    @BindView(R.id.rv_grid_main)
    RecyclerView rvGridMain;
    @BindView(R.id.mv_horn)
    MarqueeView mvHorn;
    @BindView(R.id.rv_more_order)
    RecyclerView rvMoreOrder;
    @BindView(R.id.srl_main)
    SwipeRefreshLayout srlMain;

    private List<Integer> itr = new ArrayList<>();//菜单
    private List<HomeBean.DataBean.OrderListBean> orderList = new ArrayList<>();//更多订单
    private MainGridAdapter mainGridAdapter;
    private MainOrderAdapter mainOrderAdapter;
    private RecyclerViewBannerAdapter recyclerViewBannerAdapter;
    private List<CharSequence> hornList = new ArrayList<>();//广播
    private int removePosition;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    public HomeContract.Presenter createPresenter() {
        return new HomePresenter(mContext);
    }

    @Override
    public HomeContract.View createView() {
        return this;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(getActivity(), tvMain);

        srlMain.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色

        //菜单
        GridLayoutManager NotUseList = new GridLayoutManager(getContext(), 4);
        rvGridMain.setLayoutManager(NotUseList);
        rvGridMain.addItemDecoration(new GridSpacingItemDecoration(4, 30, false));
        rvGridMain.setNestedScrollingEnabled(false);
        rvGridMain.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvGridMain.setItemAnimator(new DefaultItemAnimator()); //默认动画

        for (int i = 0; i < 8; i++) {
            itr.add(i);
        }
        rvGridMain.setAdapter(mainGridAdapter = new MainGridAdapter(itr));

        //更多订单
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMoreOrder.setLayoutManager(layoutManager);
        rvMoreOrder.setNestedScrollingEnabled(false);
        rvMoreOrder.addItemDecoration(new SpacesItemDecoration(1, 50));
        rvMoreOrder.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMoreOrder.setItemAnimator(new DefaultItemAnimator());//加载动画
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                srlMain.setRefreshing(false);
            }
        });

        mainGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //订单
                        if ("1".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, ""))) {
                            new TwoBtDialog(getActivity(), "请先实名认证后才可以发单", "去认证") {
                                @Override
                                public void confirm() {
                                    startActivity(new Intent(getContext(), AuthenticationActivity.class));
                                }
                            }.show();
                        } else
                            startActivity(new Intent(getContext(), OrderActivity.class));
                        break;
                    case 1:
                        //发单
                        if ("1".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, ""))) {
                            new TwoBtDialog(getActivity(), "请先实名认证后才可以发单", "去认证") {
                                @Override
                                public void confirm() {
                                    startActivity(new Intent(getContext(), AuthenticationActivity.class));
                                }
                            }.show();
                        } else
                            startActivity(new Intent(getContext(), SendOrderActivity.class));
                        break;
                    case 2:
                        //钱包
                        startActivity(new Intent(getContext(), WalletActivity.class));
                        break;
                    case 3:
                        //保险
                        startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 4));
                        break;
                    case 4:
                        //VIP服务
                        startActivity(new Intent(getContext(), VipActivity.class).putExtra("h5Url", BASE_LOCAL_URL + "H5/document/vipService.html").putExtra("demandType", 1));
                        break;
                    case 5:
                        //专家会诊
//                        startActivity(new Intent(getContext(), ExpertActivity.class));
                        startActivity(new Intent(getContext(), VipActivity.class).putExtra("h5Url", BASE_LOCAL_URL + "H5/document/expertConsultation.html").putExtra("demandType", 2));
                        break;
                    case 6:
                        //麻醉筹建
                        startActivity(new Intent(getContext(), VipActivity.class).putExtra("h5Url", BASE_LOCAL_URL + "H5/document/anesthesia.html").putExtra("demandType", 3));
                        break;
                    case 7:
                        //设备租赁
//                        startActivity(new Intent(getContext(), RentEquipmentActivity.class));
                        startActivity(new Intent(getContext(), VipActivity.class).putExtra("h5Url", BASE_LOCAL_URL + "H5/document/equipmentRental.html").putExtra("demandType", 4));
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        TreeMap<String, String> verifiedTypeMap = new TreeMap<>();
        verifiedTypeMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        verifiedTypeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(verifiedTypeMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getVerifiedType(verifiedTypeMap, false, false);

        TreeMap<String, String> hospitalNameMap = new TreeMap<>();
        hospitalNameMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        hospitalNameMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(hospitalNameMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getHospitalName(hospitalNameMap, false, false);

        TreeMap<String, String> homeMap = new TreeMap<>();
        homeMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
        homeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(homeMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getHome(homeMap, true, false);
    }

    @OnClick(R.id.bt_more_order)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), OrderActivity.class));
    }

    @Override
    public void resultHome(HomeBean data) {
        switch (data.getCode()) {
            case 200:
                //轮播
                recyclerViewBannerAdapter = new RecyclerViewBannerAdapter(data.getData().getPictureList());
                blBanner.setAdapter(recyclerViewBannerAdapter);

                recyclerViewBannerAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (data.getData().getPictureList().get(position).getType()) {
                            case "1"://无链接
                                break;
                            case "2"://有链接
                                startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 9).putExtra("h5Url", data.getData().getPictureList().get(position).getUrl()));
                                break;
                            case "3"://文本内容
                                startActivity(new Intent(getContext(), WebViewActivity.class).putExtra("h5Type", 10).putExtra("h5_url", data.getData().getPictureList().get(position).getContent()));
                                break;
                        }
                    }
                });

                //大喇叭
                for (int i = 0; i < data.getData().getInfoList().size(); i++) {
                    hornList.add(Html.fromHtml("医生" + data.getData().getInfoList().get(i).getNickname() + "已完成订单<font color=\"#000000\">" + data.getData().getInfoList().get(i).getSurgeryName() + "</font>费用<font color=\"#FF5555\">¥" + data.getData().getInfoList().get(i).getOrderCost() + "元</font>"));
                }
                MarqueeFactory<TextView, CharSequence> marqueeFactory = new SimpleNoticeMFs(getContext());
                marqueeFactory.setData(hornList);
//        mvHorn.setAnimInAndOut(R.anim.marquee_top_in, R.anim.marquee_bottom_out);
                mvHorn.setMarqueeFactory(marqueeFactory);
                mvHorn.startFlipping();

                //已发订单
                orderList.clear();
                orderList.addAll(data.getData().getOrderList());
                rvMoreOrder.setAdapter(mainOrderAdapter = new MainOrderAdapter(orderList));
                mainOrderAdapter.bindToRecyclerView(rvMoreOrder);
                mainOrderAdapter.openLoadAnimation();

                mainOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.cv_order_item:
                            case R.id.stv_start_time:
                            case R.id.stv_fee:
                            case R.id.stv_name:
                            case R.id.stv_address:
                                if ("2".equals(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "")))
                                    startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("order_status", data.getData().getOrderList().get(position).getStatus()).putExtra("orderId", data.getData().getOrderList().get(position).getOrderId()));
                                else
                                    ToastUtil.showShortToast("您的身份尚未认证,请您先去认证！");
                                break;
                            case R.id.bt_first:
                                if (isFastClick()) {
                                    switch (orderList.get(position).getStatus()) {
                                        case "1":
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
                                        case "2":
                                            break;
                                        case "3":
                                            new TwoBtDialog(getActivity(), "确认取消订单？", "确认") {
                                                @Override
                                                public void confirm() {
                                                    removePosition = position;
                                                    TreeMap<String, String> cancelIsOrderMap = new TreeMap<>();
                                                    cancelIsOrderMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                                    cancelIsOrderMap.put("orderId", orderList.get(position).getOrderId() + "");
                                                    cancelIsOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(cancelIsOrderMap.toString().replaceAll(" ", "") + SIGN)));
                                                    getPresenter().getCancelIsOrder(cancelIsOrderMap, false, false);
                                                }
                                            }.show();
                                            break;
                                        case "4":
                                            break;
                                        case "5":
                                            break;
                                        case "6":
                                            break;
                                        case "7":
                                            break;
                                    }
                                }
                                break;
                            case R.id.bt_second:
                                if (isFastClick()) {
                                    switch (orderList.get(position).getStatus()) {
                                        case "1":
                                            TreeMap<String, String> orderDetailsMap = new TreeMap<>();
                                            orderDetailsMap.put("userId", SPUtil.get(getContext(), USER_ID, "") + "");
                                            orderDetailsMap.put("orderId", orderList.get(position).getOrderId() + "");
                                            orderDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderDetailsMap.toString().replaceAll(" ", "") + SIGN)));
                                            getPresenter().getOrderDetails(orderDetailsMap, false, false);
                                            break;
                                        case "2":
                                            break;
                                        case "3":
                                            break;
                                        case "4":
                                            break;
                                        case "5":
                                            break;
                                        case "6":
                                            break;
                                        case "7":
                                            break;
                                    }
                                }
                                break;
                            case R.id.bt_third:
                                if (isFastClick()) {
                                    switch (orderList.get(position).getStatus()) {
                                        case "1":
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
                                        case "2":
                                            break;
                                        case "3":
                                            break;
                                        case "4":
                                            break;
                                        case "5":
                                            break;
                                        case "6":
                                            break;
                                        case "7":
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                });
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultVerifiedType(VerifiedTypeBean data) {
        switch (data.getCode()) {
            case 200:
                SPUtil.put(getContext(), IS_SUPPLEMENT_INFO, data.getData().getApproveStatus() + "");
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(getContext(), CaptchaLoginActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void resultHospitalName(HospitalNameBean data) {
        switch (data.getCode()) {
            case 200:
                SPUtil.put(getContext(), HOSPTIAL_NAME, data.getData().getApprove().getTruename());
                SPUtil.put(getContext(), PROV, data.getData().getApprove().getProv());
                SPUtil.put(getContext(), CITY, data.getData().getApprove().getCity());
                SPUtil.put(getContext(), DIST, data.getData().getApprove().getDist());
                SPUtil.put(getContext(), ADDRESS, data.getData().getApprove().getAddress());
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
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
                mainOrderAdapter.setEmptyView(R.layout.null_data, rvMoreOrder);
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
                mainOrderAdapter.setEmptyView(R.layout.null_data, rvMoreOrder);
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
    public void resultOrderDetails(OrderDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(getContext(), SendOrderSurgicalInfoActivity.class).putExtra("orderDetails", data.getData().getOrder()).putParcelableArrayListExtra("orderDetailsList", (ArrayList<? extends Parcelable>) data.getData().getOrderDetail()));
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindUntilEvent(FragmentEvent.PAUSE);
    }
}
