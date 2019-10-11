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
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.SelectOrderAddPatientAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.ModifyMedicalBean;
import com.ipd.xiangzui.bean.NarcosisListBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.ModifyMedicalContract;
import com.ipd.xiangzui.presenter.ModifyMedicalPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_103;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_104;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_105;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_106;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_107;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_110;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_95;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：补充病历
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/8/29.
 */
public class ModifyMedicalRecordActivity extends BaseActivity<ModifyMedicalContract.View, ModifyMedicalContract.Presenter> implements ModifyMedicalContract.View {

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
    @BindView(R.id.rv_patient_list)
    RecyclerView rvPatientList;
    @BindView(R.id.tv_medical_record)
    TextView tvMedicalRecord;

    private List<String> narcosisDataList = new ArrayList<>();//麻醉方式
    private List<NarcosisListBean.DataBean.NarcosisListsBean> narcosisLists = new ArrayList<>();//选择麻醉(取ID用)
    private int narcosisId = 0; //麻醉ID
    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器
    private OrderDetailsBean.DataBean.OrderBean orderDetails;
    private List<OrderDetailsBean.DataBean.OrderDetailBean> orderDetailsList;
    private SelectOrderAddPatientAdapter selectOrderAddPatientAdapter;
    private String positiveUrl = "", negativeUrl = "", surgeryAboutMedicalRecordUrl = "", bloodRoutineUrl = "", electrocardiogramUrl = "", coagulationUrl = "", infectiousDiseaseIndexUrl = "";

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_medical_record;
    }

    @Override
    public ModifyMedicalContract.Presenter createPresenter() {
        return new ModifyMedicalPresenter(this);
    }

    @Override
    public ModifyMedicalContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvModifyMedicalRecord);

        orderDetails = getIntent().getParcelableExtra("orderDetails");
        orderDetailsList = getIntent().getParcelableArrayListExtra("orderDetailsList");

        if ("1".equals(orderDetails.getOrderType())) {
            switch (orderDetailsList.get(0).getMedicalRecords()) {
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
        } else if ("2".equals(orderDetails.getOrderType())) {
            rvPatientList.setVisibility(View.VISIBLE);
            clPatientInformation.setVisibility(View.GONE);

            CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
            rvPatientList.setLayoutManager(layoutManager);
            rvPatientList.setNestedScrollingEnabled(false);
            rvPatientList.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
            rvPatientList.setItemAnimator(new DefaultItemAnimator());//加载动画

            rvPatientList.setAdapter(selectOrderAddPatientAdapter = new SelectOrderAddPatientAdapter(orderDetailsList, 2));
            selectOrderAddPatientAdapter.bindToRecyclerView(rvPatientList);
            selectOrderAddPatientAdapter.openLoadAnimation();

            selectOrderAddPatientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.stv_add_patient_item:
                            startActivityForResult(new Intent(ModifyMedicalRecordActivity.this, ModifyMedicalListActivity.class).putExtra("orderType", orderDetails.getOrderType()).putParcelableArrayListExtra("orderDetailsList", (ArrayList<? extends Parcelable>) orderDetailsList).putExtra("position", position), REQUEST_CODE_110);
                            break;
                    }
                }
            });
        }
    }

    @Override
    public void initData() {
        TreeMap<String, String> narcosisListMap = new TreeMap<>();
        narcosisListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        narcosisListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(narcosisListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getNarcosisList(narcosisListMap, false, false);

        if ("1".equals(orderDetails.getOrderType())) {
            etPatientName.setText(orderDetailsList.get(0).getPatientName());
            stvPatientSex.setRightString("1".equals(orderDetailsList.get(0).getSex()) ? "男" : "女");
            stvPatientAge.setRightString(orderDetailsList.get(0).getAge() + "岁");
            etPatientHeight.setText(orderDetailsList.get(0).getHeight() + "");
            etPatientBodyWeight.setText(orderDetailsList.get(0).getWeight() + "");
            stvAnesthesiaType.setRightString(orderDetailsList.get(0).getNarcosisType());
            if (!isEmpty(orderDetailsList.get(0).getPositiveCard()) && !isEmpty(orderDetailsList.get(0).getReverseCard())) {
                positiveUrl = orderDetailsList.get(0).getPositiveCard();
                negativeUrl = orderDetailsList.get(0).getReverseCard();
                stvIdCard.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }

            switch (orderDetailsList.get(0).getMedicalRecords()) {
                case "1":
                    if (!isEmpty(orderDetailsList.get(0).getSurgeryRelated())) {
                        surgeryAboutMedicalRecordUrl = orderDetailsList.get(0).getSurgeryRelated();
                        stvSurgeryAboutMedicalRecord.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(orderDetailsList.get(0).getRoutineBlood())) {
                        bloodRoutineUrl = orderDetailsList.get(0).getRoutineBlood();
                        stvBloodRoutine.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(orderDetailsList.get(0).getEcg())) {
                        electrocardiogramUrl = orderDetailsList.get(0).getEcg();
                        stvElectrocardiogram.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(orderDetailsList.get(0).getCruor())) {
                        coagulationUrl = orderDetailsList.get(0).getCruor();
                        stvCoagulation.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(orderDetailsList.get(0).getContagion())) {
                        infectiousDiseaseIndexUrl = orderDetailsList.get(0).getContagion();
                        stvInfectiousDiseaseIndex.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    break;
                case "2":
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
                    if (!isEmpty(orderDetailsList.get(0).getDiabetes())) {
                        rbDiabetesStart.setChecked("2".equals(orderDetailsList.get(0).getDiabetes()));
                        rbDiabetesEnd.setChecked("1".equals(orderDetailsList.get(0).getDiabetes()));
                    }
                    if (!isEmpty(orderDetailsList.get(0).getCerebralInfarction())) {
                        rbBrainStalkStart.setChecked("2".equals(orderDetailsList.get(0).getCerebralInfarction()));
                        rbBrainStalkEnd.setChecked("1".equals(orderDetailsList.get(0).getCerebralInfarction()));
                    }
                    if (!isEmpty(orderDetailsList.get(0).getHeartDisease())) {
                        rbHeartDiseaseStart.setChecked("2".equals(orderDetailsList.get(0).getHeartDisease()));
                        rbHeartDiseaseEnd.setChecked("1".equals(orderDetailsList.get(0).getHeartDisease()));
                    }
                    if (!isEmpty(orderDetailsList.get(0).getInfectDisease())) {
                        rbInfectiousDiseaseStart.setChecked("2".equals(orderDetailsList.get(0).getInfectDisease()));
                        rbInfectiousDiseaseEnd.setChecked("1".equals(orderDetailsList.get(0).getInfectDisease()));
                    }
                    if (!isEmpty(orderDetailsList.get(0).getBreatheFunction())) {
                        rbRespiratoryDysfunctionStart.setChecked("2".equals(orderDetailsList.get(0).getBreatheFunction()));
                        rbRespiratoryDysfunctionEnd.setChecked("1".equals(orderDetailsList.get(0).getBreatheFunction()));
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
                case REQUEST_CODE_110:
                    orderDetailsList = data.getParcelableArrayListExtra("orderDetailsList");
                    break;
            }
        }
    }

    private String getOrderDetailsJson() {
        List<Map<String, String>> listMap = new ArrayList<>();
        for (OrderDetailsBean.DataBean.OrderDetailBean data : orderDetailsList) {
            if ("1".equals(orderDetails.getOrderType())) {
                Map<String, String> map = new HashMap<>();
                map.put("patientName", etPatientName.getText().toString().trim());
                map.put("sex", "男".equals(stvPatientSex.getRightString()) ? "1" : "2");
                map.put("age", stvPatientAge.getRightString().replaceAll("岁", "").trim());
                map.put("orderDetailId", data.getOrderDetailId() + "");
                if (!isEmpty(etPatientHeight.getText().toString().trim()))
                    map.put("height", etPatientHeight.getText().toString().trim());
                if (!isEmpty(etPatientBodyWeight.getText().toString().trim()))
                    map.put("weight", etPatientBodyWeight.getText().toString().trim());
                if (narcosisId > 0)
                    map.put("narcosisTypeId", narcosisId + "");
                if (!isEmpty(positiveUrl))
                    map.put("positiveCard", positiveUrl);
                if (!isEmpty(negativeUrl))
                    map.put("reverseCard", negativeUrl);
                if (!isEmpty(surgeryAboutMedicalRecordUrl))
                    map.put("surgeryRelated", surgeryAboutMedicalRecordUrl);
                if (!isEmpty(bloodRoutineUrl))
                    map.put("routineBlood", bloodRoutineUrl);
                if (!isEmpty(electrocardiogramUrl))
                    map.put("ecg", electrocardiogramUrl);
                if (!isEmpty(coagulationUrl))
                    map.put("cruor", coagulationUrl);
                if (!isEmpty(infectiousDiseaseIndexUrl))
                    map.put("contagion", infectiousDiseaseIndexUrl);
//            if (data.getMinBloodPressure() > 0)
//                map1.put("minBloodPressure", data.getMinBloodPressure() + "");
//            if (data.getMaxBloodPressure() > 0)
//                map1.put("maxBloodPressure", data.getMaxBloodPressure() + "");
//            if (data.getPulse() > 0)
//                map1.put("pulse", data.getPulse() + "");
//            if (data.getBreathe() > 0)
//                map1.put("breathe", data.getBreathe() + "");
//            if (data.getAnimalHeat() > 0)
//                map1.put("animalHeat", data.getAnimalHeat() + "");
//            if (!isEmpty(data.getDiabetes()))
//                map1.put("diabetes", data.getDiabetes());
//            if (!isEmpty(data.getCerebralInfarction()))
//                map1.put("cerebralInfarction", data.getCerebralInfarction());
//            if (!isEmpty(data.getHeartDisease()))
//                map1.put("heartDisease", data.getHeartDisease());
//            if (!isEmpty(data.getInfectDisease()))
//                map1.put("infectDisease", data.getInfectDisease());
//            if (!isEmpty(data.getBreatheFunction()))
//                map1.put("breatheFunction", data.getBreatheFunction());
                listMap.add(map);
            } else {
                Map<String, String> map = new HashMap<>();
                map.put("patientName", data.getPatientName());
                map.put("sex", data.getSex());
                map.put("age", data.getAge() + "");
                map.put("orderDetailId", data.getOrderDetailId() + "");
                if (data.getHeight() > 0)
                    map.put("height", data.getHeight() + "");
                if (data.getWeight() > 0)
                    map.put("weight", data.getWeight() + "");
                if (data.getNarcosisTypeId() > 0)
                    map.put("narcosisTypeId", data.getNarcosisTypeId() + "");
                if (!isEmpty(data.getPositiveCard()))
                    map.put("positiveCard", data.getPositiveCard());
                if (!isEmpty(data.getReverseCard()))
                    map.put("reverseCard", data.getReverseCard());
                if (!isEmpty(data.getSurgeryRelated()))
                    map.put("surgeryRelated", data.getSurgeryRelated());
                if (!isEmpty(data.getRoutineBlood()))
                    map.put("routineBlood", data.getRoutineBlood());
                if (!isEmpty(data.getEcg()))
                    map.put("ecg", data.getEcg());
                if (!isEmpty(data.getCruor()))
                    map.put("cruor", data.getCruor());
                if (!isEmpty(data.getContagion()))
                    map.put("contagion", data.getContagion());
//            if (data.getMinBloodPressure() > 0)
//                map1.put("minBloodPressure", data.getMinBloodPressure() + "");
//            if (data.getMaxBloodPressure() > 0)
//                map1.put("maxBloodPressure", data.getMaxBloodPressure() + "");
//            if (data.getPulse() > 0)
//                map1.put("pulse", data.getPulse() + "");
//            if (data.getBreathe() > 0)
//                map1.put("breathe", data.getBreathe() + "");
//            if (data.getAnimalHeat() > 0)
//                map1.put("animalHeat", data.getAnimalHeat() + "");
//            if (!isEmpty(data.getDiabetes()))
//                map1.put("diabetes", data.getDiabetes());
//            if (!isEmpty(data.getCerebralInfarction()))
//                map1.put("cerebralInfarction", data.getCerebralInfarction());
//            if (!isEmpty(data.getHeartDisease()))
//                map1.put("heartDisease", data.getHeartDisease());
//            if (!isEmpty(data.getInfectDisease()))
//                map1.put("infectDisease", data.getInfectDisease());
//            if (!isEmpty(data.getBreatheFunction()))
//                map1.put("breatheFunction", data.getBreatheFunction());
                listMap.add(map);
            }
        }
        if (listMap.size() <= 0)
            return "";
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        return jsonString;
    }

    @OnClick({R.id.bt_confirm, R.id.stv_patient_sex, R.id.stv_patient_age, R.id.stv_anesthesia_type, R.id.stv_id_card, R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm:
                if (isFastClick()) {
                    TreeMap<String, String> modifyMedicalMap = new TreeMap<>();
                    modifyMedicalMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                    modifyMedicalMap.put("orderId", orderDetails.getOrderId() + "");
                    modifyMedicalMap.put("orderDetails", getOrderDetailsJson());
                    modifyMedicalMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyMedicalMap.toString().replaceAll(" ", "") + SIGN)));
                    getPresenter().getModifyMedical(modifyMedicalMap, false, false);
                }
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

    @Override
    public void resultModifyMedical(ModifyMedicalBean data) {
        switch (data.getCode()) {
            case 200:
                setResult(RESULT_OK, new Intent().putExtra("refresh", 1));
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
