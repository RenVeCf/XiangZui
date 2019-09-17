package com.ipd.xiangzui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：发单-费用信息
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/9.
 */
public class SendOrderFeeInfoActivity extends BaseActivity {

    @BindView(R.id.tv_send_order_fee_info)
    TopView tvSendOrderFeeInfo;
    @BindView(R.id.stv_surgery)
    SuperTextView stvSurgery;
    @BindView(R.id.et_add_fee)
    EditText etAddFee;
    @BindView(R.id.stv_surgery_num)
    SuperTextView stvSurgeryNum;
    @BindView(R.id.stv_add_fee)
    SuperTextView stvAddFee;

    private List<String> listData;
    private OptionsPickerView pvOptions; //条件选择器
    private int sendOrderType; //1: 单台, 2: 连台

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order_fee_info;
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
        ImmersionBar.setTitleBar(this, tvSendOrderFeeInfo);

        sendOrderType = getIntent().getIntExtra("sendOrderType", 0);
        if (sendOrderType == 1)
            stvSurgeryNum.setVisibility(View.GONE);
    }

    @Override
    public void initData() {
        stvSurgery.setCenterString("(加急费: ¥50元)");
        stvAddFee.setRightString("¥ 300元");
    }

    @Override
    public void initListener() {

    }

    //条件选择器
    private void showPickerView() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        // 隐藏软键盘
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        listData = getSurgicalNumData();
        pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                stvSurgeryNum.setRightString(listData.get(options1));
            }
        })
                .setDividerColor(getResources().getColor(R.color.white))//设置分割线的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                    @Override
                    public void customLayout(View v) {
                        final TextView tvTitle = (TextView) v.findViewById(R.id.tv_title);
                        tvTitle.setText("选择连台手术数量");
                        final Button tvSubmit = (Button) v.findViewById(R.id.bt_pickview_confirm);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvOptions.returnData();
                                pvOptions.dismiss();
                            }
                        });
                    }
                })
                .setDecorView((ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content))
                .setSelectOptions(0)//设置选择第一个
                .setOutSideCancelable(true)//点击背的地方不消失
                .build();//创建
        pvOptions.setPicker(listData);
        pvOptions.show();
    }

    private List<String> getSurgicalNumData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "台");
        }
        return list;
    }

    @OnClick({R.id.sb_send_order, R.id.stv_surgery, R.id.stv_surgery_num})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_surgery:
                stvSurgery.setCheckBoxChecked(!stvSurgery.getCheckBoxIsChecked());
                break;
            case R.id.stv_surgery_num:
                showPickerView();
                break;
            case R.id.sb_send_order:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }
}
