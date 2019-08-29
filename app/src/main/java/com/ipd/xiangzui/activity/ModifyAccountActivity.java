package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：修改/添加账户
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/29.
 */
public class ModifyAccountActivity extends BaseActivity {

    @BindView(R.id.tv_modify_account)
    TopView tvModifyAccount;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.bt_top_del_address)
    Button btTopDelAddress;
    @BindView(R.id.et_patient_name)
    EditText etPatientName;
    @BindView(R.id.et_bank_open)
    EditText etBankOpen;
    @BindView(R.id.et_bank_code)
    EditText etBankCode;
    @BindView(R.id.cb_default_account)
    MaterialCheckBox cbDefaultAccount;

    private int accountType; //1: 添加账户, 2:修改账户

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_account;
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
        ImmersionBar.setTitleBar(this, tvModifyAccount);

        accountType = getIntent().getIntExtra("accountType", 0);
        switch (accountType) {
            case 1:
                tvTopTitle.setText("添加账户");
                btTopDelAddress.setVisibility(View.GONE);
                break;
            case 2:
                tvTopTitle.setText("修改账户");
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bt_top_del_address, R.id.sb_save_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_top_del_address:
                setResult(RESULT_OK, new Intent().putExtra("del_account", 1));
                finish();
                break;
            case R.id.sb_save_account:
                if (isFastClick())
                    switch (accountType) {
                        case 1:
                            setResult(RESULT_OK, new Intent().putExtra("add_account", 1));
                            break;
                        case 2:
                            setResult(RESULT_OK, new Intent().putExtra("modify_account", 1));
                            break;
                    }
                finish();
                break;
        }
    }
}
