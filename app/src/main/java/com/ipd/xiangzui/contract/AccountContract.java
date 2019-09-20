package com.ipd.xiangzui.contract;

import com.ipd.xiangzui.base.BasePresenter;
import com.ipd.xiangzui.base.BaseView;
import com.ipd.xiangzui.bean.AddAccountBean;
import com.ipd.xiangzui.bean.AccountListBean;
import com.ipd.xiangzui.bean.DelAccountBean;
import com.ipd.xiangzui.bean.ModifyAccountBean;

import java.util.TreeMap;

import io.reactivex.ObservableTransformer;

public interface AccountContract {

    interface View extends BaseView {
        //不同的Bean单独处理
        void resultAccountList(AccountListBean data);

        void resultAddAccount(AddAccountBean data);

        void resultModifyAccount(ModifyAccountBean data);

        void resultDelAccount(DelAccountBean data);

        <T> ObservableTransformer<T, T> bindLifecycle();
    }

    abstract class Presenter extends BasePresenter<AccountContract.View> {
        public abstract void getAccountList(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getAddAccount(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getModifyAccount(TreeMap<String, String> map, boolean isDialog, boolean cancelable);

        public abstract void getDelAccount(TreeMap<String, String> map, boolean isDialog, boolean cancelable);
    }
}
