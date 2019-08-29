package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class MainOrderAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    private SuperTextView stvName;
    private SuperTextView stvAddress;
    private SuperTextView stvStartTime;
    private SuperTextView stvFee;

    public MainOrderAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_main_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        String orderType = "";

        switch (item.getOrderType()) {
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
                .setGone(R.id.tv_lavlel2, item.isAddFee() ? true : false)
                .setText(R.id.tv_lavlel2, "    " + "加价" + "   ")
                .setText(R.id.tv_name, "阑尾切除术")
                .setText(R.id.tv_type, orderType)
                .addOnClickListener(R.id.cv_order_item)
                .addOnClickListener(R.id.stv_start_time)
                .addOnClickListener(R.id.stv_fee)
                .addOnClickListener(R.id.stv_name)
                .addOnClickListener(R.id.stv_address)
                .addOnClickListener(R.id.bt_first)
                .addOnClickListener(R.id.bt_second)
                .addOnClickListener(R.id.bt_third);

        stvName = helper.getView(R.id.stv_name);
        stvAddress = helper.getView(R.id.stv_address);
        stvStartTime = helper.getView(R.id.stv_start_time);
        stvFee = helper.getView(R.id.stv_fee);

        stvName.setLeftString("医院名称:")
                .setCenterString("上海市第一人民医院");
        stvAddress.setLeftString("医院地址:")
                .setCenterString("上海市虹口区海宁路100号");
        stvStartTime.setLeftString("开始时间:")
                .setCenterString("2019-05-05 10:3");
        stvFee.setLeftString("预计费用:")
                .setCenterString("¥ " + 300 + "元");
    }
}
