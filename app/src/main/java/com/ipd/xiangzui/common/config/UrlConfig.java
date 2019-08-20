package com.ipd.xiangzui.common.config;

/**
 * Description ：URL 配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface UrlConfig {
    /**
     * 域名
     */
    String BASE_URL = "http://47.93.126.235:8010/jhzc/";
    String BASE_LOCAL_URL = "http://47.93.126.235:8010/";

    /**
     * 登陆
     */
    String CAPTCHA = "appUser/util/getSms"; //验证码
    String FORGET_PWD = "appUser/login/forgetPassword"; //忘记密码
    String LOGIN = "appUser/login/login"; //登陆
    String REGISTER = "appUser/login/regists"; //注册
}
