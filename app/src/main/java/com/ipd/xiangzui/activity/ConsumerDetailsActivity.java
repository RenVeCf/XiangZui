package com.ipd.xiangzui.activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.ConsumerDetailsAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description ：收支明细
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/29.
 */
public class ConsumerDetailsActivity extends BaseActivity {

    @BindView(R.id.rv_consumer_details)
    RecyclerView rvConsumerDetails;
    @BindView(R.id.srl_consumer_details)
    SwipeRefreshLayout srlConsumerDetails;
    @BindView(R.id.tv_consumer_details)
    TopView tvConsumerDetails;

    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();
    private ConsumerDetailsAdapter consumerDetailsAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_consumer_details;
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
        ImmersionBar.setTitleBar(this, tvConsumerDetails);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvConsumerDetails.setLayoutManager(layoutManager);
        rvConsumerDetails.setNestedScrollingEnabled(false);
        rvConsumerDetails.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvConsumerDetails.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlConsumerDetails.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        if (5 > 0) {//TODO 有接口后5更换总条数
            if (pageNum == 1) {
                str1.clear();
                for (int i = 0; i < 5; i++) {//TODO 有接口后去掉
                    TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
                    str1.add(testData);
                }
//                str1.addAll(data.getData().getMessageList());//TODO 有接口后打开
                consumerDetailsAdapter = new ConsumerDetailsAdapter(str1);
                rvConsumerDetails.setAdapter(consumerDetailsAdapter);
                consumerDetailsAdapter.bindToRecyclerView(rvConsumerDetails);
                consumerDetailsAdapter.setEnableLoadMore(true);
                consumerDetailsAdapter.openLoadAnimation();
                consumerDetailsAdapter.disableLoadMoreIfNotFullPage();

                //上拉加载
                consumerDetailsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        rvConsumerDetails.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }, 1000);
                    }
                }, rvConsumerDetails);

                if (5 > 10) {//TODO 有接口后5更换list.size
                    pageNum += 1;
                } else {
                    consumerDetailsAdapter.loadMoreEnd();
                }
            } else {
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后5更换list.size
                    pageNum += 1;
//                    consumerDetailsAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    consumerDetailsAdapter.loadMoreComplete(); //完成本次
                } else {
//                    consumerDetailsAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    consumerDetailsAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            str1.clear();
            consumerDetailsAdapter = new ConsumerDetailsAdapter(str1);
            rvConsumerDetails.setAdapter(consumerDetailsAdapter);
            consumerDetailsAdapter.loadMoreEnd(); //完成所有加载
            consumerDetailsAdapter.setEmptyView(R.layout.null_data, rvConsumerDetails);
        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlConsumerDetails.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlConsumerDetails.setRefreshing(false);
            }
        });
    }
}
