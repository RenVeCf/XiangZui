package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.ModifyOrderBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.SelectFeeBean;
import com.ipd.xiangzui.bean.SendOrderBean;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.SelectFeeContract;
import com.ipd.xiangzui.presenter.SelectFeePresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.L;
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

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：发单-费用信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/9.
 */
public class SendOrderFeeInfoActivity extends BaseActivity<SelectFeeContract.View, SelectFeeContract.Presenter> implements SelectFeeContract.View {

    @BindView(R.id.tv_send_order_fee_info)
    TopView tvSendOrderFeeInfo;
    @BindView(R.id.stv_surgery)
    SuperTextView stvSurgery;
    @BindView(R.id.et_add_fee)
    EditText etAddFee;
    @BindView(R.id.stv_surgery_num)
    SuperTextView stvSurgeryNum;
    @BindView(R.id.tv_add_fee)
    TextView tvAddFee;

    private SendOrderDataBean sendOrderData;
    private int sendOrderType; //1: 单台, 2: 连台
    private static int EDIT_OK = 10010;
    private double addFee = 0;
    private OrderDetailsBean.DataBean.OrderBean orderDetails;
    private List<OrderDetailsBean.DataBean.OrderDetailBean> orderDetailsList;
    private boolean isAddFee = false; //是否加急

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_fee_info;
    }

    @Override
    public SelectFeeContract.Presenter createPresenter() {
        return new SelectFeePresenter(this);
    }

    @Override
    public SelectFeeContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSendOrderFeeInfo);

        sendOrderData = getIntent().getParcelableExtra("sendOrderData");
        if (sendOrderData != null) {
            sendOrderType = sendOrderData.getSendOrderType();
            if (sendOrderType == 1)
                stvSurgeryNum.setVisibility(View.GONE);
        }

        orderDetails = getIntent().getParcelableExtra("orderDetails");
        orderDetailsList = getIntent().getParcelableArrayListExtra("orderDetailsList");
        if (orderDetails != null && orderDetailsList.size() > 0) {
            sendOrderType = Integer.parseInt(orderDetails.getOrderType());
            if (sendOrderType == 1)
                stvSurgeryNum.setVisibility(View.GONE);
            etAddFee.setText(orderDetails.getAhMoney() + "");
            if (!isEmpty(etAddFee.getText().toString().trim())) {
                double i = Double.parseDouble(etAddFee.getText().toString().trim()) + Double.parseDouble(etAddFee.getText().toString().trim()) * 0.15;
                if (isAddFee)
                    i += addFee;
                tvAddFee.setText("¥ " + i + "元");
            }
            etAddFee.setFocusable(false);
            stvSurgery.setEnabled(false);
        }
    }

    @Override
    public void initData() {
        TreeMap<String, String> selectFeeMap = new TreeMap<>();
        selectFeeMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        selectFeeMap.put("type", "1");
        selectFeeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(selectFeeMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getSelectFee(selectFeeMap, false, false);

        if (orderDetails != null && orderDetailsList.size() > 0)
            stvSurgeryNum.setRightString(orderDetailsList.size() + "台");
        else if (sendOrderType == 2 && sendOrderData.getTwoOrderBean().size() > 0)
            stvSurgeryNum.setRightString(sendOrderData.getTwoOrderBean().size() + "台");
    }

    @Override
    public void initListener() {
        etAddFee.addTextChangedListener(new TextWatcher() {
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
            if (EDIT_OK == msg.what && !isEmpty(etAddFee.getText().toString().trim())) {
                double i = Double.parseDouble(etAddFee.getText().toString().trim()) + Double.parseDouble(etAddFee.getText().toString().trim()) * 0.15;
                if (isAddFee)
                    i += addFee;
                tvAddFee.setText("¥ " + i + "元");
            }
        }
    };

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(EDIT_OK);
        }
    };

    private String getOrderDetailsJson(int type) {
        List<Map<String, String>> listMap = new ArrayList<>();
        switch (type) {
            case 1:
                Map<String, String> map = new HashMap<>();
                map.put("patientName", sendOrderData.getOneOrderBean().getPatientName());
                map.put("sex", "男".equals(sendOrderData.getOneOrderBean().getPatientSex()) ? "1" : "2");
                map.put("age", sendOrderData.getOneOrderBean().getPatientAge());
                if (!isEmpty(sendOrderData.getOneOrderBean().getPatientHeight()))
                    map.put("height", sendOrderData.getOneOrderBean().getPatientHeight());
                if (!isEmpty(sendOrderData.getOneOrderBean().getPatientBodyWeight()))
                    map.put("weight", sendOrderData.getOneOrderBean().getPatientBodyWeight());
                if (!isEmpty(sendOrderData.getOneOrderBean().getNarcosisId()))
                    map.put("narcosisTypeId", sendOrderData.getOneOrderBean().getNarcosisId());
                if (!isEmpty(sendOrderData.getOneOrderBean().getPositiveUrl()))
                    map.put("positiveCard", sendOrderData.getOneOrderBean().getPositiveUrl());
                if (!isEmpty(sendOrderData.getOneOrderBean().getNegativeUrl()))
                    map.put("reverseCard", sendOrderData.getOneOrderBean().getNegativeUrl());
                if (!isEmpty(sendOrderData.getOneOrderBean().getInsuranceConsentUrl()))
                    map.put("insurance", sendOrderData.getOneOrderBean().getInsuranceConsentUrl());
                if (!isEmpty(sendOrderData.getOneOrderBean().getMedicalRecords()))
                    map.put("medicalRecords", sendOrderData.getOneOrderBean().getMedicalRecords());
                if (!isEmpty(sendOrderData.getOneOrderBean().getSurgeryRelated()))
                    map.put("surgeryRelated", sendOrderData.getOneOrderBean().getSurgeryRelated());
                if (!isEmpty(sendOrderData.getOneOrderBean().getRoutineBlood()))
                    map.put("routineBlood", sendOrderData.getOneOrderBean().getRoutineBlood());
                if (!isEmpty(sendOrderData.getOneOrderBean().getEcg()))
                    map.put("ecg", sendOrderData.getOneOrderBean().getEcg());
                if (!isEmpty(sendOrderData.getOneOrderBean().getCruor()))
                    map.put("cruor", sendOrderData.getOneOrderBean().getCruor());
                if (!isEmpty(sendOrderData.getOneOrderBean().getContagion()))
                    map.put("contagion", sendOrderData.getOneOrderBean().getContagion());
                if (!isEmpty(sendOrderData.getOneOrderBean().getMinBloodPressure()))
                    map.put("minBloodPressure", sendOrderData.getOneOrderBean().getMinBloodPressure());
                if (!isEmpty(sendOrderData.getOneOrderBean().getMaxBloodPressure()))
                    map.put("maxBloodPressure", sendOrderData.getOneOrderBean().getMaxBloodPressure());
                if (!isEmpty(sendOrderData.getOneOrderBean().getPulse()))
                    map.put("pulse", sendOrderData.getOneOrderBean().getPulse());
                if (!isEmpty(sendOrderData.getOneOrderBean().getBreathe()))
                    map.put("breathe", sendOrderData.getOneOrderBean().getBreathe());
                if (!isEmpty(sendOrderData.getOneOrderBean().getAnimalHeat()))
                    map.put("animalHeat", sendOrderData.getOneOrderBean().getAnimalHeat());
                if (!isEmpty(sendOrderData.getOneOrderBean().getDiabetes()))
                    map.put("diabetes", sendOrderData.getOneOrderBean().getDiabetes());
                if (!isEmpty(sendOrderData.getOneOrderBean().getCerebralInfarction()))
                    map.put("cerebralInfarction", sendOrderData.getOneOrderBean().getCerebralInfarction());
                if (!isEmpty(sendOrderData.getOneOrderBean().getHeartDisease()))
                    map.put("heartDisease", sendOrderData.getOneOrderBean().getHeartDisease());
                if (!isEmpty(sendOrderData.getOneOrderBean().getInfectDisease()))
                    map.put("infectDisease", sendOrderData.getOneOrderBean().getInfectDisease());
                if (!isEmpty(sendOrderData.getOneOrderBean().getBreatheFunction()))
                    map.put("breatheFunction", sendOrderData.getOneOrderBean().getBreatheFunction());
                listMap.add(map);
                break;
            case 2:
                for (SendOrderDataBean.TwoOrderBean data : sendOrderData.getTwoOrderBean()) {
                    Map<String, String> map1 = new HashMap<>();
                    map1.put("surgeryName", data.getSurgicalName());
                    map1.put("patientName", data.getPatientName());
                    map1.put("sex", "男".equals(data.getPatientSex()) ? "1" : "2");
                    map1.put("age", data.getPatientAge());
                    if (!isEmpty(data.getPatientHeight()))
                        map1.put("height", data.getPatientHeight());
                    if (!isEmpty(data.getPatientBodyWeight()))
                        map1.put("weight", data.getPatientBodyWeight());
                    if (!isEmpty(data.getNarcosisId()))
                        map1.put("narcosisTypeId", data.getNarcosisId());
                    if (!isEmpty(data.getPositiveUrl()))
                        map1.put("positiveCard", data.getPositiveUrl());
                    if (!isEmpty(data.getNegativeUrl()))
                        map1.put("reverseCard", data.getNegativeUrl());
                    if (!isEmpty(data.getInsuranceConsentUrl()))
                        map1.put("insurance", data.getInsuranceConsentUrl());
                    if (!isEmpty(data.getMedicalRecords()))
                        map1.put("medicalRecords", data.getMedicalRecords());
                    if (!isEmpty(data.getSurgeryRelated()))
                        map1.put("surgeryRelated", data.getSurgeryRelated());
                    if (!isEmpty(data.getRoutineBlood()))
                        map1.put("routineBlood", data.getRoutineBlood());
                    if (!isEmpty(data.getEcg()))
                        map1.put("ecg", data.getEcg());
                    if (!isEmpty(data.getCruor()))
                        map1.put("cruor", data.getCruor());
                    if (!isEmpty(data.getContagion()))
                        map1.put("contagion", data.getContagion());
                    if (!isEmpty(data.getMinBloodPressure()))
                        map1.put("minBloodPressure", data.getMinBloodPressure());
                    if (!isEmpty(data.getMaxBloodPressure()))
                        map1.put("maxBloodPressure", data.getMaxBloodPressure());
                    if (!isEmpty(data.getPulse()))
                        map1.put("pulse", data.getPulse());
                    if (!isEmpty(data.getBreathe()))
                        map1.put("breathe", data.getBreathe());
                    if (!isEmpty(data.getAnimalHeat()))
                        map1.put("animalHeat", data.getAnimalHeat());
                    if (!isEmpty(data.getDiabetes()))
                        map1.put("diabetes", data.getDiabetes());
                    if (!isEmpty(data.getCerebralInfarction()))
                        map1.put("cerebralInfarction", data.getCerebralInfarction());
                    if (!isEmpty(data.getHeartDisease()))
                        map1.put("heartDisease", data.getHeartDisease());
                    if (!isEmpty(data.getInfectDisease()))
                        map1.put("infectDisease", data.getInfectDisease());
                    if (!isEmpty(data.getBreatheFunction()))
                        map1.put("breatheFunction", data.getBreatheFunction());
                    listMap.add(map1);
                }
                break;
            case 3:
                for (OrderDetailsBean.DataBean.OrderDetailBean data : orderDetailsList) {
                    Map<String, String> map1 = new HashMap<>();
                    if (sendOrderType == 2)
                        map1.put("surgeryName", data.getSurgeryName());
                    map1.put("patientName", data.getPatientName());
                    if (data.getOrderDetailId() > 0)
                        map1.put("orderDetailId", data.getOrderDetailId() + "");
                    map1.put("sex", "男".equals(data.getSex()) ? "1" : "2");
                    map1.put("age", data.getAge() + "");
                    if (data.getHeight() > 0)
                        map1.put("height", data.getHeight() + "");
                    if (data.getWeight() > 0)
                        map1.put("weight", data.getWeight() + "");
                    if (data.getNarcosisTypeId() > 0)
                        map1.put("narcosisTypeId", data.getNarcosisTypeId() + "");
                    if (!isEmpty(data.getPositiveCard()))
                        map1.put("positiveCard", data.getPositiveCard());
                    if (!isEmpty(data.getReverseCard()))
                        map1.put("reverseCard", data.getReverseCard());
                    if (!isEmpty(data.getInsurance()))
                        map1.put("insurance", data.getInsurance());
                    if (!isEmpty(data.getMedicalRecords()))
                        map1.put("medicalRecords", data.getMedicalRecords());
                    if (!isEmpty(data.getSurgeryRelated()))
                        map1.put("surgeryRelated", data.getSurgeryRelated());
                    if (!isEmpty(data.getRoutineBlood()))
                        map1.put("routineBlood", data.getRoutineBlood());
                    if (!isEmpty(data.getEcg()))
                        map1.put("ecg", data.getEcg());
                    if (!isEmpty(data.getCruor()))
                        map1.put("cruor", data.getCruor());
                    if (!isEmpty(data.getContagion()))
                        map1.put("contagion", data.getContagion());
                    if (data.getMinBloodPressure() > 0)
                        map1.put("minBloodPressure", data.getMinBloodPressure() + "");
                    if (data.getMaxBloodPressure() > 0)
                        map1.put("maxBloodPressure", data.getMaxBloodPressure() + "");
                    if (data.getPulse() > 0)
                        map1.put("pulse", data.getPulse() + "");
                    if (data.getBreathe() > 0)
                        map1.put("breathe", data.getBreathe() + "");
                    if (data.getAnimalHeat() > 0)
                        map1.put("animalHeat", data.getAnimalHeat() + "");
                    if (!isEmpty(data.getDiabetes()))
                        map1.put("diabetes", data.getDiabetes());
                    if (!isEmpty(data.getCerebralInfarction()))
                        map1.put("cerebralInfarction", data.getCerebralInfarction());
                    if (!isEmpty(data.getHeartDisease()))
                        map1.put("heartDisease", data.getHeartDisease());
                    if (!isEmpty(data.getInfectDisease()))
                        map1.put("infectDisease", data.getInfectDisease());
                    if (!isEmpty(data.getBreatheFunction()))
                        map1.put("breatheFunction", data.getBreatheFunction());
                    listMap.add(map1);
                }
                break;
        }
        if (listMap.size() <= 0)
            return "";
        Gson g = new Gson();
        String jsonString = g.toJson(listMap);
        return jsonString;
    }

    @OnClick({R.id.sb_send_order, R.id.stv_surgery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_surgery:
                if (!isAddFee)
                    stvSurgery.setRightIcon(getResources().getDrawable(R.drawable.ic_check_blue));
                else
                    stvSurgery.setRightIcon(getResources().getDrawable(R.drawable.ic_check_gray));
                if (!isEmpty(etAddFee.getText().toString().trim()) && Double.parseDouble(etAddFee.getText().toString().trim()) >= 3500) {
                    if (!isAddFee) {
                        double i = Double.parseDouble(etAddFee.getText().toString().trim()) + Double.parseDouble(etAddFee.getText().toString().trim()) * 0.15;
                        i += addFee;
                        tvAddFee.setText("¥ " + i + "元");
                        isAddFee = true;
                    } else {
                        double i = Double.parseDouble(etAddFee.getText().toString().trim()) + Double.parseDouble(etAddFee.getText().toString().trim()) * 0.15;
                        tvAddFee.setText("¥ " + i + "元");
                        isAddFee = false;
                    }
                } else
                    ToastUtil.showShortToast("预计费用需大于等于3500元");
                break;
            case R.id.sb_send_order:
                if (Double.parseDouble(etAddFee.getText().toString().trim()) >= 3500) {
                    if (orderDetails != null && orderDetailsList.size() > 0) {
                        TreeMap<String, String> modifyOrderMap = new TreeMap<>();
                        modifyOrderMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                        modifyOrderMap.put("orderId", orderDetails.getOrderId() + "");
                        L.i("orderType = "+ sendOrderType);
                        modifyOrderMap.put("orderType", sendOrderType + "");
                        if (sendOrderType == 1)
                            modifyOrderMap.put("surgeryName", orderDetails.getSurgeryName());
                        L.i("hospitalName = "+ orderDetails.getHospitalName());
                        modifyOrderMap.put("hospitalName", orderDetails.getHospitalName());
                        L.i("prov = "+ orderDetails.getProv());
                        modifyOrderMap.put("prov", orderDetails.getProv());
                        L.i("city = "+ orderDetails.getCity());
                        modifyOrderMap.put("city", orderDetails.getCity());
                        L.i("dist = "+ orderDetails.getDist());
                        modifyOrderMap.put("dist", orderDetails.getDist());
                        L.i("address = "+ orderDetails.getAddress());
                        modifyOrderMap.put("address", orderDetails.getAddress());
                        L.i("beginTime = "+ orderDetails.getBeginTime());
                        modifyOrderMap.put("beginTime", orderDetails.getBeginTime());
                        L.i("duration = "+ orderDetails.getDuration());
                        modifyOrderMap.put("duration", orderDetails.getDuration() + "");
                        modifyOrderMap.put("urgent", isAddFee ? "2" : "1");
                        L.i("orderDetails = "+ getOrderDetailsJson(3));
                        modifyOrderMap.put("orderDetails", getOrderDetailsJson(3));
                        modifyOrderMap.put("expectMoney", etAddFee.getText().toString().trim());
                        if (sendOrderType == 2)
                            modifyOrderMap.put("evenNum", stvSurgeryNum.getRightString().replaceAll("台", ""));
                        modifyOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyOrderMap.toString().replaceAll(" ", "") + SIGN)));
                        getPresenter().getModifyOrder(modifyOrderMap, true, false);
                    } else {
                        switch (sendOrderType) {
                            case 1:
                                TreeMap<String, String> sendOrderMap = new TreeMap<>();
                                sendOrderMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                                sendOrderMap.put("orderType", sendOrderType + "");
                                sendOrderMap.put("surgeryName", sendOrderData.getOneOrderBean().getSurgicalName());
                                sendOrderMap.put("hospitalName", sendOrderData.getOneOrderBean().getHospitalName());
                                sendOrderMap.put("prov", sendOrderData.getOneOrderBean().getProv());
                                sendOrderMap.put("city", sendOrderData.getOneOrderBean().getCity());
                                sendOrderMap.put("dist", sendOrderData.getOneOrderBean().getDist());
                                sendOrderMap.put("address", sendOrderData.getOneOrderBean().getAddress());
                                sendOrderMap.put("beginTime", sendOrderData.getOneOrderBean().getSurgicalTime());
                                sendOrderMap.put("duration", sendOrderData.getOneOrderBean().getSurgicalDuration());
                                sendOrderMap.put("urgent", isAddFee ? "2" : "1");
                                sendOrderMap.put("orderDetails", getOrderDetailsJson(1));
                                sendOrderMap.put("expectMoney", etAddFee.getText().toString().trim());
                                sendOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(sendOrderMap.toString().replaceAll(" ", "") + SIGN)));
                                getPresenter().getSendOrder(sendOrderMap, true, false);
                                break;
                            case 2:
                                TreeMap<String, String> sendOrderMap1 = new TreeMap<>();
                                sendOrderMap1.put("userId", SPUtil.get(this, USER_ID, "") + "");
                                sendOrderMap1.put("orderType", sendOrderType + "");
                                L.i("hospitalName", sendOrderData.getTwoOrderBean().get(0).getHospitalName());
                                sendOrderMap1.put("hospitalName", sendOrderData.getTwoOrderBean().get(0).getHospitalName());
                                L.i("prov", sendOrderData.getTwoOrderBean().get(0).getProv());
                                sendOrderMap1.put("prov", sendOrderData.getTwoOrderBean().get(0).getProv());
                                L.i("city", sendOrderData.getTwoOrderBean().get(0).getCity());
                                sendOrderMap1.put("city", sendOrderData.getTwoOrderBean().get(0).getCity());
                                L.i("dist", sendOrderData.getTwoOrderBean().get(0).getDist());
                                sendOrderMap1.put("dist", sendOrderData.getTwoOrderBean().get(0).getDist());
                                L.i("address", sendOrderData.getTwoOrderBean().get(0).getAddress());
                                sendOrderMap1.put("address", sendOrderData.getTwoOrderBean().get(0).getAddress());
                                L.i("beginTime", sendOrderData.getTwoOrderBean().get(0).getSurgicalTime());
                                sendOrderMap1.put("beginTime", sendOrderData.getTwoOrderBean().get(0).getSurgicalTime());
                                L.i("duration", sendOrderData.getTwoOrderBean().get(0).getSurgicalDuration());
                                sendOrderMap1.put("duration", sendOrderData.getTwoOrderBean().get(0).getSurgicalDuration());
                                sendOrderMap1.put("urgent", isAddFee ? "2" : "1");
                                L.i("orderDetails", getOrderDetailsJson(2));
                                sendOrderMap1.put("orderDetails", getOrderDetailsJson(2));
                                sendOrderMap1.put("expectMoney", etAddFee.getText().toString().trim());
                                sendOrderMap1.put("evenNum", stvSurgeryNum.getRightString().replaceAll("台", ""));
                                sendOrderMap1.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(sendOrderMap1.toString().replaceAll(" ", "") + SIGN)));
                                getPresenter().getSendOrder(sendOrderMap1, true, false);
                                break;
                        }
                    }
                } else
                    ToastUtil.showShortToast("预计费用需大于等于3500元");
                break;
        }
    }

    @Override
    public void resultSelectFee(SelectFeeBean data) {
        switch (data.getCode()) {
            case 200:
                addFee = Double.parseDouble(data.getData().getUrgent());
                stvSurgery.setCenterString("(加急费: ¥" + data.getData().getUrgent() + "元)");
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultSendOrder(SendOrderBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public void resultModifyOrder(ModifyOrderBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case 900:
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
