package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;

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
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.AVATAR;
import static com.ipd.xiangzui.common.config.IConstants.NIKE_NAME;
import static com.ipd.xiangzui.common.config.IConstants.PHONE;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.TOKEN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;

/**
 * Description ：注册
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/4.
 */
public class RegisterActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.et_phone)
    MaterialEditText etPhone;
    @BindView(R.id.et_captcha)
    MaterialEditText etCaptcha;
    @BindView(R.id.bt_captcha)
    SuperButton btCaptcha;
    @BindView(R.id.et_pwd)
    MaterialEditText etPwd;
    @BindView(R.id.et_invitation_code)
    MaterialEditText etInvitationCode;
    @BindView(R.id.cb_register)
    CheckBox cbRegister;

    private CountDownButtonHelper mCountDownHelper; //倒计时

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
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

    @OnClick({R.id.bt_captcha, R.id.tv_agreement, R.id.bt_now_login, R.id.rv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_captcha:
                if (etPhone.getText().toString().trim().length() > 0) {
                    mCountDownHelper.start();

                    TreeMap<String, String> captchaMap = new TreeMap<>();
                    captchaMap.put("telPhone", etPhone.getText().toString().trim());
                    captchaMap.put("type", "2");
                    captchaMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(captchaMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getCaptcha(captchaMap, true, false);
                } else
                    ToastUtil.showShortToast("请填写号码!");
                break;
            case R.id.tv_agreement:
                startActivity(new Intent(this, WebViewActivity.class).putExtra("h5Type", 1));
                break;
            case R.id.bt_now_login:
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
            case R.id.rv_register:
                if (cbRegister.isChecked())
                    if (etPhone.getText().toString().trim().length() > 0 && etCaptcha.getText().toString().trim().length() > 0 && etPwd.getText().toString().trim().length() > 0) {
                        TreeMap<String, String> registsMap = new TreeMap<>();
                        registsMap.put("telPhone", etPhone.getText().toString().trim());
                        registsMap.put("password", etPwd.getText().toString().trim());
                        registsMap.put("smsCode", etCaptcha.getText().toString().trim());
                        registsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(registsMap.toString().replaceAll(" ", "") + SIGN)));
                        getPresenter().getRegists(registsMap, true, false);
                    } else
                        ToastUtil.showShortToast("请填写号码！");
                else
                    ToastUtil.showShortToast("请同意用户协议！");
                break;
        }
    }

    @Override
    public void resultCaptcha(CaptchaBean data) {
        ToastUtil.showLongToast(data.getMsg());
    }

    @Override
    public void resultRegists(RegistsBean data) {
        if (data.getCode() == 200) {
            SPUtil.put(this, TOKEN, data.getData().getToken());
            SPUtil.put(this, USER_ID, data.getData().getUser().getUserId() + "");
            SPUtil.put(this, PHONE, data.getData().getUser().getTelPhone());
            SPUtil.put(this, NIKE_NAME, data.getData().getUser().getNickname());
            SPUtil.put(this, AVATAR, data.getData().getUser().getAvatar());

            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else
            ToastUtil.showShortToast(data.getMsg());
    }

    @Override
    public void resultCaptchaLogin(CaptchaLoginBean data) {

    }

    @Override
    public void resultPwdLogin(PwdLoginBean data) {

    }

    @Override
    public void resultResetPwd(ResetPwdBean data) {

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
