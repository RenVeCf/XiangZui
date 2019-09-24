package com.ipd.xiangzui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
import com.ipd.xiangzui.common.view.TwoBtDialog;
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

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_95;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_96;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：发单-患者信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class SendOrderPatientInfoActivity extends BaseActivity<NarcosisListContract.View, NarcosisListContract.Presenter> implements NarcosisListContract.View {

    @BindView(R.id.tv_send_order_patient_info)
    TopView tvSendOrderPatientInfo;
    @BindView(R.id.ll_surgical_name)
    LinearLayout llSurgicalName;
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
    private List<String> narcosisDataList = new ArrayList<>();//麻醉方式
    private List<NarcosisListBean.DataBean.NarcosisListsBean> narcosisLists = new ArrayList<>();//选择麻醉(取ID用)
    private int narcosisId = 0; //麻醉ID
    private OptionsPickerView pvOptions; //条件选择器
    private int sendOrderType; //1: 单台, 2: 连台
    private SendOrderDataBean sendOrderData;
    private String positiveUrl = "", negativeUrl = "", imgUrl = "";
    private OrderDetailsBean.DataBean.OrderBean orderDetails;
    private List<OrderDetailsBean.DataBean.OrderBean> orderDetailsList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_patient_info;
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
        ImmersionBar.setTitleBar(this, tvSendOrderPatientInfo);

        sendOrderData = getIntent().getParcelableExtra("sendOrderData");
        sendOrderType = sendOrderData.getSendOrderType();
        if (sendOrderType == 1)
            llSurgicalName.setVisibility(View.GONE);

        orderDetails = getIntent().getParcelableExtra("orderDetails");
        orderDetailsList = getIntent().getParcelableArrayListExtra("orderDetailsList");
        if (orderDetails != null && "2".equals(orderDetails.getOrderType()))
            llSurgicalName.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        TreeMap<String, String> narcosisListMap = new TreeMap<>();
        narcosisListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        narcosisListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(narcosisListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getNarcosisList(narcosisListMap, false, false);
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
                        stvPatientSex.setRightString(listData.get(options1));
                        stvPatientSex.setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 2:
                        stvPatientAge.setRightString(listData.get(options1));
                        stvPatientAge.setRightTextColor(getResources().getColor(R.color.black));
                        break;
                    case 3:
                        for (int i = 0; i < narcosisLists.size(); i++) {
                            if (narcosisLists.get(i).getNarcosisTypeName().equals(listData.get(options1)))
                                narcosisId = narcosisLists.get(i).getNarcosisTypeId();
                        }
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
                case REQUEST_CODE_96:
                    imgUrl = data.getStringExtra("imgUrl");
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
                startActivityForResult(new Intent(this, AgentCardActivity.class), REQUEST_CODE_95);
                break;
            case R.id.stv_insurance_consent:
                startActivityForResult(new Intent(this, HeadActivity.class).putExtra("title", "保险同意书"), REQUEST_CODE_96);
                break;
            case R.id.sb_next:
                if (!isEmpty(etPatientName.toString().trim()) && !"请选择".equals(stvPatientSex.getRightString()) && !"请选择".equals(stvPatientAge.getRightString())) {
                    if (sendOrderType == 2) {
                        if (isEmpty(etSurgicalName.toString().trim())) {
                            ToastUtil.showShortToast("请将信息填写完整！");
                            return;
                        } else {
                            sendOrderData.getTwoOrderBean().get(0).setSurgicalName(etSurgicalName.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setPatientName(etPatientName.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setPatientSex(stvPatientSex.getRightString());
                            sendOrderData.getTwoOrderBean().get(0).setPatientAge(stvPatientAge.getRightString().replaceAll(" 岁", ""));
                            sendOrderData.getTwoOrderBean().get(0).setPatientHeight(etPatientHeight.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setPatientBodyWeight(etPatientBodyWeight.getText().toString().trim());
                            sendOrderData.getTwoOrderBean().get(0).setNarcosisId(narcosisId + "");
                            sendOrderData.getTwoOrderBean().get(0).setPositiveUrl(positiveUrl);
                            sendOrderData.getTwoOrderBean().get(0).setNegativeUrl(negativeUrl);
                            sendOrderData.getTwoOrderBean().get(0).setInsuranceConsentUrl(imgUrl);
                        }
                    } else {
                        sendOrderData.getOneOrderBean().setPatientName(etPatientName.getText().toString().trim());
                        sendOrderData.getOneOrderBean().setPatientSex(stvPatientSex.getRightString());
                        sendOrderData.getOneOrderBean().setPatientAge(stvPatientAge.getRightString().replaceAll(" 岁", ""));
                        sendOrderData.getOneOrderBean().setPatientHeight(etPatientHeight.getText().toString().trim());
                        sendOrderData.getOneOrderBean().setPatientBodyWeight(etPatientBodyWeight.getText().toString().trim());
                        sendOrderData.getOneOrderBean().setNarcosisId(narcosisId + "");
                        sendOrderData.getOneOrderBean().setPositiveUrl(positiveUrl);
                        sendOrderData.getOneOrderBean().setNegativeUrl(negativeUrl);
                        sendOrderData.getOneOrderBean().setInsuranceConsentUrl(imgUrl);
                    }
                    if (("未上传").equals(stvInsuranceConsent.getRightString())) {
                        new TwoBtDialog(this, "不上传保险同意书，将不能顺利投保，发生意外由院方承担", "确认") {
                            @Override
                            public void confirm() {
                                startActivity(new Intent(SendOrderPatientInfoActivity.this, SendOrderMedicalRecordInfoActivity.class).putExtra("sendOrderData", sendOrderData));
                            }
                        }.show();
                    } else
                        startActivity(new Intent(this, SendOrderMedicalRecordInfoActivity.class).putExtra("sendOrderData", sendOrderData));
                } else
                    ToastUtil.showShortToast("请将信息填写完整！");
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
