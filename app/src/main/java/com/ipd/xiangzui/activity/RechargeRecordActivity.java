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
import com.ipd.xiangzui.adapter.FeeRecordAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.FeeRecordBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.FeeRecordContract;
import com.ipd.xiangzui.presenter.FeeRecordPresenter;
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
 * Description ：充值记录
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class RechargeRecordActivity extends BaseActivity<FeeRecordContract.View, FeeRecordContract.Presenter> implements FeeRecordContract.View {

    @BindView(R.id.tv_recharge_record)
    TopView tvRechargeRecord;
    @BindView(R.id.rv_recharge_record)
    RecyclerView rvRechargeRecord;
    @BindView(R.id.srl_recharge_record)
    SwipeRefreshLayout srlRechargeRecord;

    private List<FeeRecordBean.DataBean.RecordListBean> recordList = new ArrayList<>();
    private FeeRecordAdapter feeRecordAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge_record;
    }

    @Override
    public FeeRecordContract.Presenter createPresenter() {
        return new FeeRecordPresenter(this);
    }

    @Override
    public FeeRecordContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvRechargeRecord);

        // 设置管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvRechargeRecord.setLayoutManager(layoutManager);
        rvRechargeRecord.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvRechargeRecord.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlRechargeRecord.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
    }

    @Override
    public void initData() {
        TreeMap<String, String> feeRecordMap = new TreeMap<>();
        feeRecordMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        feeRecordMap.put("balanceType", "1");
        feeRecordMap.put("pageNum", pageNum + "");
        feeRecordMap.put("pageSize", "10");
        feeRecordMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(feeRecordMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getFeeRecord(feeRecordMap, false, false);
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlRechargeRecord.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData();
                srlRechargeRecord.setRefreshing(false);
            }
        });
    }

    @Override
    public void resultFeeRecord(FeeRecordBean data) {
        switch (data.getCode()) {
            case 200:
                if (data.getTotal() > 0) {
                    if (pageNum == 1) {
                        recordList.clear();
                        recordList.addAll(data.getData().getRecordList());
                        feeRecordAdapter = new FeeRecordAdapter(recordList);
                        rvRechargeRecord.setAdapter(feeRecordAdapter);
                        feeRecordAdapter.bindToRecyclerView(rvRechargeRecord);
                        feeRecordAdapter.setEnableLoadMore(true);
                        feeRecordAdapter.openLoadAnimation();
                        feeRecordAdapter.disableLoadMoreIfNotFullPage();

                        //上拉加载
                        feeRecordAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                            @Override
                            public void onLoadMoreRequested() {
                                rvRechargeRecord.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        initData();
                                    }
                                }, 1000);
                            }
                        }, rvRechargeRecord);

                        if (data.getTotal() > 10) {
                            pageNum += 1;
                        } else {
                            feeRecordAdapter.loadMoreEnd();
                        }
                    } else {
                        if ((data.getTotal() - pageNum * 10) > 0) {
                            pageNum += 1;
                            feeRecordAdapter.addData(data.getData().getRecordList());
                            feeRecordAdapter.loadMoreComplete(); //完成本次
                        } else {
                            feeRecordAdapter.addData(data.getData().getRecordList());
                            feeRecordAdapter.loadMoreEnd(); //完成所有加载
                        }
                    }
                } else {
                    recordList.clear();
                    feeRecordAdapter = new FeeRecordAdapter(recordList);
                    rvRechargeRecord.setAdapter(feeRecordAdapter);
                    feeRecordAdapter.loadMoreEnd(); //完成所有加载
                    feeRecordAdapter.setEmptyView(R.layout.null_data, rvRechargeRecord);
                }
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
