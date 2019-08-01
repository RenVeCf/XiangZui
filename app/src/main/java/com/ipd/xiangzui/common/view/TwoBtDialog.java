package com.ipd.xiangzui.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ipd.xiangzui.R;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

/**
 * Description ：自定义Dialog
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public abstract class TwoBtDialog extends Dialog implements View.OnClickListener {
    private TextView tvContent;
    private SuperButton sbCancel, sbConfirm;
    private Activity activity;
    private String content = ""; //提示内容
    private String confirmTx = ""; //确定按钮内容

    public TwoBtDialog(Activity activity, String content, String confirmTx) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
        this.content = content;
        this.confirmTx = confirmTx;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_two_bt);

        tvContent = (TextView) findViewById(R.id.tv_content);
        sbCancel = (SuperButton) findViewById(R.id.sb_cancel);
        sbConfirm = (SuperButton) findViewById(R.id.sb_confirm);

        tvContent.setText(content);
        sbConfirm.setText(confirmTx);

        sbCancel.setOnClickListener(this);
        sbConfirm.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消
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


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sb_cancel:
                this.cancel();
                break;
            case R.id.sb_confirm:
                confirm();
                this.cancel();
                break;
        }
    }

    public abstract void confirm();
}
