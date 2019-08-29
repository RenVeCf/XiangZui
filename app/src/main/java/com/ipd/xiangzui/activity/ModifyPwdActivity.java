package com.ipd.xiangzui.activity;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.edittext.PasswordEditText;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：修改密码
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class ModifyPwdActivity extends BaseActivity {

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
        finish();
    }
}
