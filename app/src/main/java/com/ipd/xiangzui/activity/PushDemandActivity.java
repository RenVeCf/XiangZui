package com.ipd.xiangzui.activity;

import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

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

/**
 * Description ：发布需求
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class PushDemandActivity extends BaseActivity {

    @BindView(R.id.tv_push_demand)
    TopView tvPushDemand;
    @BindView(R.id.stv_demand_brief)
    SuperTextView stvDemandBrief;
    @BindView(R.id.acet_demand_content)
    AppCompatEditText acetDemandContent;
    @BindView(R.id.acet_contact_info)
    AppCompatEditText acetContactInfo;

    @Override
    public int getLayoutId() {
        return R.layout.activity_push_demand;
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
        ImmersionBar.setTitleBar(this, tvPushDemand);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.stv_demand_brief, R.id.sb_confirm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_demand_brief:
                break;
            case R.id.sb_confirm:
                finish();
                break;
        }
    }
}
