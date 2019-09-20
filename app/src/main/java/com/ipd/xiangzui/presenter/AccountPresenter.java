package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.AccountListBean;
import com.ipd.xiangzui.bean.AddAccountBean;
import com.ipd.xiangzui.bean.DelAccountBean;
import com.ipd.xiangzui.bean.ModifyAccountBean;
import com.ipd.xiangzui.contract.AccountContract;
import com.ipd.xiangzui.model.AccountModel;
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
public class AccountPresenter extends AccountContract.Presenter {

    private AccountModel model;
    private Context context;

    public AccountPresenter(Context context) {
        this.model = new AccountModel();
        this.context = context;
    }

    @Override
    public void getAccountList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getAccountList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultAccountList((AccountListBean) o);
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
    public void getAddAccount(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getAddAccount(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultAddAccount((AddAccountBean) o);
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
    public void getModifyAccount(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getModifyAccount(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyAccount((ModifyAccountBean) o);
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
    public void getDelAccount(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getDelAccount(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultDelAccount((DelAccountBean) o);
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