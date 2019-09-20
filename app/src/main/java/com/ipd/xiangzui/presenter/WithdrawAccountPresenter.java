package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.WithdrawAccountBean;
import com.ipd.xiangzui.contract.WithdrawAccountContract;
import com.ipd.xiangzui.model.WithdrawAccountModel;
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
public class WithdrawAccountPresenter extends WithdrawAccountContract.Presenter {

    private WithdrawAccountModel model;
    private Context context;

    public WithdrawAccountPresenter(Context context) {
        this.model = new WithdrawAccountModel();
        this.context = context;
    }

    @Override
    public void getWithdrawAccount(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getWithdrawAccount(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultWithdrawAccount((WithdrawAccountBean) o);
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