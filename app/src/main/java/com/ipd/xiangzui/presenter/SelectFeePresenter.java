package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.SelectFeeBean;
import com.ipd.xiangzui.bean.SendOrderBean;
import com.ipd.xiangzui.contract.SelectFeeContract;
import com.ipd.xiangzui.model.SelectFeeModel;
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
public class SelectFeePresenter extends SelectFeeContract.Presenter {

    private SelectFeeModel model;
    private Context context;

    public SelectFeePresenter(Context context) {
        this.model = new SelectFeeModel();
        this.context = context;
    }

    @Override
    public void getSelectFee(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getSelectFee(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultSelectFee((SelectFeeBean) o);
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
    public void getSendOrder(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getSendOrder(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultSendOrder((SendOrderBean) o);
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