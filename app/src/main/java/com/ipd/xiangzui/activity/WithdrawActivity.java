package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description ：提现
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/12.
 */
public class WithdrawActivity extends BaseActivity {

    @BindView(R.id.tv_withdraw)
    TopView tvWithdraw;
    @BindView(R.id.tv_service_fee)
    TextView tvServiceFee;
    @BindView(R.id.et_service_fee)
    EditText etServiceFee;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_open_bank)
    EditText etOpenBank;
    @BindView(R.id.et_bank_code)
    EditText etBankCode;

    @Override
    public int getLayoutId() {
        return R.layout.activity_withdraw;
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
        ImmersionBar.setTitleBar(this, tvWithdraw);
    }

    @Override
    public void initData() {
        tvServiceFee.setText("提现金额（收取0.3%服务费）");
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_confirm:
                startActivity(new Intent(this, WithdrawSuccessActivity.class));
                break;
        }
    }
}
