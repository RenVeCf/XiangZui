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
public class SelectAddressAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    private SuperTextView stvSelectAddressItem;

    public SelectAddressAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_select_address, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        stvSelectAddressItem = helper.getView(R.id.stv_select_address_item);
        stvSelectAddressItem.setLeftTopString("上海市 青浦区")
                .setLeftBottomString("华徐公路888号1号楼2楼IPD");
        if (helper.getAdapterPosition() == 0)
            helper.setGone(R.id.tv_select, true)
                    .setGone(R.id.view_select_address_line, true);
        else
            helper.setGone(R.id.tv_select, false)
                    .setGone(R.id.view_select_address_line, false);
        if (item.isShow()) {//TODO 不知道选中效果是啥
        } else {
        }
        helper.addOnClickListener(R.id.ib_edit_address)
                .addOnClickListener(R.id.stv_select_address_item);
    }
}
