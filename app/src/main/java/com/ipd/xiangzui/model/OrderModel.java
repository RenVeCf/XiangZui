package com.ipd.xiangzui.model;

import android.content.Context;

import com.ipd.xiangzui.api.Api;
import com.ipd.xiangzui.base.BaseModel;
import com.ipd.xiangzui.progress.ObserverResponseListener;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public class OrderModel<T> extends BaseModel {

    public void getOrderList(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                             ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOrderList(map), observerListener, transformer, isDialog, cancelable);
    }

    public void getOrderDetails(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
                                ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {

        //当不需要指定是否由dialog时，可以调用这个方法
        //        subscribe(context, Api.getApiService().login(map), observerListener);
        paramSubscribe(context, Api.getApiService().getOrderDetails(map), observerListener, transformer, isDialog, cancelable);
    }
//
//    public void getIsOrderOperationEnd(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
//                                    ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
//
//        //当不需要指定是否由dialog时，可以调用这个方法
//        //        subscribe(context, Api.getApiService().login(map), observerListener);
//        paramSubscribe(context, Api.getApiService().getIsOrderOperationEnd(map), observerListener, transformer, isDialog, cancelable);
//    }
//
//    public void getIngOperationEnd(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
//                                   ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
//
//        //当不需要指定是否由dialog时，可以调用这个方法
//        //        subscribe(context, Api.getApiService().login(map), observerListener);
//        paramSubscribe(context, Api.getApiService().getIngOperationEnd(map), observerListener, transformer, isDialog, cancelable);
//    }
//
//    public void getOperationStart(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
//                                  ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
//
//        //当不需要指定是否由dialog时，可以调用这个方法
//        //        subscribe(context, Api.getApiService().login(map), observerListener);
//        paramSubscribe(context, Api.getApiService().getOperationStart(map), observerListener, transformer, isDialog, cancelable);
//    }
//
//    public void getIsArrivals(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
//                              ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
//
//        //当不需要指定是否由dialog时，可以调用这个方法
//        //        subscribe(context, Api.getApiService().login(map), observerListener);
//        paramSubscribe(context, Api.getApiService().getIsArrivals(map), observerListener, transformer, isDialog, cancelable);
//    }
//
//    public void getOrderCancel(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
//                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
//
//        //当不需要指定是否由dialog时，可以调用这个方法
//        //        subscribe(context, Api.getApiService().login(map), observerListener);
//        paramSubscribe(context, Api.getApiService().getOrderCancel(map), observerListener, transformer, isDialog, cancelable);
//    }
//
//    public void getGetOrder(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
//                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
//
//        //当不需要指定是否由dialog时，可以调用这个方法
//        //        subscribe(context, Api.getApiService().login(map), observerListener);
//        paramSubscribe(context, Api.getApiService().getGetOrder(map), observerListener, transformer, isDialog, cancelable);
//    }
//
//    public void getAnesthesiaList(Context context, TreeMap<String, String> map, boolean isDialog, boolean cancelable,
//                               ObservableTransformer<T, T> transformer, ObserverResponseListener observerListener) {
//
//        //当不需要指定是否由dialog时，可以调用这个方法
//        //        subscribe(context, Api.getApiService().login(map), observerListener);
//        paramSubscribe(context, Api.getApiService().getAnesthesiaList(map), observerListener, transformer, isDialog, cancelable);
//    }
    //// TODO: 2017/12/27 其他需要请求、数据库等等的操作
}
