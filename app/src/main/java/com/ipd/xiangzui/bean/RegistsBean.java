package com.ipd.xiangzui.bean;

public class RegistsBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"user":{"searchValue":null,"createBy":null,"createTime":"2019-09-09 16:23:14","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":15,"truename":null,"avatar":"","nickname":"享醉911124","password":"38c7ca247eaa56a80512a46d4c84aa5b","telPhone":"18502994087","myCode":null,"approveStatus":"1","balance":0,"margin":0,"parentId":null,"status":"1","userType":"1","infoSwitch":"2","soundSwitch":"1","vibrationSwitch":"1"},"token":"1201909091623139321947288986ioj2ymvz"}
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
         * user : {"searchValue":null,"createBy":null,"createTime":"2019-09-09 16:23:14","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":15,"truename":null,"avatar":"","nickname":"享醉911124","password":"38c7ca247eaa56a80512a46d4c84aa5b","telPhone":"18502994087","myCode":null,"approveStatus":"1","balance":0,"margin":0,"parentId":null,"status":"1","userType":"1","infoSwitch":"2","soundSwitch":"1","vibrationSwitch":"1"}
         * token : 1201909091623139321947288986ioj2ymvz
         */

        private UserBean user;
        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-09 16:23:14
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * userId : 15
             * truename : null
             * avatar :
             * nickname : 享醉911124
             * password : 38c7ca247eaa56a80512a46d4c84aa5b
             * telPhone : 18502994087
             * myCode : null
             * approveStatus : 1
             * balance : 0.0
             * margin : 0.0
             * parentId : null
             * status : 1
             * userType : 1
             * infoSwitch : 2
             * soundSwitch : 1
             * vibrationSwitch : 1
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int userId;
            private Object truename;
            private String avatar;
            private String nickname;
            private String password;
            private String telPhone;
            private Object myCode;
            private String approveStatus;
            private double balance;
            private double margin;
            private Object parentId;
            private String status;
            private String userType;
            private String infoSwitch;
            private String soundSwitch;
            private String vibrationSwitch;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params = params;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public Object getTruename() {
                return truename;
            }

            public void setTruename(Object truename) {
                this.truename = truename;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getTelPhone() {
                return telPhone;
            }

            public void setTelPhone(String telPhone) {
                this.telPhone = telPhone;
            }

            public Object getMyCode() {
                return myCode;
            }

            public void setMyCode(Object myCode) {
                this.myCode = myCode;
            }

            public String getApproveStatus() {
                return approveStatus;
            }

            public void setApproveStatus(String approveStatus) {
                this.approveStatus = approveStatus;
            }

            public double getBalance() {
                return balance;
            }

            public void setBalance(double balance) {
                this.balance = balance;
            }

            public double getMargin() {
                return margin;
            }

            public void setMargin(double margin) {
                this.margin = margin;
            }

            public Object getParentId() {
                return parentId;
            }

            public void setParentId(Object parentId) {
                this.parentId = parentId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUserType() {
                return userType;
            }

            public void setUserType(String userType) {
                this.userType = userType;
            }

            public String getInfoSwitch() {
                return infoSwitch;
            }

            public void setInfoSwitch(String infoSwitch) {
                this.infoSwitch = infoSwitch;
            }

            public String getSoundSwitch() {
                return soundSwitch;
            }

            public void setSoundSwitch(String soundSwitch) {
                this.soundSwitch = soundSwitch;
            }

            public String getVibrationSwitch() {
                return vibrationSwitch;
            }

            public void setVibrationSwitch(String vibrationSwitch) {
                this.vibrationSwitch = vibrationSwitch;
            }

            public static class ParamsBean {
            }
        }
    }
}
