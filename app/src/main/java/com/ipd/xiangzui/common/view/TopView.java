package com.ipd.xiangzui.common.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ipd.xiangzui.R;
import com.ipd.xiangzui.activity.RechargeRecordActivity;
import com.ipd.xiangzui.activity.WithdrawalsRecordActivity;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.isClickUtil;


/**
 * Description : 公用标题栏
 * Author : rmy
 * Email : 942685687@qq.com
 * Time : 2017/11/loading1
 */

public class TopView extends RelativeLayout implements View.OnClickListener {

    private TextView tvTopTitle;
    private LinearLayout llTopBack;
    private ImageButton ivTopCustomerService;
    private Button btTopWithdrawalsRecord, btTopRechargeRecord, btTopDelAddress;

    //各icon是否显示
    private Boolean isBack, isCustomerService, isTopWithdrawalsRecord, isTopRechargeRecord, isTopDelAddress;
    private Context mContext;

    public TopView(Context context) {
        super(context);
        initValues(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initValues(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopView);
        tvTopTitle.setText(typedArray.getString(R.styleable.TopView_title));
        tvTopTitle.setTextColor(typedArray.getColor(R.styleable.TopView_title_color, getResources().getColor(R.color.black)));
        isBack = typedArray.getBoolean(R.styleable.TopView_is_back, false);
        isCustomerService = typedArray.getBoolean(R.styleable.TopView_is_customer_service, false);
        isTopWithdrawalsRecord = typedArray.getBoolean(R.styleable.TopView_is_withdrawals_record, false);
        isTopRechargeRecord = typedArray.getBoolean(R.styleable.TopView_is_recharge_record, false);
        isTopDelAddress = typedArray.getBoolean(R.styleable.TopView_is_del_address, false);
        typedArray.recycle();

        llTopBack.setVisibility(isBack ? View.VISIBLE : View.GONE);
        ivTopCustomerService.setVisibility(isCustomerService ? View.VISIBLE : View.GONE);
        btTopWithdrawalsRecord.setVisibility(isTopWithdrawalsRecord ? View.VISIBLE : View.GONE);
        btTopRechargeRecord.setVisibility(isTopRechargeRecord ? View.VISIBLE : View.GONE);
        btTopDelAddress.setVisibility(isTopDelAddress ? View.VISIBLE : View.GONE);
    }

    public TopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initValues(context);
    }

    private void initValues(final Context context) {
        mContext = context;
        View.inflate(context, R.layout.top_view, this);
        tvTopTitle = (TextView) this.findViewById(R.id.tv_top_title);
        llTopBack = (LinearLayout) this.findViewById(R.id.ll_top_back);
        ivTopCustomerService = (ImageButton) this.findViewById(R.id.ib_top_customer_service);
        btTopWithdrawalsRecord = (Button) this.findViewById(R.id.bt_top_withdrawals_record);
        btTopRechargeRecord = (Button) this.findViewById(R.id.bt_top_recharge_record);
        btTopDelAddress = (Button) this.findViewById(R.id.bt_top_del_address);

        llTopBack.setOnClickListener(this);
        ivTopCustomerService.setOnClickListener(this);
        btTopWithdrawalsRecord.setOnClickListener(this);
        btTopRechargeRecord.setOnClickListener(this);
        btTopDelAddress.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_top_back:
                if (mContext instanceof Activity && isClickUtil.isFastClick()) {
                    ((Activity) mContext).finish();
                    if (((Activity) mContext).getCurrentFocus() != null) {
                        ((InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }
                break;
            case R.id.ib_top_customer_service:
                //                ApplicationUtil.getContext().startActivity(new Intent(ApplicationUtil.getContext(), CustomerServiceActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.bt_top_withdrawals_record:
                ApplicationUtil.getContext().startActivity(new Intent(ApplicationUtil.getContext(), WithdrawalsRecordActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            case R.id.bt_top_recharge_record:
                ApplicationUtil.getContext().startActivity(new Intent(ApplicationUtil.getContext(), RechargeRecordActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                break;
            default:
                break;
        }
    }
}
