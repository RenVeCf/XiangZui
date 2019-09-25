package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.SelectOrderAddPatientAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.OrderListBean;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.common.view.CallPhoneDialog;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.contract.OrderContract;
import com.ipd.xiangzui.presenter.OrderPresenter;
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

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：订单详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class OrderDetailsActivity extends BaseActivity<OrderContract.View, OrderContract.Presenter> implements OrderContract.View {

    @BindView(R.id.tv_order_details)
    TopView tvOrderDetails;
    @BindView(R.id.tv_order_code)
    SuperTextView tvOrderCode;
    @BindView(R.id.tv_hospital_name)
    SuperTextView tvHospitalName;
    @BindView(R.id.tv_hospital_address)
    SuperTextView tvHospitalAddress;
    @BindView(R.id.tv_surgery_type)
    SuperTextView tvSurgeryType;
    @BindView(R.id.tv_simulated_surgery_name)
    SuperTextView tvSimulatedSurgeryName;
    @BindView(R.id.tv_start_time)
    SuperTextView tvStartTime;
    @BindView(R.id.tv_continued_time)
    SuperTextView tvContinuedTime;
    @BindView(R.id.tv_continued_fee)
    SuperTextView tvContinuedFee;
    @BindView(R.id.tv_ps)
    SuperTextView tvPs;
    @BindView(R.id.tv_patient_name)
    SuperTextView tvPatientName;
    @BindView(R.id.tv_patient_sex)
    SuperTextView tvPatientSex;
    @BindView(R.id.tv_patient_age)
    SuperTextView tvPatientAge;
    @BindView(R.id.tv_patient_height)
    SuperTextView tvPatientHeight;
    @BindView(R.id.tv_patient_body_weight)
    SuperTextView tvPatientBodyWeight;
    @BindView(R.id.tv_patient_simulated_anesthesia)
    SuperTextView tvPatientSimulatedAnesthesia;
    @BindView(R.id.tv_patient_id_card)
    SuperTextView tvPatientIdCard;
    @BindView(R.id.tv_patient_insurance_consent)
    SuperTextView tvPatientInsuranceConsent;
    @BindView(R.id.cl_bottom)
    ConstraintLayout clBottom;
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
    @BindView(R.id.ll_waiting_order)
    LinearLayoutCompat llWaitingOrder;
    @BindView(R.id.ll_is_order)
    LinearLayoutCompat llIsOrder;
    @BindView(R.id.rv_patient_list)
    RecyclerView rvPatientList;
    @BindView(R.id.tv_anesthesia_info)
    AppCompatTextView tvAnesthesiaInfo;
    @BindView(R.id.tv_fee_info)
    AppCompatTextView tvFeeInfo;
    @BindView(R.id.tv_anesthesia_tool)
    SuperTextView tvAnesthesiaTool;
    @BindView(R.id.tv_anesthesia_type)
    SuperTextView tvAnesthesiaType;
    @BindView(R.id.tv_waiting_time_fee)
    SuperTextView tvWaitingTimeFee;
    @BindView(R.id.tv_surgery_fee)
    SuperTextView tvSurgeryFee;
    @BindView(R.id.tv_quicken_fee)
    SuperTextView tvQuickenFee;
    @BindView(R.id.tv_add_fee_fee)
    SuperTextView tvAddFeeFee;
    @BindView(R.id.tv_sum_fee)
    SuperTextView tvSumFee;
    @BindView(R.id.tv_pay_type)
    SuperTextView tvPayType;

    private String orderStatus; //订单状态：1：待接单 2：待开始  3：进行中4：已结束 5：待结算 6：已结算' 7：已取消
    private SelectOrderAddPatientAdapter selectOrderAddPatientAdapter;
    private List<SendOrderDataBean.TwoOrderBean> str1 = new ArrayList<>();
    private int orderId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_details;
    }

    @Override
    public OrderContract.Presenter createPresenter() {
        return new OrderPresenter(this);
    }

    @Override
    public OrderContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvOrderDetails);

        orderStatus = getIntent().getStringExtra("order_status");
        orderId = getIntent().getIntExtra("orderId", 0);

        switch (orderStatus) {
            case "1":
                llWaitingOrder.setVisibility(View.VISIBLE);
                rvPatientList.setVisibility(View.VISIBLE);
                break;
            case "2":
                llIsOrder.setVisibility(View.VISIBLE);
                break;
            case "3":
                tvPatientName.setVisibility(View.GONE);
                tvPatientSex.setVisibility(View.GONE);
                tvPatientAge.setVisibility(View.GONE);
                tvPatientHeight.setVisibility(View.GONE);
                tvPatientBodyWeight.setVisibility(View.GONE);
                tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
                tvPatientIdCard.setVisibility(View.GONE);
                tvPatientInsuranceConsent.setVisibility(View.GONE);
                clBottom.setVisibility(View.GONE);
                rvPatientList.setVisibility(View.VISIBLE);

                tvAnesthesiaInfo.setVisibility(View.VISIBLE);
                tvFeeInfo.setVisibility(View.VISIBLE);
                tvAnesthesiaTool.setVisibility(View.VISIBLE);
                tvAnesthesiaType.setVisibility(View.VISIBLE);
                tvWaitingTimeFee.setVisibility(View.VISIBLE);
                tvSurgeryFee.setVisibility(View.VISIBLE);
                tvQuickenFee.setVisibility(View.VISIBLE);
                tvAddFeeFee.setVisibility(View.VISIBLE);
                tvSumFee.setVisibility(View.VISIBLE);
                tvPayType.setVisibility(View.VISIBLE);

                tvAnesthesiaTool.setRightString("已确认");
                tvAnesthesiaType.setRightString("椎管内麻醉");
                tvWaitingTimeFee.setRightString("¥ 20元");
                tvSurgeryFee.setRightString("¥ 200元");
                tvQuickenFee.setRightString("¥ 30元");
                tvAddFeeFee.setRightString("¥ 50元");
                tvSumFee.setRightString("¥ 303元");
                tvPayType.setRightString("已结算");
                break;
            case "4":
            case "5":
            case "6":
            case "7":
                tvPatientName.setVisibility(View.GONE);
                tvPatientSex.setVisibility(View.GONE);
                tvPatientAge.setVisibility(View.GONE);
                tvPatientHeight.setVisibility(View.GONE);
                tvPatientBodyWeight.setVisibility(View.GONE);
                tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
                tvPatientIdCard.setVisibility(View.GONE);
                tvPatientInsuranceConsent.setVisibility(View.GONE);
                clBottom.setVisibility(View.GONE);
                rvPatientList.setVisibility(View.VISIBLE);

                tvAnesthesiaInfo.setVisibility(View.VISIBLE);
                tvFeeInfo.setVisibility(View.VISIBLE);
                tvAnesthesiaTool.setVisibility(View.VISIBLE);
                tvAnesthesiaType.setVisibility(View.VISIBLE);
                tvWaitingTimeFee.setVisibility(View.VISIBLE);
                tvSurgeryFee.setVisibility(View.VISIBLE);
                tvQuickenFee.setVisibility(View.VISIBLE);
                tvAddFeeFee.setVisibility(View.VISIBLE);
                tvSumFee.setVisibility(View.VISIBLE);
                tvPayType.setVisibility(View.VISIBLE);

                tvAnesthesiaTool.setRightString("已确认");
                tvAnesthesiaType.setRightString("椎管内麻醉");
                tvWaitingTimeFee.setRightString("¥ 20元");
                tvSurgeryFee.setRightString("¥ 200元");
                tvQuickenFee.setRightString("¥ 30元");
                tvAddFeeFee.setRightString("¥ 50元");
                tvSumFee.setRightString("¥ 303元");
                tvPayType.setRightString("已结算");
                break;
        }

        //更多订单
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvPatientList.setLayoutManager(layoutManager);
        rvPatientList.setNestedScrollingEnabled(false);
        rvPatientList.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvPatientList.setItemAnimator(new DefaultItemAnimator());//加载动画
    }

    @Override
    public void initData() {
        TreeMap<String, String> orderDetailsMap = new TreeMap<>();
        orderDetailsMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        orderDetailsMap.put("orderId", orderId + "");
        orderDetailsMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(orderDetailsMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getOrderDetails(orderDetailsMap, false, false);
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.bt_cancel, R.id.bt_modify, R.id.bt_quicken, R.id.bt_add_fee, R.id.bt_cancel_1, R.id.bt_medical_record, R.id.bt_call_doctor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_cancel:
                if (isFastClick())
                    new TwoBtDialog(this, "确认取消订单？", "确认") {
                        @Override
                        public void confirm() {
                            finish();
                        }
                    }.show();
                break;
            case R.id.bt_modify:
                if (isFastClick())
                    startActivity(new Intent(this, SendOrderActivity.class));
                break;
            case R.id.bt_quicken:
                break;
            case R.id.bt_add_fee:
                break;
            case R.id.bt_cancel_1:
                if (isFastClick())
                    new TwoBtDialog(this, "确认取消订单？", "确认") {
                        @Override
                        public void confirm() {
                            finish();
                        }
                    }.show();
                break;
            case R.id.bt_medical_record:
                if (isFastClick())
                    new TwoBtDialog(this, "对此订单有异议，是否进行电话咨询？", "确认") {
                        @Override
                        public void confirm() {
                            new CallPhoneDialog(OrderDetailsActivity.this) {
                            }.show();
                        }
                    }.show();
                break;
            case R.id.bt_call_doctor:
                if (isFastClick())
                    new CallPhoneDialog(this) {
                    }.show();
                break;
        }
    }

    @Override
    public void resultOrderList(OrderListBean data) {

    }

    @Override
    public void resultOrderDetails(OrderDetailsBean data) {
        switch (data.getCode()) {
            case 200:
                tvOrderCode.setRightString(data.getData().getOrder().getOrderNo());
                tvHospitalName.setRightString(data.getData().getOrder().getHospitalName());
                tvHospitalAddress.setRightString(data.getData().getOrder().getProv() + data.getData().getOrder().getCity() + data.getData().getOrder().getDist() + data.getData().getOrder().getAddress());
                tvSurgeryType.setRightString("1".equals(data.getData().getOrder().getOrderType()) ? "单台" : "连台");
                tvSimulatedSurgeryName.setRightString(data.getData().getOrder().getSurgeryName());
                tvStartTime.setRightString(data.getData().getOrder().getBeginTime());
                tvContinuedTime.setRightString(data.getData().getOrder().getDuration() + "小时");
                if ("1".equals(data.getData().getOrder().getPremium()))
                    tvContinuedFee.setRightString("¥ " + data.getData().getOrder().getAhMoney() + "元");
                else
                    tvContinuedFee.setRightString("¥ " + data.getData().getOrder().getAhMoney() + "元(含加价费用¥ " + data.getData().getOrder().getPremiumMoney() + ")");
                tvPs.setRightString(data.getData().getOrder().getPrompt());

                rvPatientList.setAdapter(selectOrderAddPatientAdapter = new SelectOrderAddPatientAdapter(data.getData().getOrderDetail(), 2));
                selectOrderAddPatientAdapter.bindToRecyclerView(rvPatientList);
                selectOrderAddPatientAdapter.openLoadAnimation();

                selectOrderAddPatientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.stv_add_patient_item:
                                startActivity(new Intent(OrderDetailsActivity.this, SelectPatientDetailsActivity.class).putExtra("orderDetailsTwo", data.getData().getOrderDetail().get(position)));
                                break;
                        }
                    }
                });

                if ("1".equals(data.getData().getOrder().getOrderType())) {
                    tvPatientName.setRightString(data.getData().getOrderDetail().get(0).getPatientName());
                    tvPatientSex.setRightString("1".equals(data.getData().getOrderDetail().get(0).getSex()) ? "男" : "女");
                    tvPatientAge.setRightString(data.getData().getOrderDetail().get(0).getAge() + "岁");
                    tvPatientHeight.setRightString(data.getData().getOrderDetail().get(0).getHeight() + "cm");
                    tvPatientBodyWeight.setRightString(data.getData().getOrderDetail().get(0).getWeight() + "kg");
                    tvPatientSimulatedAnesthesia.setRightString(data.getData().getOrderDetail().get(0).getNarcosisType());
                    if (!isEmpty(data.getData().getOrderDetail().get(0).getPositiveCard()) && !isEmpty(data.getData().getOrderDetail().get(0).getReverseCard())) {
                        tvPatientIdCard.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    }
                    if (!isEmpty(data.getData().getOrderDetail().get(0).getInsurance()))
                        tvPatientInsuranceConsent.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));

                    if (!isEmpty(data.getData().getOrderDetail().get(0).getSurgeryRelated()))
                        stvSurgeryAboutMedicalRecord.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    if (!isEmpty(data.getData().getOrderDetail().get(0).getRoutineBlood()))
                        stvBloodRoutine.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    if (!isEmpty(data.getData().getOrderDetail().get(0).getEcg()))
                        stvElectrocardiogram.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    if (!isEmpty(data.getData().getOrderDetail().get(0).getCruor()))
                        stvCoagulation.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    if (!isEmpty(data.getData().getOrderDetail().get(0).getContagion()))
                        stvInfectiousDiseaseIndex.setRightString("已上传")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));

                    stvBloodPressure.setRightString(data.getData().getOrderDetail().get(0).getMinBloodPressure() + "/" + data.getData().getOrderDetail().get(0).getMaxBloodPressure() + "mmHg");
                    stvPulse.setRightString(data.getData().getOrderDetail().get(0).getPulse() + "次/分钟");
                    stvBreathe.setRightString(data.getData().getOrderDetail().get(0).getBreathe() + "次/分钟");
                    stvBodyTemperature.setRightString(data.getData().getOrderDetail().get(0).getAnimalHeat() + "℃");
                    if ("2".equals(data.getData().getOrderDetail().get(0).getDiabetes()))
                        stvDiabetes.setRightString("有")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    if ("2".equals(data.getData().getOrderDetail().get(0).getCerebralInfarction()))
                        stvBrainStalk.setRightString("有")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    if ("2".equals(data.getData().getOrderDetail().get(0).getHeartDisease()))
                        stvHeartDisease.setRightString("有")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    if ("2".equals(data.getData().getOrderDetail().get(0).getInfectDisease()))
                        stvInfectiousDisease.setRightString("有")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    if ("2".equals(data.getData().getOrderDetail().get(0).getBreatheFunction()))
                        stvRespiratoryDysfunction.setRightString("有")
                                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                } else {
                    tvPatientName.setVisibility(View.GONE);
                    tvPatientSex.setVisibility(View.GONE);
                    tvPatientAge.setVisibility(View.GONE);
                    tvPatientHeight.setVisibility(View.GONE);
                    tvPatientBodyWeight.setVisibility(View.GONE);
                    tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
                    tvPatientIdCard.setVisibility(View.GONE);
                    tvPatientInsuranceConsent.setVisibility(View.GONE);
                    clBottom.setVisibility(View.GONE);
                    rvPatientList.setVisibility(View.VISIBLE);
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
