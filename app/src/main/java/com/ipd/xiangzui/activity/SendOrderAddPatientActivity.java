package com.ipd.xiangzui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
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
import com.ipd.xiangzui.bean.NarcosisListBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
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

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_97;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_98;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：发单-添加患者信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/9.
 */
public class SendOrderAddPatientActivity extends BaseActivity<NarcosisListContract.View, NarcosisListContract.Presenter> implements NarcosisListContract.View {

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

    private SendOrderDataBean sendOrderData;
    private List<String> listData;
    private List<String> narcosisDataList = new ArrayList<>();//麻醉方式
    private List<NarcosisListBean.DataBean.NarcosisListsBean> narcosisLists = new ArrayList<>();//选择麻醉(取ID用)
    private int narcosisId = 0; //麻醉ID
    private OptionsPickerView pvOptions; //条件选择器
    private SelectOrderAddPatientAdapter selectOrderAddPatientAdapter;
    private String positiveUrl = "", negativeUrl = "", imgUrl = "";
    private OrderDetailsBean.DataBean.OrderBean orderDetails;
    private List<OrderDetailsBean.DataBean.OrderDetailBean> orderDetailsList;
    private int selectRb;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_add_patient;
    }

    @Override
    public NarcosisListContract.Presenter createPresenter() {
        return new NarcosisListPresenter(this);
    }

    @Override
    public NarcosisListContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvSendOrderAddPatient);

        sendOrderData = getIntent().getParcelableExtra("sendOrderData");
        selectRb = getIntent().getIntExtra("selectRb", 0);
        orderDetails = getIntent().getParcelableExtra("orderDetails");
        orderDetailsList = getIntent().getParcelableArrayListExtra("orderDetailsList");

        //更多订单
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvSendOrderAddPatient.setLayoutManager(layoutManager);
        rvSendOrderAddPatient.setNestedScrollingEnabled(false);
        rvSendOrderAddPatient.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvSendOrderAddPatient.setItemAnimator(new DefaultItemAnimator());//加载动画
    }

    @Override
    public void initData() {
        TreeMap<String, String> narcosisListMap = new TreeMap<>();
        narcosisListMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        narcosisListMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(narcosisListMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getNarcosisList(narcosisListMap, false, false);

        if (orderDetails != null && orderDetailsList.size() > 0)
            rvSendOrderAddPatient.setAdapter(selectOrderAddPatientAdapter = new SelectOrderAddPatientAdapter(orderDetailsList, 1));
        else
            rvSendOrderAddPatient.setAdapter(selectOrderAddPatientAdapter = new SelectOrderAddPatientAdapter(sendOrderData.getTwoOrderBean(), 1));
        selectOrderAddPatientAdapter.bindToRecyclerView(rvSendOrderAddPatient);
        selectOrderAddPatientAdapter.openLoadAnimation();
    }

    @Override
    public void initListener() {
        selectOrderAddPatientAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.stv_add_patient_item:
                        if (sendOrderData != null)
                            startActivity(new Intent(SendOrderAddPatientActivity.this, SendOrderAddPatientDetailsActivity.class).putExtra("sendOrderData", sendOrderData.getTwoOrderBean().get(position)).putExtra("selectRb", selectRb));
                        else
                            startActivity(new Intent(SendOrderAddPatientActivity.this, SendOrderAddPatientDetailsActivity.class).putExtra("orderDetailsTwo", orderDetailsList.get(position)).putExtra("selectRb", selectRb));
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
                startActivityForResult(new Intent(this, AgentCardActivity.class), REQUEST_CODE_97);
                break;
            case R.id.stv_insurance_consent:
                startActivityForResult(new Intent(this, HeadActivity.class).putExtra("title", "保险同意书"), REQUEST_CODE_98);
                break;
            case R.id.sb_next:
                if (!isEmpty(etSurgicalName.toString().trim()) && !isEmpty(etPatientName.toString().trim()) && !"请选择".equals(stvPatientSex.getRightString()) && !"请选择".equals(stvPatientAge.getRightString())) {
                    SendOrderDataBean.TwoOrderBean oneOrder = new SendOrderDataBean.TwoOrderBean();
                    oneOrder.setHospitalName(sendOrderData.getTwoOrderBean().get(0).getHospitalName());
                    oneOrder.setProv(sendOrderData.getTwoOrderBean().get(0).getProv());
                    oneOrder.setCity(sendOrderData.getTwoOrderBean().get(0).getCity());
                    oneOrder.setDist(sendOrderData.getTwoOrderBean().get(0).getDist());
                    oneOrder.setAddress(sendOrderData.getTwoOrderBean().get(0).getAddress());
                    oneOrder.setSurgicalTime(sendOrderData.getTwoOrderBean().get(0).getSurgicalTime());
                    oneOrder.setSurgicalDuration(sendOrderData.getTwoOrderBean().get(0).getSurgicalDuration());
                    sendOrderData.getTwoOrderBean().add(oneOrder);

                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setSurgicalName(etSurgicalName.getText().toString().trim());
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setPatientName(etPatientName.getText().toString().trim());
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setPatientSex(stvPatientSex.getRightString());
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setPatientAge(stvPatientAge.getRightString().replaceAll(" 岁", ""));
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setPatientHeight(etPatientHeight.getText().toString().trim());
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setPatientBodyWeight(etPatientBodyWeight.getText().toString().trim());
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setNarcosisId(narcosisId + "");
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setPositiveUrl(positiveUrl);
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setNegativeUrl(negativeUrl);
                    sendOrderData.getTwoOrderBean().get(sendOrderData.getTwoOrderBean().size() - 1).setInsuranceConsentUrl(imgUrl);
                    if (("未上传").equals(stvInsuranceConsent.getRightString())) {
                        new TwoBtDialog(this, "不上传保险同意书，将不能顺利投保，发生意外由院方承担", "确认") {
                            @Override
                            public void confirm() {
                                setResult(RESULT_OK, new Intent().putExtra("sendOrderData", sendOrderData));
                                finish();
                            }
                        }.show();
                    } else {
                        setResult(RESULT_OK, new Intent().putExtra("sendOrderData", sendOrderData));
                        finish();
                    }
                } else if (orderDetails != null && orderDetailsList.size() > 0) {
                    startActivity(new Intent(this, SendOrderMedicalRecordInfoActivity.class).putExtra("orderDetails", orderDetails).putParcelableArrayListExtra("orderDetailsList", (ArrayList<? extends Parcelable>) orderDetailsList));
                    finish();
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
