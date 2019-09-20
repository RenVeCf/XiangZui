package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.CacheUtil;
import com.ipd.xiangzui.utils.SPUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：设置
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class SettingActivity extends BaseActivity {

    @BindView(R.id.tv_setting)
    TopView tvSetting;
    @BindView(R.id.tv_update_version)
    SuperTextView tvUpdateVersion;
    @BindView(R.id.tv_clear_cache)
    SuperTextView tvClearCache;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
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
        ImmersionBar.setTitleBar(this, tvSetting);
    }

    @Override
    public void initData() {
        tvUpdateVersion.setRightString("V 1.0");
        try {
            tvClearCache.setRightString(CacheUtil.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bt_sign_out, R.id.tv_feedback, R.id.tv_msg_remind, R.id.tv_update_version, R.id.tv_clear_cache, R.id.tv_platform_agreement})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_sign_out:
                if (isFastClick()) {
                    //清除所有临时储存
                    SPUtil.clear(ApplicationUtil.getContext());
                    ApplicationUtil.getManager().finishActivity(MainActivity.class);
                    startActivity(new Intent(this, CaptchaLoginActivity.class));
                    finish();
                }
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(this, FeedbackActivity.class));
                break;
            case R.id.tv_msg_remind:
                startActivity(new Intent(this, MsgRemindActivity.class));
                break;
            case R.id.tv_update_version:
                break;
            case R.id.tv_clear_cache:
                if (isFastClick()) {
                    CacheUtil.clearAllCache(this);
                    try {
                        tvClearCache.setRightString(CacheUtil.getTotalCacheSize(this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.tv_platform_agreement:
                startActivity(new Intent(this, WebViewActivity.class).putExtra("h5Type", 2));
//                startActivity(new Intent(this, PlatformAgreementActivity.class));
                break;
        }
    }
}
