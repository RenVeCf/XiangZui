package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

public class SelectPatientDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_add_patient_details)
    TopView tvSendOrderAddPatientDetails;
    @BindView(R.id.cl_img_upload)
    ConstraintLayout clImgUpload;
    @BindView(R.id.cl_tx_upload)
    ConstraintLayout clTxUpload;
    @BindView(R.id.stv_name)
    SuperTextView stvName;
    @BindView(R.id.stv_sex)
    SuperTextView stvSex;
    @BindView(R.id.stv_age)
    SuperTextView stvAge;
    @BindView(R.id.stv_height)
    SuperTextView stvHeight;
    @BindView(R.id.stv_body_weight)
    SuperTextView stvBodyWeight;
    @BindView(R.id.stv_anesthesia_type)
    SuperTextView stvAnesthesiaType;
    @BindView(R.id.stv_id_card)
    SuperTextView stvIdCard;
    @BindView(R.id.stv_insurance_consent)
    SuperTextView stvInsuranceConsent;
    @BindView(R.id.stv_surgery_about_medical_record)
    SuperTextView stvSurgeryAboutMedicalRecord;
    @BindView(R.id.stv_blood_routine)
    SuperTextView stvBloodRoutine;
    @BindView(R.id.stv_electrocardiogram)
    SuperTextView stvElectrocardiogram;
    @BindView(R.id.stv_coagulation)
    SuperTextView stvCoagulation;
    @BindView(R.id.stv_infectious_disease_index)
    SuperTextView stvInfectiousDiseaseIndex;
    @BindView(R.id.stv_blood_pressure)
    SuperTextView stvBloodPressure;
    @BindView(R.id.stv_pulse)
    SuperTextView stvPulse;
    @BindView(R.id.stv_breathe)
    SuperTextView stvBreathe;
    @BindView(R.id.stv_body_temperature)
    SuperTextView stvBodyTemperature;
    @BindView(R.id.stv_diabetes)
    SuperTextView stvDiabetes;
    @BindView(R.id.stv_brain_stalk)
    SuperTextView stvBrainStalk;
    @BindView(R.id.stv_heart_disease)
    SuperTextView stvHeartDisease;
    @BindView(R.id.stv_infectious_disease)
    SuperTextView stvInfectiousDisease;
    @BindView(R.id.stv_respiratory_dysfunction)
    SuperTextView stvRespiratoryDysfunction;

    private OrderDetailsBean.DataBean.OrderDetailBean orderDetailsTwo;
    private String positiveUrl = "", negativeUrl = "", insuranceConsentUrl = "", surgeryAboutMedicalRecordUrl = "", bloodRoutineUrl = "", electrocardiogramUrl = "", coagulationUrl = "", infectiousDiseaseIndexUrl = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_patient_details;
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
        ImmersionBar.setTitleBar(this, tvSendOrderAddPatientDetails);

        orderDetailsTwo = getIntent().getParcelableExtra("orderDetailsTwo");
    }

    @Override
    public void initData() {
        stvName.setRightString(orderDetailsTwo.getPatientName());
        stvSex.setRightString("1".equals(orderDetailsTwo.getSex()) ? "男" : "女");
        stvAge.setRightString(orderDetailsTwo.getAge() + "岁");
        stvHeight.setRightString(orderDetailsTwo.getHeight() + " cm");
        stvBodyWeight.setRightString(orderDetailsTwo.getWeight() + " kg");
        stvAnesthesiaType.setRightString(orderDetailsTwo.getNarcosisType());

        if (!isEmpty(orderDetailsTwo.getPositiveCard()) && !isEmpty(orderDetailsTwo.getReverseCard())) {
            positiveUrl = orderDetailsTwo.getPositiveCard();
            negativeUrl = orderDetailsTwo.getReverseCard();
            stvIdCard.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        }
        if (!isEmpty(orderDetailsTwo.getInsurance())) {
            insuranceConsentUrl = orderDetailsTwo.getInsurance();
            stvInsuranceConsent.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        }

        switch (orderDetailsTwo.getMedicalRecords()) {
            case "1":
                clImgUpload.setVisibility(View.VISIBLE);
                clTxUpload.setVisibility(View.GONE);
                if (!isEmpty(orderDetailsTwo.getSurgeryRelated())) {
                    surgeryAboutMedicalRecordUrl = orderDetailsTwo.getSurgeryRelated();
                    stvSurgeryAboutMedicalRecord.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                }
                if (!isEmpty(orderDetailsTwo.getRoutineBlood())) {
                    bloodRoutineUrl = orderDetailsTwo.getRoutineBlood();
                    stvBloodRoutine.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                }
                if (!isEmpty(orderDetailsTwo.getEcg())) {
                    electrocardiogramUrl = orderDetailsTwo.getEcg();
                    stvElectrocardiogram.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                }
                if (!isEmpty(orderDetailsTwo.getCruor())) {
                    coagulationUrl = orderDetailsTwo.getCruor();
                    stvCoagulation.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                }
                if (!isEmpty(orderDetailsTwo.getContagion())) {
                    infectiousDiseaseIndexUrl = orderDetailsTwo.getContagion();
                    stvInfectiousDiseaseIndex.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                }
                break;
            case "2":
                clImgUpload.setVisibility(View.GONE);
                clTxUpload.setVisibility(View.VISIBLE);
                stvBloodPressure.setRightString(orderDetailsTwo.getMinBloodPressure() + "/" + orderDetailsTwo.getMaxBloodPressure() + "mmHg");
                stvPulse.setRightString(orderDetailsTwo.getPulse() + "次/分钟");
                stvBreathe.setRightString(orderDetailsTwo.getBreathe() + "次/分钟");
                stvBodyTemperature.setRightString(orderDetailsTwo.getAnimalHeat() + " ℃");
                if ("2".equals(orderDetailsTwo.getDiabetes()))
                    stvDiabetes.setRightString("有")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                if ("2".equals(orderDetailsTwo.getCerebralInfarction()))
                    stvBrainStalk.setRightString("有")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                if ("2".equals(orderDetailsTwo.getHeartDisease()))
                    stvHeartDisease.setRightString("有")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                if ("2".equals(orderDetailsTwo.getInfectDisease()))
                    stvInfectiousDisease.setRightString("有")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                if ("2".equals(orderDetailsTwo.getBreatheFunction()))
                    stvRespiratoryDysfunction.setRightString("有")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                break;
            case "3":
                clImgUpload.setVisibility(View.GONE);
                clTxUpload.setVisibility(View.GONE);
                break;
        }

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index, R.id.stv_id_card, R.id.stv_insurance_consent})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_surgery_about_medical_record:
                if (!isEmpty(surgeryAboutMedicalRecordUrl))
                    startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "手术相关病历").putExtra("imgUrl", surgeryAboutMedicalRecordUrl).putExtra("twoImgType", 1));
                break;
            case R.id.stv_blood_routine:
                if (!isEmpty(bloodRoutineUrl))
                    startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "血常规").putExtra("imgUrl", bloodRoutineUrl).putExtra("twoImgType", 1));
                break;
            case R.id.stv_electrocardiogram:
                if (!isEmpty(electrocardiogramUrl))
                    startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "心电图").putExtra("imgUrl", electrocardiogramUrl).putExtra("twoImgType", 1));
                break;
            case R.id.stv_coagulation:
                if (!isEmpty(coagulationUrl))
                    startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "凝血功能").putExtra("imgUrl", coagulationUrl).putExtra("twoImgType", 1));
                break;
            case R.id.stv_infectious_disease_index:
                if (!isEmpty(infectiousDiseaseIndexUrl))
                    startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "传染病指标").putExtra("imgUrl", infectiousDiseaseIndexUrl).putExtra("twoImgType", 1));
                break;
            case R.id.stv_id_card:
                if (!isEmpty(positiveUrl) && !isEmpty(negativeUrl))
                    startActivity(new Intent(this, AgentCardActivity.class).putExtra("positiveUrl", positiveUrl).putExtra("negativeUrl", negativeUrl).putExtra("cardImgType", 1));
                break;
            case R.id.stv_insurance_consent:
                if (!isEmpty(insuranceConsentUrl))
                    startActivity(new Intent(this, HeadActivity.class).putExtra("title", "保险同意书").putExtra("imgUrl", insuranceConsentUrl).putExtra("oneImgType", 1));
                break;
        }
    }
}
