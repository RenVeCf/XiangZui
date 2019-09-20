package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.WithdrawAccountBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.WithdrawAccountContract;
import com.ipd.xiangzui.presenter.WithdrawAccountPresenter;
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

/**
 * Description ：提现
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/12.
 */
public class WithdrawActivity extends BaseActivity<WithdrawAccountContract.View, WithdrawAccountContract.Presenter> implements WithdrawAccountContract.View {

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
    public WithdrawAccountContract.Presenter createPresenter() {
        return new WithdrawAccountPresenter(this);
    }

    @Override
    public WithdrawAccountContract.View createView() {
        return this;
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
                if (!isEmpty(etServiceFee.getText().toString().trim())) {
                    TreeMap<String, String> withdrawAccountMap = new TreeMap<>();
                    withdrawAccountMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    withdrawAccountMap.put("withMoney", etServiceFee.getText().toString().trim());
                    withdrawAccountMap.put("companyName", etName.getText().toString().trim());
                    withdrawAccountMap.put("opneBank", etOpenBank.getText().toString().trim());
                    withdrawAccountMap.put("bankAccount", etBankCode.getText().toString().trim());
                    withdrawAccountMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(withdrawAccountMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getWithdrawAccount(withdrawAccountMap, false, false);
                } else
                    ToastUtil.showLongToast("请填写提现金额！");
                break;
        }
    }

    @Override
    public void resultWithdrawAccount(WithdrawAccountBean data) {
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(this, WithdrawSuccessActivity.class));
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
            default:
                ToastUtil.showShortToast(data.getMsg());
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
