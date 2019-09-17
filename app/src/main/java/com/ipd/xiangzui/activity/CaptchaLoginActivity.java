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
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

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
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：验证码登录
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/1.
 */
public class CaptchaLoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    /**
     * ----------Dragon be here!----------/
     * 　　　┏┓　　　┏┓
     * 　　┏┛┻━━━┛┻┓
     * 　　┃　　　　　　　┃
     * 　　┃　　　━　　　┃
     * 　　┃　┳┛　┗┳　┃
     * 　　┃　　　　　　　┃
     * 　　┃　　　┻　　　┃
     * 　　┃　　　　　　　┃
     * 　　┗━┓　　　┏━┛
     * 　　　　┃　　　┃神兽保佑
     * 　　　　┃　　　┃代码无BUG！
     * 　　　　┃　　　┗━━━┓
     * 　　　　┃　　　　　　　┣┓
     * 　　　　┃　　　　　　　┏┛
     * 　　　　┗┓┓┏━┳┓┏┛
     * 　　　　　┃┫┫　┃┫┫
     * 　　　　　┗┻┛　┗┻┛
     * ━━━━━━神兽出没━━━━━━
     */

    @BindView(R.id.et_phone)
    MaterialEditText etPhone;
    @BindView(R.id.et_captcha)
    MaterialEditText etCaptcha;
    @BindView(R.id.bt_captcha)
    SuperButton btCaptcha;

    private long firstTime = 0;
    private CountDownButtonHelper mCountDownHelper; //倒计时

    @Override
    public int getLayoutId() {
        return R.layout.activity_captcha_login;
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

        if (!isEmpty(SPUtil.get(this, TOKEN, "") + "")) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

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

    @OnClick({R.id.bt_captcha, R.id.bt_reset_pwd, R.id.bt_now_register, R.id.rv_login, R.id.bt_login_pwd})
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
            case R.id.bt_reset_pwd:
                startActivity(new Intent(this, ResetPwdActivity.class));
                break;
            case R.id.bt_now_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.rv_login:
                if (etPhone.getText().toString().trim().length() > 0 && etCaptcha.getText().toString().trim().length() > 0) {
                    TreeMap<String, String> captchaLoginMap = new TreeMap<>();
                    captchaLoginMap.put("telPhone", etPhone.getText().toString().trim());
                    captchaLoginMap.put("smsCode", etCaptcha.getText().toString().trim());
                    captchaLoginMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(captchaLoginMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getCaptchaLogin(captchaLoginMap, true, false);
                } else
                    ToastUtil.showShortToast("请填写号码！");
                break;
            case R.id.bt_login_pwd:
                startActivity(new Intent(this, PwdLoginActivity.class));
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
