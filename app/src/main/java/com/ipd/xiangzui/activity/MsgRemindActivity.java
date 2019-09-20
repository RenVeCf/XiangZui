package com.ipd.xiangzui.activity;

import android.content.Intent;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.GetUserInfoBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.GetUserInfoContract;
import com.ipd.xiangzui.presenter.GetUserInfoPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.TreeMap;

import butterknife.BindView;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;

/**
 * Description ：新消息提醒
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class MsgRemindActivity extends BaseActivity<GetUserInfoContract.View, GetUserInfoContract.Presenter> implements GetUserInfoContract.View {

    @BindView(R.id.tv_msg_remind)
    TopView tvMsgRemind;
    @BindView(R.id.tv_is_get_notification)
    SuperTextView tvIsGetNotification;
    @BindView(R.id.tv_is_get_sound)
    SuperTextView tvIsGetSound;
    @BindView(R.id.tv_is_get_shock)
    SuperTextView tvIsGetShock;

    @Override
    public int getLayoutId() {
        return R.layout.activity_msg_remind;
    }

    @Override
    public GetUserInfoContract.Presenter createPresenter() {
        return new GetUserInfoPresenter(this);
    }

    @Override
    public GetUserInfoContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvMsgRemind);
    }

    @Override
    public void initData() {
        TreeMap<String, String> getUserInfoMap = new TreeMap<>();
        getUserInfoMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        getUserInfoMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(getUserInfoMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getGetUserInfo(getUserInfoMap, false, false);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void resultGetUserInfo(GetUserInfoBean data) {
        switch (data.getCode()) {
            case 200:
                tvIsGetNotification.setSwitchIsChecked("1".equals(data.getData().getUser().getInfoSwitch()) ? false : true);
                tvIsGetSound.setSwitchIsChecked("1".equals(data.getData().getUser().getSoundSwitch()) ? false : true);
                tvIsGetShock.setSwitchIsChecked("1".equals(data.getData().getUser().getVibrationSwitch()) ? false : true);
                break;
            case 900:
                ToastUtil.showLongToast(data.getMsg());
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
