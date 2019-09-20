package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.RechargeAccountPayBean;
import com.ipd.xiangzui.bean.RechargeAliPayBean;
import com.ipd.xiangzui.bean.RechargeWechatPayBean;
import com.ipd.xiangzui.contract.RechargeContract;
import com.ipd.xiangzui.model.RechargeModel;
import com.ipd.xiangzui.progress.ObserverResponseListener;
import com.ipd.xiangzui.utils.ExceptionHandle;
import com.ipd.xiangzui.utils.ToastUtil;

import java.util.TreeMap;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class RechargePresenter extends RechargeContract.Presenter {

    private RechargeModel model;
    private Context context;

    public RechargePresenter(Context context) {
        this.model = new RechargeModel();
        this.context = context;
    }

    @Override
    public void getRechargeAliPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getRechargeAliPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultRechargeAliPay((RechargeAliPayBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getRechargeWechatPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getRechargeWechatPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultRechargeWechatPay((RechargeWechatPayBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }

    @Override
    public void getRechargeAccountPay(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getRechargeAccountPay(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultRechargeAccountPay((RechargeAccountPayBean) o);
                }
            }

            @Override
            public void onError(ExceptionHandle.ResponeThrowable e) {
                if (getView() != null) {
                    //// TODO: 2017/12/28 自定义处理异常
                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
                }
            }
        });
    }
}