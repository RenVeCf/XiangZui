package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.NarcosisListBean;
import com.ipd.xiangzui.bean.VerifiedBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface NarcosisListContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultNarcosisList(NarcosisListBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getNarcosisList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}