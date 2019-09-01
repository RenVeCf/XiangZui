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
import com.xuexiang.xui.widget.edittext.ClearEditText;
import com.xuexiang.xui.widget.textview.supertextview.SuperButton;

/**
 * Description ：自定义Dialog
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public abstract class EditDialog extends Dialog implements View.OnClickListener {
    private ClearEditText etContent;
    private SuperButton sbCancel, sbConfirm;
    private Activity activity;

    public EditDialog(Activity activity) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_edit);

        etContent = (ClearEditText) findViewById(R.id.et_content);
        sbCancel = (SuperButton) findViewById(R.id.sb_cancel);
        sbConfirm = (SuperButton) findViewById(R.id.sb_confirm);

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
                confirm(etContent.getText().toString().trim());
                this.cancel();
                break;
        }
    }

    public abstract void confirm(String content);
}
