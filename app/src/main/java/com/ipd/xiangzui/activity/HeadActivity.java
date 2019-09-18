package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
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
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xuexiang.xui.widget.imageview.RadiusImageView;

import java.io.File;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.UrlConfig.BASE_LOCAL_URL;

/**
 * Description ：头像
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/8.
 */
public class HeadActivity extends BaseActivity<UploadImgContract.View, UploadImgContract.Presenter> implements UploadImgContract.View {

    @BindView(R.id.tv_head)
    TopView tvHead;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.riv_head)
    RadiusImageView rivHead;

    private String imgUrl = "";//上传成功后返回的图片地址

    @Override
    public int getLayoutId() {
        return R.layout.activity_head;
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
        ImmersionBar.setTitleBar(this, tvHead);

        tvTopTitle.setText(getIntent().getStringExtra("title"));
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    public static RequestBody getImageRequestBody(String filePath) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        return RequestBody.create(MediaType.parse(options.outMimeType), new File(filePath));
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
                                    rivHead.setImageDrawable(resource);
                                }
                            });

                    String picturePath = PictureSelector.obtainMultipleResult(data).get(0).getCompressPath();
                    TreeMap<String, RequestBody> map = new TreeMap<>();
                    map.put("file\";filename=\"" + ".jpeg", getImageRequestBody(picturePath));
                    String sign = StringUtils.toUpperCase(MD5Utils.encodeMD5("{}" + SIGN));
                    getPresenter().getUploadImg(map, sign, false, false);
                    break;
            }
        }
    }

    @OnClick({R.id.riv_head, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.riv_head:
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.request(CAMERA, WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            PictureSelector.create(HeadActivity.this)
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
                break;
            case R.id.sb_confirm:
                setResult(RESULT_OK, new Intent().putExtra("imgUrl", imgUrl));
                finish();
                break;
        }
    }

    @Override
    public void resultUploadImg(UploadImgBean data) {
        switch (data.getCode()) {
            case 200:
                imgUrl = data.getFileName();
                Glide.with(this)
                        .load(BASE_LOCAL_URL + data.getFileName())
                        .into(new SimpleTarget<Drawable>() {
                            @Override
                            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                rivHead.setImageDrawable(resource);
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
