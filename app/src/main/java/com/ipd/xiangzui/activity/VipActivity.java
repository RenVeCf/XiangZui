package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

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
 * Description ：VIP服务
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class VipActivity extends BaseActivity {

    @BindView(R.id.tv_vip)
    TopView tvVip;
    @BindView(R.id.tv_vip_text)
    TextView tvVipText;

    @Override
    public int getLayoutId() {
        return R.layout.activity_vip;
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
        ImmersionBar.setTitleBar(this, tvVip);
    }

    @Override
    public void initData() {
        tvVipText.setText("享醉医疗目前运营项目共有20余钟，我们针对不同客户群体需要，进行私人订制方案，依托于大基因中心的188项全方位体检，对客户身体情况进行精准筛查，由资深专家对客户体检报告进行1对1解读，深度剖析身体状况，并有针对性的提供解决方案和配套服务。 配合罗氏第五代、欧姆龙等医学仪器，给客户提供更为精准的全方位检查，特色的结合专家1对1解读体检报告，给客户更加私密的检查环境，结合体检报告给出科学建议，以及私人订制方案等都是我们的特色所在。");
    }

    @Override
    public void initListener() {

    }

    @OnClick({R.id.sb_historical_demand, R.id.sb_push_demand})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sb_historical_demand:
                //历史需求
                startActivity(new Intent(this, HistoricalDemandActivity.class));
                break;
            case R.id.sb_push_demand:
                //发布需求
                startActivity(new Intent(this, PushDemandActivity.class));
                break;
        }
    }
}
