package com.ipd.xiangzui.activity;

import android.content.Intent;
import androidx.annotation.Nullable;
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

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_90;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_91;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_92;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_93;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_94;

/**
 * Description ：实名认证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class AuthenticationActivity extends BaseActivity {

    @BindView(R.id.tv_authentication)
    TopView tvAuthentication;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_register_address)
    EditText etRegisterAddress;
    @BindView(R.id.et_real_address)
    EditText etRealAddress;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_authentication;
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
                    stvHead.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_91:
                    stvAgentCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_92:
                    stvBusinessLicense.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_93:
                    stvMedicalInstitutionQualificationCertificate.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_94:
                    stvAgentManagementAuthorization.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
            }
        }
    }

    @OnClick({R.id.stv_head, R.id.stv_agent_card, R.id.stv_business_license, R.id.stv_medical_institution_qualification_certificate, R.id.stv_agent_management_authorization, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_head: //头像
                startActivityForResult(new Intent(this, HeadActivity.class), REQUEST_CODE_90);
                break;
            case R.id.stv_agent_card: //代理人身份证
                startActivityForResult(new Intent(this, AgentCardActivity.class), REQUEST_CODE_91);
                break;
            case R.id.stv_business_license: //医院营业执照
                startActivityForResult(new Intent(this, BusinessLicenseActivity.class), REQUEST_CODE_92);
                break;
            case R.id.stv_medical_institution_qualification_certificate: //医疗机构资格证
                startActivityForResult(new Intent(this, MedicalInstitutionQualificationCertificateActivity.class), REQUEST_CODE_93);
                break;
            case R.id.stv_agent_management_authorization: //医院代理人管理授权书
                startActivityForResult(new Intent(this, AgentManagementAuthorizationActivity.class), REQUEST_CODE_94);
                break;
            case R.id.sb_confirm:
                startActivity(new Intent(this, SendOrderActivity.class));
                finish();
                break;
        }
    }
}
