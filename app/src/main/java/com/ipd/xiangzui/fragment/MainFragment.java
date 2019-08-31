package com.ipd.xiangzui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.activity.AuthenticationActivity;
import com.ipd.xiangzui.activity.ExpertActivity;
import com.ipd.xiangzui.activity.OrderActivity;
import com.ipd.xiangzui.activity.OrderDetailsActivity;
import com.ipd.xiangzui.activity.RentEquipmentActivity;
import com.ipd.xiangzui.activity.SendOrderActivity;
import com.ipd.xiangzui.activity.VipActivity;
import com.ipd.xiangzui.activity.WalletActivity;
import com.ipd.xiangzui.adapter.MainGridAdapter;
import com.ipd.xiangzui.adapter.MainOrderAdapter;
import com.ipd.xiangzui.adapter.RecyclerViewBannerAdapter;
import com.ipd.xiangzui.base.BaseFragment;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzui.common.view.GridSpacingItemDecoration;
import com.ipd.xiangzui.common.view.SimpleNoticeMFs;
import com.ipd.xiangzui.common.view.SpacesItemDecoration;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.utils.SPUtil;
import com.xuexiang.xui.widget.banner.recycler.BannerLayout;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeFactory;
import com.xuexiang.xui.widget.textview.marqueen.MarqueeView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：首页
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/1.
 */
public class MainFragment extends BaseFragment {
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

    private List<TestMultiItemEntityBean> str = new ArrayList<>();//轮播
    private List<Integer> itr = new ArrayList<>();//菜单
    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();//更多订单
    private MainGridAdapter mainGridAdapter;
    private MainOrderAdapter mainOrderAdapter;
    final List<CharSequence> datas = Arrays.asList(Html.fromHtml("医生李**已完成订单<font color=\"#000000\">阑尾切除术</font>费用<font color=\"#FF5555\">¥" + 300 + "元</font>"), Html.fromHtml("医生李**已完成订单<font color=\"#000000\">阑尾切除术</font>费用<font color=\"#FF5555\">¥" + 300 + "元</font>"));

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main;
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
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(true).init();
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(getActivity(), tvMain);

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

        for (int i = 0; i < 2; i++) {
            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
            testData.setOrderType("0");
            str1.add(testData);
        }
        rvMoreOrder.setAdapter(mainOrderAdapter = new MainOrderAdapter(str1));
        mainOrderAdapter.bindToRecyclerView(rvMoreOrder);
        mainOrderAdapter.openLoadAnimation();
    }

    @Override
    public void initListener() {
        mainGridAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                switch (position) {
                    case 0:
                        //订单
                        startActivity(new Intent(getContext(), OrderActivity.class));
                        break;
                    case 1:
                        //发单
                        new TwoBtDialog(getActivity(), "请先实名认证后才可以发单", "去认证") {
                            @Override
                            public void confirm() {
                                //认证
                                if (isEmpty(SPUtil.get(getContext(), IS_SUPPLEMENT_INFO, "") + ""))
                                    startActivity(new Intent(getContext(), AuthenticationActivity.class));
                                else
                                    startActivity(new Intent(getContext(), SendOrderActivity.class));
                            }
                        }.show();
                        break;
                    case 2:
                        //钱包
                        startActivity(new Intent(getContext(), WalletActivity.class));
                        break;
                    case 3:
                        //保险
                        break;
                    case 4:
                        //VIP服务
                        startActivity(new Intent(getContext(), VipActivity.class));
                        break;
                    case 5:
                        //专家会诊
                        startActivity(new Intent(getContext(), ExpertActivity.class));
                        break;
                    case 6:
                        //麻醉筹建
                        startActivity(new Intent(getContext(), VipActivity.class));
                        break;
                    case 7:
                        //设备租赁
                        startActivity(new Intent(getContext(), RentEquipmentActivity.class));
                        break;
                }
            }
        });

        mainOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.cv_order_item:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.stv_start_time:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.stv_fee:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.stv_name:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.stv_address:
                        startActivity(new Intent(getContext(), OrderDetailsActivity.class).putExtra("surgery_type", 1).putExtra("order_type", "0"));
                        break;
                    case R.id.bt_first:
                        if (isFastClick())
                            new TwoBtDialog(getActivity(), "确认取消订单？", "确认") {
                                @Override
                                public void confirm() {
                                    str1.remove(position);
                                    mainOrderAdapter.notifyDataSetChanged();
                                    mainOrderAdapter.setEmptyView(R.layout.null_data, rvMoreOrder);
                                }
                            }.show();
                        break;
                    case R.id.bt_second:
                        if (isFastClick())
                            startActivity(new Intent(getContext(), SendOrderActivity.class));
                        break;
                    case R.id.bt_third:
                        if (isFastClick())
                            //TODO 加价
                            break;
                }
            }
        });
    }

    @Override
    public void initData() {
        //轮播
        for (int i = 0; i < 3; i++) {
            str.add(new TestMultiItemEntityBean());
        }
        blBanner.setAdapter(new RecyclerViewBannerAdapter(str));

        //大喇叭
        MarqueeFactory<TextView, CharSequence> marqueeFactory = new SimpleNoticeMFs(getContext());
        marqueeFactory.setData(datas);
//        mvHorn.setAnimInAndOut(R.anim.marquee_top_in, R.anim.marquee_bottom_out);
        mvHorn.setMarqueeFactory(marqueeFactory);
        mvHorn.startFlipping();
    }

    @OnClick(R.id.bt_more_order)
    public void onViewClicked() {
        startActivity(new Intent(getContext(), OrderActivity.class));
    }
}
