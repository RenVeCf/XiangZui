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
public class MsgAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    public MsgAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_msg, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        helper.setText(R.id.tv_title, "手术已被接单，请准备手术所需药品，器械")
                .setText(R.id.tv_content, "手术已被接单，请准备手术所需药品、器械");
    }
}
