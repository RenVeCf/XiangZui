package com.ipd.xiangzui.bean;

public class HospitalNameBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"approve":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"realApprove":null,"userId":null,"approveType":null,"truename":"上海任梦阳医院","depart":null,"titleName":null,"hospital":null,"contactNumber":null,"photo":null,"positiveCard":null,"reverseCard":null,"certificate":null,"chestCard":null,"registAddress":null,"runAddress":null,"hospitalAgent":null,"status":null,"auditContent":null,"auditTime":null,"isNew":null,"titleId":null,"prov":"上海","city":"上海市","dist":"青浦区","address":"画的范德萨发的","telPhone":null}}
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
         * approve : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"realApprove":null,"userId":null,"approveType":null,"truename":"上海任梦阳医院","depart":null,"titleName":null,"hospital":null,"contactNumber":null,"photo":null,"positiveCard":null,"reverseCard":null,"certificate":null,"chestCard":null,"registAddress":null,"runAddress":null,"hospitalAgent":null,"status":null,"auditContent":null,"auditTime":null,"isNew":null,"titleId":null,"prov":"上海","city":"上海市","dist":"青浦区","address":"画的范德萨发的","telPhone":null}
         */

        private ApproveBean approve;

        public ApproveBean getApprove() {
            return approve;
        }

        public void setApprove(ApproveBean approve) {
            this.approve = approve;
        }

        public static class ApproveBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * realApprove : null
             * userId : null
             * approveType : null
             * truename : 上海任梦阳医院
             * depart : null
             * titleName : null
             * hospital : null
             * contactNumber : null
             * photo : null
             * positiveCard : null
             * reverseCard : null
             * certificate : null
             * chestCard : null
             * registAddress : null
             * runAddress : null
             * hospitalAgent : null
             * status : null
             * auditContent : null
             * auditTime : null
             * isNew : null
             * titleId : null
             * prov : 上海
             * city : 上海市
             * dist : 青浦区
             * address : 画的范德萨发的
             * telPhone : null
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private Object realApprove;
            private Object userId;
            private Object approveType;
            private String truename;
            private Object depart;
            private Object titleName;
            private Object hospital;
            private Object contactNumber;
            private Object photo;
            private Object positiveCard;
            private Object reverseCard;
            private Object certificate;
            private Object chestCard;
            private Object registAddress;
            private Object runAddress;
            private Object hospitalAgent;
            private Object status;
            private Object auditContent;
            private Object auditTime;
            private Object isNew;
            private Object titleId;
            private String prov;
            private String city;
            private String dist;
            private String address;
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

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
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

            public Object getRealApprove() {
                return realApprove;
            }

            public void setRealApprove(Object realApprove) {
                this.realApprove = realApprove;
            }

            public Object getUserId() {
                return userId;
            }

            public void setUserId(Object userId) {
                this.userId = userId;
            }

            public Object getApproveType() {
                return approveType;
            }

            public void setApproveType(Object approveType) {
                this.approveType = approveType;
            }

            public String getTruename() {
                return truename;
            }

            public void setTruename(String truename) {
                this.truename = truename;
            }

            public Object getDepart() {
                return depart;
            }

            public void setDepart(Object depart) {
                this.depart = depart;
            }

            public Object getTitleName() {
                return titleName;
            }

            public void setTitleName(Object titleName) {
                this.titleName = titleName;
            }

            public Object getHospital() {
                return hospital;
            }

            public void setHospital(Object hospital) {
                this.hospital = hospital;
            }

            public Object getContactNumber() {
                return contactNumber;
            }

            public void setContactNumber(Object contactNumber) {
                this.contactNumber = contactNumber;
            }

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
                this.photo = photo;
            }

            public Object getPositiveCard() {
                return positiveCard;
            }

            public void setPositiveCard(Object positiveCard) {
                this.positiveCard = positiveCard;
            }

            public Object getReverseCard() {
                return reverseCard;
            }

            public void setReverseCard(Object reverseCard) {
                this.reverseCard = reverseCard;
            }

            public Object getCertificate() {
                return certificate;
            }

            public void setCertificate(Object certificate) {
                this.certificate = certificate;
            }

            public Object getChestCard() {
                return chestCard;
            }

            public void setChestCard(Object chestCard) {
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

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getAuditContent() {
                return auditContent;
            }

            public void setAuditContent(Object auditContent) {
                this.auditContent = auditContent;
            }

            public Object getAuditTime() {
                return auditTime;
            }

            public void setAuditTime(Object auditTime) {
                this.auditTime = auditTime;
            }

            public Object getIsNew() {
                return isNew;
            }

            public void setIsNew(Object isNew) {
                this.isNew = isNew;
            }

            public Object getTitleId() {
                return titleId;
            }

            public void setTitleId(Object titleId) {
                this.titleId = titleId;
            }

            public String getProv() {
                return prov;
            }

            public void setProv(String prov) {
                this.prov = prov;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getDist() {
                return dist;
            }

            public void setDist(String dist) {
                this.dist = dist;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
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
    }
}
