package com.ipd.xiangzui.presenter;

import android.content.Context;

import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.OrderListBean;
import com.ipd.xiangzui.contract.OrderContract;
import com.ipd.xiangzui.model.OrderModel;
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
public class OrderPresenter extends OrderContract.Presenter {

    private OrderModel model;
    private Context context;

    public OrderPresenter(Context context) {
        this.model = new OrderModel();
        this.context = context;
    }

    @Override
    public void getOrderList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOrderList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOrderList((OrderListBean) o);
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
    public void getOrderDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
        model.getOrderDetails(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
            @Override
            public void onNext(Object o) {
                //这一步是必须的，判断view是否已经被销毁
                if (getView() != null) {
                    getView().resultOrderDetails((OrderDetailsBean) o);
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

//    @Override
//    public void getIsOrderOperationEnd(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
//        model.getIsOrderOperationEnd(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
//            @Override
//            public void onNext(Object o) {
//                //这一步是必须的，判断view是否已经被销毁
//                if (getView() != null) {
//                    getView().resultIsOrderOperationEnd((IsOrderOperationEndBean) o);
//                }
//            }
//
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                if (getView() != null) {
//                    //// TODO: 2017/12/28 自定义处理异常
//                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void getIngOperationEnd(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
//        model.getIngOperationEnd(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
//            @Override
//            public void onNext(Object o) {
//                //这一步是必须的，判断view是否已经被销毁
//                if (getView() != null) {
//                    getView().resultIngOperationEnd((IngOperationEndBean) o);
//                }
//            }
//
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                if (getView() != null) {
//                    //// TODO: 2017/12/28 自定义处理异常
//                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void getOperationStart(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
//        model.getOperationStart(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
//            @Override
//            public void onNext(Object o) {
//                //这一步是必须的，判断view是否已经被销毁
//                if (getView() != null) {
//                    getView().resultOperationStart((OperationStartBean) o);
//                }
//            }
//
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                if (getView() != null) {
//                    //// TODO: 2017/12/28 自定义处理异常
//                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void getIsArrivals(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
//        model.getIsArrivals(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
//            @Override
//            public void onNext(Object o) {
//                //这一步是必须的，判断view是否已经被销毁
//                if (getView() != null) {
//                    getView().resultIsArrivals((IsArrivalsBean) o);
//                }
//            }
//
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                if (getView() != null) {
//                    //// TODO: 2017/12/28 自定义处理异常
//                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void getOrderCancel(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
//        model.getOrderCancel(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
//            @Override
//            public void onNext(Object o) {
//                //这一步是必须的，判断view是否已经被销毁
//                if (getView() != null) {
//                    getView().resultOrderCancel((OrderCancelBean) o);
//                }
//            }
//
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                if (getView() != null) {
//                    //// TODO: 2017/12/28 自定义处理异常
//                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void getGetOrder(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
//        model.getGetOrder(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
//            @Override
//            public void onNext(Object o) {
//                //这一步是必须的，判断view是否已经被销毁
//                if (getView() != null) {
//                    getView().resultGetOrder((GetOrderBean) o);
//                }
//            }
//
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                if (getView() != null) {
//                    //// TODO: 2017/12/28 自定义处理异常
//                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
//                }
//            }
//        });
//    }
//
//    @Override
//    public void getAnesthesiaList(TreeMap<String, String> map, boolean isDialog, boolean cancelable) {
//        model.getAnesthesiaList(context, map, isDialog, cancelable, getView().bindLifecycle(), new ObserverResponseListener() {
//            @Override
//            public void onNext(Object o) {
//                //这一步是必须的，判断view是否已经被销毁
//                if (getView() != null) {
//                    getView().resultAnesthesiaList((AnesthesiaListBean) o);
//                }
//            }
//
//            @Override
//            public void onError(ExceptionHandle.ResponeThrowable e) {
//                if (getView() != null) {
//                    //// TODO: 2017/12/28 自定义处理异常
//                    ToastUtil.showShortToast(ExceptionHandle.handleException(e).message);
//                }
//            }
//        });
//    }
}