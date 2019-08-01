package com.ipd.xiangzui.activity;

import android.widget.TextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;

/**
 * Description ：发单-添加患者信息详情
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/11.
 */
public class SendOrderAddPatientDetailsActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_add_patient_details)
    TopView tvSendOrderAddPatientDetails;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_add_patient_details;
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
    }

    @Override
    public void initData() {
        tvTopTitle.setText("患者1");
        stvName.setRightString("李先生");
        stvSex.setRightString("男");
        stvAge.setRightString("22岁");
        stvHeight.setRightString("172cm");
        stvBodyWeight.setRightString("66kg");
        stvAnesthesiaType.setRightString("椎管内麻醉");

        stvIdCard.setRightString("已上传")
                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        stvInsuranceConsent.setRightString("已上传")
                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        stvSurgeryAboutMedicalRecord.setRightString("已上传")
                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        stvBloodRoutine.setRightString("已上传")
                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        stvElectrocardiogram.setRightString("已上传")
                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        stvCoagulation.setRightString("已上传")
                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
        stvInfectiousDiseaseIndex.setRightString("已上传")
                .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
    }

    @Override
    public void initListener() {

    }
}
