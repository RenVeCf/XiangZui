package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.HomeBean;
import com.ipd.xiangzui.bean.HospitalNameBean;
import com.ipd.xiangzui.bean.VerifiedTypeBean;
import com.ipd.xiangzui.contract.HomeContract;
import com.ipd.xiangzui.model.HomeModel;
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
public class HomePresenter extends HomeContract.Presenter {

    private HomeModel model;
    private Context context;

    public HomePresenter(Context context) {
        this.model = new HomeModel();
        this.context = context;
    }

    @Override
    public void getHome(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getHome(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultHome((HomeBean) o);
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
    public void getVerifiedType(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getVerifiedType(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultVerifiedType((VerifiedTypeBean) o);
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
    public void getHospitalName(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getHospitalName(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultHospitalName((HospitalNameBean) o);
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