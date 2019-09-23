package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.HistoricalDemandBean;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class HistoricalDemandAdapter extends BaseQuickAdapter<HistoricalDemandBean.DataBean.HistoryListBean, BaseViewHolder> {
    private SuperTextView stvHistoricalDemand_title;

    public HistoricalDemandAdapter(@Nullable List<HistoricalDemandBean.DataBean.HistoryListBean> data) {
        super(R.layout.adapter_historical_demand, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HistoricalDemandBean.DataBean.HistoryListBean item) {
        helper.setText(R.id.tv_historical_demand_content, item.getContent());
        stvHistoricalDemand_title = helper.getView(R.id.stv_historical_demand_title);
        stvHistoricalDemand_title.setLeftString(item.getSketch())
                .setRightString("1".equals(item.getStatus()) ? "待处理" : "已处理");
    }
}
