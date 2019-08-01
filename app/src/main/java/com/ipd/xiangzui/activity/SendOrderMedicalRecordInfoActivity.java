package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

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

/**
 * Description ：发单-病历信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class SendOrderMedicalRecordInfoActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_medical_record_info)
    TopView tvSendOrderMedicalRecordInfo;
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
    @BindView(R.id.et_blood_pressure_start)
    EditText etBloodPressureStart;
    @BindView(R.id.et_blood_pressure_end)
    EditText etBloodPressureEnd;
    @BindView(R.id.et_pulse)
    EditText etPulse;
    @BindView(R.id.et_breathe)
    EditText etBreathe;
    @BindView(R.id.et_body_temperature)
    EditText etBodyTemperature;
    @BindView(R.id.rb_diabetes_start)
    RadioButton rbDiabetesStart;
    @BindView(R.id.rb_diabetes_end)
    RadioButton rbDiabetesEnd;
    @BindView(R.id.rb_brain_stalk_start)
    RadioButton rbBrainStalkStart;
    @BindView(R.id.rb_brain_stalk_end)
    RadioButton rbBrainStalkEnd;
    @BindView(R.id.rb_heart_disease_start)
    RadioButton rbHeartDiseaseStart;
    @BindView(R.id.rb_heart_disease_end)
    RadioButton rbHeartDiseaseEnd;
    @BindView(R.id.rb_infectious_disease_start)
    RadioButton rbInfectiousDiseaseStart;
    @BindView(R.id.rb_infectious_disease_end)
    RadioButton rbInfectiousDiseaseEnd;
    @BindView(R.id.rb_respiratory_dysfunction_start)
    RadioButton rbRespiratoryDysfunctionStart;
    @BindView(R.id.rb_respiratory_dysfunction_end)
    RadioButton rbRespiratoryDysfunctionEnd;
    @BindView(R.id.ll_blood_pressure)
    LinearLayout llBloodPressure;
    @BindView(R.id.ll_pulse)
    LinearLayout llPulse;
    @BindView(R.id.ll_breathe)
    LinearLayout llBreathe;
    @BindView(R.id.ll_body_temperature)
    LinearLayout llBodyTemperature;
    @BindView(R.id.ll_diabetes)
    LinearLayout llDiabetes;
    @BindView(R.id.ll_brain_stalk)
    LinearLayout llBrainStalk;
    @BindView(R.id.ll_heart_disease)
    LinearLayout llHeartDisease;
    @BindView(R.id.ll_infectious_disease)
    LinearLayout llInfectiousDisease;
    @BindView(R.id.ll_respiratory_dysfunction)
    LinearLayout llRespiratoryDysfunction;
    @BindView(R.id.ll_add_patient)
    LinearLayout llAddPatient;

    private int sendOrderType; //1: 单台, 2: 连台

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_medical_record_info;
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
        ImmersionBar.setTitleBar(this, tvSendOrderMedicalRecordInfo);

        sendOrderType = getIntent().getIntExtra("sendOrderType", 0);
        if (sendOrderType == 1)
            llAddPatient.setVisibility(View.GONE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.rb_none, R.id.rb_edit, R.id.rb_img, R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index, R.id.sb_add_patient, R.id.sb_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_none://暂无
                //填写上传
                llBloodPressure.setVisibility(View.GONE);
                llPulse.setVisibility(View.GONE);
                llBreathe.setVisibility(View.GONE);
                llBodyTemperature.setVisibility(View.GONE);
                llDiabetes.setVisibility(View.GONE);
                llBrainStalk.setVisibility(View.GONE);
                llHeartDisease.setVisibility(View.GONE);
                llInfectiousDisease.setVisibility(View.GONE);
                llRespiratoryDysfunction.setVisibility(View.GONE);
                //图片上传
                stvSurgeryAboutMedicalRecord.setVisibility(View.GONE);
                stvBloodRoutine.setVisibility(View.GONE);
                stvElectrocardiogram.setVisibility(View.GONE);
                stvCoagulation.setVisibility(View.GONE);
                stvInfectiousDiseaseIndex.setVisibility(View.GONE);
                break;
            case R.id.rb_edit://填写上传
                //填写上传
                llBloodPressure.setVisibility(View.VISIBLE);
                llPulse.setVisibility(View.VISIBLE);
                llBreathe.setVisibility(View.VISIBLE);
                llBodyTemperature.setVisibility(View.VISIBLE);
                llDiabetes.setVisibility(View.VISIBLE);
                llBrainStalk.setVisibility(View.VISIBLE);
                llHeartDisease.setVisibility(View.VISIBLE);
                llInfectiousDisease.setVisibility(View.VISIBLE);
                llRespiratoryDysfunction.setVisibility(View.VISIBLE);
                //图片上传
                stvSurgeryAboutMedicalRecord.setVisibility(View.GONE);
                stvBloodRoutine.setVisibility(View.GONE);
                stvElectrocardiogram.setVisibility(View.GONE);
                stvCoagulation.setVisibility(View.GONE);
                stvInfectiousDiseaseIndex.setVisibility(View.GONE);
                break;
            case R.id.rb_img://图片上传
                //填写上传
                llBloodPressure.setVisibility(View.GONE);
                llPulse.setVisibility(View.GONE);
                llBreathe.setVisibility(View.GONE);
                llBodyTemperature.setVisibility(View.GONE);
                llDiabetes.setVisibility(View.GONE);
                llBrainStalk.setVisibility(View.GONE);
                llHeartDisease.setVisibility(View.GONE);
                llInfectiousDisease.setVisibility(View.GONE);
                llRespiratoryDysfunction.setVisibility(View.GONE);
                //图片上传
                stvSurgeryAboutMedicalRecord.setVisibility(View.VISIBLE);
                stvBloodRoutine.setVisibility(View.VISIBLE);
                stvElectrocardiogram.setVisibility(View.VISIBLE);
                stvCoagulation.setVisibility(View.VISIBLE);
                stvInfectiousDiseaseIndex.setVisibility(View.VISIBLE);
                break;
            case R.id.stv_surgery_about_medical_record:
                startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class));
                break;
            case R.id.stv_blood_routine:
                startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class));
                break;
            case R.id.stv_electrocardiogram:
                startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class));
                break;
            case R.id.stv_coagulation:
                startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class));
                break;
            case R.id.stv_infectious_disease_index:
                startActivity(new Intent(this, SurgeryAboutMedicalRecordActivity.class));
                break;
            case R.id.sb_add_patient:
                startActivity(new Intent(this, SendOrderAddPatientActivity.class));
                break;
            case R.id.sb_next:
                startActivity(new Intent(this, SendOrderFeeInfoActivity.class).putExtra("sendOrderType", sendOrderType));
                break;
        }
    }
}
