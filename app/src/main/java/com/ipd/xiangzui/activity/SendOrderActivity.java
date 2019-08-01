package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.widget.RadioButton;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：发单
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/5.
 */
public class SendOrderActivity extends BaseActivity {

    @BindView(R.id.tv_send_order)
    TopView tvSendOrder;
    @BindView(R.id.rb_start)
    RadioButton rbStart;
    @BindView(R.id.rb_end)
    RadioButton rbEnd;

    //    private Animation animOut;

    @Override
    public int getLayoutId() {
        return R.layout.activity_send_order;
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
        ImmersionBar.setTitleBar(this, tvSendOrder);

        //        // 加载动画资源
        //        animOut = AnimationUtils.loadAnimation(this, R.anim.send_order_out);
        //        //设置动画结束后保留结束状态
        //        animOut.setFillAfter(false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.sb_next)
    public void onViewClicked() {
        //1: 单台, 2: 连台
        if (rbStart.isChecked())
            startActivity(new Intent(this, SendOrderSurgicalInfoActivity.class).putExtra("sendOrderType", 1));
        else
            startActivity(new Intent(this, SendOrderSurgicalInfoActivity.class).putExtra("sendOrderType", 2));
    }
}
