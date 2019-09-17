package com.ipd.xiangzui.bean;

import java.util.List;

public class HomeBean {
    /**
     * msg : 操作成功
     * code : 200
     * data : {"orderList":[],"infoList":[{"searchValue":null,"createBy":null,"createTime":"2019-09-09 02:51:14","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":40,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190947835678已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"测试01","orderCost":1384},{"searchValue":null,"createBy":null,"createTime":"2019-09-09 01:59:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":39,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190911551426已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"测试最新订单02","orderCost":1445},{"searchValue":null,"createBy":null,"createTime":"2019-09-09 01:45:35","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":38,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190971360392已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"最新测试","orderCost":1809},{"searchValue":null,"createBy":null,"createTime":"2019-09-09 01:33:59","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":37,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190971360392已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"最新测试","orderCost":976},{"searchValue":null,"createBy":null,"createTime":"2019-09-08 23:43:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":36,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190971360392已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"最新测试","orderCost":976},{"searchValue":null,"createBy":null,"createTime":"2019-09-06 22:31:17","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":35,"userId":3,"infoType":"3","title":"订单完成","brief":null,"content":"190979078479已完成手术","orderId":null,"status":null,"nickname":"懒懒的小猪","surgeryName":"连台手术","orderCost":1020},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 15:04:20","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":34,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190973597383已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"连台手术","orderCost":1860},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 14:44:09","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":33,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190925942947已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"测试单台手术10","orderCost":1180},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 12:05:18","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":30,"userId":3,"infoType":"3","title":"订单完成","brief":null,"content":"190989490630已完成手术","orderId":null,"status":null,"nickname":"懒懒的小猪","surgeryName":"手术名字","orderCost":1860},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 10:16:04","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":29,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190969584040已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"张三订单","orderCost":976},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 09:59:15","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":28,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190920919074已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"连台手术","orderCost":1860},{"searchValue":null,"createBy":null,"createTime":"2019-09-05 01:33:01","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":27,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190923567746已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"患者信息","orderCost":1180},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 23:37:29","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":26,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190903332025已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"连台手术","orderCost":6960},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 23:24:38","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":25,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190914934875已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"连台手术","orderCost":2199.32},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 23:13:24","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":24,"userId":8,"infoType":"3","title":"订单完成","brief":null,"content":"190965597346已完成手术","orderId":null,"status":null,"nickname":"测试医生端01","surgeryName":"测试单台手术01","orderCost":2132},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 20:47:32","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":23,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190987615535已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"手术名字","orderCost":1860},{"searchValue":null,"createBy":null,"createTime":"2019-09-04 13:53:12","updateBy":null,"updateTime":null,"remark":null,"params":{},"infoId":18,"userId":10,"infoType":"3","title":"订单完成","brief":null,"content":"190942059278已完成手术","orderId":null,"status":null,"nickname":"享醉994726","surgeryName":"测试单台手术","orderCost":0}],"pictureList":[{"pictureId":24,"title":"测试医院端轮播图文本内容02","picPath":"upload/2019/09/03/104a00114b6cb762a0e658713e3d26de.jpg","status":"1","content":"<p>测试医院端轮播图文本内容02<\/p>","type":"3","pictureType":"2","url":null,"createTime":"2019-09-03 15:12:38","updateTime":null},{"pictureId":23,"title":"测试医院端轮播图链接网址网易02","picPath":"upload/2019/09/03/b48990b8acc3204bcfe0ef1c813dff00.jpg","status":"1","content":null,"type":"2","pictureType":"2","url":"https://www.163.com","createTime":"2019-09-03 15:11:23","updateTime":null},{"pictureId":22,"title":"测试医院端轮播图无02","picPath":"upload/2019/09/03/7543811020f5d2d04d05c30fc34d55ba.jpg","status":"1","content":null,"type":"1","pictureType":"2","url":null,"createTime":"2019-09-03 15:09:00","updateTime":null},{"pictureId":21,"title":"测试医院端轮播图文本内容01","picPath":"upload/2019/09/03/b9bada448509b02d689c8d52421fc953.jpg","status":"1","content":"<p>测试医院端轮播图文本内容01<\/p>","type":"3","pictureType":"2","url":null,"createTime":"2019-09-03 15:08:05","updateTime":null},{"pictureId":20,"title":"测试医院端轮播图无01","picPath":"upload/2019/09/03/15125e0495c2edf1bacfe5a4f8cb877d.jpeg","status":"1","content":null,"type":"1","pictureType":"2","url":null,"createTime":"2019-09-03 15:06:45","updateTime":"2019-09-03 15:09:08"},{"pictureId":19,"title":"测试医院端轮播图链接网址01","picPath":"upload/2019/09/03/5a418000ead8eb355937ec0a9c7608a3.jpg","status":"1","content":null,"type":"2","pictureType":"2","url":"https://www.baidu.com","createTime":"2019-09-03 15:05:18","updateTime":"2019-09-03 15:07:14"}]}
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
        private List<?> orderList;
        private List<InfoListBean> infoList;
        private List<PictureListBean> pictureList;

        public List<?> getOrderList() {
            return orderList;
        }

        public void setOrderList(List<?> orderList) {
            this.orderList = orderList;
        }

        public List<InfoListBean> getInfoList() {
            return infoList;
        }

        public void setInfoList(List<InfoListBean> infoList) {
            this.infoList = infoList;
        }

        public List<PictureListBean> getPictureList() {
            return pictureList;
        }

        public void setPictureList(List<PictureListBean> pictureList) {
            this.pictureList = pictureList;
        }

        public static class InfoListBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : 2019-09-09 02:51:14
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {}
             * infoId : 40
             * userId : 10
             * infoType : 3
             * title : 订单完成
             * brief : null
             * content : 190947835678已完成手术
             * orderId : null
             * status : null
             * nickname : 享醉994726
             * surgeryName : 测试01
             * orderCost : 1384.0
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
            private String nickname;
            private String surgeryName;
            private double orderCost;

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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getSurgeryName() {
                return surgeryName;
            }

            public void setSurgeryName(String surgeryName) {
                this.surgeryName = surgeryName;
            }

            public double getOrderCost() {
                return orderCost;
            }

            public void setOrderCost(double orderCost) {
                this.orderCost = orderCost;
            }

            public static class ParamsBean {
            }
        }

        public static class PictureListBean {
            /**
             * pictureId : 24
             * title : 测试医院端轮播图文本内容02
             * picPath : upload/2019/09/03/104a00114b6cb762a0e658713e3d26de.jpg
             * status : 1
             * content : <p>测试医院端轮播图文本内容02</p>
             * type : 3
             * pictureType : 2
             * url : null
             * createTime : 2019-09-03 15:12:38
             * updateTime : null
             */

            private int pictureId;
            private String title;
            private String picPath;
            private String status;
            private String content;
            private String type;
            private String pictureType;
            private String url;
            private String createTime;
            private Object updateTime;

            public int getPictureId() {
                return pictureId;
            }

            public void setPictureId(int pictureId) {
                this.pictureId = pictureId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicPath() {
                return picPath;
            }

            public void setPicPath(String picPath) {
                this.picPath = picPath;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getPictureType() {
                return pictureType;
            }

            public void setPictureType(String pictureType) {
                this.pictureType = pictureType;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }
        }
    }
}
