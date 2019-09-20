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
import com.ipd.xiangzui.bean.AccountListBean;
import com.ipd.xiangzui.bean.AddAccountBean;
import com.ipd.xiangzui.bean.DelAccountBean;
import com.ipd.xiangzui.bean.ModifyAccountBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.contract.AccountContract;
import com.ipd.xiangzui.presenter.AccountPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：修改/添加账户
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/29.
 */
public class ModifyAccountActivity extends BaseActivity<AccountContract.View, AccountContract.Presenter> implements AccountContract.View {

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
    private int broughtId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_account;
    }

    @Override
    public AccountContract.Presenter createPresenter() {
        return new AccountPresenter(this);
    }

    @Override
    public AccountContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvModifyAccount);

        broughtId = getIntent().getIntExtra("broughtId", 0);
        accountType = getIntent().getIntExtra("accountType", 0);
        switch (accountType) {
            case 1:
                tvTopTitle.setText("添加账户");
                btTopDelAddress.setVisibility(View.GONE);
                break;
            case 2:
                tvTopTitle.setText("修改账户");
                etPatientName.setText(getIntent().getStringExtra("companyName"));
                etBankOpen.setText(getIntent().getStringExtra("openBank"));
                etBankCode.setText(getIntent().getStringExtra("bankAccount"));
                cbDefaultAccount.setChecked("2".equals(getIntent().getStringExtra("defaultBrought")) ? true : false);
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
                new TwoBtDialog(this, "确认删除账户？", "确认") {
                    @Override
                    public void confirm() {
                        TreeMap<String, String> delAccountMap = new TreeMap<>();
                        delAccountMap.put("userId", SPUtil.get(ModifyAccountActivity.this, USER_ID, "") + "");
                        delAccountMap.put("broughtId", broughtId + "");
                        delAccountMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(delAccountMap.toString().replaceAll(" ", "") + SIGN)));
                        getPresenter().getDelAccount(delAccountMap, false, false);
                    }
                }.show();
                break;
            case R.id.sb_save_account:
                if (isFastClick())
                    if (!isEmpty(etBankCode.getText().toString().trim()) && !isEmpty(etPatientName.getText().toString().trim()) && !isEmpty(etBankOpen.getText().toString().trim())) {
                        switch (accountType) {
                            case 1:
                                TreeMap<String, String> addAccountMap = new TreeMap<>();
                                addAccountMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                                addAccountMap.put("companyName", etPatientName.getText().toString().trim());
                                addAccountMap.put("openBank", etBankOpen.getText().toString().trim());
                                addAccountMap.put("bankAccount", etBankCode.getText().toString().trim());
                                addAccountMap.put("defaultBrought", cbDefaultAccount.isChecked() ? "2" : "1");
                                addAccountMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(addAccountMap.toString().replaceAll(" ", "") + SIGN)));
                                getPresenter().getAddAccount(addAccountMap, false, false);
                                break;
                            case 2:
                                TreeMap<String, String> modifyAccountMap = new TreeMap<>();
                                modifyAccountMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                                modifyAccountMap.put("companyName", etPatientName.getText().toString().trim());
                                modifyAccountMap.put("openBank", etBankOpen.getText().toString().trim());
                                modifyAccountMap.put("bankAccount", etBankCode.getText().toString().trim());
                                modifyAccountMap.put("broughtId", broughtId + "");
                                modifyAccountMap.put("defaultBrought", cbDefaultAccount.isChecked() ? "2" : "1");
                                modifyAccountMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyAccountMap.toString().replaceAll(" ", "") + SIGN)));
                                getPresenter().getModifyAccount(modifyAccountMap, false, false);
                                break;
                        }
                    } else
                        ToastUtil.showShortToast("请将信息填写完整！");
                break;
        }
    }

    @Override
    public void resultAccountList(AccountListBean data) {

    }

    @Override
    public void resultAddAccount(AddAccountBean data) {
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("add_account", 1));
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
    public void resultModifyAccount(ModifyAccountBean data) {
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("modify_account", 1));
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
    public void resultDelAccount(DelAccountBean data) {
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("del_account", 1));
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
