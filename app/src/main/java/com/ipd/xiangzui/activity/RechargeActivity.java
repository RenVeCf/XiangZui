package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：充值
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class RechargeActivity extends BaseActivity {

    @BindView(R.id.tv_recharge)
    TopView tvRecharge;
    @BindView(R.id.et_service_fee)
    EditText etServiceFee;
    @BindView(R.id.stv_wechat_withdraw)
    SuperTextView stvWechatWithdraw;
    @BindView(R.id.stv_ali_withdraw)
    SuperTextView stvAliWithdraw;
    @BindView(R.id.stv_account)
    SuperTextView stvAccount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
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
        ImmersionBar.setTitleBar(this, tvRecharge);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.stv_wechat_withdraw, R.id.stv_ali_withdraw, R.id.stv_account, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_wechat_withdraw:
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_blue);
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_gray);
                stvAccount.setRightIcon(R.drawable.ic_check_gray);
                break;
            case R.id.stv_ali_withdraw:
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_blue);
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_gray);
                stvAccount.setRightIcon(R.drawable.ic_check_gray);
                break;
            case R.id.stv_account:
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_gray);
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_gray);
                stvAccount.setRightIcon(R.drawable.ic_check_blue);
                break;
            case R.id.sb_confirm:
                startActivity(new Intent(this, RechargeSuccessActivity.class));
                break;
        }
    }
}
