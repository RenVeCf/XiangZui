package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.os.Parcelable;
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
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_103;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_104;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_105;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_106;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_107;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_109;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_112;

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
    @BindView(R.id.rb_img)
    RadioButton rbImg;
    @BindView(R.id.rb_edit)
    RadioButton rbEdit;
    @BindView(R.id.rb_none)
    RadioButton rbNone;


    private SendOrderDataBean sendOrderData;
    private OrderDetailsBean.DataBean.OrderBean orderDetails;
    private List<OrderDetailsBean.DataBean.OrderDetailBean> orderDetailsList;
    private int sendOrderType; //1: 单台, 2: 连台
    private int selectRb = 1; //1: 图片上传, 2: 填写上传, 3: 暂无
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
        if (sendOrderData != null) {
            sendOrderType = sendOrderData.getSendOrderType();
            if (sendOrderType == 1)
                llAddPatient.setVisibility(View.GONE);
        }
        orderDetails = getIntent().getParcelableExtra("orderDetails");
        orderDetailsList = getIntent().getParcelableArrayListExtra("orderDetailsList");
        if (orderDetails != null && orderDetailsList.size() > 0) {
            sendOrderType = Integer.parseInt(orderDetails.getOrderType());
            if (sendOrderType == 1)
                llAddPatient.setVisibility(View.GONE);

            switch (orderDetailsList.get(0).getMedicalRecords()) {
                case "1":
                    rbImg.setChecked(true);
                    img();
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getSurgeryRelated())) {
                        surgeryAboutMedicalRecordUrl = orderDetailsList.get(0).getSurgeryRelated();
                        stvSurgeryAboutMedicalRecord.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getRoutineBlood())) {
                        bloodRoutineUrl = orderDetailsList.get(0).getRoutineBlood();
                        stvBloodRoutine.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getEcg())) {
                        electrocardiogramUrl = orderDetailsList.get(0).getEcg();
                        stvElectrocardiogram.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getCruor())) {
                        coagulationUrl = orderDetailsList.get(0).getCruor();
                        stvCoagulation.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getContagion())) {
                        infectiousDiseaseIndexUrl = orderDetailsList.get(0).getContagion();
                        stvInfectiousDiseaseIndex.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    break;
                case "2":
                    rbEdit.setChecked(true);
                    edit();
                    if (orderDetailsList.get(0).getMinBloodPressure() > 0) {
                        etBloodPressureStart.setText(orderDetailsList.get(0).getMinBloodPressure() + "");
                    }
                    if (orderDetailsList.get(0).getMaxBloodPressure() > 0) {
                        etBloodPressureEnd.setText(orderDetailsList.get(0).getMaxBloodPressure() + "");
                    }
                    if (orderDetailsList.get(0).getPulse() > 0) {
                        etPulse.setText(orderDetailsList.get(0).getPulse() + "");
                    }
                    if (orderDetailsList.get(0).getBreathe() > 0) {
                        etBreathe.setText(orderDetailsList.get(0).getBreathe() + "");
                    }
                    if (orderDetailsList.get(0).getAnimalHeat() > 0) {
                        etBodyTemperature.setText(orderDetailsList.get(0).getAnimalHeat() + "");
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getDiabetes())) {
                        rbDiabetesStart.setChecked("2".equals(orderDetailsList.get(0).getDiabetes()));
                        rbDiabetesEnd.setChecked("1".equals(orderDetailsList.get(0).getDiabetes()));
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getCerebralInfarction())) {
                        rbBrainStalkStart.setChecked("2".equals(orderDetailsList.get(0).getCerebralInfarction()));
                        rbBrainStalkEnd.setChecked("1".equals(orderDetailsList.get(0).getCerebralInfarction()));
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getHeartDisease())) {
                        rbHeartDiseaseStart.setChecked("2".equals(orderDetailsList.get(0).getHeartDisease()));
                        rbHeartDiseaseEnd.setChecked("1".equals(orderDetailsList.get(0).getHeartDisease()));
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getInfectDisease())) {
                        rbInfectiousDiseaseStart.setChecked("2".equals(orderDetailsList.get(0).getInfectDisease()));
                        rbInfectiousDiseaseEnd.setChecked("1".equals(orderDetailsList.get(0).getInfectDisease()));
                    }
                    if (!StringUtils.isEmpty(orderDetailsList.get(0).getBreatheFunction())) {
                        rbRespiratoryDysfunctionStart.setChecked("2".equals(orderDetailsList.get(0).getBreatheFunction()));
                        rbRespiratoryDysfunctionEnd.setChecked("1".equals(orderDetailsList.get(0).getBreatheFunction()));
                    }
                    break;
                case "3":
                    rbNone.setChecked(true);
                    none();
                    break;
            }
        }
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
                case REQUEST_CODE_109:
                    sendOrderData = data.getParcelableExtra("sendOrderData");
                    break;
                case REQUEST_CODE_112:
                    orderDetails = data.getParcelableExtra("orderDetails");
                    orderDetailsList = data.getParcelableArrayListExtra("orderDetailsList");
                    break;
            }
        }
    }

    //暂无
    private void none() {
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
    }

    //填写上传
    private void edit() {
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
    }

    //图片上传
    private void img() {
        //填写上传
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
    }

    @OnClick({R.id.rb_none, R.id.rb_edit, R.id.rb_img, R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index, R.id.sb_add_patient, R.id.sb_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_none:
                none();
                break;
            case R.id.rb_edit:
                edit();
                break;
            case R.id.rb_img:
                img();
                break;
            case R.id.stv_surgery_about_medical_record:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "手术相关病历").putExtra("imgUrl", surgeryAboutMedicalRecordUrl), REQUEST_CODE_103);
                break;
            case R.id.stv_blood_routine:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "血常规").putExtra("imgUrl", bloodRoutineUrl), REQUEST_CODE_104);
                break;
            case R.id.stv_electrocardiogram:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "心电图").putExtra("imgUrl", electrocardiogramUrl), REQUEST_CODE_105);
                break;
            case R.id.stv_coagulation:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "凝血功能").putExtra("imgUrl", coagulationUrl), REQUEST_CODE_106);
                break;
            case R.id.stv_infectious_disease_index:
                startActivityForResult(new Intent(this, SurgeryAboutMedicalRecordActivity.class).putExtra("title", "传染病指标").putExtra("imgUrl", infectiousDiseaseIndexUrl), REQUEST_CODE_107);
                break;
            case R.id.sb_add_patient:
                if (orderDetails != null && orderDetailsList.size() > 0)
                    startActivityForResult(new Intent(this, SendOrderAddPatientActivity.class).putExtra("orderDetails", orderDetails).putParcelableArrayListExtra("orderDetailsList", (ArrayList<? extends Parcelable>) orderDetailsList).putExtra("selectRb", selectRb), REQUEST_CODE_112);
                else
                    startActivityForResult(new Intent(this, SendOrderAddPatientActivity.class).putExtra("sendOrderData", sendOrderData).putExtra("selectRb", selectRb), REQUEST_CODE_109);
                break;
            case R.id.sb_next:
                if (orderDetails != null && orderDetailsList.size() > 0) {
                    for (int i = 0; i < orderDetailsList.size(); i++) {
                        orderDetailsList.get(i).setMedicalRecords(selectRb + "");
                        switch (selectRb) {
                            case 1:
                                orderDetailsList.get(i).setSurgeryRelated(surgeryAboutMedicalRecordUrl);
                                orderDetailsList.get(i).setRoutineBlood(bloodRoutineUrl);
                                orderDetailsList.get(i).setEcg(electrocardiogramUrl);
                                orderDetailsList.get(i).setCruor(coagulationUrl);
                                orderDetailsList.get(i).setContagion(infectiousDiseaseIndexUrl);
                                break;
                            case 2:
                                orderDetailsList.get(i).setMinBloodPressure(Double.parseDouble(etBloodPressureStart.getText().toString().trim()));
                                orderDetailsList.get(i).setMaxBloodPressure(Double.parseDouble(etBloodPressureEnd.getText().toString().trim()));
                                orderDetailsList.get(i).setPulse(Integer.parseInt(etPulse.getText().toString().trim()));
                                orderDetailsList.get(i).setBreathe(Integer.parseInt(etBreathe.getText().toString().trim()));
                                orderDetailsList.get(i).setAnimalHeat(Double.parseDouble(etBodyTemperature.getText().toString().trim()));
                                orderDetailsList.get(i).setDiabetes(rbDiabetesStart.isChecked() ? "2" : "1");
                                orderDetailsList.get(i).setCerebralInfarction(rbBrainStalkStart.isChecked() ? "2" : "1");
                                orderDetailsList.get(i).setHeartDisease(rbHeartDiseaseStart.isChecked() ? "2" : "1");
                                orderDetailsList.get(i).setInfectDisease(rbInfectiousDiseaseStart.isChecked() ? "2" : "1");
                                orderDetailsList.get(i).setBreatheFunction(rbRespiratoryDysfunctionStart.isChecked() ? "2" : "1");
                                break;
                            case 3:
                                break;
                        }
                    }
                } else {
                    if (sendOrderType == 1) {
                        sendOrderData.getOneOrderBean().setMedicalRecords(selectRb + "");
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
                                for (int i = 0; i < sendOrderData.getTwoOrderBean().size(); i++) {
                                    sendOrderData.getTwoOrderBean().get(i).setMedicalRecords(selectRb + "");
                                    sendOrderData.getTwoOrderBean().get(i).setSurgeryRelated(surgeryAboutMedicalRecordUrl);
                                    sendOrderData.getTwoOrderBean().get(i).setRoutineBlood(bloodRoutineUrl);
                                    sendOrderData.getTwoOrderBean().get(i).setEcg(electrocardiogramUrl);
                                    sendOrderData.getTwoOrderBean().get(i).setCruor(coagulationUrl);
                                    sendOrderData.getTwoOrderBean().get(i).setContagion(infectiousDiseaseIndexUrl);
                                }
                                break;
                            case 2:
                                for (int i = 0; i < sendOrderData.getTwoOrderBean().size(); i++) {
                                    sendOrderData.getTwoOrderBean().get(i).setMedicalRecords(selectRb + "");
                                    sendOrderData.getTwoOrderBean().get(i).setMinBloodPressure(etBloodPressureStart.getText().toString().trim());
                                    sendOrderData.getTwoOrderBean().get(i).setMaxBloodPressure(etBloodPressureEnd.getText().toString().trim());
                                    sendOrderData.getTwoOrderBean().get(i).setPulse(etPulse.getText().toString().trim());
                                    sendOrderData.getTwoOrderBean().get(i).setBreathe(etBreathe.getText().toString().trim());
                                    sendOrderData.getTwoOrderBean().get(i).setAnimalHeat(etBodyTemperature.getText().toString().trim());
                                    sendOrderData.getTwoOrderBean().get(i).setDiabetes(rbDiabetesStart.isChecked() ? "2" : "1");
                                    sendOrderData.getTwoOrderBean().get(i).setCerebralInfarction(rbBrainStalkStart.isChecked() ? "2" : "1");
                                    sendOrderData.getTwoOrderBean().get(i).setHeartDisease(rbHeartDiseaseStart.isChecked() ? "2" : "1");
                                    sendOrderData.getTwoOrderBean().get(i).setInfectDisease(rbInfectiousDiseaseStart.isChecked() ? "2" : "1");
                                    sendOrderData.getTwoOrderBean().get(i).setBreatheFunction(rbRespiratoryDysfunctionStart.isChecked() ? "2" : "1");
                                }
                                break;
                            case 3:
                                break;
                        }
                    }
                }

                startActivity(new Intent(this, SendOrderFeeInfoActivity.class).putExtra("sendOrderData", sendOrderData).putExtra("orderDetails", orderDetails).putParcelableArrayListExtra("orderDetailsList", (ArrayList<? extends Parcelable>) orderDetailsList));
                break;
        }
    }
}
