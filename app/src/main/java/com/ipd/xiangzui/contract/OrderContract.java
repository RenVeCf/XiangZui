package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.OrderListBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface OrderContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultOrderList(OrderListBean data);

        void resultOrderDetails(OrderDetailsBean data);

//        void resultIsOrderOperationEnd(IsOrderOperationEndBean data);
//
//        void resultIngOperationEnd(IngOperationEndBean data);
//
//        void resultOperationStart(OperationStartBean data);
//
//        void resultIsArrivals(IsArrivalsBean data);
//
//        void resultOrderCancel(OrderCancelBean data);
//
//        void resultGetOrder(GetOrderBean data);
//
//        void resultAnesthesiaList(AnesthesiaListBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getOrderList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOrderDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

//        public abstract void getIsOrderOperationEnd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
//
//        public abstract void getIngOperationEnd(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
//
//        public abstract void getOperationStart(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
//
//        public abstract void getIsArrivals(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
//
//        public abstract void getOrderCancel(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
//
//        public abstract void getGetOrder(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
//
//        public abstract void getAnesthesiaList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}