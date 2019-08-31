package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.SelectOrderAddPatientAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.ipd.xiangzui.common.view.CallPhoneDialog;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：订单详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/16.
 */
public class OrderDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_order_details)
    TopView tvOrderDetails;
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
    @BindView(R.id.tv_patient_surgery_medical_record)
    SuperTextView tvPatientSurgeryMedicalRecord;
    @BindView(R.id.tv_patient_blood_routine)
    SuperTextView tvPatientBloodRoutine;
    @BindView(R.id.tv_patient_electrocardiogram)
    SuperTextView tvPatientElectrocardiogram;
    @BindView(R.id.tv_patient_coagulation)
    SuperTextView tvPatientCoagulation;
    @BindView(R.id.tv_patient_infectious_disease)
    SuperTextView tvPatientInfectiousDisease;
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

    private int surgeryType;//1: 单台，2: 连台
    private String orderType;//订单状态 0:待接单， 1:已接单， 2:进行中， 3:已完成
    private SelectOrderAddPatientAdapter selectOrderAddPatientAdapter;
    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_order_details;
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
        ImmersionBar.setTitleBar(this, tvOrderDetails);

        surgeryType = getIntent().getIntExtra("surgery_type", 0);
        orderType = getIntent().getStringExtra("order_type");

        switch (orderType) {
            case "0":
                llWaitingOrder.setVisibility(View.VISIBLE);
                break;
            case "1":
                llIsOrder.setVisibility(View.VISIBLE);
                break;
            case "2":
                tvPatientName.setVisibility(View.GONE);
                tvPatientSex.setVisibility(View.GONE);
                tvPatientAge.setVisibility(View.GONE);
                tvPatientHeight.setVisibility(View.GONE);
                tvPatientBodyWeight.setVisibility(View.GONE);
                tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
                tvPatientIdCard.setVisibility(View.GONE);
                tvPatientInsuranceConsent.setVisibility(View.GONE);
                tvPatientSurgeryMedicalRecord.setVisibility(View.GONE);
                tvPatientBloodRoutine.setVisibility(View.GONE);
                tvPatientElectrocardiogram.setVisibility(View.GONE);
                tvPatientCoagulation.setVisibility(View.GONE);
                tvPatientInfectiousDisease.setVisibility(View.GONE);
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
            case "3":
                tvPatientName.setVisibility(View.GONE);
                tvPatientSex.setVisibility(View.GONE);
                tvPatientAge.setVisibility(View.GONE);
                tvPatientHeight.setVisibility(View.GONE);
                tvPatientBodyWeight.setVisibility(View.GONE);
                tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
                tvPatientIdCard.setVisibility(View.GONE);
                tvPatientInsuranceConsent.setVisibility(View.GONE);
                tvPatientSurgeryMedicalRecord.setVisibility(View.GONE);
                tvPatientBloodRoutine.setVisibility(View.GONE);
                tvPatientElectrocardiogram.setVisibility(View.GONE);
                tvPatientCoagulation.setVisibility(View.GONE);
                tvPatientInfectiousDisease.setVisibility(View.GONE);
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

        for (int i = 0; i < 2; i++) {
            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
            str1.add(testData);
        }
        rvPatientList.setAdapter(selectOrderAddPatientAdapter = new SelectOrderAddPatientAdapter(str1, 2));
        selectOrderAddPatientAdapter.bindToRecyclerView(rvPatientList);
        selectOrderAddPatientAdapter.openLoadAnimation();
    }

    @Override
    public void initData() {
        tvHospitalName.setRightString("上海市第一人民医院");
        tvHospitalAddress.setRightString("上海市虹口区海宁路100号");
        tvSurgeryType.setRightString(surgeryType == 1 ? "单台" : "连台");
        tvSimulatedSurgeryName.setRightString("阑尾切除术");
        tvStartTime.setRightString("2019-05-05 10:30");
        tvContinuedTime.setRightString("2h");
        tvContinuedFee.setRightString("¥ 300元");
        tvPs.setRightString("高龄、药物过敏");
        if (surgeryType == 1) {
            tvPatientName.setRightString("李先生");
            tvPatientSex.setRightString("男");
            tvPatientAge.setRightString("22岁");
            tvPatientHeight.setRightString("172cm");
            tvPatientBodyWeight.setRightString("66kg");
            tvPatientSimulatedAnesthesia.setRightString("椎管内麻醉");
            tvPatientIdCard.setRightString("已上传");
            tvPatientInsuranceConsent.setRightString("已上传");
            tvPatientSurgeryMedicalRecord.setRightString("已上传");
            tvPatientBloodRoutine.setRightString("已上传");
            tvPatientElectrocardiogram.setRightString("已上传");
            tvPatientCoagulation.setRightString("已上传");
            tvPatientInfectiousDisease.setRightString("已上传");
        } else {
            tvPatientName.setVisibility(View.GONE);
            tvPatientSex.setVisibility(View.GONE);
            tvPatientAge.setVisibility(View.GONE);
            tvPatientHeight.setVisibility(View.GONE);
            tvPatientBodyWeight.setVisibility(View.GONE);
            tvPatientSimulatedAnesthesia.setVisibility(View.GONE);
            tvPatientIdCard.setVisibility(View.GONE);
            tvPatientInsuranceConsent.setVisibility(View.GONE);
            tvPatientSurgeryMedicalRecord.setVisibility(View.GONE);
            tvPatientBloodRoutine.setVisibility(View.GONE);
            tvPatientElectrocardiogram.setVisibility(View.GONE);
            tvPatientCoagulation.setVisibility(View.GONE);
            tvPatientInfectiousDisease.setVisibility(View.GONE);
            rvPatientList.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initListener() {
        selectOrderAddPatientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.stv_add_patient_item:
                        startActivity(new Intent(OrderDetailsActivity.this, SendOrderAddPatientDetailsActivity.class));
                        break;
                }
            }
        });
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
}
