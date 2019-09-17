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
import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.TestMultiItemEntityBean;
import com.ipd.xiangzui.common.view.CustomLinearLayoutManager;
import com.ipd.xiangzui.common.view.TopView;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.L;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description ：钱包
 * Author ： rmy
 * Email ： 942685687@qq.com
 * Time ： 2019/7/1.
 */
public class WalletActivity extends BaseActivity {

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

    private List<TestMultiItemEntityBean> list = new ArrayList<>();
    private ConsumerDetailsAdapter consumerDetailsAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_wallet;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
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

        for (int i = 0; i < 4; i++) {
            TestMultiItemEntityBean testData = new TestMultiItemEntityBean();
            list.add(testData);
        }
        rvConsumerDetails.setAdapter(consumerDetailsAdapter = new ConsumerDetailsAdapter(list));
        consumerDetailsAdapter.bindToRecyclerView(rvConsumerDetails);
        consumerDetailsAdapter.openLoadAnimation();
    }

    @Override
    public void initData() {
        stvAccountBalance.setCenterString("500.00");
        tvEarnestMoney.setText("500.00");
        tvSumIncome.setText("500.00");
        tvSumExpenditure.setText("500.00");
    }

    @Override
    public void initListener() {
        stvConsumerDetails.setRightTvClickListener(new SuperTextView.OnRightTvClickListener() {
            @Override
            public void onClickListener() {
                //更多收支明细

            }
        });

        stvAccountBalanceType.setCheckBoxCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    stvAccountBalance.setCenterString("500.00");
                else
                    stvAccountBalance.setCenterString(stvAccountBalance.getCenterString().replaceAll("500.00", "******"));
            }
        });
    }

    @OnClick({R.id.stv_account_balance_type, R.id.sb_refund_deposit, R.id.sb_withdraw, R.id.sb_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.stv_account_balance_type: //隐藏or显示金额
                stvAccountBalanceType.setCheckBoxChecked(!stvAccountBalanceType.getCheckBoxIsChecked());
                break;
            case R.id.sb_refund_deposit: //退还保证金
                break;
            case R.id.sb_withdraw: //提现
                startActivity(new Intent(this, WithdrawActivity.class));
                break;
            case R.id.sb_recharge: //充值
                startActivity(new Intent(this, RechargeActivity.class));
                break;
        }
    }
}
