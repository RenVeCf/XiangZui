package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：密码登录
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/4.
 */
public class PwdLoginActivity extends BaseActivity {

    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;

    private long firstTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pwd_login;
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
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

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

    @OnClick({R.id.bt_reset_pwd, R.id.bt_now_register, R.id.rv_login, R.id.bt_login_captcha})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_reset_pwd:
                startActivity(new Intent(this, ResetPwdActivity.class));
                break;
            case R.id.bt_now_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.rv_login:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.bt_login_captcha:
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }
}
