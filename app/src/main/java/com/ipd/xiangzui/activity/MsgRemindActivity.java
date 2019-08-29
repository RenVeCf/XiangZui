package com.ipd.xiangzui.activity;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

/**
 * Description ：新消息提醒
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class MsgRemindActivity extends BaseActivity {

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
        ImmersionBar.setTitleBar(this, tvMsgRemind);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }
}
