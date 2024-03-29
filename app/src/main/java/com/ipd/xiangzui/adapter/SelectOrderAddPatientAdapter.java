package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.SendOrderDataBean;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class SelectOrderAddPatientAdapter<T> extends BaseQuickAdapter<T, BaseViewHolder> {
    private SuperTextView stvAddPatientItem;
    private int type;//1: 发单时的患者样式, 2: 订单时的患者样式

    public SelectOrderAddPatientAdapter(@Nullable List<T> data, int type) {
        super(R.layout.adapter_select_order_add_patient, data);
        this.type = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        stvAddPatientItem = helper.getView(R.id.stv_add_patient_item);

        if (item instanceof SendOrderDataBean.TwoOrderBean) {
            stvAddPatientItem.setLeftString("患者" + (helper.getAdapterPosition() + 1))
                    .setRightString(((SendOrderDataBean.TwoOrderBean) item).getPatientName());
            if (type == 2)
                stvAddPatientItem.setRightIcon(0)
                        .setLeftTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_bottom_navigation))
                        .setRightTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_bottom_navigation_select));
        } else if (item instanceof OrderDetailsBean.DataBean.OrderDetailBean) {
            stvAddPatientItem.setLeftString("患者" + (helper.getAdapterPosition() + 1))
                    .setRightString(((OrderDetailsBean.DataBean.OrderDetailBean) item).getPatientName());
            if (type == 2)
                stvAddPatientItem.setRightIcon(0)
                        .setLeftTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_bottom_navigation))
                        .setRightTextColor(ApplicationUtil.getContext().getResources().getColor(R.color.tx_bottom_navigation_select));
        }
        helper.addOnClickListener(R.id.stv_add_patient_item);
    }
}
