package com.ipd.xiangzui.bean;

import java.util.List;

public class AccountListBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"broughtList":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"broughtId":9,"userId":17,"companyName":"啦啦啦啦","openBank":"噜啦啦噜啦啦","bankAccount":"5554585555577","defaultBrought":"2"}]}
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
        private List<BroughtListBean> broughtList;

        public List<BroughtListBean> getBroughtList() {
            return broughtList;
        }

        public void setBroughtList(List<BroughtListBean> broughtList) {
            this.broughtList = broughtList;
        }

        public static class BroughtListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * broughtId : 9
             * userId : 17
             * companyName : 啦啦啦啦
             * openBank : 噜啦啦噜啦啦
             * bankAccount : 5554585555577
             * defaultBrought : 2
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int broughtId;
            private int userId;
            private String companyName;
            private String openBank;
            private String bankAccount;
            private String defaultBrought;

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

            public int getBroughtId() {
                return broughtId;
            }

            public void setBroughtId(int broughtId) {
                this.broughtId = broughtId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }

            public String getOpenBank() {
                return openBank;
            }

            public void setOpenBank(String openBank) {
                this.openBank = openBank;
            }

            public String getBankAccount() {
                return bankAccount;
            }

            public void setBankAccount(String bankAccount) {
                this.bankAccount = bankAccount;
            }

            public String getDefaultBrought() {
                return defaultBrought;
            }

            public void setDefaultBrought(String defaultBrought) {
                this.defaultBrought = defaultBrought;
            }

            public static class ParamsBean {
            }
        }
    }
}
