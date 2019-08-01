package com.ipd.xiangzui.common.view;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ipd.xiangzui.R;

/**
 * Description ：底部弹出Dialog
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/24.
 */
public abstract class BottomPayDialog extends Dialog implements View.OnClickListener {
    private Activity activity;
    private LinearLayout llAliPay, llWechatPay, llRewardBalance;
    private ImageView ivAliPay, ivWechatPay, ivRewardBalance;
    private TextView tvRewardBalance;
    private Button btPay;
    private int payType = 0; //1:支付宝，2:微信
    private boolean isRewardBalance = false; //true:使用奖励余额, false:不使用奖励余额

    public BottomPayDialog(Activity activity) {
        super(activity, R.style.MyDialogTheme);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bottom_pay);

        llAliPay = (LinearLayout) findViewById(R.id.ll_ali_pay);
        ivAliPay = (ImageView) findViewById(R.id.iv_ali_pay);
        llWechatPay = (LinearLayout) findViewById(R.id.ll_wechat_pay);
        ivWechatPay = (ImageView) findViewById(R.id.iv_wechat_pay);
        llRewardBalance = (LinearLayout) findViewById(R.id.ll_reward_balance);
        ivRewardBalance = (ImageView) findViewById(R.id.iv_reward_balance);
        tvRewardBalance = (TextView) findViewById(R.id.tv_reward_balance);
        btPay = (Button) findViewById(R.id.bt_pay);

        tvRewardBalance.setText("");

        llAliPay.setOnClickListener(this);
        llWechatPay.setOnClickListener(this);
        llRewardBalance.setOnClickListener(this);
        btPay.setOnClickListener(this);

        setViewLocation();
        setCanceledOnTouchOutside(true);//外部点击取消
    }

    /**
     * 设置dialog位于屏幕底部
     */
    private void setViewLocation() {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;

        Window window = this.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.x = 0;
        lp.y = height;
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // 设置显示位置
        onWindowAttributesChanged(lp);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_ali_pay:
                ivAliPay.setImageResource(R.drawable.ic_check_blue);
                ivWechatPay.setImageResource(R.drawable.ic_check_gray);
                payType = 1;
                break;
            case R.id.ll_wechat_pay:
                ivAliPay.setImageResource(R.drawable.ic_check_blue);
                ivWechatPay.setImageResource(R.drawable.ic_check_gray);
                payType = 2;
                break;
            case R.id.ll_reward_balance:
                Drawable.ConstantState drawableRewardBalance = ivRewardBalance.getDrawable().getConstantState();
                if (activity.getResources().getDrawable(R.drawable.ic_check_blue).getConstantState().equals(drawableRewardBalance)) {
                    ivRewardBalance.setImageResource(R.drawable.ic_check_gray);
                    isRewardBalance = true;
                } else {
                    ivRewardBalance.setImageResource(R.drawable.ic_check_blue);
                    isRewardBalance = false;
                }
                break;
            case R.id.bt_pay:
                goPay(payType, isRewardBalance);
                this.cancel();
                break;
        }
    }

    public abstract void goPay(int payType, boolean isRewardBalance);
}
