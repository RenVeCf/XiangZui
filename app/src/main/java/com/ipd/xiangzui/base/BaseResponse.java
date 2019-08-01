package com.ipd.xiangzui.base;

/**
 * Description ：因为这个程序的后台返回格式不是 RESTful 架构格式的，所以不算是所有Bean的父类，在这里只作为返回错误信息的Bean
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */

public class BaseResponse<T> {

    private String errorCode;
    private String errorMsg;
    private String displayedMsg;
    private String timestamp;
    private String status;
    private T data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getDisplayedMsg() {
        return displayedMsg;
    }

    public void setDisplayedMsg(String displayedMsg) {
        this.displayedMsg = displayedMsg;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
