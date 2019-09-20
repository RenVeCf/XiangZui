package com.ipd.xiangzui.bean;

public class RechargeAliPayBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"sign":"partner=\"2088231587436524\"&seller_id=\"xiangzuish@163.com\"&out_trade_no=\"190945324430\"&subject=\"享醉\"&body=\"支付宝付款\"&total_fee=\"0.01\"&notify_url=\"http://47.244.137.174:8083/xz/ah/rechargePay/alipayReturnUrl\"&service=\"mobile.securitypay.pay\"&payment_type=\"1\"&_input_charset=\"utf-8\"&it_b_pay=\"30m\"&return_url=\"m.alipay.com\"&sign=\"rVAdjCQygfFrFZOTKzqZEMlEwM1pjzQhBYq83RBbvnsPL44E6CYxAOsBiGGirgu97tFaT7GQFjtCuiKm%2BVNLZJrXBVhid8GFGiIpN8L1eJUF7C7e3922%2FjG2%2Bo5A175vCrNBYjoVyj%2BLo0yf%2BF9IMAJdL6IIC%2BgPCZi98oOx2fk%3D\"&sign_type=\"RSA\""}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * sign : partner="2088231587436524"&seller_id="xiangzuish@163.com"&out_trade_no="190945324430"&subject="享醉"&body="支付宝付款"&total_fee="0.01"&notify_url="http://47.244.137.174:8083/xz/ah/rechargePay/alipayReturnUrl"&service="mobile.securitypay.pay"&payment_type="1"&_input_charset="utf-8"&it_b_pay="30m"&return_url="m.alipay.com"&sign="rVAdjCQygfFrFZOTKzqZEMlEwM1pjzQhBYq83RBbvnsPL44E6CYxAOsBiGGirgu97tFaT7GQFjtCuiKm%2BVNLZJrXBVhid8GFGiIpN8L1eJUF7C7e3922%2FjG2%2Bo5A175vCrNBYjoVyj%2BLo0yf%2BF9IMAJdL6IIC%2BgPCZi98oOx2fk%3D"&sign_type="RSA"
         */

        private String sign;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
