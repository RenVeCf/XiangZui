package com.ipd.xiangzui.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Description ：父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */

public abstract class BaseFragment<V extends BaseView, P extends BasePresenter<V>> extends RxFragment {

    //引用V层和P层
    private P presenter;
    private V view;
    public Context mContext;
    private Unbinder unbinder;

    public P getPresenter() {
        return presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (this.view == null) {
            this.view = createView();
        }
        if (presenter != null && view != null) {
            presenter.attachView(this.view);
        }
        //初始化本地数据
        init(view);
        //初始化布局监听器
        initListener();
        //初始化网络数据
        initData();
        return view;
    }

    //由子类指定具体类型
    public abstract int getLayoutId();

    public abstract P createPresenter();

    public abstract V createView();

    public abstract void init(View view);

    public abstract void initListener();

    public abstract void initData();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
        if (unbinder != null) {
            unbinder.unbind();
        }
    }
}