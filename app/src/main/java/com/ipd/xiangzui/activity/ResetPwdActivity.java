package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：密码重置
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/4.
 */
public class ResetPwdActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    MaterialEditText etPhone;
    @BindView(R.id.et_captcha)
    MaterialEditText etCaptcha;
    @BindView(R.id.bt_captcha)
    SuperButton btCaptcha;
    @BindView(R.id.et_pwd)
    MaterialEditText etPwd;

    private CountDownButtonHelper mCountDownHelper; //倒计时

    @Override
    public int getLayoutId() {
        return R.layout.activity_reset_pwd;
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
        ImmersionBar.with(this).statusBarDarkFont(false).init();

        mCountDownHelper = new CountDownButtonHelper(btCaptcha, 60);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onDestroy() {
        mCountDownHelper.cancel();
        super.onDestroy();
    }

    @OnClick({R.id.bt_captcha, R.id.rv_reset_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_captcha:
                mCountDownHelper.start();
                break;
            case R.id.rv_reset_pwd:
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }
}
