package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.ModifyUserInfoBean;
import com.ipd.xiangzui.bean.UploadImgBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.ModifyUserInfoContract;
import com.ipd.xiangzui.presenter.ModifyUserInfoPresenter;
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

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ipd.xiangzui.activity.HeadActivity.getImageRequestBody;
import static com.ipd.xiangzui.common.config.IConstants.AVATAR;
import static com.ipd.xiangzui.common.config.IConstants.NIKE_NAME;
import static com.ipd.xiangzui.common.config.IConstants.PHONE;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.common.config.UrlConfig.BASE_LOCAL_URL;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：医院信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class HospitialInfoActivity extends BaseActivity<ModifyUserInfoContract.View, ModifyUserInfoContract.Presenter> implements ModifyUserInfoContract.View {

    @BindView(R.id.riv_head)
    RadiusImageView rivHead;
    @BindView(R.id.tv_hospitial_info)
    TopView tvHospitialInfo;
    @BindView(R.id.et_phone)
    AppCompatEditText etPhone;
    @BindView(R.id.et_nickname)
    AppCompatEditText etNickname;

    private String headImgUrl;
    private int EDIT_OK = 10010;

    @Override
    public int getLayoutId() {
        return R.layout.activity_hospitial_info;
    }

    @Override
    public ModifyUserInfoContract.Presenter createPresenter() {
        return new ModifyUserInfoPresenter(this);
    }

    @Override
    public ModifyUserInfoContract.View createView() {
        return this;
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
        Glide.with(ApplicationUtil.getContext()).load(BASE_LOCAL_URL + SPUtil.get(this, AVATAR, "")).apply(new RequestOptions().placeholder(R.mipmap.ic_test_head)).into(rivHead);
        etNickname.setText(SPUtil.get(this, NIKE_NAME, "") + "");
        etPhone.setText(SPUtil.get(this, PHONE, "") + "");
    }

    @Override
    public void initListener() {
        etNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //输入时的调用
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mHandler.removeCallbacks(mRunnable);
                //800毫秒没有输入认为输入完毕
                mHandler.postDelayed(mRunnable, 1200);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (EDIT_OK == msg.what) {
                TreeMap<String, String> modifyUserInfoMap = new TreeMap<>();
                modifyUserInfoMap.put("userId", SPUtil.get(HospitialInfoActivity.this, USER_ID, "") + "");
                modifyUserInfoMap.put("nickname", etNickname.getText().toString().trim());
                modifyUserInfoMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyUserInfoMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getModifyUserInfo(modifyUserInfoMap, false, false);
            }
        }
    };

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            mHandler.sendEmptyMessage(EDIT_OK);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    TreeMap<String, RequestBody> map = new TreeMap<>();
                    map.put("file\";filename=\"" + ".jpeg", getImageRequestBody(PictureSelector.obtainMultipleResult(data).get(0).getCompressPath()));
                    String sign = StringUtils.toUpperCase(MD5Utils.encodeMD5("{}" + SIGN));
                    getPresenter().getUploadImg(map, sign, false, false);
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
                RxPermissions rxPermissions = new RxPermissions(this);
                rxPermissions.request(CAMERA, WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (granted) {
                            PictureSelector.create(HospitialInfoActivity.this)
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
            case R.id.tv_certification:
                startActivity(new Intent(this, AuthenticationActivity.class));
                break;
            case R.id.tv_modify_pwd:
                startActivity(new Intent(this, ModifyPwdActivity.class));
                break;
        }
    }

    @Override
    public void resultModifyUserInfo(ModifyUserInfoBean data) {
        switch (data.getCode()) {
            case 200:
                if (!isEmpty(headImgUrl)) {
                    SPUtil.put(this, AVATAR, headImgUrl);
                    Glide.with(this)
                            .load(BASE_LOCAL_URL + headImgUrl)
                            .into(new SimpleTarget<Drawable>() {
                                @Override
                                public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                                    rivHead.setImageDrawable(resource);
                                }
                            });
                } else {
                    SPUtil.put(this, NIKE_NAME, etNickname.getText().toString().trim());
                }
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
    public void resultUploadImg(UploadImgBean data) {
        switch (data.getCode()) {
            case 200:
                headImgUrl = data.getFileName();
                TreeMap<String, String> modifyUserInfoMap = new TreeMap<>();
                modifyUserInfoMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                modifyUserInfoMap.put("avatar", data.getFileName());
                modifyUserInfoMap.put("nickname", etNickname.getText().toString().trim());
                modifyUserInfoMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(modifyUserInfoMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getModifyUserInfo(modifyUserInfoMap, false, false);
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
