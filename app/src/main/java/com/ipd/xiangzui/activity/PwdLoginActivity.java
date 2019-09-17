package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.CaptchaBean;
import com.ipd.xiangzui.bean.CaptchaLoginBean;
import com.ipd.xiangzui.bean.PwdLoginBean;
import com.ipd.xiangzui.bean.RegistsBean;
import com.ipd.xiangzui.bean.ResetPwdBean;
import com.ipd.xiangzui.contract.LoginContract;
import com.ipd.xiangzui.presenter.LoginPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.AVATAR;
import static com.ipd.xiangzui.common.config.IConstants.IS_SUPPLEMENT_INFO;
import static com.ipd.xiangzui.common.config.IConstants.NIKE_NAME;
import static com.ipd.xiangzui.common.config.IConstants.PHONE;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.TOKEN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;

/**
 * Description ：密码登录
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/4.
 */
public class PwdLoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    MaterialEditText etPhone;
    @BindView(R.id.et_pwd)
    MaterialEditText etPwd;

    private long firstTime = 0;

    @Override
    public int getLayoutId() {
        return R.layout.activity_pwd_login;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginContract.View createView() {
        return this;
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
                if (etPhone.getText().toString().trim().length() > 0 && etPwd.getText().toString().trim().length() > 0) {
                    TreeMap<String, String> pwdLoginMap = new TreeMap<>();
                    pwdLoginMap.put("telPhone", etPhone.getText().toString().trim());
                    pwdLoginMap.put("password", etPwd.getText().toString().trim());
                    pwdLoginMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(pwdLoginMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getPwdLogin(pwdLoginMap, true, false);
                } else
                    ToastUtil.showShortToast("请填写号码！");
                break;
            case R.id.bt_login_captcha:
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultCaptcha(CaptchaBean data) {

    }

    @Override
    public void resultRegists(RegistsBean data) {

    }

    @Override
    public void resultCaptchaLogin(CaptchaLoginBean data) {

    }

    @Override
    public void resultPwdLogin(PwdLoginBean data) {
        if (data.getCode() == 200) {
            SPUtil.put(this, TOKEN, data.getData().getToken());
            SPUtil.put(this, USER_ID, data.getData().getUser().getUserId() + "");
            SPUtil.put(this, PHONE, data.getData().getUser().getTelPhone());
            SPUtil.put(this, NIKE_NAME, data.getData().getUser().getNickname());
            SPUtil.put(this, AVATAR, data.getData().getUser().getAvatar());
            SPUtil.put(this, IS_SUPPLEMENT_INFO, data.getData().getUser().getApproveStatus());

            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else
            ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultResetPwd(ResetPwdBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
