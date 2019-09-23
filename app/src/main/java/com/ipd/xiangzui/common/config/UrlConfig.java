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
    String VERIFIED = "ah/user/userApprove"; //用户信息认证-修改保存
    String VERIFIED_TYPE = "ah/user/approveStatus"; //通过id查询用户是否认证信息
    String HOSPITAL_NAME = "ah/user/invoiceData"; //通过id查询用户是否医院地址、名称
    String NARCASIS_LIST = "ah/order/narcosisList"; //麻醉方式列表
    String UPLOAD_IMG = "ah/util/upload"; //上传图片
    String UPLOAD_IMGS = "ah/util/arrUpload"; //上传多图片
    String SELECT_FEE = "ah/order/urgentMoney"; //加急费-取消费用用信息查询
    String SEND_ORDER = "ah/order/invoice"; //医院端-首页-发单 -正式
    String HISTORICAL_DEMAND = "ah/demand/historyList"; //历史需求
    String SEND_DEMAND = "ah/demand/releaseDemand"; //发布需求

    /**
     * 订单
     */
    String ORDER_DETAILS = "ah/order/orderDetails"; //医院端-订单详情
    String ORDER_LIST = "ah/order/orderList"; //医院端-订单列表
    String IS_ORDER_OPERATION_END = "ad/order/operationLower"; //医生端订单-已接单-点击结束手术-提交数据
    String ING_OPERATION_END = "ad/order/endOperation"; //医生端订单-进行中--点击结束手术
    String OPERATION_START = "ad/order/startOperation"; //医生端订单-已接单-开始手术
    String IS_ARRIVALS = "ad/order/arrivePlace"; //医生端订单-已接单-到达地点
    String ORDER_CANCEL = "ad/order/alreadyCancel"; //医生端订单-已接单-取消订单
    String GET_ORDER = "ad/order/receipt"; //医生端订单-接单
    String ANESTHESIA_LIST = "ah/order/narcosisList"; //麻醉方式列表

    /**
     * 我的
     */
    String MODIFY_USER_INFO = "ah/user/updateUser"; //修改用户信息----两个端通用
    String MODIFY_USER_PWD = "ah/user/changePass"; //修改密码--两个端通用
    String WALLET = "ah/wallet/balaList"; //钱包--列表--通用
    String FEE_RECORD = "ah/wallet/recordList"; //充值-提现记录记录
    String RECHARGE_ALI_PAY = "ah/rechargePay/alipay"; //充值-支付包
    String RECHARGE_WECHAT_PAY = "ah/rechargePay/wechatPay"; //充值-微信充值
    String RECHARGE_ACCOUNT_PAY = "ah/rechargePay/publicTransfer"; //充值-对公转账
    String WITHDRAW_ALI_PAY = "ah/wallet/payWithdrawal"; //提现-支付包
    String COLLECTION_LIST = "ad/collection/collectionList"; //我的收藏列表
    String MSG_LIST = "ah/user/userInfo"; //我的消息
    String FEED_BACK = "ah/setup/opinion"; //意见反馈
    String GET_USER_INFO = "ah/user/selectByUser"; //通过用户id查询用户信息--两个端通用
    String ADDRESS_LIST = "ah/address/addrList"; //医院端-地址列表
    String ADD_ADDRESS = "ah/address/insertAddr"; //医院端-添加地址
    String MODIFY_ADDRESS = "ah/address/updateAddr"; //医院端-修改地址
    String DEL_ADDRESS = "ah/address/deleteAddr"; //医院端-删除地址
    String ACCOUNT_LIST = "ah/brought/broughtList"; //医院端-对公账户列表
    String ADD_ACCOUNT = "ah/brought/addBrought"; //医院端-对公账户添加
    String MODIFY_ACCOUNT = "ah/brought/updateBrought"; //医院端-对公账户修改
    String DEL_ACCOUNT = "ah/brought/deleteBrought"; //医院端-对公账户删除
    String OPEN_INVOICE = "ah/invoice/applyTicket"; //医生端-申请开票
    String WITHDRAW_ACCOUNT = "ah/wallet/publecWithdrawal"; //提现-对公转账
}
