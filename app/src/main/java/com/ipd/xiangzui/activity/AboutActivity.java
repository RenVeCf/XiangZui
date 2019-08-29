package com.ipd.xiangzui.activity;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import butterknife.BindView;

/**
 * Description ：关于平台
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class AboutActivity extends BaseActivity {

    @BindView(R.id.tv_about)
    TopView tvAbout;

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
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
        ImmersionBar.setTitleBar(this, tvAbout);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
