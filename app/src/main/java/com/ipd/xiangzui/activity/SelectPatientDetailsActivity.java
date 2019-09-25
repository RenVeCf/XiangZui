package com.ipd.xiangzui.activity;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

public class SelectPatientDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_add_patient_details)
    TopView tvSendOrderAddPatientDetails;
    @BindView(R.id.stv_name)
    SuperTextView stvName;
    @BindView(R.id.stv_sex)
    SuperTextView stvSex;
    @BindView(R.id.stv_age)
    SuperTextView stvAge;
    @BindView(R.id.stv_height)
    SuperTextView stvHeight;
    @BindView(R.id.stv_body_weight)
    SuperTextView stvBodyWeight;
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

    private OrderDetailsBean.DataBean.OrderDetailBean orderDetailsTwo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_select_patient_details;
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
        ImmersionBar.setTitleBar(this, tvSendOrderAddPatientDetails);

        orderDetailsTwo = getIntent().getParcelableExtra("orderDetailsTwo");
    }

    @Override
    public void initData() {
        stvName.setRightString(orderDetailsTwo.getPatientName());
        stvSex.setRightString("1".equals(orderDetailsTwo.getSex()) ? "男" : "女");
        stvAge.setRightString(orderDetailsTwo.getAge() + "岁");
        stvHeight.setRightString(orderDetailsTwo.getHeight() + "cm");
        stvBodyWeight.setRightString(orderDetailsTwo.getWeight() + "kg");
        stvAnesthesiaType.setRightString(orderDetailsTwo.getNarcosisType());

        if (!isEmpty(orderDetailsTwo.getPositiveCard()) && !isEmpty(orderDetailsTwo.getReverseCard())) {
            stvIdCard.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        }
        if (!isEmpty(orderDetailsTwo.getInsurance()))
            stvInsuranceConsent.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailsTwo.getSurgeryRelated()))
            stvSurgeryAboutMedicalRecord.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailsTwo.getRoutineBlood()))
            stvBloodRoutine.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailsTwo.getEcg()))
            stvElectrocardiogram.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailsTwo.getCruor()))
            stvCoagulation.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        if (!isEmpty(orderDetailsTwo.getContagion()))
            stvInfectiousDiseaseIndex.setRightString("已上传")
                    .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
    }

    @Override
    public void initListener() {

    }
}
