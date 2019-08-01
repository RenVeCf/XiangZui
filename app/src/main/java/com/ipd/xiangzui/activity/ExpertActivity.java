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
import com.ipd.xiangzui.adapter.ExpertAdapter;
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
 * Description ：专家会诊
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class ExpertActivity extends BaseActivity {

    @BindView(R.id.tv_expert)
    TopView tvExpert;
    @BindView(R.id.rv_expert)
    RecyclerView rvExpert;
    @BindView(R.id.srl_expert)
    SwipeRefreshLayout srlExpert;

    private ExpertAdapter expertAdapter;
    private List<TestMultiItemEntityBean> expertDemandBean = new ArrayList<>();
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_expert;
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
        ImmersionBar.setTitleBar(this, tvExpert);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvExpert.setLayoutManager(layoutManager);
        rvExpert.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvExpert.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlExpert.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        if (5 > 0) {//TODO 有接口后,5替换为总条数
            if (pageNum == 1) {
                expertDemandBean.clear();
                for (int i = 0; i < 5; i++) {//TODO 假数据
                    expertDemandBean.add(new TestMultiItemEntityBean());
                }
//                expertDemandBean.addAll(data.getData().getRoomList()); //TODO 暂用假数据代替
                expertAdapter = new ExpertAdapter(expertDemandBean);
                rvExpert.setAdapter(expertAdapter);
                expertAdapter.bindToRecyclerView(rvExpert);
                expertAdapter.setEnableLoadMore(true);
                expertAdapter.openLoadAnimation();
                expertAdapter.disableLoadMoreIfNotFullPage();

                //上拉加载
                expertAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        rvExpert.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }, 1000);
                    }
                }, rvExpert);

                if (5 > 10) {//TODO 有接口后,5替换为总条数
                    pageNum += 1;
                } else {
                    expertAdapter.loadMoreEnd();
                }
            } else {
                for (int i = 0; i < 5; i++) {//TODO 假数据
                    expertDemandBean.add(new TestMultiItemEntityBean());
                }
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后,5替换为总条数
                    pageNum += 1;
                    expertAdapter.addData(expertDemandBean);
                    expertAdapter.loadMoreComplete(); //完成本次
                } else {
                    expertAdapter.addData(expertDemandBean);
                    expertAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            expertDemandBean.clear();
            expertAdapter = new ExpertAdapter(expertDemandBean);
            rvExpert.setAdapter(expertAdapter);
            expertAdapter.loadMoreEnd(); //完成所有加载
            expertAdapter.setEmptyView(R.layout.null_data, rvExpert);
        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlExpert.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlExpert.setRefreshing(false);
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
