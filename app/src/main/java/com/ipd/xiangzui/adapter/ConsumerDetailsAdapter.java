package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.WalletBean;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class ConsumerDetailsAdapter extends BaseQuickAdapter<WalletBean.DataBean.BalaListBean, BaseViewHolder> {
    private SuperTextView stvConsumerDetailsItem;

    public ConsumerDetailsAdapter(@Nullable List<WalletBean.DataBean.BalaListBean> data) {
        super(R.layout.adapter_consumer_details, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, WalletBean.DataBean.BalaListBean item) {
        stvConsumerDetailsItem = helper.getView(R.id.stv_consumer_details_item);
        stvConsumerDetailsItem.setLeftTopString(item.getTitle())
                .setLeftBottomString(item.getCreateTime())
                .setRightString(("1".equals(item.getCategory()) ? "+" : "-") + item.getBalanceMoney());
    }
}
