package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.HomeBean;
import com.ipd.xiangzui.bean.OrderListBean;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class MainOrderAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {

    private SuperTextView tvName;
    private SuperTextView stvName;
    private SuperTextView stvAddress;
    private SuperTextView stvStartTime;
    private SuperTextView stvFee;

    private String status; //订单状态：1：待接单 2：待开始  3：进行中4：已结束 5：待结算 6：已结算' 7：已取消
    private String surgeryName; //手术名称
    private String urgent; //1：无 2：加急
    private String premium; //1：不加价  2：加价
    private String hospitalName; //医院名称
    private String prov; //省
    private String city; //市
    private String dist; //区
    private String address; //医院地址
    private String beginTime; //开始时间
    private double expectMoney; //预计费用


    public MainOrderAdapter(@Nullable List<T> data) {
        super(R.layout.adapter_main_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        if (item instanceof HomeBean.DataBean.OrderListBean) {
            status = ((HomeBean.DataBean.OrderListBean) item).getStatus();
            urgent = ((HomeBean.DataBean.OrderListBean) item).getUrgent();
            surgeryName = ((HomeBean.DataBean.OrderListBean) item).getSurgeryName();
            hospitalName = ((HomeBean.DataBean.OrderListBean) item).getHospitalName();
            prov = ((HomeBean.DataBean.OrderListBean) item).getProv();
            city = ((HomeBean.DataBean.OrderListBean) item).getCity();
            dist = ((HomeBean.DataBean.OrderListBean) item).getDist();
            address = ((HomeBean.DataBean.OrderListBean) item).getAddress();
            beginTime = ((HomeBean.DataBean.OrderListBean) item).getBeginTime();
            expectMoney = ((HomeBean.DataBean.OrderListBean) item).getExpectMoney();
        } else if (item instanceof OrderListBean.DataBean.OrderListsBean) {
            status = ((OrderListBean.DataBean.OrderListsBean) item).getStatus();
            urgent = ((OrderListBean.DataBean.OrderListsBean) item).getUrgent();
            premium = ((OrderListBean.DataBean.OrderListsBean) item).getPremium();
            surgeryName = ((OrderListBean.DataBean.OrderListsBean) item).getSurgeryName();
            hospitalName = ((OrderListBean.DataBean.OrderListsBean) item).getHospitalName();
            prov = ((OrderListBean.DataBean.OrderListsBean) item).getProv();
            city = ((OrderListBean.DataBean.OrderListsBean) item).getCity();
            dist = ((OrderListBean.DataBean.OrderListsBean) item).getDist();
            address = ((OrderListBean.DataBean.OrderListsBean) item).getAddress();
            beginTime = ((OrderListBean.DataBean.OrderListsBean) item).getBeginTime();
            expectMoney = ((OrderListBean.DataBean.OrderListsBean) item).getExpectMoney();
        }

        String orderType = "";

        switch (status) {
            case "0":
                orderType = "待接单";
                helper.setText(R.id.bt_first, "取消订单")
                        .setText(R.id.bt_second, "修改订单")
                        .setText(R.id.bt_third, "加价");
                break;
            case "1":
                orderType = "待开始";
                helper.setText(R.id.bt_first, "取消订单")
                        .setText(R.id.bt_second, "补充病历")
                        .setText(R.id.bt_third, "联系医生");
                break;
            case "2":
                orderType = "进行中";
                helper.setText(R.id.bt_first, "查看详情")
                        .setText(R.id.bt_second, "异议")
                        .setText(R.id.bt_third, "确认");
                break;
            case "3":
                orderType = 1 == 1 ? "已结算" : "未结算";
                helper.setText(R.id.bt_first, "查看详情")
                        .setGone(R.id.bt_second, false)
                        .setGone(R.id.bt_third, false);
                break;
        }
        helper.setText(R.id.tv_lavlel1, "    " + "单台" + "   ")
                .setGone(R.id.tv_lavlel2, "1".equals(premium) ? false : true)
                .setText(R.id.tv_lavlel2, "    " + "加价" + "   ")
                .setText(R.id.tv_type, orderType)
                .addOnClickListener(R.id.cv_order_item)
                .addOnClickListener(R.id.stv_start_time)
                .addOnClickListener(R.id.stv_fee)
                .addOnClickListener(R.id.stv_name)
                .addOnClickListener(R.id.stv_address)
                .addOnClickListener(R.id.bt_first)
                .addOnClickListener(R.id.bt_second)
                .addOnClickListener(R.id.bt_third);

        tvName = helper.getView(R.id.tv_name);
        stvName = helper.getView(R.id.stv_name);
        stvAddress = helper.getView(R.id.stv_address);
        stvStartTime = helper.getView(R.id.stv_start_time);
        stvFee = helper.getView(R.id.stv_fee);

        tvName.setLeftString("手术名称:")
                .setCenterString(surgeryName);
        stvName.setLeftString("医院名称:")
                .setCenterString(hospitalName);
        stvAddress.setLeftString("医院地址:")
                .setCenterString(prov + city + dist + address);
        stvStartTime.setLeftString("开始时间:")
                .setCenterString(beginTime);
        stvFee.setLeftString("预计费用:")
                .setCenterString("¥ " + expectMoney + "元");
    }
}
