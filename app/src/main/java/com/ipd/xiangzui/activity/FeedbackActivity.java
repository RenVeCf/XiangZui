package com.ipd.xiangzui.activity;

import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatEditText;

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
 * Description ：意见反馈
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class FeedbackActivity extends BaseActivity {

    @BindView(R.id.rg_feedback)
    RadioGroup rgFeedback;
    @BindView(R.id.acet_demand_content)
    AppCompatEditText acetDemandContent;
    @BindView(R.id.acet_contact_info)
    AppCompatEditText acetContactInfo;
    @BindView(R.id.tv_feedback)
    TopView tvFeedback;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
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
        ImmersionBar.setTitleBar(this, tvFeedback);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        rgFeedback.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int btId) {

            }
        });
    }

    @OnClick(R.id.sb_confirm)
    public void onViewClicked() {
        finish();
    }
}
