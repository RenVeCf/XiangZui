package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.ImageSelectGridAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.UploadImgBean;
import com.ipd.xiangzui.common.view.TopView;
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

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.reactivex.functions.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static com.ipd.xiangzui.activity.HeadActivity.getImageRequestBody;
import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.UrlConfig.BASE_URL;
import static com.ipd.xiangzui.common.config.UrlConfig.UPLOAD_IMGS;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：发单-手术相关病历
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/9.
 */
public class SurgeryAboutMedicalRecordActivity extends BaseActivity implements ImageSelectGridAdapter.OnAddPicClickListener {

    @BindView(R.id.tv_surgery_about_medical_record)
    TopView tvSurgeryAboutMedicalRecord;
    @BindView(R.id.tv_top_title)
    TextView tvTopTitle;
    @BindView(R.id.rv_surgery_about_medical_record)
    RecyclerView rvSurgeryAboutMedicalRecord;

    private SweetAlertDialog sad;
    private ImageSelectGridAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_surgery_about_medical_record;
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
        ImmersionBar.setTitleBar(this, tvSurgeryAboutMedicalRecord);

        tvTopTitle.setText(getIntent().getStringExtra("title"));

        //设置RecyclerView方向和是否反转
        GridLayoutManager NotUseList = new GridLayoutManager(this, 4);
        rvSurgeryAboutMedicalRecord.setLayoutManager(NotUseList);
        rvSurgeryAboutMedicalRecord.setHasFixedSize(true); //item如果一样的大小，可以设置为true让RecyclerView避免重新计算大小
        rvSurgeryAboutMedicalRecord.setItemAnimator(new DefaultItemAnimator()); //默认动画

        mAdapter = new ImageSelectGridAdapter(this, this);
        mAdapter.setSelectMax(5);
        rvSurgeryAboutMedicalRecord.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(new ImageSelectGridAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                PictureSelector.create(SurgeryAboutMedicalRecordActivity.this).themeStyle(R.style.picture_default_style).openExternalPreview(position, mAdapter.mList);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    for (int i = 0; i < PictureSelector.obtainMultipleResult(data).size(); i++) {
                        LocalMedia localMedia = new LocalMedia();
                        localMedia.setCompressed(true);
                        localMedia.setCompressPath(PictureSelector.obtainMultipleResult(data).get(i).getCompressPath());
                        mAdapter.setSelectList(localMedia);
                        mAdapter.notifyDataSetChanged();
                    }
                    break;
            }
        }
    }

    @Override
    public void onAddPicClick() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(CAMERA, WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    PictureSelector.create(SurgeryAboutMedicalRecordActivity.this)
                            .openGallery(PictureMimeType.ofImage())
                            .maxSelectNum(5)// 最大图片选择数量 int
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
    }

    private void show() {
        if (sad == null) {
            sad = new SweetAlertDialog(this);
            sad.changeAlertType(SweetAlertDialog.PROGRESS_TYPE);
            sad.setTitleText(getResources().getString(R.string.loading));

            if (!sad.isShowing()) {
                sad.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (sad != null) {
            sad.dismiss();
            sad = null;
        }
    }

    @OnClick(R.id.bt_confirm)
    public void onViewClicked() {
        if (isFastClick()) {
            show();
            MultipartBody.Builder bbb = new MultipartBody.Builder().setType(MultipartBody.FORM);
            for (int i = 0; i < mAdapter.mList.size(); i++) {
                File f = new File(mAdapter.mList.get(i).getCompressPath());
                if (f != null) {
                    bbb.addFormDataPart("file", f.getName(), getImageRequestBody(mAdapter.mList.get(i).getCompressPath()));
                }
            }
            String sign = StringUtils.toUpperCase(MD5Utils.encodeMD5("{}" + SIGN));
            bbb.addFormDataPart("sign", sign);

            MultipartBody rrr = bbb.build();
            Request r = new Request.Builder()
                    .url(BASE_URL + UPLOAD_IMGS)
                    .post(rrr)
                    .build();
            OkHttpClient client = new OkHttpClient();
            client.newCall(r).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    ToastUtil.showShortToast(e + "");
                    dismissProgressDialog();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    UploadImgBean jsonTopicsBean = new Gson().fromJson(response.body().string(), UploadImgBean.class);
                    ToastUtil.showLongToast(jsonTopicsBean.getMsg());
                    dismissProgressDialog();
                    switch (jsonTopicsBean.getCode()) {
                        case 200:
                            setResult(RESULT_OK, new Intent().putExtra("imgUrl", jsonTopicsBean.getFileName()));
                            finish();
                            break;
                        case 900:
                            ToastUtil.showShortToast(jsonTopicsBean.getMsg());
                            //清除所有临时储存
                            SPUtil.clear(ApplicationUtil.getContext());
                            ApplicationUtil.getManager().finishActivity(MainActivity.class);
                            startActivity(new Intent(SurgeryAboutMedicalRecordActivity.this, CaptchaLoginActivity.class));
                            finish();
                            break;
                    }
                }
            });
        }
    }
}
