package com.ipd.xiangzui.fragment;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseFragment;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;

/**
 * Description ：我的
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/1.
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.tv_my)
    TopView tvMy;
    @BindView(R.id.tv_is_certification)
    AppCompatTextView tvIsCertification;
    @BindView(R.id.tv_hospital_name)
    AppCompatTextView tvHospitalName;
    @BindView(R.id.iv_hospital_head)
    RadiusImageView ivHospitalHead;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden)
            ImmersionBar.with(this).statusBarDarkFont(false).init();
    }

    @Override
    public void init(View view) {
        //防止状态栏和标题重叠
        ImmersionBar.with(this).statusBarDarkFont(false).init();
        ImmersionBar.setTitleBar(getActivity(), tvMy);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        ivHospitalHead.setImageResource(R.mipmap.ic_test_head);
        tvHospitalName.setText("上海东方医院");
        tvIsCertification.setText("未认证");
    }
}
