package com.ipd.xiangzui.bean;

import java.util.List;

public class HistoricalDemandBean {
    /**
     * msg : 操作成功
     * total : 1
     * code : 200
     * data : {"historyList":[{"searchValue":null,"createBy":null,"createTime":"2019-09-23 11:25:08","updateBy":null,"updateTime":null,"remark":null,"params":{},"demandId":9,"userId":17,"demandType":"1","content":"抹了跌","sketch":"fdsafdsa","contactInfo":"明灭","status":"1","handleTime":null}]}
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
        private List<HistoryListBean> historyList;

        public List<HistoryListBean> getHistoryList() {
            return historyList;
        }

        public void setHistoryList(List<HistoryListBean> historyList) {
            this.historyList = historyList;
        }

        public static class HistoryListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-23 11:25:08
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * demandId : 9
             * userId : 17
             * demandType : 1
             * content : 抹了跌
             * sketch : fdsafdsa
             * contactInfo : 明灭
             * status : 1
             * handleTime : null
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int demandId;
            private int userId;
            private String demandType;
            private String content;
            private String sketch;
            private String contactInfo;
            private String status;
            private Object handleTime;

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

            public int getDemandId() {
                return demandId;
            }

            public void setDemandId(int demandId) {
                this.demandId = demandId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getDemandType() {
                return demandType;
            }

            public void setDemandType(String demandType) {
                this.demandType = demandType;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getSketch() {
                return sketch;
            }

            public void setSketch(String sketch) {
                this.sketch = sketch;
            }

            public String getContactInfo() {
                return contactInfo;
            }

            public void setContactInfo(String contactInfo) {
                this.contactInfo = contactInfo;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getHandleTime() {
                return handleTime;
            }

            public void setHandleTime(Object handleTime) {
                this.handleTime = handleTime;
            }

            public static class ParamsBean {
            }
        }
    }
}
