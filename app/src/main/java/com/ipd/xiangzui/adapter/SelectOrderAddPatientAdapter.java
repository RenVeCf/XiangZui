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
 * Time ： 2019/6/23.
 */
public class SelectOrderAddPatientAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {
    private SuperTextView stvAddPatientItem;

    public SelectOrderAddPatientAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_select_order_add_patient, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        stvAddPatientItem = helper.getView(R.id.stv_add_patient_item);
        stvAddPatientItem.setLeftString("患者1")
                .setRightString("李先生");
        helper.addOnClickListener(R.id.stv_add_patient_item);
    }
}