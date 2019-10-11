package com.ipd.xiangzui.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.ipd.xiangzui.R;
import com.ipd.xiangzui.utils.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

import io.reactivex.functions.Consumer;

import static android.Manifest.permission.CALL_PHONE;
import static com.ipd.xiangzui.common.config.IConstants.SERVICE_PHONE;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：自定义Dialog
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/24.
 */
public abstract class CallPhoneDialog extends Dialog implements View.OnClickListener {
    private SuperButton btCancel, btConfirm;
    private AppCompatTextView tvCallNum;
    private Activity activity;
    private String phoneNum;//医生电话

    public CallPhoneDialog(Activity activity, String phoneNum) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
        this.phoneNum = phoneNum;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_call_phone);

        tvCallNum = (AppCompatTextView) findViewById(R.id.tv_call_num);
        btCancel = (SuperButton) findViewById(R.id.bt_cancel);
        btConfirm = (SuperButton) findViewById(R.id.bt_confirm);

        if (!isEmpty(phoneNum))
            tvCallNum.setText("拨打 " + phoneNum);
        else
            tvCallNum.setText("拨打 " + SERVICE_PHONE);

        btCancel.setOnClickListener(this);
        btConfirm.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(false);//外部点击取消
    }

    /**
     * 设置dialog位于屏幕中部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }

    private void rxPermissionCall() {
        RxPermissions rxPermissions = new RxPermissions((FragmentActivity) activity);
        rxPermissions.request(CALL_PHONE).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean granted) throws Exception {
                if (granted) {
                    callPhone();
                } else {
                    // 权限被拒绝
                    ToastUtil.showLongToast("权限被拒绝");
                }
            }
        });
    }

    //打电话
    private void callPhone() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data;
        if (!isEmpty(phoneNum))
            data = Uri.parse("tel:" + phoneNum);
        else
            data = Uri.parse("tel:" + SERVICE_PHONE);
        intent.setData(data);
        if (ActivityCompat.checkSelfPermission(activity, CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        activity.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (isFastClick()) {
            switch (v.getId()) {
                case R.id.bt_cancel:
                    this.cancel();
                    break;
                case R.id.bt_confirm:
                    rxPermissionCall();
                    this.cancel();
                    break;
            }
        }
    }
}
