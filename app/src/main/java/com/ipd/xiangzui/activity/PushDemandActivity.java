package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.HistoricalDemandBean;
import com.ipd.xiangzui.bean.SendDemandBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.DemandContract;
import com.ipd.xiangzui.presenter.DemandPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;

/**
 * Description ：发布需求
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/15.
 */
public class PushDemandActivity extends BaseActivity<DemandContract.View, DemandContract.Presenter> implements DemandContract.View {

    @BindView(R.id.tv_push_demand)
    TopView tvPushDemand;
    @BindView(R.id.stv_demand_brief)
    SuperTextView stvDemandBrief;
    @BindView(R.id.acet_demand_content)
    AppCompatEditText acetDemandContent;
    @BindView(R.id.acet_contact_info)
    AppCompatEditText acetContactInfo;

    private int demandType;

    @Override
    public int getLayoutId() {
        return R.layout.activity_push_demand;
    }

    @Override
    public DemandContract.Presenter createPresenter() {
        return new DemandPresenter(this);
    }

    @Override
    public DemandContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvPushDemand);

        demandType = getIntent().getIntExtra("demandType", 0);
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
                TreeMap<String, String> sendDemandMap = new TreeMap<>();
                sendDemandMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
                sendDemandMap.put("sketch", "fdsafdsa");
                sendDemandMap.put("contactInfo", acetContactInfo.getText().toString().trim());
                sendDemandMap.put("content", acetDemandContent.getText().toString().trim());
                sendDemandMap.put("demandType", demandType + "");
                sendDemandMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(sendDemandMap.toString().replaceAll(" ", "") + SIGN)));
                getPresenter().getSendDemand(sendDemandMap, false, false);
                break;
        }
    }

    @Override
    public void resultHistoricalDemand(HistoricalDemandBean data) {

    }

    @Override
    public void resultSendDemand(SendDemandBean data) {
        ToastUtil.showShortToast(data.getMsg());
        switch (data.getCode()) {
            case 200:
                finish();
                break;
            case 900:
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
