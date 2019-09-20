package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.FeeRecordBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

public interface FeeRecordContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultFeeRecord(FeeRecordBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<FeeRecordContract.View> {
        public abstract void getFeeRecord(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}
