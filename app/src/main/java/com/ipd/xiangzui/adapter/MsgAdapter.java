package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.MsgListBean;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;

import java.util.List;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class MsgAdapter extends BaseQuickAdapter<MsgListBean.DataBean.AddrListBean, BaseViewHolder> {

    public MsgAdapter(@Nullable List<MsgListBean.DataBean.AddrListBean> data) {
        super(R.layout.adapter_msg, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgListBean.DataBean.AddrListBean item) {
        helper.setText(R.id.tv_title, item.getTitle())
                .setText(R.id.tv_content, item.getContent());
    }
}
