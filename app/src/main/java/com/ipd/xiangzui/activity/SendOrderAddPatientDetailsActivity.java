package com.ipd.xiangzui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.NarcosisListBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.SendOrderDataBean;
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
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_97;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_98;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：发单-添加患者信息详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/11.
 */
public class SendOrderAddPatientDetailsActivity extends BaseActivity<NarcosisListContract.View, NarcosisListContract.Presenter> implements NarcosisListContract.View {

    @BindView(R.id.tv_send_order_add_patient_details)
    TopView tvSendOrderAddPatientDetails;
    @BindView(R.id.et_surgical_name)
    EditText etSurgicalName;
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

    private List<String> listData;
    private List<String> narcosisDataList = new ArrayList<>();//麻醉方式
    private List<NarcosisListBean.DataBean.NarcosisListsBean> narcosisLists = new ArrayList<>();//选择麻醉(取ID用)
    private int narcosisId = 0; //麻醉ID
    private OptionsPickerView pvOptions; //条件选择器
    private int selectRb; //1:图片上传，2：填写上传，3：暂无
    private SendOrderDataBean.TwoOrderBean sendOrderData;
    private OrderDetailsBean.DataBean.OrderDetailBean orderDetailsTwo;
    private static int EDIT_OK = 10011;
    private String positiveUrl = "", negativeUrl = "", insuranceConsentUrl = "", surgeryAboutMedicalRecordUrl = "", bloodRoutineUrl = "", electrocardiogramUrl, coagulationUrl, infectiousDiseaseIndexUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_add_patient_details;
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
        ImmersionBar.setTitleBar(this, tvSendOrderAddPatientDetails);

