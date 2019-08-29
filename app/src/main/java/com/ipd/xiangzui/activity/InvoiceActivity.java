package com.ipd.xiangzui.activity;

import android.widget.EditText;
import android.widget.RadioButton;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：申请开票
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class InvoiceActivity extends BaseActivity {

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
        finish();
    }
}
