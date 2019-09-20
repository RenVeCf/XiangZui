package com.ipd.xiangzui.activity;

import android.content.Intent;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.ModifyUserPwdBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.ModifyUserPwdContract;
import com.ipd.xiangzui.presenter.ModifyUserPwdPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.edittext.PasswordEditText;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;

/**
 * Description ：修改密码
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class ModifyPwdActivity extends BaseActivity<ModifyUserPwdContract.View, ModifyUserPwdContract.Presenter> implements ModifyUserPwdContract.View {

    @BindView(R.id.tv_modify_pwd)
    TopView tvModifyPwd;
    @BindView(R.id.new_pwd)
    PasswordEditText newPwd;
    @BindView(R.id.new_pwd_again)
    PasswordEditText newPwdAgain;
    @BindView(R.id.old_pwd)
    PasswordEditText oldPwd;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_pwd;
    }

    @Override
    public ModifyUserPwdContract.Presenter createPresenter() {
        return new ModifyUserPwdPresenter(this);
    }

    @Override
    public ModifyUserPwdContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvModifyPwd);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.bt_confirm)
    public void onViewClicked() {
        TreeMap<String, String> modifyUserPwdMap = new TreeMap<>();
        modifyUserPwdMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        modifyUserPwdMap.put("orgPassword", oldPwd.getText().toString().trim());
        modifyUserPwdMap.put("newPassword", newPwd.getText().toString().trim());
        modifyUserPwdMap.put("newPassword2", newPwdAgain.getText().toString().trim());
        modifyUserPwdMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyUserPwdMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getModifyUserPwd(modifyUserPwdMap, true, false);
    }

    @Override
    public void resultModifyUserPwd(ModifyUserPwdBean data) {
        switch (data.getCode()) {
            case 200:
                finish();
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
