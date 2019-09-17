package com.ipd.xiangzui.adapter;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.bean.HomeBean;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.ipd.xiangzui.utils.ApplicationUtil;

import java.util.List;

import static com.ipd.xiangzui.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/4.
 */
public class RecyclerViewBannerAdapter extends BaseQuickAdapter<HomeBean.DataBean.PictureListBean, BaseViewHolder> {

    public RecyclerViewBannerAdapter(@Nullable List<HomeBean.DataBean.PictureListBean> data) {
        super(R.layout.adapter_recyclerview_banner, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeBean.DataBean.PictureListBean s) {
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + s.getPicPath()).apply(new RequestOptions().placeholder(R.mipmap.bg_test_banner)).into((AppCompatImageView) helper.getView(R.id.aciv_banner_item));
    }
}
