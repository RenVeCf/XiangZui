package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import androidx.annotation.Nullable;
import android.view.View;

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
 * Description ：发单-患者身份证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class PatientIdCardActivity extends BaseActivity {

    @BindView(R.id.tv_patient_id_card)
    TopView tvPatientIdCard;
    @BindView(R.id.riv_positive_card)
    RadiusImageView rivPositiveCard;
    @BindView(R.id.riv_negative_card)
    RadiusImageView rivNegativeCard;

    private int cardType = 0; // 1: 正面，2: 反面

    @Override
    public int getLayoutId() {
        return R.layout.activity_patient_id_card;
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
        ImmersionBar.setTitleBar(this, tvPatientIdCard);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    Glide.with(this)
                            .load(PictureSelector.obtainMultipleResult(data).get(0).getCompressPath())
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                    switch (cardType) {
                                        case 1:
                                            rivPositiveCard.setImageDrawable(resource);
                                            break;
                                        case 2:
                                            rivNegativeCard.setImageDrawable(resource);
                                            break;
                                    }

                                }
                            });
                    break;
            }
        }
    }

    @OnClick({R.id.riv_positive_card, R.id.riv_negative_card, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.riv_positive_card:
                cardType = 1;
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .isCamera(true)
                        .compress(true)
                        .minimumCompressSize(100)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.riv_negative_card:
                cardType = 2;
                PictureSelector.create(this)
                        .openGallery(PictureMimeType.ofImage())
                        .maxSelectNum(1)// 最大图片选择数量 int
                        .isCamera(true)
                        .compress(true)
                        .minimumCompressSize(100)
                        .forResult(PictureConfig.CHOOSE_REQUEST);
                break;
            case R.id.sb_confirm:
                setResult(RESULT_OK, new Intent().putExtra("patient_card", 1));
                finish();
                break;
        }
    }
}
