package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class RentEquipmentAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    public RentEquipmentAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_rent_equipment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        helper.setImageResource(R.id.riv_rent_equipment, R.mipmap.ic_test_rent_equipment)
                .setText(R.id.tv_rent_equipment_name, "科曼医疗设备")
                .setText(R.id.tv_rent_equipment_content, "科曼的产品涵盖了电生理监护、心电诊断、超声母婴监护、呼吸麻醉、婴儿保育以及手术室设备等六大领域");
    }
}
