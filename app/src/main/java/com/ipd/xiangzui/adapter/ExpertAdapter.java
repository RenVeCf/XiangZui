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
public class ExpertAdapter extends BaseQuickAdapter<TestMultiItemEntityBean, BaseViewHolder> {

    public ExpertAdapter(@Nullable List<TestMultiItemEntityBean> data) {
        super(R.layout.adapter_expert, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TestMultiItemEntityBean item) {
        helper.setImageResource(R.id.riv_expert, R.mipmap.ic_test_expert)
                .setText(R.id.tv_expert_name, "张正国")
                .setText(R.id.tv_expert_title, "主治医师")
                .setText(R.id.tv_expert_content, "擅长项目：各种麻醉管理及术后危重病人的 处理，尤其是肝移植病人术后的处理");
    }
}
