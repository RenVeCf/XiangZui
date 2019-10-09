package com.ipd.xiangzui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.NarcosisListBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.NarcosisListContract;
import com.ipd.xiangzui.presenter.NarcosisListPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_103;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_104;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_105;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_106;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_107;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_95;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

public class ModifyMedicalListActivity extends BaseActivity<NarcosisListContract.View, NarcosisListContract.Presenter> implements NarcosisListContract.View {

    @BindView(R.id.et_patient_name)
    EditText etPatientName;
    @BindView(R.id.stv_patient_sex)
    SuperTextView stvPatientSex;
    @BindView(R.id.stv_patient_age)
    SuperTextView stvPatientAge;
    @BindView(R.id.et_patient_height)
    EditText etPatientHeight;
    @BindView(R.id.et_patient_body_weight)
    EditText etPatientBodyWeight;
    @BindView(R.id.stv_anesthesia_type)
    SuperTextView stvAnesthesiaType;
    @BindView(R.id.stv_id_card)
    SuperTextView stvIdCard;
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
    @BindView(R.id.tv_modify_medical_record)
    TopView tvModifyMedicalRecord;
    @BindView(R.id.cl_img_upload)
    ConstraintLayout clImgUpload;
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
    @BindView(R.id.cl_tx_upload)
    ConstraintLayout clTxUpload;
    @BindView(R.id.cl_patient_information)
    ConstraintLayout clPatientInformation;
    @BindView(R.id.tv_medical_record)
    TextView tvMedicalRecord;

