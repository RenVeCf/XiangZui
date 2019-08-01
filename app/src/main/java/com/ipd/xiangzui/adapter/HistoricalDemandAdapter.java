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
public class HistoricalDemandAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {
    private SuperTextView stvHistoricalDemand_title;

    public HistoricalDemandAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_historical_demand, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        helper.setText(R.id.tv_historical_demand_content, "科曼的产品涵盖了电生理监护、心电诊断、超声母婴监护、呼吸麻醉、婴儿保育以及手术室设备等六大领域");
        stvHistoricalDemand_title = helper.getView(R.id.stv_historical_demand_title);
        stvHistoricalDemand_title.setLeftString("VIP服务")
                .setRightString("已处理");
    }
}
