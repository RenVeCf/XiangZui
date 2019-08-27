package com.ipd.xiangzui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.SelectOrderAddPatientAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_95;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_97;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_98;

/**
 * Description ：发单-添加患者信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/9.
 */
public class SendOrderAddPatientActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_add_patient)
    TopView tvSendOrderAddPatient;
    @BindView(R.id.rv_send_order_add_patient)
    RecyclerView rvSendOrderAddPatient;
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

    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器
    private SelectOrderAddPatientAdapter selectOrderAddPatientAdapter;
    private List<TestMultiItemEntityBean> str1 = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_add_patient;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSendOrderAddPatient);

        //更多订单
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvSendOrderAddPatient.setLayoutManager(layoutManager);
        rvSendOrderAddPatient.setNestedScrollingEnabled(false);
        rvSendOrderAddPatient.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvSendOrderAddPatient.setItemAnimator(new DefaultItemAnimator());//加载动画

        for (int i = 0; i < 2; i++) {
            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
            str1.add(testData);
        }
        rvSendOrderAddPatient.setAdapter(selectOrderAddPatientAdapter = new SelectOrderAddPatientAdapter(str1, 1));
        selectOrderAddPatientAdapter.bindToRecyclerView(rvSendOrderAddPatient);
        selectOrderAddPatientAdapter.openLoadAnimation();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        selectOrderAddPatientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.stv_add_patient_item:
                        startActivity(new Intent(SendOrderAddPatientActivity.this, SendOrderAddPatientDetailsActivity.class));
                        break;
                }
            }
        });
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
                        stvPatientSex.setRightString(listData.get(options1));
                        stvPatientSex.setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        stvPatientAge.setRightString(listData.get(options1));
                        stvPatientAge.setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
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
                case REQUEST_CODE_97:
                    stvIdCard.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
                case REQUEST_CODE_98:
                    stvInsuranceConsent.setRightString("已上传")
                            .setRightTextColor(getResources().getColor(R.color.tx_bottom_navigation_select));
                    break;
            }
        }
    }

    @OnClick({R.id.stv_patient_sex, R.id.stv_patient_age, R.id.stv_anesthesia_type, R.id.stv_id_card, R.id.stv_insurance_consent, R.id.sb_next})
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
                startActivityForResult(new Intent(this, PatientIdCardActivity.class), REQUEST_CODE_95);
                break;
            case R.id.stv_insurance_consent:
                break;
            case R.id.sb_next:
                if (("未上传").equals(stvInsuranceConsent.getRightString().toString().trim())) {
                    new TwoBtDialog(this, "不上传保险同意书，将不能顺利投保，发生意外由院方承担", "确认") {
                        @Override
                        public void confirm() {
                            finish();
                        }
                    }.show();
                } else
                    finish();
                break;
        }
    }
}
