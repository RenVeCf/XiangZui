package com.ipd.xiangzui.activity;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.ViewPagerAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.NavitationFollowScrollLayoutText;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.fragment.OrderFragment;
import com.ipd.xiangzui.utils.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description ：订单
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class OrderActivity extends BaseActivity {

    @BindView(R.id.tv_order)
    TopView tvOrder;
    @BindView(R.id.nfsl_order)
    NavitationFollowScrollLayoutText nfslOrder;
    @BindView(R.id.vp_order)
    ViewPager vpOrder;

    private String[] titles;
    private List<Fragment> fragments;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order;
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
        ImmersionBar.setTitleBar(this, tvOrder);
    }

    @Override
    public void initData() {
        titles = new String[]{"待接单", "已接单", "进行中", "已完成"};
        //向集合添加Fragment
        fragments = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            fragments.add(new OrderFragment());
        }
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragments);
        vpOrder.setAdapter(viewPagerAdapter);
        vpOrder.setOffscreenPageLimit(titles.length);

        //设置导航条
        nfslOrder.setViewPager(this, titles, vpOrder, R.color.tx_bottom_navigation, R.color.white, 14, 14, 24, true, R.color.white, 0, 0, 0, 80);
        nfslOrder.setBgLine(this, 1, R.color.whitesmoke);
        nfslOrder.setNavLine(this, 1, R.color.whitesmoke);
    }

    @Override
    public void initListener() {

    }
}
