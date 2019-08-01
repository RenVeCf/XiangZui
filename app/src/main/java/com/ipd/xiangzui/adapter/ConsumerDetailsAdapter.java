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
public class ConsumerDetailsAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {
    private SuperTextView stvConsumerDetailsItem;

    public ConsumerDetailsAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_consumer_details, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        stvConsumerDetailsItem = helper.getView(R.id.stv_consumer_details_item);
        stvConsumerDetailsItem.setLeftTopString("订单：20190613001")
                .setLeftBottomString("2018-09-09 15:45")
                .setRightString("- 300.00");
    }
}
