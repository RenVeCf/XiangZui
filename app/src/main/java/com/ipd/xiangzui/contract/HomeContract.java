package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.AddFeeBean;
import com.ipd.xiangzui.bean.CancelIsOrderBean;
import com.ipd.xiangzui.bean.CancelOrderBean;
import com.ipd.xiangzui.bean.GetUserInfoBean;
import com.ipd.xiangzui.bean.HomeBean;
import com.ipd.xiangzui.bean.HospitalNameBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.OrderIsOrverBean;
import com.ipd.xiangzui.bean.OrderQuickBean;
import com.ipd.xiangzui.bean.SelectFeeBean;
import com.ipd.xiangzui.bean.VerifiedTypeBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

/**
 * Description ：MemberCenterContract  V 、P契约类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/4/2.
 */
public interface HomeContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultHome(HomeBean data);

        void resultVerifiedType(VerifiedTypeBean data);

        void resultHospitalName(HospitalNameBean data);

        void resultCancelOrder(CancelOrderBean data);

        void resultCancelIsOrder(CancelIsOrderBean data);

        void resultAddFee(AddFeeBean data);

        void resultOrderQuick(OrderQuickBean data);

        void resultOrderDetails(OrderDetailsBean data);

        void resultGetUserInfo(GetUserInfoBean data);

        void resultSelectFee(SelectFeeBean data);

        void resultOrderIsOrver(OrderIsOrverBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getHome(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getVerifiedType(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getHospitalName(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getCancelOrder(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getCancelIsOrder(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAddFee(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOrderQuick(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOrderDetails(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getGetUserInfo(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getSelectFee(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getOrderIsOrver(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}