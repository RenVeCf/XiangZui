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
public class AccountAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    private SuperTextView stvAccountItem;

    public AccountAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_account, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        stvAccountItem = helper.getView(R.id.stv_account_item);
        stvAccountItem.setLeftTopString("上海涵宇科技有限公司")
                .setLeftString("中国建设银行")
                .setLeftBottomString("51032324324432");
        if (helper.getAdapterPosition() == 0)
            helper.setGone(R.id.tv_select, true)
                    .setGone(R.id.view_account_line, true);
        else
            helper.setGone(R.id.tv_select, false)
                    .setGone(R.id.view_account_line, false);
        helper.addOnClickListener(R.id.ib_edit_account)
                .addOnClickListener(R.id.stv_account_item);
    }
}
