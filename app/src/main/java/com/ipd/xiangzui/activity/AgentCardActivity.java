package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.UploadImgBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.UploadImgContract;
import com.ipd.xiangzui.presenter.UploadImgPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ipd.xiangzui.activity.HeadActivity.getImageRequestBody;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：代理人身份证
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class AgentCardActivity extends BaseActivity<UploadImgContract.View, UploadImgContract.Presenter> implements UploadImgContract.View {

    @BindView(R.id.tv_agent_card)
    TopView tvAgentCard;
    @BindView(R.id.riv_positive_card)
    RadiusImageView rivPositiveCard;
    @BindView(R.id.riv_negative_card)
    RadiusImageView rivNegativeCard;
    @BindView(R.id.ll_card)
    LinearLayout llCard;

    private int type; //0:修改，1:查看
    private int cardType = 0; // 1: 正面，2: 反面
    private String positiveUrl, negativeUrl; //正面图片地址，反面图片地址
    private List<LocalMedia> medias = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.activity_agent_card;
    }

    @Override
    public UploadImgContract.Presenter createPresenter() {
        return new UploadImgPresenter(this);
    }

    @Override
    public UploadImgContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvAgentCard);

        type = getIntent().getIntExtra("cardImgType", 0);
        if (type == 1)
            llCard.setVisibility(View.GONE);
        positiveUrl = getIntent().getStringExtra("positiveUrl");
        negativeUrl = getIntent().getStringExtra("negativeUrl");
        if (!isEmpty(positiveUrl))
            Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + positiveUrl).apply(new RequestOptions().placeholder(R.mipmap.bg_positive_card)).into(rivPositiveCard);
        if (!isEmpty(negativeUrl))
            Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + negativeUrl).apply(new RequestOptions().placeholder(R.mipmap.bg_negative_card)).into(rivNegativeCard);
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
                    String picturePath = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
                    TreeMap<String, RequestBody> map = new TreeMap<>();
                    map.put("file\";filename=\"" + ".jpeg", getImageRequestBody(picturePath));
                    String sign = StringUtils.toUpperCase(MD5Utils.encodeMD5("{}" + SIGN));
                    getPresenter().getUploadImg(map, sign, false, false);
                    break;
            }
        }
    }

    @OnClick({R.id.riv_positive_card, R.id.riv_negative_card, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.riv_positive_card:
                if (type == 0) {
                    cardType = 1;
                    RxPermissions rxPermissions = new RxPermissions(this);
                    rxPermissions.request(CAMERA, WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (granted) {
                                PictureSelector.create(AgentCardActivity.this)
                                        .openGallery(PictureMimeType.ofImage())
                                        .maxSelectNum(1)// 最大图片选择数量 int
                                        .isCamera(true)
                                        .compress(true)
                                        .minimumCompressSize(100)
                                        .forResult(PictureConfig.CHOOSE_REQUEST);
                            } else {
                                // 权限被拒绝
                                ToastUtil.showLongToast(R.string.permission_rejected);
                            }
                        }
                    });
                } else {
                    medias.clear();
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setCompressed(true);
                    localMedia.setCompressPath(BASE_LOCAL_URL + positiveUrl);
                    medias.add(localMedia);
                    PictureSelector.create(this).themeStyle(R.style.picture_default_style).openExternalPreview(0, medias);
                }
                break;
            case R.id.riv_negative_card:
                if (type == 0) {
                    cardType = 2;
                    RxPermissions rxPermissions1 = new RxPermissions(this);
                    rxPermissions1.request(CAMERA, WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                        @Override
                        public void accept(Boolean granted) throws Exception {
                            if (granted) {
                                PictureSelector.create(AgentCardActivity.this)
                                        .openGallery(PictureMimeType.ofImage())
                                        .maxSelectNum(1)// 最大图片选择数量 int
                                        .isCamera(true)
                                        .compress(true)
                                        .minimumCompressSize(100)
                                        .forResult(PictureConfig.CHOOSE_REQUEST);
                            } else {
                                // 权限被拒绝
                                ToastUtil.showLongToast(R.string.permission_rejected);
                            }
                        }
                    });
                } else {
                    medias.clear();
                    LocalMedia localMedia = new LocalMedia();
                    localMedia.setCompressed(true);
                    localMedia.setCompressPath(BASE_LOCAL_URL + negativeUrl);
                    medias.add(localMedia);
                    PictureSelector.create(this).themeStyle(R.style.picture_default_style).openExternalPreview(0, medias);
                }
                break;
            case R.id.sb_confirm:
                Drawable.ConstantState drawablePositive = rivPositiveCard.getDrawable().getConstantState();
                Drawable.ConstantState drawableNegative = rivNegativeCard.getDrawable().getConstantState();
                if (!drawablePositive.equals(ContextCompat.getDrawable(this, R.mipmap.bg_positive_card).getConstantState()) && !drawableNegative.equals(ContextCompat.getDrawable(this, R.mipmap.bg_negative_card).getConstantState())) {
                    setResult(RESULT_OK, new Intent().putExtra("positiveUrl", positiveUrl).putExtra("negativeUrl", negativeUrl));
                    finish();
                    break;
                } else
                    ToastUtil.showShortToast("请将资料填写完整！");
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        switch (data.getCode()) {
            case 200:
                Glide.with(this)
                        .load(BASE_LOCAL_URL + data.getFileName())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                switch (cardType) {
                                    case 1:
                                        positiveUrl = data.getFileName();
                                        rivPositiveCard.setImageDrawable(resource);
                                        break;
                                    case 2:
                                        negativeUrl = data.getFileName();
                                        rivNegativeCard.setImageDrawable(resource);
                                        break;
                                }

                            }
                        });
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
