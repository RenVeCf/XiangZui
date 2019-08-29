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
 * Description ：平台协议
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class PlatformAgreementActivity extends BaseActivity {

    @BindView(R.id.tv_platform_agreement)
    TopView tvPlatformAgreement;

    @Override
    public int getLayoutId() {
        return R.layout.activity_platform_agreement;
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
        ImmersionBar.setTitleBar(this, tvPlatformAgreement);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
