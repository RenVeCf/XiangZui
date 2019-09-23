package com.ipd.xiangzui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.HistoricalDemandAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.HistoricalDemandBean;
import com.ipd.xiangzui.bean.SendDemandBean;
import com.ipd.xiangzui.common.view.SpacesItemDecoration;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.DemandContract;
import com.ipd.xiangzui.presenter.DemandPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;

/**
 * Description ：历史需求
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class HistoricalDemandActivity extends BaseActivity<DemandContract.View, DemandContract.Presenter> implements DemandContract.View {

    @BindView(R.id.tv_historical_demand)
    TopView tvHistoricalDemand;
    @BindView(R.id.rv_historical_demand)
    RecyclerView rvHistoricalDemand;
    @BindView(R.id.srl_historical_demand)
    SwipeRefreshLayout srlHistoricalDemand;

    private HistoricalDemandAdapter historicalDemandAdapter;
    private List<HistoricalDemandBean.DataBean.HistoryListBean> historyList = new ArrayList<>();
    private int pageNum = 1;//页数
    private int demandType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_historical_demand;
    }

    @Override
    public DemandContract.Presenter createPresenter() {
        return new DemandPresenter(this);
    }

    @Override
    public DemandContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvHistoricalDemand);

        demandType = getIntent().getIntExtra("demandType", 0);

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
        TreeMap<String, String> historicalDemandMap = new TreeMap<>();
        historicalDemandMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        historicalDemandMap.put("demandType", demandType + "");
        historicalDemandMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(historicalDemandMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getHistoricalDemand(historicalDemandMap, false, false);
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

    @Override
    public void resultHistoricalDemand(HistoricalDemandBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        historyList.clear();
                        historyList.addAll(data.getData().getHistoryList());
                        historicalDemandAdapter = new HistoricalDemandAdapter(historyList);
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

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            historicalDemandAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            historicalDemandAdapter.addData(historyList);
                            historicalDemandAdapter.loadMoreComplete(); //完成本次
                        } else {
                            historicalDemandAdapter.addData(historyList);
                            historicalDemandAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    historyList.clear();
                    historicalDemandAdapter = new HistoricalDemandAdapter(historyList);
                    rvHistoricalDemand.setAdapter(historicalDemandAdapter);
                    historicalDemandAdapter.loadMoreEnd(); //完成所有加载
                    historicalDemandAdapter.setEmptyView(R.layout.null_data, rvHistoricalDemand);
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
    public void resultSendDemand(SendDemandBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
