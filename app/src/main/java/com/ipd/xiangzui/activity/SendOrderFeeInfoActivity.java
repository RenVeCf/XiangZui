package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.SelectFeeBean;
import com.ipd.xiangzui.bean.SendOrderBean;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.SelectFeeContract;
import com.ipd.xiangzui.presenter.SelectFeePresenter;
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

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;

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
        sendOrderType = sendOrderData.getSendOrderType();
        if (sendOrderType == 1)
            stvSurgeryNum.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        TreeMap<String, String> selectFeeMap = new TreeMap<>();
        selectFeeMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        selectFeeMap.put("type", "1");
        selectFeeMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(selectFeeMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getSelectFee(selectFeeMap, false, false);

        if (sendOrderData.getTwoOrderBean().size() > 0)
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
            if (EDIT_OK == msg.what) {
                double i = Double.parseDouble(etAddFee.getText().toString().trim()) + Double.parseDouble(etAddFee.getText().toString().trim()) * 0.15;
                if (stvSurgery.getCheckBoxIsChecked())
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

    private String getOrderDetailsJson(int type){
        List<Map<String, String>> listMap = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        switch (type) {
            case 1:
                map.put("patientName", sendOrderData.getOneOrderBean().getPatientName());
                map.put("sex", sendOrderData.getOneOrderBean().getPatientSex());
                map.put("age", sendOrderData.getOneOrderBean().getPatientAge());
                map.put("height", sendOrderData.getOneOrderBean().getPatientHeight());
                map.put("weight", sendOrderData.getOneOrderBean().getPatientBodyWeight());
                map.put("narcosisTypeId", sendOrderData.getOneOrderBean().getNarcosisId());
                map.put("positiveCard", sendOrderData.getOneOrderBean().getPositiveUrl());
                map.put("reverseCard", sendOrderData.getOneOrderBean().getNegativeUrl());
                map.put("insurance", sendOrderData.getOneOrderBean().getInsuranceConsentUrl());
                map.put("medicalRecords", sendOrderData.getOneOrderBean().getMedicalRecords());
                map.put("surgeryRelated", sendOrderData.getOneOrderBean().getSurgeryRelated());
                map.put("routineBlood", sendOrderData.getOneOrderBean().getRoutineBlood());
                map.put("ecg", sendOrderData.getOneOrderBean().getEcg());
                map.put("cruor", sendOrderData.getOneOrderBean().getCruor());
                map.put("contagion", sendOrderData.getOneOrderBean().getContagion());
                map.put("minBloodPressure", sendOrderData.getOneOrderBean().getMinBloodPressure());
                map.put("maxBloodPressure", sendOrderData.getOneOrderBean().getMaxBloodPressure());
                map.put("pulse", sendOrderData.getOneOrderBean().getPulse());
                map.put("breathe", sendOrderData.getOneOrderBean().getBreathe());
                map.put("animalHeat", sendOrderData.getOneOrderBean().getAnimalHeat());
                map.put("diabetes", sendOrderData.getOneOrderBean().getDiabetes());
                map.put("cerebralInfarction", sendOrderData.getOneOrderBean().getCerebralInfarction());
                map.put("heartDisease", sendOrderData.getOneOrderBean().getHeartDisease());
                map.put("infectDisease", sendOrderData.getOneOrderBean().getInfectDisease());
                map.put("breatheFunction", sendOrderData.getOneOrderBean().getBreatheFunction());
                break;
            case 2:
                String patientName;
                for (SendOrderDataBean.TwoOrderBean data : sendOrderData.getTwoOrderBean()) {
                    patientName += data.getPatientName() + ",";
                }
                map.put("patientName", "");
                map.put("sex", appVehicleStatusBean.getStatusName() + "");
                map.put("age", appVehicleStatusBean.getStatus() + ""); // 1:正常  2：破损
                map.put("height", appVehicleStatusBean.getDamagedCost() + "");
                map.put("weight", appVehicleStatusBean.getParentId() + "");
                map.put("narcosisTypeId", appVehicleStatusBean.getParentId() + "");
                map.put("positiveCard", appVehicleStatusBean.getParentId() + "");
                map.put("reverseCard", appVehicleStatusBean.getParentId() + "");
                map.put("insurance", appVehicleStatusBean.getParentId() + "");
                map.put("medicalRecords", appVehicleStatusBean.getParentId() + "");
                map.put("surgeryRelated", appVehicleStatusBean.getParentId() + "");
                map.put("routineBlood", appVehicleStatusBean.getParentId() + "");
                map.put("ecg", appVehicleStatusBean.getParentId() + "");
                map.put("cruor", appVehicleStatusBean.getParentId() + "");
                map.put("contagion", appVehicleStatusBean.getParentId() + "");
                map.put("minBloodPressure", appVehicleStatusBean.getParentId() + "");
                map.put("maxBloodPressure", appVehicleStatusBean.getParentId() + "");
                map.put("pulse", appVehicleStatusBean.getParentId() + "");
                map.put("breathe", appVehicleStatusBean.getParentId() + "");
                map.put("animalHeat", appVehicleStatusBean.getParentId() + "");
                map.put("diabetes", appVehicleStatusBean.getParentId() + "");
                map.put("cerebralInfarction", appVehicleStatusBean.getParentId() + "");
                map.put("heartDisease", appVehicleStatusBean.getParentId() + "");
                map.put("infectDisease", appVehicleStatusBean.getParentId() + "");
                map.put("breatheFunction", appVehicleStatusBean.getParentId() + "");
                break;
        }
        listMap.add(map);
        return ;
    }

    @OnClick({R.id.sb_send_order, R.id.stv_surgery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_surgery:
                stvSurgery.setCheckBoxChecked(!stvSurgery.getCheckBoxIsChecked());
                if (Double.parseDouble(etAddFee.getText().toString().trim()) >= 1500) {
                    if (stvSurgery.getCheckBoxIsChecked()) {
                        double i = Double.parseDouble(etAddFee.getText().toString().trim()) + Double.parseDouble(etAddFee.getText().toString().trim()) * 0.15;
                        i += addFee;
                        tvAddFee.setText("¥ " + i + "元");
                    } else {
                        double i = Double.parseDouble(etAddFee.getText().toString().trim()) + Double.parseDouble(etAddFee.getText().toString().trim()) * 0.15;
                        tvAddFee.setText("¥ " + i + "元");
                    }
                } else
                    ToastUtil.showShortToast("预计费用需大于等于1500元");
                break;
            case R.id.sb_send_order:
                if (Double.parseDouble(tvAddFee.getText().toString().trim()) > 1500) {
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
                            sendOrderMap.put("urgent", stvSurgery.getCheckBoxIsChecked() ? "2" : "1");
                            sendOrderMap.put("orderDetails", getOrderDetailsJson(1));
                            sendOrderMap.put("expectMoney", etAddFee.getText().toString().trim());
                            sendOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(sendOrderMap.toString().replaceAll(" ", "") + SIGN)));
                            getPresenter().getSendOrder(sendOrderMap, false, false);
                            break;
                        case 2:
                            TreeMap<String, String> sendOrderMap = new TreeMap<>();
                            sendOrderMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                            sendOrderMap.put("orderType", sendOrderType + "");
                            sendOrderMap.put("surgeryName", "");
                            sendOrderMap.put("hospitalName", "");
                            sendOrderMap.put("prov", "1");
                            sendOrderMap.put("city", "1");
                            sendOrderMap.put("dist", "1");
                            sendOrderMap.put("address", "1");
                            sendOrderMap.put("beginTime", "1");
                            sendOrderMap.put("duration", "1");
                            sendOrderMap.put("urgent", "1");
                            sendOrderMap.put("orderDetails", getOrderDetailsJson(2));
                            sendOrderMap.put("expectMoney", "1");
                            sendOrderMap.put("evenNum", "1");
                            sendOrderMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(sendOrderMap.toString().replaceAll(" ", "") + SIGN)));
                            getPresenter().getSendOrder(sendOrderMap, false, false);
                            break;
                    }
                } else {
                    ToastUtil.showShortToast("预计费用需大于等于1500元");
                }

                startActivity(new Intent(this, MainActivity.class));
                finish();
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

    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
