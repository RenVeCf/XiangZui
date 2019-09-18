package com.ipd.xiangzui.bean;

import java.util.List;

public class NarcosisListBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"narcosisList":[{"searchValue":null,"createBy":null,"createTime":"2019-08-22 17:41:40","updateBy":null,"updateTime":null,"remark":null,"params":{},"narcosisTypeId":3,"narcosisTypeName":"局部侵润麻醉"},{"searchValue":null,"createBy":null,"createTime":"2019-08-22 17:41:09","updateBy":null,"updateTime":null,"remark":null,"params":{},"narcosisTypeId":2,"narcosisTypeName":"椎管内麻醉"},{"searchValue":null,"createBy":null,"createTime":"2019-08-22 17:40:46","updateBy":null,"updateTime":null,"remark":null,"params":{},"narcosisTypeId":1,"narcosisTypeName":"全身麻醉"}]}
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
        private List<NarcosisListsBean> narcosisList;

        public List<NarcosisListsBean> getNarcosisList() {
            return narcosisList;
        }

        public void setNarcosisList(List<NarcosisListsBean> narcosisList) {
            this.narcosisList = narcosisList;
        }

        public static class NarcosisListsBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-08-22 17:41:40
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * narcosisTypeId : 3
             * narcosisTypeName : 局部侵润麻醉
             */

            private Object searchValue;
            private Object createBy;
            private String createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int narcosisTypeId;
            private String narcosisTypeName;

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

            public int getNarcosisTypeId() {
                return narcosisTypeId;
            }

            public void setNarcosisTypeId(int narcosisTypeId) {
                this.narcosisTypeId = narcosisTypeId;
            }

            public String getNarcosisTypeName() {
                return narcosisTypeName;
            }

            public void setNarcosisTypeName(String narcosisTypeName) {
                this.narcosisTypeName = narcosisTypeName;
            }

            public static class ParamsBean {
            }
        }
    }
}
