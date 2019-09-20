package com.ipd.xiangzui.bean;

public class GetUserInfoBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"approveStatus":2,"approve":{"searchValue":null,"createBy":null,"createTime":"2019-09-12 15:03:06","updateBy":null,"updateTime":null,"remark":null,"params":{},"realApprove":23,"userId":15,"approveType":"1","truename":"任地方","depart":"急诊","titleName":"初级(住院医师)","hospital":"上海人民一笑","contactNumber":"18502994087","photo":"upload/2019/09/12/e9cb299b4c2c32d510bf7d898f87b5f8.jpeg","positiveCard":"upload/2019/09/12/e8950b043ef0e25510369a47ccb9af48.jpeg","reverseCard":"upload/2019/09/12/42374cbfd105d657e480cbafc75b331f.jpeg","certificate":"upload/2019/09/12/0dfec1734d6cb427fecaf23869126b32.jpeg","chestCard":"upload/2019/09/12/bfbc26bd8630dc4ff53a2ed1c9b1aca8.jpeg","registAddress":null,"runAddress":null,"hospitalAgent":null,"status":"2","auditContent":null,"auditTime":"2019-09-12 15:04:42","isNew":"1","titleId":1,"prov":null,"city":null,"dist":null,"address":null,"telPhone":null},"user":{"searchValue":null,"createBy":null,"createTime":"2019-09-09 16:23:14","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":15,"truename":null,"avatar":"upload/2019/09/12/790e70d1e114f53de3a516932510e8ec.jpeg","nickname":"享醉91","password":"123456","telPhone":"18502994087","myCode":null,"approveStatus":"2","balance":0,"margin":0,"parentId":null,"status":"1","userType":"1","infoSwitch":"2","soundSwitch":"1","vibrationSwitch":"1"}}
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
         * approveStatus : 2
         * approve : {"searchValue":null,"createBy":null,"createTime":"2019-09-12 15:03:06","updateBy":null,"updateTime":null,"remark":null,"params":{},"realApprove":23,"userId":15,"approveType":"1","truename":"任地方","depart":"急诊","titleName":"初级(住院医师)","hospital":"上海人民一笑","contactNumber":"18502994087","photo":"upload/2019/09/12/e9cb299b4c2c32d510bf7d898f87b5f8.jpeg","positiveCard":"upload/2019/09/12/e8950b043ef0e25510369a47ccb9af48.jpeg","reverseCard":"upload/2019/09/12/42374cbfd105d657e480cbafc75b331f.jpeg","certificate":"upload/2019/09/12/0dfec1734d6cb427fecaf23869126b32.jpeg","chestCard":"upload/2019/09/12/bfbc26bd8630dc4ff53a2ed1c9b1aca8.jpeg","registAddress":null,"runAddress":null,"hospitalAgent":null,"status":"2","auditContent":null,"auditTime":"2019-09-12 15:04:42","isNew":"1","titleId":1,"prov":null,"city":null,"dist":null,"address":null,"telPhone":null}
         * user : {"searchValue":null,"createBy":null,"createTime":"2019-09-09 16:23:14","updateBy":null,"updateTime":null,"remark":null,"params":{},"userId":15,"truename":null,"avatar":"upload/2019/09/12/790e70d1e114f53de3a516932510e8ec.jpeg","nickname":"享醉91","password":"123456","telPhone":"18502994087","myCode":null,"approveStatus":"2","balance":0,"margin":0,"parentId":null,"status":"1","userType":"1","infoSwitch":"2","soundSwitch":"1","vibrationSwitch":"1"}
         */

        private int approveStatus;
        private ApproveBean approve;
        private UserBean user;

        public int getApproveStatus() {
            return approveStatus;
        }

        public void setApproveStatus(int approveStatus) {
            this.approveStatus = approveStatus;
        }

        public ApproveBean getApprove() {
            return approve;
        }

        public void setApprove(ApproveBean approve) {
            this.approve = approve;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class ApproveBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-12 15:03:06
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * realApprove : 23
             * userId : 15
             * approveType : 1
             * truename : 任地方
             * depart : 急诊
             * titleName : 初级(住院医师)
             * hospital : 上海人民一笑
             * contactNumber : 18502994087
             * photo : upload/2019/09/12/e9cb299b4c2c32d510bf7d898f87b5f8.jpeg
             * positiveCard : upload/2019/09/12/e8950b043ef0e25510369a47ccb9af48.jpeg
             * reverseCard : upload/2019/09/12/42374cbfd105d657e480cbafc75b331f.jpeg
             * certificate : upload/2019/09/12/0dfec1734d6cb427fecaf23869126b32.jpeg
             * chestCard : upload/2019/09/12/bfbc26bd8630dc4ff53a2ed1c9b1aca8.jpeg
             * registAddress : null
             * runAddress : null
             * hospitalAgent : null
             * status : 2
             * auditContent : null
             * auditTime : 2019-09-12 15:04:42
             * isNew : 1
             * titleId : 1
             * prov : null
             * city : null
             * dist : null
             * address : null
             * telPhone : null
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int realApprove;
            private int userId;
            private String approveType;
            private String truename;
            private String depart;
            private String titleName;
            private String hospital;
            private String contactNumber;
            private String photo;
            private String positiveCard;
            private String reverseCard;
            private String certificate;
            private String chestCard;
            private Object registAddress;
            private Object runAddress;
            private Object hospitalAgent;
            private String status;
            private Object auditContent;
            private String auditTime;
            private String isNew;
            private int titleId;
            private Object prov;
            private Object city;
            private Object dist;
            private Object address;
            private Object telPhone;

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

            public int getRealApprove() {
                return realApprove;
            }

            public void setRealApprove(int realApprove) {
                this.realApprove = realApprove;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getApproveType() {
                return approveType;
            }

            public void setApproveType(String approveType) {
                this.approveType = approveType;
            }

            public String getTruename() {
                return truename;
            }

            public void setTruename(String truename) {
                this.truename = truename;
            }

            public String getDepart() {
                return depart;
            }

            public void setDepart(String depart) {
                this.depart = depart;
            }

            public String getTitleName() {
                return titleName;
            }

            public void setTitleName(String titleName) {
                this.titleName = titleName;
            }

            public String getHospital() {
                return hospital;
            }

            public void setHospital(String hospital) {
                this.hospital = hospital;
            }

            public String getContactNumber() {
                return contactNumber;
            }

            public void setContactNumber(String contactNumber) {
                this.contactNumber = contactNumber;
            }

            public String getPhoto() {
                return photo;
            }

            public void setPhoto(String photo) {
                this.photo = photo;
            }

            public String getPositiveCard() {
                return positiveCard;
            }

            public void setPositiveCard(String positiveCard) {
                this.positiveCard = positiveCard;
            }

            public String getReverseCard() {
                return reverseCard;
            }

            public void setReverseCard(String reverseCard) {
                this.reverseCard = reverseCard;
            }

            public String getCertificate() {
                return certificate;
            }

            public void setCertificate(String certificate) {
                this.certificate = certificate;
            }

            public String getChestCard() {
                return chestCard;
            }

            public void setChestCard(String chestCard) {
                this.chestCard = chestCard;
            }

            public Object getRegistAddress() {
                return registAddress;
            }

            public void setRegistAddress(Object registAddress) {
                this.registAddress = registAddress;
            }

            public Object getRunAddress() {
                return runAddress;
            }

            public void setRunAddress(Object runAddress) {
                this.runAddress = runAddress;
            }

            public Object getHospitalAgent() {
                return hospitalAgent;
            }

            public void setHospitalAgent(Object hospitalAgent) {
                this.hospitalAgent = hospitalAgent;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getAuditContent() {
                return auditContent;
            }

            public void setAuditContent(Object auditContent) {
                this.auditContent = auditContent;
            }

            public String getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(String auditTime) {
                this.auditTime = auditTime;
            }

            public String getIsNew() {
                return isNew;
            }

            public void setIsNew(String isNew) {
                this.isNew = isNew;
            }

            public int getTitleId() {
                return titleId;
            }

            public void setTitleId(int titleId) {
                this.titleId = titleId;
            }

            public Object getProv() {
                return prov;
            }

            public void setProv(Object prov) {
                this.prov = prov;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public Object getDist() {
                return dist;
            }

            public void setDist(Object dist) {
                this.dist = dist;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getTelPhone() {
                return telPhone;
            }

            public void setTelPhone(Object telPhone) {
                this.telPhone = telPhone;
            }

            public static class ParamsBean {
            }
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
             * avatar : upload/2019/09/12/790e70d1e114f53de3a516932510e8ec.jpeg
             * nickname : 享醉91
             * password : 123456
             * telPhone : 18502994087
             * myCode : null
             * approveStatus : 2
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
            private ParamsBeanX params;
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

            public ParamsBeanX getParams() {
                return params;
            }

            public void setParams(ParamsBeanX params) {
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

            public static class ParamsBeanX {
            }
        }
    }
}
