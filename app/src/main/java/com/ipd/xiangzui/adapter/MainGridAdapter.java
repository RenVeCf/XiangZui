package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class MainGridAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

    public MainGridAdapter(@Nullable List<Integer> data) {
        super(R.layout.adapter_main_grid, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Integer item) {
        switch (item) {
            case 0:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_select_order)
                        .setText(R.id.tv_main_grid_item, "订单");
                break;
            case 1:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_send_order)
                        .setText(R.id.tv_main_grid_item, "发单");
                break;
            case 2:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_wallet)
                        .setText(R.id.tv_main_grid_item, "钱包");
                break;
            case 3:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_insurance)
                        .setText(R.id.tv_main_grid_item, "保险");
                break;
            case 4:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_vip)
                        .setText(R.id.tv_main_grid_item, "VIP服务");
                break;
            case 5:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_expert)
                        .setText(R.id.tv_main_grid_item, "专家会诊");
                break;
            case 6:
                helper.setImageResource(R.id.aciv_main_grid_item, R.drawable.ic_hocus)
                        .setText(R.id.tv_main_grid_item, "麻醉筹建");
                break;
            case 7:
                helper.setImageResource(R.id.aciv_main_grid_item, R.mipmap.ic_rent_equipment)
                        .setText(R.id.tv_main_grid_item, "设备租赁");
                break;
        }
    }
}