    private List<String> narcosisDataList = new ArrayList<>();//麻醉方式
    private List<NarcosisListBean.DataBean.NarcosisListsBean> narcosisLists = new ArrayList<>();//选择麻醉(取ID用)
    private int narcosisId = 0, position = 0;
    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器
    private String orderType; //1:单台，2：连台
    private List<OrderDetailsBean.DataBean.OrderDetailBean> orderDetailsList;
    private String positiveUrl = "", negativeUrl = "", surgeryAboutMedicalRecordUrl = "", bloodRoutineUrl = "", electrocardiogramUrl = "", coagulationUrl = "", infectiousDiseaseIndexUrl = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_medical_list;
    }

    @Override
    public NarcosisListContract.Presenter createPresenter() {
        return new NarcosisListPresenter(this);
    }

    @Override
    public NarcosisListContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvModifyMedicalRecord);

        position = getIntent().getIntExtra("position", 0);
        orderType = getIntent().getStringExtra("orderType");
        orderDetailsList = getIntent().getParcelableArrayListExtra("orderDetailsList");

        switch (orderDetailsList.get(position).getMedicalRecords()) {
            case "1":
                clImgUpload.setVisibility(View.VISIBLE);
                break;
            case "2":
                clTxUpload.setVisibility(View.VISIBLE);
                break;
            case "3":
                tvMedicalRecord.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void initData() {
        TreeMap<String, String> narcosisListMap = new TreeMap<>();
        narcosisListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        narcosisListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(narcosisListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getNarcosisList(narcosisListMap, false, false);

        if ("1".equals(orderType)) {
            etPatientName.setText(orderDetailsList.get(position).getPatientName());
            stvPatientSex.setRightString("1".equals(orderDetailsList.get(position).getSex()) ? "男" : "女");
            stvPatientAge.setRightString(orderDetailsList.get(position).getAge() + "岁");
            etPatientHeight.setText(orderDetailsList.get(position).getHeight() + "");
            etPatientBodyWeight.setText(orderDetailsList.get(position).getWeight() + "");
            stvAnesthesiaType.setRightString(orderDetailsList.get(position).getNarcosisType());
            if (!isEmpty(orderDetailsList.get(position).getPositiveCard()) && !isEmpty(orderDetailsList.get(position).getReverseCard())) {
                positiveUrl = orderDetailsList.get(position).getPositiveCard();
                negativeUrl = orderDetailsList.get(position).getReverseCard();
                stvIdCard.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }

            switch (orderDetailsList.get(position).getMedicalRecords()) {
                case "1":
                    if (!isEmpty(orderDetailsList.get(position).getSurgeryRelated())) {
                        surgeryAboutMedicalRecordUrl = orderDetailsList.get(position).getSurgeryRelated();
                        stvSurgeryAboutMedicalRecord.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(orderDetailsList.get(position).getRoutineBlood())) {
                        bloodRoutineUrl = orderDetailsList.get(position).getRoutineBlood();
                        stvBloodRoutine.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(orderDetailsList.get(position).getEcg())) {
                        electrocardiogramUrl = orderDetailsList.get(position).getEcg();
                        stvElectrocardiogram.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(orderDetailsList.get(position).getCruor())) {
                        coagulationUrl = orderDetailsList.get(position).getCruor();
                        stvCoagulation.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(orderDetailsList.get(position).getContagion())) {
                        infectiousDiseaseIndexUrl = orderDetailsList.get(position).getContagion();
                        stvInfectiousDiseaseIndex.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    break;
                case "2":
                    if (orderDetailsList.get(position).getMinBloodPressure() > 0) {
                        etBloodPressureStart.setText(orderDetailsList.get(position).getMinBloodPressure() + "");
                    }
                    if (orderDetailsList.get(position).getMaxBloodPressure() > 0) {
                        etBloodPressureEnd.setText(orderDetailsList.get(position).getMaxBloodPressure() + "");
                    }
                    if (orderDetailsList.get(position).getPulse() > 0) {
                        etPulse.setText(orderDetailsList.get(position).getPulse() + "");
                    }
                    if (orderDetailsList.get(position).getBreathe() > 0) {
                        etBreathe.setText(orderDetailsList.get(position).getBreathe() + "");
                    }
                    if (orderDetailsList.get(position).getAnimalHeat() > 0) {
                        etBodyTemperature.setText(orderDetailsList.get(position).getAnimalHeat() + "");
                    }
                    if (!isEmpty(orderDetailsList.get(position).getDiabetes())) {
                        rbDiabetesStart.setChecked("2".equals(orderDetailsList.get(position).getDiabetes()));
                    }
                    if (!isEmpty(orderDetailsList.get(position).getCerebralInfarction())) {
                        rbBrainStalkStart.setChecked("2".equals(orderDetailsList.get(position).getCerebralInfarction()));
                    }
                    if (!isEmpty(orderDetailsList.get(position).getHeartDisease())) {
                        rbHeartDiseaseStart.setChecked("2".equals(orderDetailsList.get(position).getHeartDisease()));
                    }
                    if (!isEmpty(orderDetailsList.get(position).getInfectDisease())) {
                        rbInfectiousDiseaseStart.setChecked("2".equals(orderDetailsList.get(position).getInfectDisease()));
                    }
                    if (!isEmpty(orderDetailsList.get(position).getBreatheFunction())) {
                        rbRespiratoryDysfunctionStart.setChecked("2".equals(orderDetailsList.get(position).getBreatheFunction()));
                    }
                    break;
            }
        }
    }

    @Override
    public void initListener() {

    }

    //条件选择器
    private void showPickerView(final int type) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        switch (type) {
            case 1:
                listData = getSexData();
                break;
            case 2:
                listData = getAgeData();
                break;
            case 3:
                listData = getAnesthesiaData();
                break;
        }
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                switch (type) {
                    case 1:
                        stvPatientSex.setRightString(listData.get(options1))
                                .setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        stvPatientAge.setRightString(listData.get(options1))
                                .setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        for (int i = 0; i < narcosisLists.size(); i++) {
                            if (narcosisLists.get(i).getNarcosisTypeName().equals(listData.get(options1)))
                                narcosisId = narcosisLists.get(i).getNarcosisTypeId();
                        }
                        stvAnesthesiaType.setRightString(listData.get(options1))
                                .setRightTextColor(getResources().getColor(R.color.black));
                        break;
                }
            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        switch (type) {
                            case 1:
                                tvTitle.setText("选择性别");
                                break;
                            case 2:
                                tvTitle.setText("选择年龄");
                                break;
                            case 3:
                                tvTitle.setText("选择麻醉方式");
                                break;
                        }
                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .setDecorView(getWindow().getDecorView().findViewById(android.R.id.content))
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private List<String> getSexData() {
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        return list;
    }

    private List<String> getAgeData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            list.add(i + " 岁");
        }
        return list;
    }

    private List<String> getAnesthesiaData() {
        return narcosisDataList;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_95:
                    positiveUrl = data.getStringExtra("positiveUrl");
                    negativeUrl = data.getStringExtra("negativeUrl");
                    stvIdCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
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

    @Override
    public void resultNarcosisList(NarcosisListBean data) {
        switch (data.getCode()) {
            case 200:
                narcosisLists.clear();
                narcosisLists.addAll(data.getData().getNarcosisList());
                for (NarcosisListBean.DataBean.NarcosisListsBean datas : narcosisLists) {
                    narcosisDataList.add(datas.getNarcosisTypeName());
                }
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

    @OnClick({R.id.bt_confirm, R.id.stv_patient_sex, R.id.stv_patient_age, R.id.stv_anesthesia_type, R.id.stv_id_card, R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm:
                orderDetailsList.get(position).setPatientName(etPatientName.getText().toString().trim());
                orderDetailsList.get(position).setSex("男".equals(stvPatientSex.getRightString()) ? "1" : "2");
                orderDetailsList.get(position).setAge(Integer.parseInt(stvPatientAge.getRightString().replaceAll("岁", "")));
                orderDetailsList.get(position).setHeight(Double.parseDouble(etPatientHeight.getText().toString().trim()));
                orderDetailsList.get(position).setWeight(Double.parseDouble(etPatientBodyWeight.getText().toString().trim()));
                orderDetailsList.get(position).setNarcosisTypeId(narcosisId);
                orderDetailsList.get(position).setPositiveCard(positiveUrl);
                orderDetailsList.get(position).setReverseCard(negativeUrl);

                switch (orderDetailsList.get(position).getMedicalRecords()) {
                    case "1":
                        orderDetailsList.get(position).setSurgeryRelated(surgeryAboutMedicalRecordUrl);
                        orderDetailsList.get(position).setRoutineBlood(bloodRoutineUrl);
                        orderDetailsList.get(position).setEcg(electrocardiogramUrl);
                        orderDetailsList.get(position).setCruor(coagulationUrl);
                        orderDetailsList.get(position).setContagion(infectiousDiseaseIndexUrl);
                        break;
                    case "2":
                        orderDetailsList.get(position).setMinBloodPressure(Double.parseDouble(etBloodPressureStart.getText().toString().trim()));
                        orderDetailsList.get(position).setMaxBloodPressure(Double.parseDouble(etBloodPressureEnd.getText().toString().trim()));
                        orderDetailsList.get(position).setPulse(Integer.parseInt(etPulse.getText().toString().trim()));
                        orderDetailsList.get(position).setBreathe(Integer.parseInt(etBreathe.getText().toString().trim()));
                        orderDetailsList.get(position).setAnimalHeat(Double.parseDouble(etBodyTemperature.getText().toString().trim()));
                        orderDetailsList.get(position).setDiabetes(rbDiabetesStart.isChecked() ? "2" : "1");
                        orderDetailsList.get(position).setCerebralInfarction(rbBrainStalkStart.isChecked() ? "2" : "1");
                        orderDetailsList.get(position).setHeartDisease(rbHeartDiseaseStart.isChecked() ? "2" : "1");
                        orderDetailsList.get(position).setInfectDisease(rbInfectiousDiseaseStart.isChecked() ? "2" : "1");
                        orderDetailsList.get(position).setBreatheFunction(rbRespiratoryDysfunctionStart.isChecked() ? "2" : "1");
                        break;
                }
                setResult(RESULT_OK, new Intent().putParcelableArrayListExtra("orderDetailsList", (ArrayList<? extends Parcelable>) orderDetailsList));
                finish();
                break;
            case R.id.stv_patient_sex:
                showPickerView(1);
                break;
            case R.id.stv_patient_age:
                showPickerView(2);
                break;
            case R.id.stv_anesthesia_type:
                showPickerView(3);
                break;
            case R.id.stv_id_card:
                startActivityForResult(new Intent(this, AgentCardActivity.class).putExtra("positiveUrl", positiveUrl).putExtra("negativeUrl", negativeUrl), REQUEST_CODE_95);
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
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
