package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.AddressListBean;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class SelectAddressAdapter extends BaseQuickAdapter<AddressListBean.DataBean.AddrListBean, BaseViewHolder> {

    private SuperTextView stvSelectAddressItem;
    private int addressType;//1: 选择地址, 2: 地址管理

    public SelectAddressAdapter(@Nullable List<AddressListBean.DataBean.AddrListBean> data, int addressType) {
        super(R.layout.adapter_select_address, data);
        this.addressType = addressType;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressListBean.DataBean.AddrListBean item) {
        stvSelectAddressItem = helper.getView(R.id.stv_select_address_item);
        stvSelectAddressItem.setLeftTopString(item.getProv() + " " + item.getCity() + " " + item.getDist())
                .setLeftBottomString(item.getDetail());

//        if (addressType == 1) {
//            if (helper.getAdapterPosition() == 0)
//                helper.setGone(R.id.tv_select, true)
//                        .setGone(R.id.view_select_address_line, true);
//            else
//                helper.setGone(R.id.tv_select, false)
//                        .setGone(R.id.view_select_address_line, false);
//        } else {
//            if (item.isShow()) {
//                helper.setGone(R.id.tv_select, true)
//                        .setGone(R.id.view_select_address_line, true);
//            } else {
//                helper.setGone(R.id.tv_select, false)
//                        .setGone(R.id.view_select_address_line, false);
//            }
//        }
        if ("2".equals(item.getDefaultAddress())) {
            helper.setGone(R.id.tv_select, true)
                    .setGone(R.id.view_select_address_line, true);
        } else {
            helper.setGone(R.id.tv_select, false)
                    .setGone(R.id.view_select_address_line, false);
        }
        helper.addOnClickListener(R.id.ib_edit_address)
                .addOnClickListener(R.id.stv_select_address_item);
    }
}
