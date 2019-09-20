package com.ipd.xiangzui.bean;

import java.util.List;

public class AddressListBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"addrList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"addressId":14,"userId":17,"prov":"上海","city":"上海市","dist":"青浦区","detail":"Mr 名命名哦四","defaultAddress":"2"}]}
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
        private List<AddrListBean> addrList;

        public List<AddrListBean> getAddrList() {
            return addrList;
        }

        public void setAddrList(List<AddrListBean> addrList) {
            this.addrList = addrList;
        }

        public static class AddrListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * addressId : 14
             * userId : 17
             * prov : 上海
             * city : 上海市
             * dist : 青浦区
             * detail : Mr 名命名哦四
             * defaultAddress : 2
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int addressId;
            private int userId;
            private String prov;
            private String city;
            private String dist;
            private String detail;
            private String defaultAddress;
            private boolean show;

            public boolean isShow() {
                return show;
            }

            public void setShow(boolean show) {
                this.show = show;
            }

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

            public int getAddressId() {
                return addressId;
            }

            public void setAddressId(int addressId) {
                this.addressId = addressId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
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

            public String getDetail() {
                return detail;
            }

            public void setDetail(String detail) {
                this.detail = detail;
            }

            public String getDefaultAddress() {
                return defaultAddress;
            }

            public void setDefaultAddress(String defaultAddress) {
                this.defaultAddress = defaultAddress;
            }

            public static class ParamsBean {
            }
        }
    }
}
