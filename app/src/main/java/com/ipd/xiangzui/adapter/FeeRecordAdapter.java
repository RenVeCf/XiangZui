package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.FeeRecordBean;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class FeeRecordAdapter extends BaseQuickAdapter<FeeRecordBean.DataBean.RecordListBean, BaseViewHolder> {
    private SuperTextView stvConsumerDetailsItem;

    public FeeRecordAdapter(@Nullable List<FeeRecordBean.DataBean.RecordListBean> data) {
        super(R.layout.adapter_consumer_details, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FeeRecordBean.DataBean.RecordListBean item) {
        stvConsumerDetailsItem = helper.getView(R.id.stv_consumer_details_item);
        stvConsumerDetailsItem.setLeftTopString(item.getTitle())
                .setLeftBottomString(item.getCreateTime())
                .setRightString(("1".equals(item.getCategory()) ? "+" : "-") + item.getBalanceMoney());
    }
}
