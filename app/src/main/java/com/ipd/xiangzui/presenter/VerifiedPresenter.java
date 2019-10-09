package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.GetUserInfoBean;
import com.ipd.xiangzui.bean.VerifiedBean;
import com.ipd.xiangzui.contract.VerifiedContract;
import com.ipd.xiangzui.model.VerifiedModel;
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
public class VerifiedPresenter extends VerifiedContract.Presenter {

    private VerifiedModel model;
    private Context context;

    public VerifiedPresenter(Context context) {
        this.model = new VerifiedModel();
        this.context = context;
    }

    @Override
    public void getVerified(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getVerified(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultVerified((VerifiedBean) o);
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
    public void getGetUserInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getGetUserInfo(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultGetUserInfo((GetUserInfoBean) o);
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