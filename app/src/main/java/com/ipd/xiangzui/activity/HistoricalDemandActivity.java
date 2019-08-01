package com.ipd.xiangzui.activity;

import android.annotation.SuppressLint;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.HistoricalDemandAdapter;
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

/**
 * Description ：历史需求
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class HistoricalDemandActivity extends BaseActivity {

    @BindView(R.id.tv_historical_demand)
    TopView tvHistoricalDemand;
    @BindView(R.id.rv_historical_demand)
    RecyclerView rvHistoricalDemand;
    @BindView(R.id.srl_historical_demand)
    SwipeRefreshLayout srlHistoricalDemand;

    private HistoricalDemandAdapter historicalDemandAdapter;
    private List<TestMultiItemEntityBean> historicalDemandBean = new ArrayList<>();
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_historical_demand;
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
        ImmersionBar.setTitleBar(this, tvHistoricalDemand);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvHistoricalDemand.setLayoutManager(layoutManager);
        rvHistoricalDemand.addItemDecoration(new SpacesItemDecoration(2, 30));
        rvHistoricalDemand.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvHistoricalDemand.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlHistoricalDemand.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        if (5 > 0) {//TODO 有接口后,5替换为总条数
            if (pageNum == 1) {
                historicalDemandBean.clear();
                for (int i = 0; i < 5; i++) {//TODO 假数据
                    historicalDemandBean.add(new TestMultiItemEntityBean());
                }
//                historicalDemandBean.addAll(data.getData().getRoomList()); //TODO 暂用假数据代替
                historicalDemandAdapter = new HistoricalDemandAdapter(historicalDemandBean);
                rvHistoricalDemand.setAdapter(historicalDemandAdapter);
                historicalDemandAdapter.bindToRecyclerView(rvHistoricalDemand);
                historicalDemandAdapter.setEnableLoadMore(true);
                historicalDemandAdapter.openLoadAnimation();
                historicalDemandAdapter.disableLoadMoreIfNotFullPage();

                //上拉加载
                historicalDemandAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        rvHistoricalDemand.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }, 1000);
                    }
                }, rvHistoricalDemand);

                if (5 > 10) {//TODO 有接口后,5替换为总条数
                    pageNum += 1;
                } else {
                    historicalDemandAdapter.loadMoreEnd();
                }
            } else {
                for (int i = 0; i < 5; i++) {//TODO 假数据
                    historicalDemandBean.add(new TestMultiItemEntityBean());
                }
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后,5替换为总条数
                    pageNum += 1;
                    historicalDemandAdapter.addData(historicalDemandBean);
                    historicalDemandAdapter.loadMoreComplete(); //完成本次
                } else {
                    historicalDemandAdapter.addData(historicalDemandBean);
                    historicalDemandAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            historicalDemandBean.clear();
            historicalDemandAdapter = new HistoricalDemandAdapter(historicalDemandBean);
            rvHistoricalDemand.setAdapter(historicalDemandAdapter);
            historicalDemandAdapter.loadMoreEnd(); //完成所有加载
            historicalDemandAdapter.setEmptyView(R.layout.null_data, rvHistoricalDemand);
        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlHistoricalDemand.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlHistoricalDemand.setRefreshing(false);
            }
        });
    }
}
