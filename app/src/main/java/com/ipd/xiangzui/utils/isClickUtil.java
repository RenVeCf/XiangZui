package com.ipd.xiangzui.utils;

/**
 * Description ：防重复点击
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/9/6.
 */
public class isClickUtil {
    private static final int MIN_DELAY_TIME = 500;  // 两次点击间隔不能少于500ms
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long currentClickTime = System.currentTimeMillis();
        if ((currentClickTime - lastClickTime) >= MIN_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = currentClickTime;
        return flag;
    }
}
