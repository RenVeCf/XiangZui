package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;
import butterknife.OnClick;

import static com.ipd.xiangzui.common.config.IConstants.REQUEST_CODE_100;

/**
 * Description ：发单-手术信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class SendOrderSurgicalInfoActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_surgical_info)
    TopView tvSendOrderSurgicalInfo;
    @BindView(R.id.ll_surgical_name)
    LinearLayout llSurgicalName;
    @BindView(R.id.et_surgical_name)
    EditText etSurgicalName;
    @BindView(R.id.et_hospital_name)
    EditText etHospitalName;
    @BindView(R.id.stv_surgical_address)
    SuperTextView stvSurgicalAddress;
    @BindView(R.id.stv_surgical_time)
    SuperTextView stvSurgicalTime;
    @BindView(R.id.stv_surgical_duration)
    SuperTextView stvSurgicalDuration;

    private int sendOrderType; //1: 单台, 2: 连台

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_surgical_info;
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
        ImmersionBar.setTitleBar(this, tvSendOrderSurgicalInfo);

        sendOrderType = getIntent().getIntExtra("sendOrderType", 0);
        if (sendOrderType == 2)
            llSurgicalName.setVisibility(View.GONE);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            switch (requestCode) {
                case REQUEST_CODE_100:
                    stvSurgicalAddress.setRightString(data.getStringExtra("select_top_address") + " " + data.getStringExtra("select_bottom_address"));
                    break;
            }
        }
    }

    @OnClick({R.id.stv_surgical_address, R.id.stv_surgical_time, R.id.stv_surgical_duration, R.id.sb_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_surgical_address: //手术地点
                startActivityForResult(new Intent(this, SelectAddressActivity.class).putExtra("address_type", 1), REQUEST_CODE_100);
                break;
            case R.id.stv_surgical_time: //手术开始时间
                break;
            case R.id.stv_surgical_duration: //预计手术时长
                break;
            case R.id.sb_next:
                startActivity(new Intent(this, SendOrderPatientInfoActivity.class).putExtra("sendOrderType", sendOrderType));
                break;
        }
    }
}
