package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.HistoricalDemandBean;
import com.ipd.xiangzui.bean.SendDemandBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface DemandContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultHistoricalDemand(HistoricalDemandBean data);

        void resultSendDemand(SendDemandBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getHistoricalDemand(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getSendDemand(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}