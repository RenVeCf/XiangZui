package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.ModifyMedicalBean;
import com.ipd.xiangzui.bean.NarcosisListBean;
import com.ipd.xiangzui.contract.ModifyMedicalContract;
import com.ipd.xiangzui.model.ModifyMedicalModel;
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
public class ModifyMedicalPresenter extends ModifyMedicalContract.Presenter {

    private ModifyMedicalModel model;
    private Context context;

    public ModifyMedicalPresenter(Context context) {
        this.model = new ModifyMedicalModel();
        this.context = context;
    }

    @Override
    public void getNarcosisList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getNarcosisList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultNarcosisList((NarcosisListBean) o);
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
    public void getModifyMedical(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getModifyMedical(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyMedical((ModifyMedicalBean) o);
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