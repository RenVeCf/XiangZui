package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_103;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_104;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_105;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_106;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_107;

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

    private SendOrderDataBean sendOrderData;
    private int sendOrderType; //1: 单台, 2: 连台
    private int selectRb = 0; //1: 图片上传, 2: 填写上传, 3: 暂无
    private String surgeryAboutMedicalRecordUrl = "", bloodRoutineUrl = "", electrocardiogramUrl = "", coagulationUrl = "", infectiousDiseaseIndexUrl = "";

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

        sendOrderData = getIntent().getParcelableExtra("sendOrderData");
        sendOrderType = sendOrderData.getSendOrderType();
        if (sendOrderType == 1)
            llAddPatient.setVisibility(View.GONE);
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
                case REQUEST_CODE_103:
                    surgeryAboutMedicalRecordUrl = data.getStringExtra("imgUrl");
                    stvSurgeryAboutMedicalRecord.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_104:
                    bloodRoutineUrl = data.getStringExtra("imgUrl");
                    stvBloodRoutine.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_105:
                    electrocardiogramUrl = data.getStringExtra("imgUrl");
                    stvElectrocardiogram.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_106:
                    coagulationUrl = data.getStringExtra("imgUrl");
                    stvCoagulation.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_107:
                    infectiousDiseaseIndexUrl = data.getStringExtra("imgUrl");
                    stvInfectiousDiseaseIndex.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
            }
        }
    }

    @OnClick({R.id.rb_none, R.id.rb_edit, R.id.rb_img, R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index, R.id.sb_add_patient, R.id.sb_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_none://暂无
                //填写上传
                selectRb = 3;
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
                selectRb = 2;
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
                //图片上传
                selectRb = 1;
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
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "手术相关病历"), REQUEST_CODE_103);
                break;
            case R.id.stv_blood_routine:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "血常规"), REQUEST_CODE_104);
                break;
            case R.id.stv_electrocardiogram:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "心电图"), REQUEST_CODE_105);
                break;
            case R.id.stv_coagulation:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "凝血功能"), REQUEST_CODE_106);
                break;
            case R.id.stv_infectious_disease_index:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "传染病指标"), REQUEST_CODE_107);
                break;
            case R.id.sb_add_patient:
                startActivity(new Intent(this, SendOrderAddPatientActivity.class).putExtra("sendOrderData", sendOrderData));
                break;
            case R.id.sb_next:
                if (sendOrderType == 1) {
                    switch (selectRb) {
                        case 1:
                            sendOrderData.getOneOrderBean().setSurgeryRelated(surgeryAboutMedicalRecordUrl);
                            sendOrderData.getOneOrderBean().setRoutineBlood(bloodRoutineUrl);
                            sendOrderData.getOneOrderBean().setEcg(electrocardiogramUrl);
                            sendOrderData.getOneOrderBean().setCruor(coagulationUrl);
                            sendOrderData.getOneOrderBean().setContagion(infectiousDiseaseIndexUrl);
                            break;
                        case 2:
                            sendOrderData.getOneOrderBean().setMinBloodPressure(etBloodPressureStart.getText().toString().trim());
                            sendOrderData.getOneOrderBean().setMaxBloodPressure(etBloodPressureEnd.getText().toString().trim());
                            sendOrderData.getOneOrderBean().setPulse(etPulse.getText().toString().trim());
                            sendOrderData.getOneOrderBean().setBreathe(etBreathe.getText().toString().trim());
                            sendOrderData.getOneOrderBean().setAnimalHeat(etBodyTemperature.getText().toString().trim());
                            sendOrderData.getOneOrderBean().setDiabetes(rbDiabetesStart.isChecked() ? "2" : "1");
                            sendOrderData.getOneOrderBean().setCerebralInfarction(rbBrainStalkStart.isChecked() ? "2" : "1");
                            sendOrderData.getOneOrderBean().setHeartDisease(rbHeartDiseaseStart.isChecked() ? "2" : "1");
                            sendOrderData.getOneOrderBean().setInfectDisease(rbInfectiousDiseaseStart.isChecked() ? "2" : "1");
                            sendOrderData.getOneOrderBean().setBreatheFunction(rbRespiratoryDysfunctionStart.isChecked() ? "2" : "1");
                            break;
                        case 3:
                            break;
                    }
                } else {
                    switch (selectRb) {
                        case 1:
                            sendOrderData.getTwoOrderBean().get(0).setSurgeryRelated(surgeryAboutMedicalRecordUrl);
                            sendOrderData.getTwoOrderBean().get(0).setRoutineBlood(bloodRoutineUrl);
                            sendOrderData.getTwoOrderBean().get(0).setEcg(electrocardiogramUrl);
                            sendOrderData.getTwoOrderBean().get(0).setCruor(coagulationUrl);
                            sendOrderData.getTwoOrderBean().get(0).setContagion(infectiousDiseaseIndexUrl);
                            break;
                        case 2:
                            sendOrderData.getTwoOrderBean().get(0).setMinBloodPressure(etBloodPressureStart.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setMaxBloodPressure(etBloodPressureEnd.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setPulse(etPulse.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setBreathe(etBreathe.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setAnimalHeat(etBodyTemperature.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setDiabetes(rbDiabetesStart.isChecked() ? "2" : "1");
                            sendOrderData.getTwoOrderBean().get(0).setCerebralInfarction(rbBrainStalkStart.isChecked() ? "2" : "1");
                            sendOrderData.getTwoOrderBean().get(0).setHeartDisease(rbHeartDiseaseStart.isChecked() ? "2" : "1");
                            sendOrderData.getTwoOrderBean().get(0).setInfectDisease(rbInfectiousDiseaseStart.isChecked() ? "2" : "1");
                            sendOrderData.getTwoOrderBean().get(0).setBreatheFunction(rbRespiratoryDysfunctionStart.isChecked() ? "2" : "1");
                            break;
                        case 3:
                            break;
                    }
                }
                startActivity(new Intent(this, SendOrderFeeInfoActivity.class).putExtra("sendOrderData", sendOrderData));
                break;
        }
    }
}
