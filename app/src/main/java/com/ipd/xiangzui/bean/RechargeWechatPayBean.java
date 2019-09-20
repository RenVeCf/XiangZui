package com.ipd.xiangzui.bean;

import com.google.gson.annotations.SerializedName;

public class RechargeWechatPayBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"sign":{"package":"Sign=WXPay","appid":"wx2bed183f1f29ee2f","sign":"E7C9D73DC650AAE89207946B4E0D8E99","partnerid":"1514143401","prepayid":"wx12162003317195a264fd4aaf1048105100","noncestr":"1620012808","timestamp":1568276403}}
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
         * sign : {"package":"Sign=WXPay","appid":"wx2bed183f1f29ee2f","sign":"E7C9D73DC650AAE89207946B4E0D8E99","partnerid":"1514143401","prepayid":"wx12162003317195a264fd4aaf1048105100","noncestr":"1620012808","timestamp":1568276403}
         */

        private SignBean sign;

        public SignBean getSign() {
            return sign;
        }

        public void setSign(SignBean sign) {
            this.sign = sign;
        }

        public static class SignBean {
            /**
             * package : Sign=WXPay
             * appid : wx2bed183f1f29ee2f
             * sign : E7C9D73DC650AAE89207946B4E0D8E99
             * partnerid : 1514143401
             * prepayid : wx12162003317195a264fd4aaf1048105100
             * noncestr : 1620012808
             * timestamp : 1568276403
             */

            @SerializedName("package")
            private String packageX;
            private String appid;
            private String sign;
            private String partnerid;
            private String prepayid;
            private String noncestr;
            private int timestamp;

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getSign() {
                return sign;
            }

            public void setSign(String sign) {
                this.sign = sign;
            }

            public String getPartnerid() {
                return partnerid;
            }

            public void setPartnerid(String partnerid) {
                this.partnerid = partnerid;
            }

            public String getPrepayid() {
                return prepayid;
            }

            public void setPrepayid(String prepayid) {
                this.prepayid = prepayid;
            }

            public String getNoncestr() {
                return noncestr;
            }

            public void setNoncestr(String noncestr) {
                this.noncestr = noncestr;
            }

            public int getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(int timestamp) {
                this.timestamp = timestamp;
            }
        }
    }
}