        selectRb = getIntent().getIntExtra("selectRb", 0);
        sendOrderData = getIntent().getParcelableExtra("sendOrderData");
        orderDetailsTwo = getIntent().getParcelableExtra("orderDetailsTwo");
    }

    @Override
    public void initData() {
        TreeMap<String, String> narcosisListMap = new TreeMap<>();
        narcosisListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        narcosisListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(narcosisListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getNarcosisList(narcosisListMap, false, false);

        if (orderDetailsTwo != null) {
            etSurgicalName.setText(orderDetailsTwo.getSurgeryName());
            etPatientName.setText(orderDetailsTwo.getPatientName());
            stvPatientSex.setRightString("1".equals(orderDetailsTwo.getSex()) ? "男" : "女");
            stvPatientAge.setRightString(orderDetailsTwo.getAge() + "岁");
            etPatientHeight.setText(orderDetailsTwo.getHeight() + "");
            etPatientBodyWeight.setText(orderDetailsTwo.getWeight() + "");
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
        } else {
            etSurgicalName.setText(sendOrderData.getSurgicalName());
            etPatientName.setText(sendOrderData.getPatientName());
            stvPatientSex.setRightString("1".equals(sendOrderData.getPatientSex()) ? "男" : "女");
            stvPatientAge.setRightString(sendOrderData.getPatientAge() + "岁");
            etPatientHeight.setText(sendOrderData.getPatientHeight() + "");
            etPatientBodyWeight.setText(sendOrderData.getPatientBodyWeight() + "");
            stvAnesthesiaType.setRightString(sendOrderData.getNarcosisId());
            if (!isEmpty(sendOrderData.getPositiveUrl()) && !isEmpty(sendOrderData.getNegativeUrl())) {
                positiveUrl = sendOrderData.getPositiveUrl();
                negativeUrl = sendOrderData.getNegativeUrl();
                stvIdCard.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }
            if (!isEmpty(sendOrderData.getInsuranceConsentUrl())) {
                insuranceConsentUrl = sendOrderData.getInsuranceConsentUrl();
                stvInsuranceConsent.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }
            if (!isEmpty(sendOrderData.getSurgeryRelated())) {
                surgeryAboutMedicalRecordUrl = sendOrderData.getSurgeryRelated();
                stvSurgeryAboutMedicalRecord.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }
            if (!isEmpty(sendOrderData.getRoutineBlood())) {
                bloodRoutineUrl = sendOrderData.getRoutineBlood();
                stvBloodRoutine.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }
            if (!isEmpty(sendOrderData.getEcg())) {
                electrocardiogramUrl = sendOrderData.getEcg();
                stvElectrocardiogram.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }
            if (!isEmpty(sendOrderData.getCruor())) {
                coagulationUrl = sendOrderData.getCruor();
                stvCoagulation.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }
            if (!isEmpty(sendOrderData.getContagion())) {
                infectiousDiseaseIndexUrl = sendOrderData.getContagion();
                stvInfectiousDiseaseIndex.setRightString("已上传")
                        .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
            }
        }
    }

    @Override
    public void initListener() {
        etSurgicalName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            //输入时的调用
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mHandler.removeCallbacks(mRunnable);
                //800毫秒没有输入认为输入完毕
                mHandler.postDelayed(mRunnable, 800);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (EDIT_OK == msg.what) {
                if (orderDetailsTwo != null) {
                    orderDetailsTwo.setSurgeryName(etSurgicalName.getText().toString().trim());
                    orderDetailsTwo.setPatientName(etPatientName.getText().toString().trim());
                    orderDetailsTwo.setHeight(Double.parseDouble(etPatientHeight.getText().toString().trim()));
                    orderDetailsTwo.setWeight(Double.parseDouble(etPatientBodyWeight.getText().toString().trim()));
                } else {
                    sendOrderData.setSurgicalName(etSurgicalName.getText().toString().trim());
                    sendOrderData.setPatientName(etPatientName.getText().toString().trim());
                    sendOrderData.setPatientHeight(etPatientHeight.getText().toString().trim());
                    sendOrderData.setPatientBodyWeight(etPatientBodyWeight.getText().toString().trim());
                }
            }
        }
    };

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(EDIT_OK);
        }
    };

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
                        stvPatientSex.setRightString(listData.get(options1));
                        stvPatientSex.setRightTextColor(getResources().getColor(R.color.black));

                        if (orderDetailsTwo != null) {
                            orderDetailsTwo.setSex("1".equals(stvPatientSex.getRightString()) ? "男" : "女");
                        } else {
                            sendOrderData.setPatientSex("1".equals(stvPatientSex.getRightString()) ? "男" : "女");
                        }
                        break;
                    case 2:
                        stvPatientAge.setRightString(listData.get(options1));
                        stvPatientAge.setRightTextColor(getResources().getColor(R.color.black));

                        if (orderDetailsTwo != null) {
                            orderDetailsTwo.setAge(Integer.parseInt(stvPatientAge.getRightString().replaceAll("岁", "")));
                        } else {
                            sendOrderData.setPatientAge(stvPatientAge.getRightString().replaceAll("岁", ""));
                        }
                        break;
                    case 3:
                        for (int i = 0; i < narcosisLists.size(); i++) {
                            if (narcosisLists.get(i).getNarcosisTypeName().equals(listData.get(options1)))
                                narcosisId = narcosisLists.get(i).getNarcosisTypeId();
                        }
                        stvAnesthesiaType.setRightString(listData.get(options1));
                        stvAnesthesiaType.setRightTextColor(getResources().getColor(R.color.black));

                        if (orderDetailsTwo != null) {
                            orderDetailsTwo.setNarcosisTypeId(narcosisId);
                        } else {
                            sendOrderData.setNarcosisId(narcosisId + "");
                        }
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
                .setDecorView((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content))
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
                case REQUEST_CODE_97:
                    positiveUrl = data.getStringExtra("positiveUrl");
                    negativeUrl = data.getStringExtra("negativeUrl");
                    stvIdCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_98:
                    insuranceConsentUrl = data.getStringExtra("imgUrl");
                    stvInsuranceConsent.setRightString("已上传")
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

    @OnClick({R.id.stv_patient_sex, R.id.stv_patient_age, R.id.stv_anesthesia_type, R.id.stv_id_card, R.id.stv_insurance_consent, R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index})
    public void onViewClicked(View view) {
        switch (view.getId()) {
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
                startActivityForResult(new Intent(this, AgentCardActivity.class), REQUEST_CODE_97);
                break;
            case R.id.stv_insurance_consent:
                startActivityForResult(new Intent(this, HeadActivity.class).putExtra("title", "保险同意书"), REQUEST_CODE_98);
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
        }
    }

    @Override
    public void resultNarcosisList(NarcosisListBean data) {
        switch (data.getCode()) {
            case 200:
                narcosisLists.clear();
                narcosisLists.addAll(data.getData().getNarcosisList());
                for (NarcosisListBean.DataBean.NarcosisListsBean datas : data.getData().getNarcosisList()) {
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
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
