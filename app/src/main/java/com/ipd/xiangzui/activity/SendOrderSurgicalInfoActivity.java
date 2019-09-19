package com.ipd.xiangzui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.common.config.IConstants.ADDRESS;
import static com.ipd.xiangzui.common.config.IConstants.CITY;
import static com.ipd.xiangzui.common.config.IConstants.DIST;
import static com.ipd.xiangzui.common.config.IConstants.HOSPTIAL_NAME;
import static com.ipd.xiangzui.common.config.IConstants.PROV;
import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_100;
import static com.ipd.xiangzui.utils.DateUtils.timedate1;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：发单-手术信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class SendOrderSurgicalInfoActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_surgical_info)
    TopView tvSendOrderSurgicalInfo;
    @BindView(R.id.ll_surgical_name)
    LinearLayout llSurgicalName;
    @BindView(R.id.et_surgical_name)
    AppCompatEditText etSurgicalName;
    @BindView(R.id.et_hospital_name)
    EditText etHospitalName;
    @BindView(R.id.stv_surgical_address)
    SuperTextView stvSurgicalAddress;
    @BindView(R.id.stv_surgical_time)
    SuperTextView stvSurgicalTime;
    @BindView(R.id.stv_surgical_duration)
    SuperTextView stvSurgicalDuration;

    private List<String> listData;
    private TimePickerView pvTime;
    private OptionsPickerView pvOptions; //条件选择器
    private int sendOrderType; //1: 单台, 2: 连台

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_surgical_info;
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
        ImmersionBar.setTitleBar(this, tvSendOrderSurgicalInfo);

        sendOrderType = getIntent().getIntExtra("sendOrderType", 0);
        if (sendOrderType == 2)
            llSurgicalName.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        etHospitalName.setText(SPUtil.get(this, HOSPTIAL_NAME, "") + "");
        stvSurgicalAddress.setRightString(SPUtil.get(this, PROV, "") + "" + SPUtil.get(this, CITY, "") + SPUtil.get(this, DIST, "") + SPUtil.get(this, ADDRESS, ""));
    }

    @Override
    public void initListener() {

    }

    //时间选择器
    private void selectTime() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        //startDate.set(2013,1,1);
        Calendar endDate = Calendar.getInstance();
        //endDate.set(2020,1,1);

        //正确设置方式 原因：注意事项有说明
        startDate.set(2019, 9, 20);
        endDate.set(2030, 11, 31);

        pvTime = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
                stvSurgicalTime.setRightString(timedate1(date.getTime() + ""));
            }
        })
                .setLayoutRes(R.layout.pickerview_custom_time, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        tvTitle.setText("选择拟手术开始时间");

                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (isFastClick()) {
                                    pvTime.returnData();
                                    pvTime.dismiss();
                                }
                            }
                        });
                    }
                })
                .setType(new boolean[]{true, true, true, true, true, false})// 默认全部显示
//                .setCancelText("请选择起始时间")//取消按钮文字
//                .setSubmitText("")//确认按钮文字
                //                .setContentSize(18)//滚轮文字大小
