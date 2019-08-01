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

    private SuperTextView stvLablel;
    private SuperTextView stvName;
    private SuperTextView stvAddress;
    private SuperTextView stvStartTime;
    private SuperTextView stvFee;

    public MainOrderAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_main_order, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        stvLablel = helper.getView(R.id.stv_lablel);
        stvName = helper.getView(R.id.stv_name);
        stvAddress = helper.getView(R.id.stv_address);
        stvStartTime = helper.getView(R.id.stv_start_time);
        stvFee = helper.getView(R.id.stv_fee);

        stvLablel.setLeftString("    " + "单台" + "   ")
                .setCenterString("阑尾切除术")
                .setRightString("待接单");
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
