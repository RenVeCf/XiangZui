package com.ipd.xiangzui.fragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.activity.AboutActivity;
import com.ipd.xiangzui.activity.AccountActivity;
import com.ipd.xiangzui.activity.HospitialInfoActivity;
import com.ipd.xiangzui.activity.InvoiceActivity;
import com.ipd.xiangzui.activity.MsgActivity;
import com.ipd.xiangzui.activity.SelectAddressActivity;
import com.ipd.xiangzui.activity.SettingActivity;
import com.ipd.xiangzui.activity.WalletActivity;
import com.ipd.xiangzui.base.BaseFragment;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_101;

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_101:
                    Glide.with(this)
                            .load(data.getStringExtra("modify_head"))
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                    ivHospitalHead.setImageDrawable(resource);
                                }
                            });
                    tvHospitalName.setText(data.getStringExtra("modify_name"));
                    break;
            }
        }
    }

    @OnClick({R.id.cl_hospital_info, R.id.tv_wallet, R.id.tv_address, R.id.tv_account, R.id.tv_msg, R.id.tv_invoice, R.id.tv_about, R.id.tv_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_hospital_info:
                startActivityForResult(new Intent(getContext(), HospitialInfoActivity.class), REQUEST_CODE_101);
                break;
            case R.id.tv_wallet:
                startActivity(new Intent(getContext(), WalletActivity.class));
                break;
            case R.id.tv_address:
                startActivity(new Intent(getContext(), SelectAddressActivity.class).putExtra("address_type", 2));
                break;
            case R.id.tv_account:
                startActivity(new Intent(getContext(), AccountActivity.class));
                break;
            case R.id.tv_msg:
                startActivity(new Intent(getContext(), MsgActivity.class));
                break;
            case R.id.tv_invoice:
                startActivity(new Intent(getContext(), InvoiceActivity.class));
                break;
            case R.id.tv_about:
                startActivity(new Intent(getContext(), AboutActivity.class));
                break;
            case R.id.tv_setting:
                startActivity(new Intent(getContext(), SettingActivity.class));
                break;
        }
    }
}
