package com.ipd.xiangzui.bean;

import java.util.List;

public class OrderListBean {
    /**
     * msg : 操作成功
     * total : 3
     * code : 200
     * data : {"orderList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"orderId":37,"userId":17,"orderType":"1","surgeryName":"摸摸摸","hospitalName":"上海任梦阳医院","prov":"上海","city":"上海市","dist":"青浦区","address":"画的范德萨发的","duration":1,"urgent":"2","urgentMoney":50,"premium":"1","premiumMoney":0,"evenNum":null,"expectMoney":2000,"status":"1","payType":null,"takeOrderId":null,"takeOrderTime":null,"cancelTime":null,"prompt":"","arriveTime":null,"beginTime":"2019-10-20 10:05:00","waitTime":null,"surgeryTime":null,"waitMoney":0,"surgeryMoney":0,"totalMoney":0,"taxMoney":0,"version":1,"orderNo":"190938319893","ahNumber":"18502994087","adNumber":null,"overtimeMoney":0,"invoicepayMoney":2300,"promptMoney":300,"endTime":null,"adMoney":1700,"ahMoney":2350,"adactualMoney":0,"ahactualMoney":0,"adpremiumMoney":0,"ahpremiumMoney":0,"takeName":null,"takePhone":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"orderId":36,"userId":17,"orderType":"1","surgeryName":"呃呃","hospitalName":"上海任梦阳医院","prov":"上海","city":"上海市","dist":"青浦区","address":"画的范德萨发的","duration":1,"urgent":"1","urgentMoney":0,"premium":"1","premiumMoney":0,"evenNum":null,"expectMoney":2000,"status":"1","payType":null,"takeOrderId":null,"takeOrderTime":null,"cancelTime":null,"prompt":"","arriveTime":null,"beginTime":"2019-10-20 10:03:00","waitTime":null,"surgeryTime":null,"waitMoney":0,"surgeryMoney":0,"totalMoney":0,"taxMoney":0,"version":1,"orderNo":"190924510353","ahNumber":"18502994087","adNumber":null,"overtimeMoney":0,"invoicepayMoney":2300,"promptMoney":300,"endTime":null,"adMoney":1700,"ahMoney":2300,"adactualMoney":0,"ahactualMoney":0,"adpremiumMoney":0,"ahpremiumMoney":0,"takeName":null,"takePhone":null},{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"orderId":35,"userId":17,"orderType":"2","surgeryName":"恶心","hospitalName":"上海任梦阳医院","prov":"上海","city":"上海市","dist":"青浦区","address":"画的范德萨发的","duration":1,"urgent":"2","urgentMoney":50,"premium":"1","premiumMoney":0,"evenNum":2,"expectMoney":2000,"status":"1","payType":null,"takeOrderId":null,"takeOrderTime":null,"cancelTime":null,"prompt":"","arriveTime":null,"beginTime":"2019-10-20 09:55:00","waitTime":null,"surgeryTime":null,"waitMoney":0,"surgeryMoney":0,"totalMoney":0,"taxMoney":0,"version":1,"orderNo":"190982549159","ahNumber":"18502994087","adNumber":null,"overtimeMoney":0,"invoicepayMoney":2300,"promptMoney":300,"endTime":null,"adMoney":1700,"ahMoney":2350,"adactualMoney":0,"ahactualMoney":0,"adpremiumMoney":0,"ahpremiumMoney":0,"takeName":null,"takePhone":null}]}
     */

    private String msg;
    private int total;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
        private List<OrderListsBean> orderList;

