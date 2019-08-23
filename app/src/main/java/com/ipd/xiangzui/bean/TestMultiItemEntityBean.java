package com.ipd.xiangzui.bean;

/**
 * Description ：
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2019/6/23.
 */
public class TestMultiItemEntityBean {
    private boolean isShow;
    private boolean addFee;
    private String orderType;

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public boolean isAddFee() {
        return addFee;
    }

    public void setAddFee(boolean addFee) {
        this.addFee = addFee;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
