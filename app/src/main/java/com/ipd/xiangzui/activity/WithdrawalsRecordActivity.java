package com.ipd.xiangzui.activity;

import android.annotation.SuppressLint;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
 * Description ：提现记录
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/12.
 */
public class WithdrawalsRecordActivity extends BaseActivity {

    @BindView(R.id.tv_withdrawals_record)
    TopView tvWithdrawalsRecord;
    @BindView(R.id.rv_withdrawals_record)
    RecyclerView rvWithdrawalsRecord;
    @BindView(R.id.srl_withdrawals_record)
    SwipeRefreshLayout srlWithdrawalsRecord;

    private List<TestMultiItemEntityBean> list;
    private ConsumerDetailsAdapter consumerDetailsAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdrawals_record;
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
        ImmersionBar.setTitleBar(this, tvWithdrawalsRecord);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvWithdrawalsRecord.setLayoutManager(layoutManager);
        rvWithdrawalsRecord.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvWithdrawalsRecord.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlWithdrawalsRecord.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色

        list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            list.add(new TestMultiItemEntityBean());
        }
        consumerDetailsAdapter = new ConsumerDetailsAdapter(list);
        rvWithdrawalsRecord.setAdapter(consumerDetailsAdapter);
        consumerDetailsAdapter.bindToRecyclerView(rvWithdrawalsRecord);
        consumerDetailsAdapter.setEnableLoadMore(true);
        consumerDetailsAdapter.openLoadAnimation();
        consumerDetailsAdapter.disableLoadMoreIfNotFullPage();
    }

    @Override
    public void initData() {
        consumerDetailsAdapter.loadMoreComplete();
        consumerDetailsAdapter.loadMoreEnd();
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlWithdrawalsRecord.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                srlWithdrawalsRecord.setRefreshing(false);
            }
        });

        //上拉加载
        consumerDetailsAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                initData();
            }
        }, rvWithdrawalsRecord);
    }
}
