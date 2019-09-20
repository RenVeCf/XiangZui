package com.ipd.xiangzui.bean;

import java.util.List;

public class MsgListBean {
    /**
     * msg : 操作成功
     * total : 2
     * code : 200
     * data : {"addrList":[{"searchValue":null,"createBy":null,"createTime":"2019-09-12 15:04:42","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":42,"userId":15,"infoType":"1","title":"系统通知消息","brief":null,"content":"恭喜您提交的用户认证信息,系统以审核通过！","orderId":null,"status":null,"nickname":null,"surgeryName":null,"orderCost":null},{"searchValue":null,"createBy":null,"createTime":"2019-09-12 15:01:46","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":41,"userId":15,"infoType":"1","title":"系统通知消息","brief":null,"content":"审核信息被拒原因:","orderId":null,"status":null,"nickname":null,"surgeryName":null,"orderCost":null}]}
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
             * createTime : 2019-09-12 15:04:42
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * infoId : 42
             * userId : 15
             * infoType : 1
             * title : 系统通知消息
             * brief : null
             * content : 恭喜您提交的用户认证信息,系统以审核通过！
             * orderId : null
             * status : null
             * nickname : null
             * surgeryName : null
             * orderCost : null
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int infoId;
            private int userId;
            private String infoType;
            private String title;
            private Object brief;
            private String content;
            private Object orderId;
            private Object status;
            private Object nickname;
            private Object surgeryName;
            private Object orderCost;

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

            public int getInfoId() {
                return infoId;
            }

            public void setInfoId(int infoId) {
                this.infoId = infoId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getInfoType() {
                return infoType;
            }

            public void setInfoType(String infoType) {
                this.infoType = infoType;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getBrief() {
                return brief;
            }

            public void setBrief(Object brief) {
                this.brief = brief;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getOrderId() {
                return orderId;
            }

            public void setOrderId(Object orderId) {
                this.orderId = orderId;
            }

            public Object getStatus() {
                return status;
            }

            public void setStatus(Object status) {
                this.status = status;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public Object getSurgeryName() {
                return surgeryName;
            }

            public void setSurgeryName(Object surgeryName) {
                this.surgeryName = surgeryName;
            }

            public Object getOrderCost() {
                return orderCost;
            }

            public void setOrderCost(Object orderCost) {
                this.orderCost = orderCost;
            }

            public static class ParamsBean {
            }
        }
    }
}
