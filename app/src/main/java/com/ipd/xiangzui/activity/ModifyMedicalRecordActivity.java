package com.ipd.xiangzui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
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
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
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
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_95;

/**
 * Description ：补充病历
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/8/29.
 */
public class ModifyMedicalRecordActivity extends BaseActivity {

    @BindView(R.id.et_patient_name)
    EditText etPatientName;
    @BindView(R.id.stv_patient_sex)
    SuperTextView stvPatientSex;
    @BindView(R.id.stv_patient_age)
    SuperTextView stvPatientAge;
    @BindView(R.id.stv_patient_height)
    SuperTextView stvPatientHeight;
    @BindView(R.id.stv_patient_body_weight)
    SuperTextView stvPatientBodyWeight;
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

    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_medical_record;
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
        ImmersionBar.setTitleBar(this, tvModifyMedicalRecord);
    }

    @Override
    public void initData() {

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
                listData = getHeightData();
                break;
            case 4:
                listData = getBodyWeightData();
                break;
            case 5:
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
                        break;
                    case 2:
                        stvPatientAge.setRightString(listData.get(options1));
                        stvPatientAge.setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        stvPatientHeight.setRightString(listData.get(options1));
                        stvPatientHeight.setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 4:
                        stvPatientBodyWeight.setRightString(listData.get(options1));
                        stvPatientBodyWeight.setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 5:
                        stvAnesthesiaType.setRightString(listData.get(options1));
                        stvAnesthesiaType.setRightTextColor(getResources().getColor(R.color.black));
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
                                tvTitle.setText("选择身高");
                                break;
                            case 4:
                                tvTitle.setText("选择体重");
                                break;
                            case 5:
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

    private List<String> getHeightData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
            list.add(i + " cm");
        }
        return list;
    }

    private List<String> getBodyWeightData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            list.add(i + " kg");
        }
        return list;
    }

    private List<String> getAnesthesiaData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("...麻醉");
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_95:
                    stvIdCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_103:
                    stvSurgeryAboutMedicalRecord.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_104:
                    stvBloodRoutine.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_105:
                    stvElectrocardiogram.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_106:
                    stvCoagulation.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_107:
                    stvInfectiousDiseaseIndex.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
            }
        }
    }

    @OnClick({R.id.bt_confirm, R.id.stv_patient_sex, R.id.stv_patient_age, R.id.stv_patient_height, R.id.stv_patient_body_weight, R.id.stv_anesthesia_type, R.id.stv_id_card, R.id.stv_surgery_about_medical_record, R.id.stv_blood_routine, R.id.stv_electrocardiogram, R.id.stv_coagulation, R.id.stv_infectious_disease_index})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_confirm:
                finish();
                break;
            case R.id.stv_patient_sex:
                showPickerView(1);
                break;
            case R.id.stv_patient_age:
                showPickerView(2);
                break;
            case R.id.stv_patient_height:
                showPickerView(3);
                break;
            case R.id.stv_patient_body_weight:
                showPickerView(4);
                break;
            case R.id.stv_anesthesia_type:
                showPickerView(5);
                break;
            case R.id.stv_id_card:
                startActivityForResult(new Intent(this, PatientIdCardActivity.class), REQUEST_CODE_95);
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
}
