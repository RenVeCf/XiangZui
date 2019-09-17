package com.ipd.xiangzui.activity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.fragment.MainFragment;
import com.ipd.xiangzui.fragment.MyFragment;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.NavigationBarUtil;
import com.ipd.xiangzui.utils.ToastUtil;

import butterknife.BindView;

/**
 * Description ：
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/1.
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private long firstTime = 0;
    private Fragment currentFragment = new Fragment();
    private MainFragment mainFragment = new MainFragment();
    private MyFragment myFragment = new MyFragment();

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
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
        //适配虚拟按键
        if (NavigationBarUtil.hasNavigationBar(this)) {
            NavigationBarUtil.initActivity(findViewById(android.R.id.content));
        }
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        switchFragment(mainFragment).commit();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    //Fragment优化
    public FragmentTransaction switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (!targetFragment.isAdded()) {
            //第一次使用switchFragment()时currentFragment为null，所以要判断一下
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction.add(R.id.ll_main, targetFragment, targetFragment.getClass().getName());
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment);
        }
        currentFragment = targetFragment;
        return transaction;
    }

    //双击退出程序
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtil.showShortToast(getResources().getString(R.string.click_out_again));
            firstTime = secondTime;
        } else {
            ApplicationUtil.getManager().exitApp();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    switchFragment(mainFragment).commit();
                    return true;
                case R.id.navigation_my:
                    switchFragment(myFragment).commit();
                    return true;
            }
            return false;
        }
    };
}