//                .setTitleSize(16)//标题文字大小
//                .setTitleText("请选择起始时间")
                .setDividerColor(getResources().getColor(R.color.transparent))//设置分割线的颜色
                .setDecorView((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content))
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
//                .setTitleColor(Color.BLACK)//标题文字颜色
//                .setSubmitColor(Color.BLACK)//确定按钮文字颜色
//                .setCancelColor(Color.BLACK)//取消按钮文字颜色
//                .setTitleBgColor(0xFFFFFFFF)//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                .setRangDate(startDate, endDate)//起始终止年月日设定
                .setLabel("", "", "", "", "", "秒")//默认设置为年月日时分秒
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .isDialog(false)//是否显示为对话框样式
                .build();
        pvTime.show();
    }

    //条件选择器
    private void showPickerView() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

        listData = getTimeData();
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                stvSurgicalDuration.setRightString(listData.get(options1));
            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        tvTitle.setText("选择拟手术预计时长");

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

    private List<String> getTimeData() {
        List<String> list = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            list.add(i + " 小时");
        }
        return list;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_100:
                    SPUtil.put(this, PROV, data.getStringExtra("prov"));
                    SPUtil.put(this, CITY, data.getStringExtra("city"));
                    SPUtil.put(this, DIST, data.getStringExtra("dist"));
                    SPUtil.put(this, ADDRESS, data.getStringExtra("address"));
                    stvSurgicalAddress.setRightString(data.getStringExtra("prov") + data.getStringExtra("city") + data.getStringExtra("dist") + data.getStringExtra("address"));
                    break;
            }
        }
    }

    @OnClick({R.id.stv_surgical_address, R.id.stv_surgical_time, R.id.stv_surgical_duration, R.id.sb_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_surgical_address: //手术地点
                startActivityForResult(new Intent(this, SelectAddressActivity.class).putExtra("address_type", 1), REQUEST_CODE_100);
                break;
            case R.id.stv_surgical_time: //拟手术开始时间
                selectTime();
                break;
            case R.id.stv_surgical_duration: //拟手术预计时长
                showPickerView();
                break;
            case R.id.sb_next:
                if (!isEmpty(etSurgicalName.getText().toString().trim()) && !isEmpty(etHospitalName.getText().toString().trim()) && !"请选择".equals(stvSurgicalAddress.getRightString()) && !"请选择".equals(stvSurgicalTime.getRightString()) && !"请选择".equals(stvSurgicalDuration.getRightString()) && sendOrderType == 1) {
                    SendOrderDataBean sendOrderData = new SendOrderDataBean();
                    SendOrderDataBean.OneOrderBean oneOrder = new SendOrderDataBean.OneOrderBean();
                    oneOrder.setSurgicalName(etSurgicalName.getText().toString().trim());
                    oneOrder.setHospitalName(etHospitalName.getText().toString().trim());
                    oneOrder.setProv(SPUtil.get(this, PROV, "") + "");
                    oneOrder.setCity(SPUtil.get(this, CITY, "") + "");
                    oneOrder.setDist(SPUtil.get(this, DIST, "") + "");
                    oneOrder.setAddress(SPUtil.get(this, ADDRESS, "") + "");
                    oneOrder.setSurgicalTime(stvSurgicalTime.getRightString());
                    oneOrder.setSurgicalDuration(stvSurgicalDuration.getRightString().replaceAll("小时", ""));
                    sendOrderData.setSendOrderType(sendOrderType);
                    sendOrderData.setOneOrderBean(oneOrder);

                    startActivity(new Intent(this, SendOrderPatientInfoActivity.class).putExtra("sendOrderData", sendOrderData));
                } else if (!isEmpty(etHospitalName.getText().toString().trim()) && !"请选择".equals(stvSurgicalAddress.getRightString()) && !"请选择".equals(stvSurgicalTime.getRightString()) && !"请选择".equals(stvSurgicalDuration.getRightString()) && sendOrderType == 2) {
                    SendOrderDataBean sendOrderData = new SendOrderDataBean();
                    List<SendOrderDataBean.TwoOrderBean> oneOrderList = new ArrayList<>();
                    SendOrderDataBean.TwoOrderBean oneOrder = new SendOrderDataBean.TwoOrderBean();
                    oneOrder.setHospitalName(etHospitalName.getText().toString().trim());
                    oneOrder.setProv(SPUtil.get(this, PROV, "") + "");
                    oneOrder.setCity(SPUtil.get(this, CITY, "") + "");
                    oneOrder.setDist(SPUtil.get(this, DIST, "") + "");
                    oneOrder.setAddress(SPUtil.get(this, ADDRESS, "") + "");
                    oneOrder.setSurgicalTime(stvSurgicalTime.getRightString());
                    oneOrder.setSurgicalDuration(stvSurgicalDuration.getRightString().replaceAll("小时", ""));
                    oneOrderList.add(oneOrder);
                    sendOrderData.setSendOrderType(sendOrderType);
                    sendOrderData.setTwoOrderBean(oneOrderList);

                    startActivity(new Intent(this, SendOrderPatientInfoActivity.class).putExtra("sendOrderData", sendOrderData));
                } else
                    ToastUtil.showShortToast("请将资料填写完整!");
                break;
        }
    }
}
