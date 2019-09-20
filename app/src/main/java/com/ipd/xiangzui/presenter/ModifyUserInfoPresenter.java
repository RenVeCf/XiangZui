package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.ModifyUserInfoBean;
import com.ipd.xiangzui.bean.UploadImgBean;
import com.ipd.xiangzui.contract.ModifyUserInfoContract;
import com.ipd.xiangzui.model.ModifyUserInfoModel;
import com.ipd.xiangzui.progress.ObserverResponseListener;
import com.ipd.xiangzui.utils.ExceptionHandle;
import com.ipd.xiangzui.utils.ToastUtil;

import java.util.TreeMap;

import okhttp3.RequestBody;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class ModifyUserInfoPresenter extends ModifyUserInfoContract.Presenter {

    private ModifyUserInfoModel model;
    private Context context;

    public ModifyUserInfoPresenter(Context context) {
        this.model = new ModifyUserInfoModel();
        this.context = context;
    }

    @Override
    public void getModifyUserInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getModifyUserInfo(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultModifyUserInfo((ModifyUserInfoBean) o);
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
    public void getUploadImg(TreeMap<String, RequestBody> map, String sign, boolean isDialog, boolean cancelable) {
        model.getUploadImg(context, map, sign, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultUploadImg((UploadImgBean) o);
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