package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.SelectFeeBean;
import com.ipd.xiangzui.bean.SendOrderBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface SelectFeeContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultSelectFee(SelectFeeBean data);

        void resultSendOrder(SendOrderBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getSelectFee(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getSendOrder(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}