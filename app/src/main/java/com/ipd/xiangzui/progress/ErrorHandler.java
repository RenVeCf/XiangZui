package com.ipd.xiangzui.progress;

import com.google.gson.Gson;
import com.ipd.xiangzui.base.BaseResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

/**
 * Description ：错误信息统一处理
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/9/4.
 */
public class ErrorHandler {
    public static BaseResponse handle(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException error = (HttpException) throwable;
            try {
                return new Gson().fromJson(error.response().errorBody().string(), BaseResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throwable.printStackTrace();
        }
        return null;
    }
}
