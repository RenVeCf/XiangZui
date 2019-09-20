package com.ipd.xiangzui.activity;

import android.content.Intent;
import android.widget.RadioGroup;

import androidx.appcompat.widget.AppCompatEditText;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.FeedBackBean;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.contract.FeedBackContract;
import com.ipd.xiangzui.presenter.FeedBackPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;

import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.StringUtils.isEmpty;

/**
 * Description ：意见反馈
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/8/28.
 */
public class FeedbackActivity extends BaseActivity<FeedBackContract.View, FeedBackContract.Presenter> implements FeedBackContract.View {

    @BindView(R.id.rg_feedback)
    RadioGroup rgFeedback;
    @BindView(R.id.acet_demand_content)
    AppCompatEditText acetDemandContent;
    @BindView(R.id.acet_contact_info)
    AppCompatEditText acetContactInfo;
    @BindView(R.id.tv_feedback)
    TopView tvFeedback;

    private int selectItem = 1;

    @Override
    public int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    public FeedBackContract.Presenter createPresenter() {
        return new FeedBackPresenter(this);
    }

    @Override
    public FeedBackContract.View createView() {
        return this;
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
                switch (btId) {
                    case R.id.rb_product_suggestion:
                        selectItem = 1;
                        break;
                    case R.id.rb_other_suggestion:
                        selectItem = 2;
                        break;
                    case R.id.rb_abnormal_function:
                        selectItem = 3;
                        break;
                    case R.id.rb_safe_question:
                        selectItem = 4;
                        break;
                }
            }
        });
    }

    @OnClick(R.id.sb_confirm)
    public void onViewClicked() {
        if (!isEmpty(acetDemandContent.getText().toString().trim()) && !isEmpty(acetContactInfo.getText().toString().trim())) {
            TreeMap<String, String> feedBackMap = new TreeMap<>();
            feedBackMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
            feedBackMap.put("opinionType", selectItem + "");
            feedBackMap.put("content", acetDemandContent.getText().toString().trim());
            feedBackMap.put("contactInfo", acetContactInfo.getText().toString().trim());
            feedBackMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(feedBackMap.toString().replaceAll(" ", "") + SIGN)));
            getPresenter().getFeedBack(feedBackMap, true, false);
        } else
            ToastUtil.showLongToast("请将信息填写完整!");
    }

    @Override
    public void resultFeedBack(FeedBackBean data) {
        switch (data.getCode()) {
            case 900:
                ToastUtil.showLongToast(data.getMsg());
                //清除所有临时储存
                SPUtil.clear(ApplicationUtil.getContext());
                ApplicationUtil.getManager().finishActivity(MainActivity.class);
                startActivity(new Intent(this, CaptchaLoginActivity.class));
                finish();
                break;
            default:
                ToastUtil.showLongToast(data.getMsg());
                finish();
                break;
        }
    }

    @Override
    public <T> ObservableTransformer<T, T> bindLifecycle() {
        return this.bindToLifecycle();
    }
}
