package com.ipd.xiangzui.common.config;

/**
 * Description ：URL 配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface UrlConfig {
    /**
     * 域名
     */
    String BASE_URL = "http://47.244.137.174:8083/xz/";
    String BASE_LOCAL_URL = "http://47.244.137.174:8083/";

    /**
     * 登陆
     */
    String CAPTCHA = "ah/util/getSms"; //获取短信接口
    String REGISTER = "ah/login/regists"; //注册
    String CAPTCHA_LOGIN = "ah/login/smsCodelogin"; //验证码登录
    String PWD_LOGIN = "ah/login/login"; //密码登陆
    String RESET_PWD = "ah/login/passReset"; //重置密码

    /**
     * 首页
     */
    String HOME = "ah/index/indexData"; //首页数据
    String TITLE_LIST = "ad/user/titleList"; //职称-列表
    String VERIFIED = "ad/user/userApprove"; //用户信息认证-修改保存
    String UPLOAD_IMG = "ah/util/upload"; //上传图片
    String UPLOAD_IMGS = "ah/util/arrUpload"; //上传图片

    /**
     * 订单
     */
    String ORDER_DETAILS = "ad/order/orderDetails"; //医生端订单-列表-详情
    String ORDER_LIST = "ad/order/orderList"; //医生端订单-列表
    String IS_ORDER_OPERATION_END = "ad/order/operationLower"; //医生端订单-已接单-点击结束手术-提交数据
    String ING_OPERATION_END = "ad/order/endOperation"; //医生端订单-进行中--点击结束手术
    String OPERATION_START = "ad/order/startOperation"; //医生端订单-已接单-开始手术
    String IS_ARRIVALS = "ad/order/arrivePlace"; //医生端订单-已接单-到达地点
    String ORDER_CANCEL = "ad/order/alreadyCancel"; //医生端订单-已接单-取消订单
    String GET_ORDER = "ad/order/receipt"; //医生端订单-接单
    String ANESTHESIA_LIST = "ah/order/narcosisList"; //麻醉方式列表

    /**
     * 医学专栏
     */
    String SPECIAL_COLUMN = "ad/medical/medicalList"; //医学专栏-列表
    String SPECIAL_COLUMN_DETAILS = "ad/medical/medicalDetails"; //专栏详情
    String SPECIAL_COLUMN_COLLECTION = "ad/medical/collection"; //医院专栏点击收藏

    /**
     * 线下活动
     */
    String OFFLINE_ACTIVITES_LIST = "ad/activity/activityList"; //线下活动-列表
    String OFFLINE_ACTIVITES_DETAILS = "ad/activity/activityDetails"; //活动详情
    String OFFLINE_ACTIVITES_DETAILS_PAY = "ad/activity/activityPayDetails"; //活动详情--购买页面
    String OFFLINE_ACTIVITES_FREE = "ad/activityRegist/freeRegist"; //免费报名
    String OFFLINE_ACTIVITES_ALI_PAY = "ad/activityRegist/alipay"; //支付包报名
    String OFFLINE_ACTIVITES_WECHAT_PAY = "ad/activityRegist/wechatPay"; //微信报名
    String OFFLINE_ACTIVITES_BALANCE_PAY = "ad/activityRegist/payBalance"; //余额报名
    String OFFLINE_ACTIVITES_MY = "ad/activity/myActivityList"; //我的活动列表
    String OFFLINE_ACTIVITES_CANCEL = "ad/activity/cancelRegist"; //活动-取消报名

    /**
     * 我的
     */
    String MODIFY_USER_INFO = "ah/user/updateUser"; //修改用户信息----两个端通用
    String MODIFY_USER_PWD = "ah/user/changePass"; //修改密码--两个端通用
    String WALLET = "ah/wallet/balaList"; //钱包--列表--通用
    String FEE_RECORD = "ah/wallet/recordList"; //充值-提现记录记录
    String RECHARGE_ALI_PAY = "ah/rechargePay/alipay"; //充值-支付包
    String RECHARGE_WECHAT_PAY = "ah/rechargePay/wechatPay"; //充值-微信充值
    String WITHDRAW_ALI_PAY = "ah/wallet/payWithdrawal"; //提现-支付包
    String COLLECTION_LIST = "ad/collection/collectionList"; //我的收藏列表
    String MSG_LIST = "ah/user/userInfo"; //我的消息
    String FEED_BACK = "ah/setup/opinion"; //意见反馈
    String GET_USER_INFO = "ah/user/selectByUser"; //通过用户id查询用户信息--两个端通用
}
