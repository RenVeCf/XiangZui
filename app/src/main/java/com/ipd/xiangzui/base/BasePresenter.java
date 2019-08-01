package com.ipd.xiangzui.base;

/**
 * Description ：P层父类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */

public abstract class BasePresenter<V extends BaseView> {

    private V mView;

    public V getView() {
        return mView;
    }

    /**
     * View初始化
     * @param v
     */
    public void attachView(V v) {
        mView = v;
    }

    /**
     * View销毁
     */
    public void detachView() {
        mView = null;
    }
}