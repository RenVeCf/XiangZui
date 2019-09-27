package com.ipd.xiangzui.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class OrderDetailsBean implements Parcelable {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"orderDetail":[{"searchValue":null,"createBy":null,"createTime":"2019-09-24 14:35:32","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderDetailId":48,"orderId":44,"patientName":"佛祖可口可乐","sex":"1","age":17,"height":85754,"weight":6754,"narcosisTypeId":1,"narcosisType":"","positiveCard":"upload/2019/09/24/e31875508e2c54a119e69e5f2f9d6a5d.jpeg","reverseCard":"upload/2019/09/24/a830c454f2a7a732183a90abc9d2aa6c.jpeg","insurance":"upload/2019/09/24/e80f6ff331a343d8b75e37ef9e2c2aaa.jpeg","medicalRecords":"1","surgeryRelated":"upload/2019/09/24/13d2e4ef9dec222278d0ad15554b4a3d.jpg","routineBlood":"upload/2019/09/24/eb137ab33b1d03b7a0707da05fd3796a.jpg","ecg":"upload/2019/09/24/6ed52ce2ef545ab259bc9e88451c000d.jpg,upload/2019/09/24/db5bcefcde7b5a92eae6b0cca5368e7c.png","cruor":"upload/2019/09/24/4f01d98985a418885c3012752bc0a0de.jpg","contagion":"upload/2019/09/24/f3ecc2bf3ed1864d1c23d27035e88dc4.jpg","minBloodPressure":0,"maxBloodPressure":0,"pulse":0,"breathe":0,"animalHeat":0,"diabetes":"1","cerebralInfarction":"1","heartDisease":"1","infectDisease":"1","breatheFunction":"1","narcosisForm":"","complete":"1","handover":"1","status":"1","beginTime":null,"endTime":null,"surgeryName":null,"anestxMode":""},{"searchValue":null,"createBy":null,"createTime":"2019-09-24 14:35:32","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderDetailId":49,"orderId":44,"patientName":"他家啪啪啪","sex":"2","age":17,"height":145,"weight":145,"narcosisTypeId":1,"narcosisType":"","positiveCard":"upload/2019/09/24/30c1447cdfa69aa46627c1a9dade50a0.jpeg","reverseCard":"upload/2019/09/24/6a3bcbe6a84e79bea9c0c3a1667f642f.jpeg","insurance":"upload/2019/09/24/7ce4d0174a2537fdf49c17d38ded2022.jpeg","medicalRecords":"1","surgeryRelated":"upload/2019/09/24/13d2e4ef9dec222278d0ad15554b4a3d.jpg","routineBlood":"upload/2019/09/24/eb137ab33b1d03b7a0707da05fd3796a.jpg","ecg":"upload/2019/09/24/6ed52ce2ef545ab259bc9e88451c000d.jpg,upload/2019/09/24/db5bcefcde7b5a92eae6b0cca5368e7c.png","cruor":"upload/2019/09/24/4f01d98985a418885c3012752bc0a0de.jpg","contagion":"upload/2019/09/24/f3ecc2bf3ed1864d1c23d27035e88dc4.jpg","minBloodPressure":0,"maxBloodPressure":0,"pulse":0,"breathe":0,"animalHeat":0,"diabetes":"1","cerebralInfarction":"1","heartDisease":"1","infectDisease":"1","breatheFunction":"1","narcosisForm":"","complete":"1","handover":"1","status":"1","beginTime":null,"endTime":null,"surgeryName":null,"anestxMode":""}],"order":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"orderId":44,"userId":17,"orderType":"2","surgeryName":"你弄的","hospitalName":"上海任梦阳医院","prov":"上海","city":"上海市","dist":"青浦区","address":"画的范德萨发的","duration":1,"urgent":"2","urgentMoney":50,"premium":"2","premiumMoney":0,"evenNum":2,"expectMoney":2000,"status":"1","payType":null,"takeOrderId":null,"takeOrderTime":null,"cancelTime":null,"prompt":"","arriveTime":null,"beginTime":"2019-10-20 14:31:00","waitTime":null,"surgeryTime":null,"waitMoney":0,"surgeryMoney":0,"totalMoney":0,"taxMoney":0,"version":1,"orderNo":"190993280036","ahNumber":"18502994087","adNumber":null,"overtimeMoney":0,"invoicepayMoney":2300,"promptMoney":300,"endTime":null,"adMoney":1700,"ahMoney":2350,"adactualMoney":0,"ahactualMoney":0,"adpremiumMoney":31.45,"ahpremiumMoney":42.55,"takeName":null,"takePhone":null}}
     */

    private String msg;
    private int code;
    private DataBean data;

    protected OrderDetailsBean(Parcel in) {
        msg = in.readString();
        code = in.readInt();
    }

    public static final Creator<OrderDetailsBean> CREATOR = new Creator<OrderDetailsBean>() {
        @Override
        public OrderDetailsBean createFromParcel(Parcel in) {
            return new OrderDetailsBean(in);
        }

        @Override
        public OrderDetailsBean[] newArray(int size) {
            return new OrderDetailsBean[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(msg);
        parcel.writeInt(code);
    }

    public static class DataBean implements Parcelable{
        /**
         * orderDetail : [{"searchValue":null,"createBy":null,"createTime":"2019-09-24 14:35:32","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderDetailId":48,"orderId":44,"patientName":"佛祖可口可乐","sex":"1","age":17,"height":85754,"weight":6754,"narcosisTypeId":1,"narcosisType":"","positiveCard":"upload/2019/09/24/e31875508e2c54a119e69e5f2f9d6a5d.jpeg","reverseCard":"upload/2019/09/24/a830c454f2a7a732183a90abc9d2aa6c.jpeg","insurance":"upload/2019/09/24/e80f6ff331a343d8b75e37ef9e2c2aaa.jpeg","medicalRecords":"1","surgeryRelated":"upload/2019/09/24/13d2e4ef9dec222278d0ad15554b4a3d.jpg","routineBlood":"upload/2019/09/24/eb137ab33b1d03b7a0707da05fd3796a.jpg","ecg":"upload/2019/09/24/6ed52ce2ef545ab259bc9e88451c000d.jpg,upload/2019/09/24/db5bcefcde7b5a92eae6b0cca5368e7c.png","cruor":"upload/2019/09/24/4f01d98985a418885c3012752bc0a0de.jpg","contagion":"upload/2019/09/24/f3ecc2bf3ed1864d1c23d27035e88dc4.jpg","minBloodPressure":0,"maxBloodPressure":0,"pulse":0,"breathe":0,"animalHeat":0,"diabetes":"1","cerebralInfarction":"1","heartDisease":"1","infectDisease":"1","breatheFunction":"1","narcosisForm":"","complete":"1","handover":"1","status":"1","beginTime":null,"endTime":null,"surgeryName":null,"anestxMode":""},{"searchValue":null,"createBy":null,"createTime":"2019-09-24 14:35:32","updateBy":null,"updateTime":null,"remark":null,"params":{},"orderDetailId":49,"orderId":44,"patientName":"他家啪啪啪","sex":"2","age":17,"height":145,"weight":145,"narcosisTypeId":1,"narcosisType":"","positiveCard":"upload/2019/09/24/30c1447cdfa69aa46627c1a9dade50a0.jpeg","reverseCard":"upload/2019/09/24/6a3bcbe6a84e79bea9c0c3a1667f642f.jpeg","insurance":"upload/2019/09/24/7ce4d0174a2537fdf49c17d38ded2022.jpeg","medicalRecords":"1","surgeryRelated":"upload/2019/09/24/13d2e4ef9dec222278d0ad15554b4a3d.jpg","routineBlood":"upload/2019/09/24/eb137ab33b1d03b7a0707da05fd3796a.jpg","ecg":"upload/2019/09/24/6ed52ce2ef545ab259bc9e88451c000d.jpg,upload/2019/09/24/db5bcefcde7b5a92eae6b0cca5368e7c.png","cruor":"upload/2019/09/24/4f01d98985a418885c3012752bc0a0de.jpg","contagion":"upload/2019/09/24/f3ecc2bf3ed1864d1c23d27035e88dc4.jpg","minBloodPressure":0,"maxBloodPressure":0,"pulse":0,"breathe":0,"animalHeat":0,"diabetes":"1","cerebralInfarction":"1","heartDisease":"1","infectDisease":"1","breatheFunction":"1","narcosisForm":"","complete":"1","handover":"1","status":"1","beginTime":null,"endTime":null,"surgeryName":null,"anestxMode":""}]
         * order : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"orderId":44,"userId":17,"orderType":"2","surgeryName":"你弄的","hospitalName":"上海任梦阳医院","prov":"上海","city":"上海市","dist":"青浦区","address":"画的范德萨发的","duration":1,"urgent":"2","urgentMoney":50,"premium":"2","premiumMoney":0,"evenNum":2,"expectMoney":2000,"status":"1","payType":null,"takeOrderId":null,"takeOrderTime":null,"cancelTime":null,"prompt":"","arriveTime":null,"beginTime":"2019-10-20 14:31:00","waitTime":null,"surgeryTime":null,"waitMoney":0,"surgeryMoney":0,"totalMoney":0,"taxMoney":0,"version":1,"orderNo":"190993280036","ahNumber":"18502994087","adNumber":null,"overtimeMoney":0,"invoicepayMoney":2300,"promptMoney":300,"endTime":null,"adMoney":1700,"ahMoney":2350,"adactualMoney":0,"ahactualMoney":0,"adpremiumMoney":31.45,"ahpremiumMoney":42.55,"takeName":null,"takePhone":null}
         */

        private OrderBean order;
        private List<OrderDetailBean> orderDetail;

        protected DataBean(Parcel in) {
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel in) {
                return new DataBean(in);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<OrderDetailBean> getOrderDetail() {
            return orderDetail;
        }

        public void setOrderDetail(List<OrderDetailBean> orderDetail) {
            this.orderDetail = orderDetail;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
        }

        public static class OrderBean implements Parcelable{
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * orderId : 44
             * userId : 17
             * orderType : 2
             * surgeryName : 你弄的
             * hospitalName : 上海任梦阳医院
             * prov : 上海
             * city : 上海市
             * dist : 青浦区
             * address : 画的范德萨发的
             * duration : 1.0
             * urgent : 2
             * urgentMoney : 50.0
             * premium : 2
             * premiumMoney : 0.0
             * evenNum : 2
             * expectMoney : 2000.0
             * status : 1
             * payType : null
             * takeOrderId : null
             * takeOrderTime : null
             * cancelTime : null
             * prompt :
             * arriveTime : null
             * beginTime : 2019-10-20 14:31:00
             * waitTime : null
             * surgeryTime : null
             * waitMoney : 0.0
             * surgeryMoney : 0.0
             * totalMoney : 0.0
             * taxMoney : 0.0
             * version : 1
             * orderNo : 190993280036
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
             * adpremiumMoney : 31.45
             * ahpremiumMoney : 42.55
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
            private int evenNum;
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
            private Object adNumber;
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

            protected OrderBean(Parcel in) {
                orderId = in.readInt();
                userId = in.readInt();
                orderType = in.readString();
                surgeryName = in.readString();
                hospitalName = in.readString();
                prov = in.readString();
                city = in.readString();
                dist = in.readString();
                address = in.readString();
                duration = in.readDouble();
                urgent = in.readString();
                urgentMoney = in.readDouble();
                premium = in.readString();
                premiumMoney = in.readDouble();
                evenNum = in.readInt();
                expectMoney = in.readDouble();
                status = in.readString();
                prompt = in.readString();
                beginTime = in.readString();
                waitMoney = in.readDouble();
                surgeryMoney = in.readDouble();
                totalMoney = in.readDouble();
                taxMoney = in.readDouble();
                version = in.readInt();
                orderNo = in.readString();
                ahNumber = in.readString();
                overtimeMoney = in.readDouble();
                invoicepayMoney = in.readDouble();
                promptMoney = in.readDouble();
                adMoney = in.readDouble();
                ahMoney = in.readDouble();
                adactualMoney = in.readDouble();
                ahactualMoney = in.readDouble();
                adpremiumMoney = in.readDouble();
                ahpremiumMoney = in.readDouble();
            }

            public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
                @Override
                public OrderBean createFromParcel(Parcel in) {
                    return new OrderBean(in);
                }

                @Override
                public OrderBean[] newArray(int size) {
                    return new OrderBean[size];
                }
            };

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

            public int getEvenNum() {
                return evenNum;
            }

            public void setEvenNum(int evenNum) {
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

            public Object getAdNumber() {
                return adNumber;
            }

            public void setAdNumber(Object adNumber) {
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

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(orderId);
                parcel.writeInt(userId);
                parcel.writeString(orderType);
                parcel.writeString(surgeryName);
                parcel.writeString(hospitalName);
                parcel.writeString(prov);
                parcel.writeString(city);
                parcel.writeString(dist);
                parcel.writeString(address);
                parcel.writeDouble(duration);
                parcel.writeString(urgent);
                parcel.writeDouble(urgentMoney);
                parcel.writeString(premium);
                parcel.writeDouble(premiumMoney);
                parcel.writeInt(evenNum);
                parcel.writeDouble(expectMoney);
                parcel.writeString(status);
                parcel.writeString(prompt);
                parcel.writeString(beginTime);
                parcel.writeDouble(waitMoney);
                parcel.writeDouble(surgeryMoney);
                parcel.writeDouble(totalMoney);
                parcel.writeDouble(taxMoney);
                parcel.writeInt(version);
                parcel.writeString(orderNo);
                parcel.writeString(ahNumber);
                parcel.writeDouble(overtimeMoney);
                parcel.writeDouble(invoicepayMoney);
                parcel.writeDouble(promptMoney);
                parcel.writeDouble(adMoney);
                parcel.writeDouble(ahMoney);
                parcel.writeDouble(adactualMoney);
                parcel.writeDouble(ahactualMoney);
                parcel.writeDouble(adpremiumMoney);
                parcel.writeDouble(ahpremiumMoney);
            }

            public static class ParamsBean implements Parcelable{
                protected ParamsBean(Parcel in) {
                }

                public static final Creator<ParamsBean> CREATOR = new Creator<ParamsBean>() {
                    @Override
                    public ParamsBean createFromParcel(Parcel in) {
                        return new ParamsBean(in);
                    }

                    @Override
                    public ParamsBean[] newArray(int size) {
                        return new ParamsBean[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                }
            }
        }

        public static class OrderDetailBean implements Parcelable{
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-24 14:35:32
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * orderDetailId : 48
             * orderId : 44
             * patientName : 佛祖可口可乐
             * sex : 1
             * age : 17
             * height : 85754.0
             * weight : 6754.0
             * narcosisTypeId : 1
             * narcosisType :
             * positiveCard : upload/2019/09/24/e31875508e2c54a119e69e5f2f9d6a5d.jpeg
             * reverseCard : upload/2019/09/24/a830c454f2a7a732183a90abc9d2aa6c.jpeg
             * insurance : upload/2019/09/24/e80f6ff331a343d8b75e37ef9e2c2aaa.jpeg
             * medicalRecords : 1
             * surgeryRelated : upload/2019/09/24/13d2e4ef9dec222278d0ad15554b4a3d.jpg
             * routineBlood : upload/2019/09/24/eb137ab33b1d03b7a0707da05fd3796a.jpg
             * ecg : upload/2019/09/24/6ed52ce2ef545ab259bc9e88451c000d.jpg,upload/2019/09/24/db5bcefcde7b5a92eae6b0cca5368e7c.png
             * cruor : upload/2019/09/24/4f01d98985a418885c3012752bc0a0de.jpg
             * contagion : upload/2019/09/24/f3ecc2bf3ed1864d1c23d27035e88dc4.jpg
             * minBloodPressure : 0.0
             * maxBloodPressure : 0.0
             * pulse : 0
             * breathe : 0
             * animalHeat : 0.0
             * diabetes : 1
             * cerebralInfarction : 1
             * heartDisease : 1
             * infectDisease : 1
             * breatheFunction : 1
             * narcosisForm :
             * complete : 1
             * handover : 1
             * status : 1
             * beginTime : null
             * endTime : null
             * surgeryName : null
             * anestxMode :
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBeanX params;
            private int orderDetailId;
            private int orderId;
            private String patientName;
            private String sex;
            private int age;
            private double height;
            private double weight;
            private int narcosisTypeId;
            private String narcosisType;
            private String positiveCard;
            private String reverseCard;
            private String insurance;
            private String medicalRecords;
            private String surgeryRelated;
            private String routineBlood;
            private String ecg;
            private String cruor;
            private String contagion;
            private double minBloodPressure;
            private double maxBloodPressure;
            private int pulse;
            private int breathe;
            private double animalHeat;
            private String diabetes;
            private String cerebralInfarction;
            private String heartDisease;
            private String infectDisease;
            private String breatheFunction;
            private String narcosisForm;
            private String complete;
            private String handover;
            private String status;
            private Object beginTime;
            private Object endTime;
            private String surgeryName;
            private String anestxMode;

            public OrderDetailBean() {
                super();
            }

            public OrderDetailBean(Parcel in) {
                createTime = in.readString();
                orderDetailId = in.readInt();
                orderId = in.readInt();
                patientName = in.readString();
                sex = in.readString();
                age = in.readInt();
                height = in.readDouble();
                weight = in.readDouble();
                narcosisTypeId = in.readInt();
                narcosisType = in.readString();
                positiveCard = in.readString();
                reverseCard = in.readString();
                insurance = in.readString();
                medicalRecords = in.readString();
                surgeryRelated = in.readString();
                routineBlood = in.readString();
                ecg = in.readString();
                cruor = in.readString();
                contagion = in.readString();
                minBloodPressure = in.readDouble();
                maxBloodPressure = in.readDouble();
                pulse = in.readInt();
                breathe = in.readInt();
                animalHeat = in.readDouble();
                diabetes = in.readString();
                cerebralInfarction = in.readString();
                heartDisease = in.readString();
                infectDisease = in.readString();
                breatheFunction = in.readString();
                narcosisForm = in.readString();
                complete = in.readString();
                handover = in.readString();
                status = in.readString();
                surgeryName = in.readString();
                anestxMode = in.readString();
            }

            public static final Creator<OrderDetailBean> CREATOR = new Creator<OrderDetailBean>() {
                @Override
                public OrderDetailBean createFromParcel(Parcel in) {
                    return new OrderDetailBean(in);
                }

                @Override
                public OrderDetailBean[] newArray(int size) {
                    return new OrderDetailBean[size];
                }
            };

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

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public String getPatientName() {
                return patientName;
            }

            public void setPatientName(String patientName) {
                this.patientName = patientName;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public double getHeight() {
                return height;
            }

            public void setHeight(double height) {
                this.height = height;
            }

            public double getWeight() {
                return weight;
            }

            public void setWeight(double weight) {
                this.weight = weight;
            }

            public int getNarcosisTypeId() {
                return narcosisTypeId;
            }

            public void setNarcosisTypeId(int narcosisTypeId) {
                this.narcosisTypeId = narcosisTypeId;
            }

            public String getNarcosisType() {
                return narcosisType;
            }

            public void setNarcosisType(String narcosisType) {
                this.narcosisType = narcosisType;
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

            public String getInsurance() {
                return insurance;
            }

            public void setInsurance(String insurance) {
                this.insurance = insurance;
            }

            public String getMedicalRecords() {
                return medicalRecords;
            }

            public void setMedicalRecords(String medicalRecords) {
                this.medicalRecords = medicalRecords;
            }

            public String getSurgeryRelated() {
                return surgeryRelated;
            }

            public void setSurgeryRelated(String surgeryRelated) {
                this.surgeryRelated = surgeryRelated;
            }

            public String getRoutineBlood() {
                return routineBlood;
            }

            public void setRoutineBlood(String routineBlood) {
                this.routineBlood = routineBlood;
            }

            public String getEcg() {
                return ecg;
            }

            public void setEcg(String ecg) {
                this.ecg = ecg;
            }

            public String getCruor() {
                return cruor;
            }

            public void setCruor(String cruor) {
                this.cruor = cruor;
            }

            public String getContagion() {
                return contagion;
            }

            public void setContagion(String contagion) {
                this.contagion = contagion;
            }

            public double getMinBloodPressure() {
                return minBloodPressure;
            }

            public void setMinBloodPressure(double minBloodPressure) {
                this.minBloodPressure = minBloodPressure;
            }

            public double getMaxBloodPressure() {
                return maxBloodPressure;
            }

            public void setMaxBloodPressure(double maxBloodPressure) {
                this.maxBloodPressure = maxBloodPressure;
            }

            public int getPulse() {
                return pulse;
            }

            public void setPulse(int pulse) {
                this.pulse = pulse;
            }

            public int getBreathe() {
                return breathe;
            }

            public void setBreathe(int breathe) {
                this.breathe = breathe;
            }

            public double getAnimalHeat() {
                return animalHeat;
            }

            public void setAnimalHeat(double animalHeat) {
                this.animalHeat = animalHeat;
            }

            public String getDiabetes() {
                return diabetes;
            }

            public void setDiabetes(String diabetes) {
                this.diabetes = diabetes;
            }

            public String getCerebralInfarction() {
                return cerebralInfarction;
            }

            public void setCerebralInfarction(String cerebralInfarction) {
                this.cerebralInfarction = cerebralInfarction;
            }

            public String getHeartDisease() {
                return heartDisease;
            }

            public void setHeartDisease(String heartDisease) {
                this.heartDisease = heartDisease;
            }

            public String getInfectDisease() {
                return infectDisease;
            }

            public void setInfectDisease(String infectDisease) {
                this.infectDisease = infectDisease;
            }

            public String getBreatheFunction() {
                return breatheFunction;
            }

            public void setBreatheFunction(String breatheFunction) {
                this.breatheFunction = breatheFunction;
            }

            public String getNarcosisForm() {
                return narcosisForm;
            }

            public void setNarcosisForm(String narcosisForm) {
                this.narcosisForm = narcosisForm;
            }

            public String getComplete() {
                return complete;
            }

            public void setComplete(String complete) {
                this.complete = complete;
            }

            public String getHandover() {
                return handover;
            }

            public void setHandover(String handover) {
                this.handover = handover;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getBeginTime() {
                return beginTime;
            }

            public void setBeginTime(Object beginTime) {
                this.beginTime = beginTime;
            }

            public Object getEndTime() {
                return endTime;
            }

            public void setEndTime(Object endTime) {
                this.endTime = endTime;
            }

            public String getSurgeryName() {
                return surgeryName;
            }

            public void setSurgeryName(String surgeryName) {
                this.surgeryName = surgeryName;
            }

            public String getAnestxMode() {
                return anestxMode;
            }

            public void setAnestxMode(String anestxMode) {
                this.anestxMode = anestxMode;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeString(createTime);
                parcel.writeInt(orderDetailId);
                parcel.writeInt(orderId);
                parcel.writeString(patientName);
                parcel.writeString(sex);
                parcel.writeInt(age);
                parcel.writeDouble(height);
                parcel.writeDouble(weight);
                parcel.writeInt(narcosisTypeId);
                parcel.writeString(narcosisType);
                parcel.writeString(positiveCard);
                parcel.writeString(reverseCard);
                parcel.writeString(insurance);
                parcel.writeString(medicalRecords);
                parcel.writeString(surgeryRelated);
                parcel.writeString(routineBlood);
                parcel.writeString(ecg);
                parcel.writeString(cruor);
                parcel.writeString(contagion);
                parcel.writeDouble(minBloodPressure);
                parcel.writeDouble(maxBloodPressure);
                parcel.writeInt(pulse);
                parcel.writeInt(breathe);
                parcel.writeDouble(animalHeat);
                parcel.writeString(diabetes);
                parcel.writeString(cerebralInfarction);
                parcel.writeString(heartDisease);
                parcel.writeString(infectDisease);
                parcel.writeString(breatheFunction);
                parcel.writeString(narcosisForm);
                parcel.writeString(complete);
                parcel.writeString(handover);
                parcel.writeString(status);
                parcel.writeString(surgeryName);
                parcel.writeString(anestxMode);
            }

            public static class ParamsBeanX implements Parcelable{
                protected ParamsBeanX(Parcel in) {
                }

                public static final Creator<ParamsBeanX> CREATOR = new Creator<ParamsBeanX>() {
                    @Override
                    public ParamsBeanX createFromParcel(Parcel in) {
                        return new ParamsBeanX(in);
                    }

                    @Override
                    public ParamsBeanX[] newArray(int size) {
                        return new ParamsBeanX[size];
                    }
                };

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel parcel, int i) {
                }
            }
        }
    }
}
