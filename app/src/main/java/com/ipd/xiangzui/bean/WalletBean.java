package com.ipd.xiangzui.bean;

import java.util.List;

public class WalletBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"income":0,"margin":0,"balance":0,"balaList":[{"searchValue":null,"createBy":null,"createTime":"2019-09-11 18:02:02","updateBy":null,"updateTime":null,"remark":null,"params":{},"balanceId":167,"userId":15,"balanceType":"4","category":"1","balanceMoney":0,"balanceTypeId":null,"balanceNo":null,"title":"活动取消报名退款!"}],"expend":0}
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
        /**
         * income : 0.0
         * margin : 0.0
         * balance : 0.0
         * balaList : [{"searchValue":null,"createBy":null,"createTime":"2019-09-11 18:02:02","updateBy":null,"updateTime":null,"remark":null,"params":{},"balanceId":167,"userId":15,"balanceType":"4","category":"1","balanceMoney":0,"balanceTypeId":null,"balanceNo":null,"title":"活动取消报名退款!"}]
         * expend : 0.0
         */

        private double income;
        private double margin;
        private double balance;
        private double expend;
        private List<BalaListBean> balaList;

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
            this.income = income;
        }

        public double getMargin() {
            return margin;
        }

        public void setMargin(double margin) {
            this.margin = margin;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public double getExpend() {
            return expend;
        }

        public void setExpend(double expend) {
            this.expend = expend;
        }

        public List<BalaListBean> getBalaList() {
            return balaList;
        }

        public void setBalaList(List<BalaListBean> balaList) {
            this.balaList = balaList;
        }

        public static class BalaListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-11 18:02:02
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * balanceId : 167
             * userId : 15
             * balanceType : 4
             * category : 1
             * balanceMoney : 0.0
             * balanceTypeId : null
             * balanceNo : null
             * title : 活动取消报名退款!
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int balanceId;
            private int userId;
            private String balanceType;
            private String category;
            private double balanceMoney;
            private Object balanceTypeId;
            private Object balanceNo;
            private String title;

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

            public int getBalanceId() {
                return balanceId;
            }

            public void setBalanceId(int balanceId) {
                this.balanceId = balanceId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getBalanceType() {
                return balanceType;
            }

            public void setBalanceType(String balanceType) {
                this.balanceType = balanceType;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public double getBalanceMoney() {
                return balanceMoney;
            }

            public void setBalanceMoney(double balanceMoney) {
                this.balanceMoney = balanceMoney;
            }

            public Object getBalanceTypeId() {
                return balanceTypeId;
            }

            public void setBalanceTypeId(Object balanceTypeId) {
                this.balanceTypeId = balanceTypeId;
            }

            public Object getBalanceNo() {
                return balanceNo;
            }

            public void setBalanceNo(Object balanceNo) {
                this.balanceNo = balanceNo;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public static class ParamsBean {
            }
        }
    }
}
