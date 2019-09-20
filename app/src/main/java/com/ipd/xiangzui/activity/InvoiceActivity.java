package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.widget.EditText;
import android.widget.RadioButton;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.OpenInvoiceBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.OpenInvoiceContract;
import com.ipd.xiangzui.presenter.OpenInvoicePresenter;
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

/**
 * Description ：申请开票
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class InvoiceActivity extends BaseActivity<OpenInvoiceContract.View, OpenInvoiceContract.Presenter> implements OpenInvoiceContract.View {

    @BindView(R.id.rb_start)
    RadioButton rbStart;
    @BindView(R.id.rb_end)
    RadioButton rbEnd;
    @BindView(R.id.et_company_name)
    EditText etCompanyName;
    @BindView(R.id.et_tax_code)
    EditText etTaxCode;
    @BindView(R.id.et_company_address)
    EditText etCompanyAddress;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_open_bank)
    EditText etOpenBank;
    @BindView(R.id.et_bank_code)
    EditText etBankCode;
    @BindView(R.id.et_emall)
    EditText etEmall;
    @BindView(R.id.et_delivery_address)
    EditText etDeliveryAddress;
    @BindView(R.id.et_contact)
    EditText etContact;
    @BindView(R.id.et_contact_phone)
    EditText etContactPhone;
    @BindView(R.id.tv_invoice)
    TopView tvInvoice;

    @Override
    public int getLayoutId() {
        return R.layout.activity_invoice;
    }

    @Override
    public OpenInvoiceContract.Presenter createPresenter() {
        return new OpenInvoicePresenter(this);
    }

    @Override
    public OpenInvoiceContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvInvoice);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.bt_confirm)
    public void onViewClicked() {
        TreeMap<String, String> openInvoiceMap = new TreeMap<>();
        openInvoiceMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        openInvoiceMap.put("invoiceType", rbStart.isChecked() ? "1" : "2");
        openInvoiceMap.put("companyName", etCompanyName.getText().toString().trim());
        openInvoiceMap.put("ein", etTaxCode.getText().toString().trim());
        openInvoiceMap.put("companyAddress", etCompanyAddress.getText().toString().trim());
        openInvoiceMap.put("telPhone", etPhone.getText().toString().trim());
        openInvoiceMap.put("openBank", etOpenBank.getText().toString().trim());
        openInvoiceMap.put("bankAccount", etBankCode.getText().toString().trim());
        openInvoiceMap.put("courierAddress", etDeliveryAddress.getText().toString().trim());
        openInvoiceMap.put("contacts", etContact.getText().toString().trim());
        openInvoiceMap.put("contactNumber", etContactPhone.getText().toString().trim());
        openInvoiceMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(openInvoiceMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getOpenInvoice(openInvoiceMap, false, false);
    }

    @Override
    public void resultOpenInvoice(OpenInvoiceBean data) {
        switch (data.getCode()) {
            case 200:
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
