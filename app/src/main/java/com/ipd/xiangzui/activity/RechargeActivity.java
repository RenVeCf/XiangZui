package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.aliPay.AliPay;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.RechargeAccountPayBean;
import com.ipd.xiangzui.bean.RechargeAliPayBean;
import com.ipd.xiangzui.bean.RechargeWechatPayBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.RechargeContract;
import com.ipd.xiangzui.presenter.RechargePresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_100;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：充值
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class RechargeActivity extends BaseActivity<RechargeContract.View, RechargeContract.Presenter> implements RechargeContract.View {

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
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_name)
    LinearLayout llName;
    @BindView(R.id.tv_open_bank)
    TextView tvOpenBank;
    @BindView(R.id.ll_open_bank)
    LinearLayout llOpenBank;
    @BindView(R.id.tv_bank_code)
    TextView tvBankCode;
    @BindView(R.id.ll_bank_code)
    LinearLayout llBankCode;
    @BindView(R.id.tv_pay_code)
    TextView tvPayCode;
    @BindView(R.id.ll_pay_code)
    LinearLayout llPayCode;

    private int payType; //1: 微信， 2：支付宝 , 3: 对公转账
    private int broughtId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recharge;
    }

    @Override
    public RechargeContract.Presenter createPresenter() {
        return new RechargePresenter(this);
    }

    @Override
    public RechargeContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvRecharge);

        broughtId = getIntent().getIntExtra("broughtId", 0);

        stvWechatWithdraw.setCheckBoxChecked(true);
        payType = 1;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    private void pay(int payType) {
        switch (payType) {
            case 1:
                TreeMap<String, String> rechargeWechatPayMap = new TreeMap<>();
                rechargeWechatPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                rechargeWechatPayMap.put("balanceMoney", etServiceFee.getText().toString().trim());
                rechargeWechatPayMap.put("type", "2");
                rechargeWechatPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(rechargeWechatPayMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getRechargeWechatPay(rechargeWechatPayMap, false, false);
                break;
            case 2:
                TreeMap<String, String> rechargeAliPayMap = new TreeMap<>();
                rechargeAliPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                rechargeAliPayMap.put("balanceMoney", etServiceFee.getText().toString().trim());
                rechargeAliPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(rechargeAliPayMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getRechargeAliPay(rechargeAliPayMap, false, false);
                break;
            case 3:
                TreeMap<String, String> rechargeAccountPayMap = new TreeMap<>();
                rechargeAccountPayMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                rechargeAccountPayMap.put("balanceMoney", etServiceFee.getText().toString().trim());
                rechargeAccountPayMap.put("broughtId", broughtId + "");
                rechargeAccountPayMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(rechargeAccountPayMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getRechargeAccountPay(rechargeAccountPayMap, false, false);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_100:
                    broughtId = data.getIntExtra("broughtId", 0);
                    tvPayCode.setText(data.getStringExtra("open_bank") + "(尾号：" + data.getStringExtra("bank_code").substring(data.getStringExtra("bank_code").length() - 4) + ")");
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
        finish();
    }

    @OnClick({R.id.ll_top_back, R.id.stv_wechat_withdraw, R.id.stv_ali_withdraw, R.id.stv_account, R.id.sb_confirm, R.id.ll_pay_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
                finish();
                break;
            case R.id.stv_wechat_withdraw:
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_blue);
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_gray);
                stvAccount.setRightIcon(R.drawable.ic_check_gray);
                payType = 1;

                llName.setVisibility(View.GONE);
                llOpenBank.setVisibility(View.GONE);
                llBankCode.setVisibility(View.GONE);
                llPayCode.setVisibility(View.GONE);
                break;
            case R.id.stv_ali_withdraw:
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_blue);
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_gray);
                stvAccount.setRightIcon(R.drawable.ic_check_gray);
                payType = 2;

                llName.setVisibility(View.GONE);
                llOpenBank.setVisibility(View.GONE);
                llBankCode.setVisibility(View.GONE);
                llPayCode.setVisibility(View.GONE);
                break;
            case R.id.stv_account:
                stvWechatWithdraw.setRightIcon(R.drawable.ic_check_gray);
                stvAliWithdraw.setRightIcon(R.drawable.ic_check_gray);
                stvAccount.setRightIcon(R.drawable.ic_check_blue);
                payType = 3;

                llName.setVisibility(View.VISIBLE);
                llOpenBank.setVisibility(View.VISIBLE);
                llBankCode.setVisibility(View.VISIBLE);
                llPayCode.setVisibility(View.VISIBLE);
                break;
            case R.id.sb_confirm:
                if (!isEmpty(etServiceFee.getText().toString().trim()))
                    pay(payType);
                else
                    ToastUtil.showLongToast("请填写充值金额！");
                break;
            case R.id.ll_pay_code:
                startActivityForResult(new Intent(this, AccountActivity.class).putExtra("account_type", 1), REQUEST_CODE_100);
                break;
        }
    }

    @Override
    public void resultRechargeAliPay(RechargeAliPayBean data) {
        switch (data.getCode()) {
            case 200:
                new AliPay(this, data.getData().getSign());
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
    public void resultRechargeWechatPay(RechargeWechatPayBean data) {
        switch (data.getCode()) {
            case 200:
                IWXAPI api = WXAPIFactory.createWXAPI(this, null);
                api.registerApp("wx2bed183f1f29ee2f");
                PayReq req = new PayReq();
                req.appId = data.getData().getSign().getAppid();//你的微信appid
                req.partnerId = data.getData().getSign().getPartnerid();//商户号
                req.prepayId = data.getData().getSign().getPrepayid();//预支付交易会话ID
                req.nonceStr = data.getData().getSign().getNoncestr();//随机字符串
                req.timeStamp = data.getData().getSign().getTimestamp() + "";//时间戳
                req.packageValue = data.getData().getSign().getPackageX(); //扩展字段, 这里固定填写Sign = WXPay
                req.sign = data.getData().getSign().getSign();//签名
                //  req.extData         = "app data"; // optional
                // 在支付之前，如果应用没有注册到微信，应该先调用IWXMsg.registerApp将应用注册到微信
                api.sendReq(req);
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
    public void resultRechargeAccountPay(RechargeAccountPayBean data) {
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(this, RechargeSuccessActivity.class));
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
