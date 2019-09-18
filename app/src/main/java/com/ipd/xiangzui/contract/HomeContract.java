package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.HomeBean;
import com.ipd.xiangzui.bean.HospitalNameBean;
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

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void getHome(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getVerifiedType(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getHospitalName(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}