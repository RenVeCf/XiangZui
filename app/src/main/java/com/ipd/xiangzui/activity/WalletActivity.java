package com.ipd.xiangzui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gyf.immersionbar.ImmersionBar;
import com.ipd.xiangzui.R;
import com.ipd.xiangzui.adapter.ConsumerDetailsAdapter;
import com.ipd.xiangzui.base.BaseActivity;
import com.ipd.xiangzui.bean.WalletBean;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.common.view.TwoBtDialog;
import com.ipd.xiangzui.contract.WalletContract;
import com.ipd.xiangzui.presenter.WalletPresenter;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.MD5Utils;
import com.ipd.xiangzui.utils.SPUtil;
import com.ipd.xiangzui.utils.StringUtils;
import com.ipd.xiangzui.utils.ToastUtil;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.ObservableTransformer;

import static com.ipd.xiangzui.common.config.IConstants.SIGN;
import static com.ipd.xiangzui.common.config.IConstants.USER_ID;
import static com.ipd.xiangzui.utils.isClickUtil.isFastClick;

/**
 * Description ：钱包
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/1.
 */
public class WalletActivity extends BaseActivity<WalletContract.View, WalletContract.Presenter> implements WalletContract.View {

    @BindView(R.id.tv_wallet)
    TopView tvWallet;
    @BindView(R.id.stv_account_balance)
    SuperTextView stvAccountBalance;
    @BindView(R.id.stv_account_balance_type)
    SuperTextView stvAccountBalanceType;
    @BindView(R.id.tv_earnest_money)
    TextView tvEarnestMoney;
    @BindView(R.id.tv_sum_income)
    TextView tvSumIncome;
    @BindView(R.id.tv_sum_expenditure)
    TextView tvSumExpenditure;
    @BindView(R.id.stv_consumer_details)
    SuperTextView stvConsumerDetails;
    @BindView(R.id.rv_consumer_details)
    RecyclerView rvConsumerDetails;

    private List<WalletBean.DataBean.BalaListBean> balaList = new ArrayList<>();
    private ConsumerDetailsAdapter consumerDetailsAdapter;
    private String balance; //余额

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    public WalletContract.Presenter createPresenter() {
        return new WalletPresenter(this);
    }

    @Override
    public WalletContract.View createView() {
        return this;
    }

    @SuppressLint("WrongConstant")
    @Override
    public void init() {
        //将每个Activity加入到栈中
        ApplicationUtil.getManager().addActivity(this);
        //防止状态栏和标题重叠
        ImmersionBar.setTitleBar(this, tvWallet);

        //更多收支明细
        CustomLinearLayoutManager layoutManager = new CustomLinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);//方向
        rvConsumerDetails.setLayoutManager(layoutManager);
        rvConsumerDetails.setNestedScrollingEnabled(false);
        rvConsumerDetails.setHasFixedSize(true);// 如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        rvConsumerDetails.setItemAnimator(new DefaultItemAnimator());//加载动画
    }

    @Override
    public void initData() {
        TreeMap<String, String> walletMap = new TreeMap<>();
        walletMap.put("userId", SPUtil.get(this, USER_ID, "") + "");
        walletMap.put("sign", StringUtils.toUpperCase(MD5Utils.encodeMD5(walletMap.toString().replaceAll(" ", "") + SIGN)));
        getPresenter().getWallet(walletMap, true, false);
    }

    @Override
    public void initListener() {
        stvConsumerDetails.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClickListener() {
                //更多收支明细
                startActivity(new Intent(WalletActivity.this, MoveConsumerActivity.class));
            }
        });

        stvAccountBalanceType.setCheckBoxCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    stvAccountBalance.setCenterString(balance);
                else
                    stvAccountBalance.setCenterString(stvAccountBalance.getCenterString().replaceAll(balance, "******"));
            }
        });
    }

    @OnClick({R.id.stv_account_balance_type, R.id.sb_refund_deposit, R.id.sb_withdraw, R.id.sb_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_account_balance_type: //隐藏or显示金额
                stvAccountBalanceType.setCheckBoxChecked(!stvAccountBalanceType.getCheckBoxIsChecked());
                if (stvAccountBalanceType.getCheckBoxIsChecked())
                    stvAccountBalance.setCenterString(balance);
                else
                    stvAccountBalance.setCenterString(stvAccountBalance.getCenterString().replaceAll(balance, "******"));
                break;
            case R.id.sb_refund_deposit: //退还保证金
                if (isFastClick())
                    new TwoBtDialog(this, "保证金不足时无法发单，是否确认退还保证金？", "确认") {
                        @Override
                        public void confirm() {
                            startActivity(new Intent(WalletActivity.this, WithdrawActivity.class).putExtra("type", 2));
                        }
                    }.show();
                break;
            case R.id.sb_withdraw: //提现
                startActivity(new Intent(this, WithdrawActivity.class).putExtra("type", 1));
                break;
            case R.id.sb_recharge: //充值
                startActivity(new Intent(this, RechargeActivity.class));
                break;
        }
    }

    @Override
    public void resultWallet(WalletBean data) {
        switch (data.getCode()) {
            case 200:
                balance = data.getData().getBalance() + "";
                stvAccountBalance.setCenterString(data.getData().getBalance() + "");
                tvEarnestMoney.setText(data.getData().getMargin() + "");
                tvSumIncome.setText(data.getData().getIncome() + "");
                tvSumExpenditure.setText(data.getData().getExpend() + "");

                balaList.clear();
                balaList.addAll(data.getData().getBalaList());
                rvConsumerDetails.setAdapter(consumerDetailsAdapter = new ConsumerDetailsAdapter(balaList));
                consumerDetailsAdapter.bindToRecyclerView(rvConsumerDetails);
                consumerDetailsAdapter.openLoadAnimation();
                break;
            case 900:
                ToastUtil.showShortToast(data.getMsg());
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
