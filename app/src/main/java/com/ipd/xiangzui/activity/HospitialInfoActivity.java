package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：医院信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class HospitialInfoActivity extends BaseActivity {

    @BindView(R.id.riv_head)
    RadiusImageView rivHead;
    @BindView(R.id.tv_hospitial_info)
    TopView tvHospitialInfo;
    @BindView(R.id.et_phone)
    AppCompatEditText etPhone;
    @BindView(R.id.et_nickname)
    AppCompatEditText etNickname;

    private String headImgUrl;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hospitial_info;
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
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvHospitialInfo);
    }

    @Override
    public void initData() {
        rivHead.setImageResource(R.mipmap.ic_test_head);
        etPhone.setText("18321836625");
        etNickname.setText("上海东方医院");
    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    headImgUrl = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
                    Glide.with(this)
                            .load(headImgUrl)
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                    rivHead.setImageDrawable(resource);
                                }
                            });
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK, new Intent().putExtra("modify_head", headImgUrl).putExtra("modify_name", etNickname.getText().toString().trim()));
        finish();
    }

    @OnClick({R.id.ll_top_back, R.id.ll_head, R.id.tv_certification, R.id.tv_modify_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                setResult(RESULT_OK, new Intent().putExtra("modify_head", headImgUrl).putExtra("modify_name", etNickname.getText().toString().trim()));
                finish();
                break;
            case R.id.ll_head:
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .isCamera(true)
                        .compress(true)
                        .minimumCompressSize(100)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.tv_certification:
                startActivity(new Intent(this, CertificationActivity.class));
                break;
            case R.id.tv_modify_pwd:
                startActivity(new Intent(this, ModifyPwdActivity.class));
                break;
        }
    }
}
