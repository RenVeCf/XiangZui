package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.ImageSelectGridAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;

import butterknife.BindView;
import butterknife.OnClick;

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
        mAdapter.setSelectMax(9);
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
        PictureSelector.create(SurgeryAboutMedicalRecordActivity.this)
                .openGallery(PictureMimeType.ofImage())
                .maxSelectNum(9)// 最大图片选择数量 int
                .isCamera(true)
                .compress(true)
                .minimumCompressSize(100)
                .forResult(PictureConfig.CHOOSE_REQUEST);
    }

    @OnClick(R.id.bt_confirm)
    public void onViewClicked() {
        setResult(RESULT_OK, new Intent().putExtra("is_upload", 1));
        finish();
    }
}
