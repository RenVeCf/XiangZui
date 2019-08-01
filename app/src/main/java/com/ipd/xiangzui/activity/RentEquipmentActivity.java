package com.ipd.xiangzui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.RentEquipmentAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：设备租赁
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class RentEquipmentActivity extends BaseActivity {

    @BindView(R.id.tv_rent_equipment)
    TopView tvRentEquipment;
    @BindView(R.id.rv_rent_equipment)
    RecyclerView rvRentEquipment;
    @BindView(R.id.srl_rent_equipment)
    SwipeRefreshLayout srlRentEquipment;

    private RentEquipmentAdapter rentEquipmentAdapter;
    private List<TestMultiItemEntityBean> rentEquipmentBean = new ArrayList<>();
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_rent_equipment;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvRentEquipment);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvRentEquipment.setLayoutManager(layoutManager);
        rvRentEquipment.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvRentEquipment.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlRentEquipment.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        if (5 > 0) {//TODO 有接口后,5替换为总条数
            if (pageNum == 1) {
                rentEquipmentBean.clear();
                for (int i = 0; i < 5; i++) {//TODO 假数据
                    rentEquipmentBean.add(new TestMultiItemEntityBean());
                }
//                rentEquipmentBean.addAll(data.getData().getRoomList()); //TODO 暂用假数据代替
                rentEquipmentAdapter = new RentEquipmentAdapter(rentEquipmentBean);
                rvRentEquipment.setAdapter(rentEquipmentAdapter);
                rentEquipmentAdapter.bindToRecyclerView(rvRentEquipment);
                rentEquipmentAdapter.setEnableLoadMore(true);
                rentEquipmentAdapter.openLoadAnimation();
                rentEquipmentAdapter.disableLoadMoreIfNotFullPage();

                //上拉加载
                rentEquipmentAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        rvRentEquipment.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }, 1000);
                    }
                }, rvRentEquipment);

                if (5 > 10) {//TODO 有接口后,5替换为总条数
                    pageNum += 1;
                } else {
                    rentEquipmentAdapter.loadMoreEnd();
                }
            } else {
                for (int i = 0; i < 5; i++) {//TODO 假数据
                    rentEquipmentBean.add(new TestMultiItemEntityBean());
                }
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后,5替换为总条数
                    pageNum += 1;
                    rentEquipmentAdapter.addData(rentEquipmentBean);
                    rentEquipmentAdapter.loadMoreComplete(); //完成本次
                } else {
                    rentEquipmentAdapter.addData(rentEquipmentBean);
                    rentEquipmentAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            rentEquipmentAdapter.loadMoreEnd(); //完成所有加载
            rentEquipmentAdapter.setEmptyView(R.layout.null_data, rvRentEquipment);
        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlRentEquipment.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlRentEquipment.setRefreshing(false);
            }
        });
    }

    @OnClick({R.id.sb_historical_demand, R.id.sb_push_demand})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_historical_demand:
                //历史需求
                startActivity(new Intent(this, HistoricalDemandActivity.class));
                break;
            case R.id.sb_push_demand:
                //发布需求
                startActivity(new Intent(this, PushDemandActivity.class));
                break;
        }
    }
}
