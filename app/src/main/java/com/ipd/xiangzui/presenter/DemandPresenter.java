package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.HistoricalDemandBean;
import com.ipd.xiangzui.bean.SendDemandBean;
import com.ipd.xiangzui.contract.DemandContract;
import com.ipd.xiangzui.model.DemandModel;
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
public class DemandPresenter extends DemandContract.Presenter {

    private DemandModel model;
    private Context context;

    public DemandPresenter(Context context) {
        this.model = new DemandModel();
        this.context = context;
    }

    @Override
    public void getHistoricalDemand(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getHistoricalDemand(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultHistoricalDemand((HistoricalDemandBean) o);
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
    public void getSendDemand(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getSendDemand(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultSendDemand((SendDemandBean) o);
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