        public List<OrderListsBean> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<OrderListsBean> orderList) {
            this.orderList = orderList;
        }

        public static class OrderListsBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * orderId : 37
             * userId : 17
             * orderType : 1
             * surgeryName : 摸摸摸
             * hospitalName : 上海任梦阳医院
             * prov : 上海
             * city : 上海市
             * dist : 青浦区
             * address : 画的范德萨发的
             * duration : 1.0
             * urgent : 2
             * urgentMoney : 50.0
             * premium : 1
             * premiumMoney : 0.0
             * evenNum : null
             * expectMoney : 2000.0
             * status : 1
             * payType : null
             * takeOrderId : null
             * takeOrderTime : null
             * cancelTime : null
             * prompt :
             * arriveTime : null
             * beginTime : 2019-10-20 10:05:00
             * waitTime : null
             * surgeryTime : null
             * waitMoney : 0.0
             * surgeryMoney : 0.0
             * totalMoney : 0.0
             * taxMoney : 0.0
             * version : 1
             * orderNo : 190938319893
             * ahNumber : 18502994087
             * adNumber : null
             * overtimeMoney : 0.0
             * invoicepayMoney : 2300.0
             * promptMoney : 300.0
             * endTime : null
             * adMoney : 1700.0
             * ahMoney : 2350.0
             * adactualMoney : 0.0
             * ahactualMoney : 0.0
             * adpremiumMoney : 0.0
             * ahpremiumMoney : 0.0
             * takeName : null
             * takePhone : null
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int orderId;
            private int userId;
            private String orderType;
            private String surgeryName;
            private String hospitalName;
            private String prov;
            private String city;
            private String dist;
            private String address;
            private double duration;
            private String urgent;
            private double urgentMoney;
            private String premium;
            private double premiumMoney;
            private Object evenNum;
            private double expectMoney;
            private String status;
            private Object payType;
            private Object takeOrderId;
            private Object takeOrderTime;
            private Object cancelTime;
            private String prompt;
            private Object arriveTime;
            private String beginTime;
            private Object waitTime;
            private Object surgeryTime;
            private double waitMoney;
            private double surgeryMoney;
            private double totalMoney;
            private double taxMoney;
            private int version;
            private String orderNo;
            private String ahNumber;
            private String adNumber;
            private double overtimeMoney;
            private double invoicepayMoney;
            private double promptMoney;
            private Object endTime;
            private double adMoney;
            private double ahMoney;
            private double adactualMoney;
            private double ahactualMoney;
            private double adpremiumMoney;
            private double ahpremiumMoney;
            private Object takeName;
            private Object takePhone;

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

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getOrderType() {
                return orderType;
            }

            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }

            public String getSurgeryName() {
                return surgeryName;
            }

            public void setSurgeryName(String surgeryName) {
                this.surgeryName = surgeryName;
            }

            public String getHospitalName() {
                return hospitalName;
            }

            public void setHospitalName(String hospitalName) {
                this.hospitalName = hospitalName;
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

            public double getDuration() {
                return duration;
            }

            public void setDuration(double duration) {
                this.duration = duration;
            }

            public String getUrgent() {
                return urgent;
            }

            public void setUrgent(String urgent) {
                this.urgent = urgent;
            }

            public double getUrgentMoney() {
                return urgentMoney;
            }

            public void setUrgentMoney(double urgentMoney) {
                this.urgentMoney = urgentMoney;
            }

            public String getPremium() {
                return premium;
            }

            public void setPremium(String premium) {
                this.premium = premium;
            }

            public double getPremiumMoney() {
                return premiumMoney;
            }

            public void setPremiumMoney(double premiumMoney) {
                this.premiumMoney = premiumMoney;
            }

            public Object getEvenNum() {
                return evenNum;
            }

            public void setEvenNum(Object evenNum) {
                this.evenNum = evenNum;
            }

            public double getExpectMoney() {
                return expectMoney;
            }

            public void setExpectMoney(double expectMoney) {
                this.expectMoney = expectMoney;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getPayType() {
                return payType;
            }

            public void setPayType(Object payType) {
                this.payType = payType;
            }

            public Object getTakeOrderId() {
                return takeOrderId;
            }

            public void setTakeOrderId(Object takeOrderId) {
                this.takeOrderId = takeOrderId;
            }

            public Object getTakeOrderTime() {
                return takeOrderTime;
            }

            public void setTakeOrderTime(Object takeOrderTime) {
                this.takeOrderTime = takeOrderTime;
            }

            public Object getCancelTime() {
                return cancelTime;
            }

            public void setCancelTime(Object cancelTime) {
                this.cancelTime = cancelTime;
            }

            public String getPrompt() {
                return prompt;
            }

            public void setPrompt(String prompt) {
                this.prompt = prompt;
            }

            public Object getArriveTime() {
                return arriveTime;
            }

            public void setArriveTime(Object arriveTime) {
                this.arriveTime = arriveTime;
            }

            public String getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(String beginTime) {
                this.beginTime = beginTime;
            }

            public Object getWaitTime() {
                return waitTime;
            }

            public void setWaitTime(Object waitTime) {
                this.waitTime = waitTime;
            }

            public Object getSurgeryTime() {
                return surgeryTime;
            }

            public void setSurgeryTime(Object surgeryTime) {
                this.surgeryTime = surgeryTime;
            }

            public double getWaitMoney() {
                return waitMoney;
            }

            public void setWaitMoney(double waitMoney) {
                this.waitMoney = waitMoney;
            }

            public double getSurgeryMoney() {
                return surgeryMoney;
            }

            public void setSurgeryMoney(double surgeryMoney) {
                this.surgeryMoney = surgeryMoney;
            }

            public double getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(double totalMoney) {
                this.totalMoney = totalMoney;
            }

            public double getTaxMoney() {
                return taxMoney;
            }

            public void setTaxMoney(double taxMoney) {
                this.taxMoney = taxMoney;
            }

            public int getVersion() {
                return version;
            }

            public void setVersion(int version) {
                this.version = version;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getAhNumber() {
                return ahNumber;
            }

            public void setAhNumber(String ahNumber) {
                this.ahNumber = ahNumber;
            }

            public String getAdNumber() {
                return adNumber;
            }

            public void setAdNumber(String adNumber) {
                this.adNumber = adNumber;
            }

            public double getOvertimeMoney() {
                return overtimeMoney;
            }

            public void setOvertimeMoney(double overtimeMoney) {
                this.overtimeMoney = overtimeMoney;
            }

            public double getInvoicepayMoney() {
                return invoicepayMoney;
            }

            public void setInvoicepayMoney(double invoicepayMoney) {
                this.invoicepayMoney = invoicepayMoney;
            }

            public double getPromptMoney() {
                return promptMoney;
            }

            public void setPromptMoney(double promptMoney) {
                this.promptMoney = promptMoney;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public double getAdMoney() {
                return adMoney;
            }

            public void setAdMoney(double adMoney) {
                this.adMoney = adMoney;
            }

            public double getAhMoney() {
                return ahMoney;
            }

            public void setAhMoney(double ahMoney) {
                this.ahMoney = ahMoney;
            }

            public double getAdactualMoney() {
                return adactualMoney;
            }

            public void setAdactualMoney(double adactualMoney) {
                this.adactualMoney = adactualMoney;
            }

            public double getAhactualMoney() {
                return ahactualMoney;
            }

            public void setAhactualMoney(double ahactualMoney) {
                this.ahactualMoney = ahactualMoney;
            }

            public double getAdpremiumMoney() {
                return adpremiumMoney;
            }

            public void setAdpremiumMoney(double adpremiumMoney) {
                this.adpremiumMoney = adpremiumMoney;
            }

            public double getAhpremiumMoney() {
                return ahpremiumMoney;
            }

            public void setAhpremiumMoney(double ahpremiumMoney) {
                this.ahpremiumMoney = ahpremiumMoney;
            }

            public Object getTakeName() {
                return takeName;
            }

            public void setTakeName(Object takeName) {
                this.takeName = takeName;
            }

            public Object getTakePhone() {
                return takePhone;
            }

            public void setTakePhone(Object takePhone) {
                this.takePhone = takePhone;
            }

            public static class ParamsBean {
            }
        }
    }
}
