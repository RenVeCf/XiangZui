package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.VerifiedBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.VerifiedContract;
import com.ipd.xiangzui.presenter.VerifiedPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_108;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_90;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_91;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_92;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_93;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_94;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：实名认证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class AuthenticationActivity extends BaseActivity<VerifiedContract.View, VerifiedContract.Presenter> implements VerifiedContract.View {

    @BindView(R.id.tv_authentication)
    TopView tvAuthentication;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_register_address)
    EditText etRegisterAddress;
    @BindView(R.id.stv_real_address)
    SuperTextView stvRealAddress;
    @BindView(R.id.stv_head)
    SuperTextView stvHead;
    @BindView(R.id.stv_agent_card)
    SuperTextView stvAgentCard;
    @BindView(R.id.stv_business_license)
    SuperTextView stvBusinessLicense;
    @BindView(R.id.stv_medical_institution_qualification_certificate)
    SuperTextView stvMedicalInstitutionQualificationCertificate;
    @BindView(R.id.stv_agent_management_authorization)
    SuperTextView stvAgentManagementAuthorization;

    private String photo, positiveUrl, negativeUrl, businessLicense,medicalInstitutionQualificationCertificate, agentManagementAuthorization, prov, city, dist, address;

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
    }

    @Override
    public VerifiedContract.Presenter createPresenter() {
        return new VerifiedPresenter(this);
    }

    @Override
    public VerifiedContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAuthentication);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_90:
                    photo = data.getStringExtra("imgUrl");
                    stvHead.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_91:
                    positiveUrl = data.getStringExtra("positiveUrl");
                    negativeUrl = data.getStringExtra("negativeUrl");
                    stvAgentCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_92:
                    businessLicense = data.getStringExtra("imgUrl");
                    stvBusinessLicense.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_93:
                    medicalInstitutionQualificationCertificate = data.getStringExtra("imgUrl");
                    stvMedicalInstitutionQualificationCertificate.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_94:
                    agentManagementAuthorization = data.getStringExtra("imgUrl");
                    stvAgentManagementAuthorization.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_108:
                    prov = data.getStringExtra("prov"); //省
                    city = data.getStringExtra("city"); //市
                    dist = data.getStringExtra("dist"); //区
                    address = data.getStringExtra("address"); //详细地址
                    stvRealAddress.setRightString(prov + city + dist + address);
                    break;
            }
        }
    }

    @OnClick({R.id.stv_real_address, R.id.stv_head, R.id.stv_agent_card, R.id.stv_business_license, R.id.stv_medical_institution_qualification_certificate, R.id.stv_agent_management_authorization, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_real_address: //实际经营地址
                startActivityForResult(new Intent(this, SelectAddressActivity.class).putExtra("address_type", 1), REQUEST_CODE_108);
                break;
            case R.id.stv_head: //头像
                startActivityForResult(new Intent(this, HeadActivity.class).putExtra("title", "照片"), REQUEST_CODE_90);
                break;
            case R.id.stv_agent_card: //代理人身份证
                startActivityForResult(new Intent(this, AgentCardActivity.class), REQUEST_CODE_91);
                break;
            case R.id.stv_business_license: //医院营业执照
                startActivityForResult(new Intent(this, HeadActivity.class).putExtra("title", "医院营业执照"), REQUEST_CODE_92);
                break;
            case R.id.stv_medical_institution_qualification_certificate: //医疗机构资格证
                startActivityForResult(new Intent(this, HeadActivity.class).putExtra("title", "医疗机构资格证"), REQUEST_CODE_93);
                break;
            case R.id.stv_agent_management_authorization: //医院代理人管理授权书
                startActivityForResult(new Intent(this, HeadActivity.class).putExtra("title", "医院代理人管理授权书"), REQUEST_CODE_94);
                break;
            case R.id.sb_confirm:
                if (!isEmpty(etName.getText().toString().trim()) && !isEmpty(etPhone.getText().toString().trim()) && !isEmpty(etRegisterAddress.getText().toString().trim()) && !"请选择".equals(stvRealAddress.getRightString()) && !isEmpty(photo) && !isEmpty(positiveUrl) && !isEmpty(negativeUrl) && !isEmpty(businessLicense) && !isEmpty(medicalInstitutionQualificationCertificate) && !isEmpty(agentManagementAuthorization)) {
                    TreeMap<String, String> verifiedMap = new TreeMap<>();
                    verifiedMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    verifiedMap.put("truename", etName.getText().toString().trim());
                    verifiedMap.put("contactNumber", etPhone.getText().toString().trim());
                    verifiedMap.put("registAddress", etRegisterAddress.getText().toString().trim());
                    verifiedMap.put("prov", prov);
                    verifiedMap.put("city", city);
                    verifiedMap.put("dist", dist);
                    verifiedMap.put("address", address);
                    verifiedMap.put("photo", photo);
                    verifiedMap.put("positiveCard", positiveUrl);
                    verifiedMap.put("reverseCard", negativeUrl);
                    verifiedMap.put("certificate", businessLicense);
                    verifiedMap.put("chestCard", medicalInstitutionQualificationCertificate);
                    verifiedMap.put("hospitalAgent", agentManagementAuthorization);
                    verifiedMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(verifiedMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getVerified(verifiedMap, true, false);
                } else
                    ToastUtil.showLongToast("请将信息填写完整！");
                break;
        }
    }

    @Override
    public void resultVerified(VerifiedBean data) {
        switch (data.getCode()) {
            case 200:
                finish();
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
