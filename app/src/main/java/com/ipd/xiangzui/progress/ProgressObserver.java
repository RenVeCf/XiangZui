package com.ipd.xiangzui.progress;

import android.content.Context;
import android.util.Log;

import com.ipd.xiangzui.R;
import com.ipd.xiangzui.utils.ExceptionHandle;
import com.ipd.xiangzui.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * 观察者
 */
public class ProgressObserver<T> implements Observer<T>, ProgressCancelListener {
    private static final String TAG = "ProgressObserver____ ";
    private ObserverResponseListener listener;
    private ProgressDialogHandler mProgressDialogHandler;
    private Context context;
    private Disposable d;

    public ProgressObserver(Context context, ObserverResponseListener listener, boolean isDialog, boolean cancelable) {
        this.listener = listener;
        this.context = context;
        if (isDialog) {
            mProgressDialogHandler = new ProgressDialogHandler(context, this, cancelable);
        }
    }

    /**
     * 开启加载Dialog
     */
    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    /**
     * 关闭加载Dialog
     */
    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    @Override
    public void onSubscribe(Disposable d) {
        this.d = d;
        Log.e(TAG, "onSubscribe: ");
        showProgressDialog();
    }

    @Override
    public void onNext(T t) {
        listener.onNext(t);//可定制接口，通过code回调处理不同的业务
    }

    @Override
    public void onError(Throwable e) {
        //        BaseResponse errBody = ErrorHandler.handle(e);
        dismissProgressDialog();
        Log.e(TAG, "onError: ", e);
        //自定义异常处理
        if (e instanceof ExceptionHandle.ResponeThrowable) {
            listener.onError((ExceptionHandle.ResponeThrowable) e);
        } else {
            listener.onError(new ExceptionHandle.ResponeThrowable(e, ExceptionHandle.ERROR.UNKNOWN));
        }

        if (e instanceof UnknownHostException) {
            ToastUtil.showLongToast(context.getResources().getString(R.string.open_network));
        } else if (e instanceof SocketTimeoutException) {
            ToastUtil.showLongToast(context.getResources().getString(R.string.link_timeout));
        } else if (e instanceof ConnectException) {
            ToastUtil.showLongToast(context.getResources().getString(R.string.link_failed));
        } else if (e instanceof HttpException) {
            ToastUtil.showLongToast(context.getResources().getString(R.string.link_timeout));
        } else {
            /*if (errBody != null) {
                //401是Token过期，每月月初Token过期
                if (errBody.getDisplayedMsg().equals("无效的令牌")) {
                    SPUtil.clear(ApplicationUtil.getContext());
                    ToastUtil.showLongToast(errBody.getDisplayedMsg());
                    ApplicationUtil.getManager().finishAllActivity();
                    ApplicationUtil.getContext().startActivity(new Intent(ApplicationUtil.getContext(), LoginActivity.class));
                } else {
                    //displayedMsg是后台返回的错误信息
                    ToastUtil.showLongToast(errBody.getDisplayedMsg());
                }
            }*/
        }
    }

    @Override
    public void onComplete() {
        dismissProgressDialog();
        Log.e(TAG, "onComplete: ");
    }

    @Override
    public void onCancelProgress() {
        Log.e(TAG, "requestCancel: ");
        //如果处于订阅状态，则取消订阅
        if (!d.isDisposed()) {
            d.dispose();
        }
    }
}
