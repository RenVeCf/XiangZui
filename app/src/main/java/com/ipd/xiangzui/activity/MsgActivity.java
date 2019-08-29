package com.ipd.xiangzui.activity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.MsgAdapter;
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
 * Description ：我的消息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class MsgActivity extends BaseActivity {

    @BindView(R.id.tv_msg)
    TopView tvMsg;
    @BindView(R.id.rv_msg)
    RecyclerView rvMsg;
    @BindView(R.id.srl_msg)
    SwipeRefreshLayout srlMsg;

    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();
    private MsgAdapter msgAdapter;
    private int pageNum = 1;//页数

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg;
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
        ImmersionBar.setTitleBar(this, tvMsg);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvMsg.setLayoutManager(layoutManager);
        rvMsg.setNestedScrollingEnabled(false);
        rvMsg.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvMsg.setItemAnimator(new DefaultItemAnimator());//加载动画
        srlMsg.setColorSchemeResources(R.color.tx_bottom_navigation_select);//刷新圈颜色
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
                msgAdapter = new MsgAdapter(str1);
                rvMsg.setAdapter(msgAdapter);
                msgAdapter.bindToRecyclerView(rvMsg);
                msgAdapter.setEnableLoadMore(true);
                msgAdapter.openLoadAnimation();
                msgAdapter.disableLoadMoreIfNotFullPage();

                //上拉加载
                msgAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                    @Override
                    public void onLoadMoreRequested() {
                        rvMsg.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                initData();
                            }
                        }, 1000);
                    }
                }, rvMsg);

                if (5 > 10) {//TODO 有接口后5更换list.size
                    pageNum += 1;
                } else {
                    msgAdapter.loadMoreEnd();
                }
            } else {
                if ((5 - pageNum * 10) > 0) {//TODO 有接口后5更换list.size
                    pageNum += 1;
//                    msgAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    msgAdapter.loadMoreComplete(); //完成本次
                } else {
//                    msgAdapter.addData(data.getData().getMessageList());//TODO 有接口后打开
                    msgAdapter.loadMoreEnd(); //完成所有加载
                }
            }
        } else {
            str1.clear();
            msgAdapter = new MsgAdapter(str1);
            rvMsg.setAdapter(msgAdapter);
            msgAdapter.loadMoreEnd(); //完成所有加载
            msgAdapter.setEmptyView(R.layout.null_data, rvMsg);
        }
    }

    @Override
    public void initListener() {
        //下拉刷新
        srlMsg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                initData();
                srlMsg.setRefreshing(false);
            }
        });
    }
}
