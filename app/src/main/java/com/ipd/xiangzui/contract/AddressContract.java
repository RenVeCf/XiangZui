package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.AddAddressBean;
import com.ipd.xiangzui.bean.AddressListBean;
import com.ipd.xiangzui.bean.DelAddressBean;
import com.ipd.xiangzui.bean.ModifyAddressBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

public interface AddressContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultAddressList(AddressListBean data);

        void resultAddAddress(AddAddressBean data);

        void resultModifyAddress(ModifyAddressBean data);

        void resultDelAddress(DelAddressBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<AddressContract.View> {
        public abstract void getAddressList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAddAddress(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getModifyAddress(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getDelAddress(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}
