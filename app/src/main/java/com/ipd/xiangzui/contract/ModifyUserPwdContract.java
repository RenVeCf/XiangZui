package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.ModifyUserPwdBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface ModifyUserPwdContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultModifyUserPwd(ModifyUserPwdBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getModifyUserPwd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}