package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.OpenInvoiceBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

public interface OpenInvoiceContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultOpenInvoice(OpenInvoiceBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<OpenInvoiceContract.View> {
        public abstract void getOpenInvoice(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}
