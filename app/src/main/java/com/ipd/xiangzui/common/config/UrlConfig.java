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
    String BASE_URL = "http://47.93.126.235:8010/jhzc/";
    String BASE_LOCAL_URL = "http://47.93.126.235:8010/";

    /**
     * 登陆
     */
    String VERIFICATION_CODE = "appUser/util/getSms"; //验证码
    String FORGET_PWD = "appUser/login/forgetPassword"; //忘记密码
    String LOGIN = "appUser/login/login"; //登陆
    String REGISTER = "appUser/login/regists"; //注册
    String UPLOAD_ID_IMG = "appUser/login/uploadID"; //上传身份证照片
    String UPLOAD_IMG = "appUser/util/upload"; //上传图片
    String MODIFY_USER_DATA = "appUser/user/userUpdate"; //修改用户信息
    String FEED_BACK = "appUser/feedback/userFeedback"; //用户意见反馈


    /**
     * 在线下单
     */
    String HOME = "appUser/onlineOrder/index"; //轮播图
    String CHOICE_STORE = "appUser/onlineOrder/city"; //用户端-根据位置选择门店
    String SEARCH_CHOICE_STORE = "appUser/onlineOrder/storeSelect"; //用户端-门店查询-模糊查询
    String SELECT_CAR = "appUser/onlineOrder/clickVehicle"; //用户端-点击选车
    String COUPON = "appUser/coupon/userCoupon"; //我的优惠卷
    String ALI_PAY = "appUser/Pay/vehiclePay"; //用户端-选车-支付宝支付
    String WEIXIN_PAY = "appUser/Pay/weChatPay"; //用户端-选车-微信支付


    /**
     * 订单查看
     */
    String SELECT_ORDER_ALL = "appUser/order/orderSelect"; //订单查看-列表数据
    String ORDER_DETAILS = "appUser/order/orderDetails"; //订单查看-详情
    String CANCEL_ORDER = "appUser/order/orderCancel"; //订单-待付款-取消订单
    String GET_CAR_CANCEL_ORDER = "appUser/order/orderCancelVehicle"; //订单-取车-取消订单
    String SELECT_VEHICLE = "appUser/order/selectVehicle"; //订单查看-详情-查看车辆
    String GET_CAR = "appUser/order/orderConfirm"; //订单查看-详情-确认取车
    String CAR_RETURN_DETAILS = "appUser/order/carReturnDetails"; //查看退车单
    String CAR_RETURN_CANCEL_ORDER = "appUser/order/orderCarCancel"; //订单-查看退车单-取消订单
    String CAR_RETURN_CONFIRM = "appUser/order/OkconfirmVehicle"; //提前还车-查看推车单-确认
    String CONFIRM_VEHICLE = "appUser/order/confirmVehicle"; //提前还车-确认页面
    String EXTEND_TIME = "appUser/order/extendVehicle"; //延长租车-页面数据
    String EXTEND_TIME_LIST = "appUser/order/vehicleServices"; //延长租车-租车服务--列表
    String EXTEND_TIME_ALI = "appUser/ExtendPay/ExtendVehiclePay"; //延长租车-支付宝支付
    String EXTEND_TIME_WECHAT = "appUser/ExtendPay/ExtendweChatPay"; //延长租车-微信支付
    String SELECT_CAR_CANCEL_ORDER = "appUser/order/orderPickCancel"; //订单-查看车辆-取消订单
    String SELECT_PAY_ALI = "appUser/stayOrde/Pay"; //待付款--支付包支付
    String SELECT_PAY_WECHAT = "appUser/stayOrde/weChatPay"; //待付款--微信支付


    /**
     * 我的钱包
     */
    String WALLET = "appUser/balance/userDetailed"; //我的钱包余额明细
    String CASH_WITHDRAWAL_FEE = "appUser/balance/putConf"; //提现费率
    String SELECT_BACK = "appUser/bank/myList"; //我的银行卡列表
    String SELECT_OPENING_BACK = "appUser/bank/bankType"; //添加银行卡-选择开户行
    String ADD_BANK = "appUser/bank/insertBank"; //添加银行卡
    String LAST_BANK = "appUser/bank/latelyBank"; //我的银行卡最近使用的-一张银行卡
    String CASH_WITHDRAWAL = "appUser/balance/balancePutforwardPay"; //我的钱包提现


    /**
     * 维修保养
     */
    String REPAIR_LIST = "appUser/repair/repairIndex"; //维修保养-首页
    String REPAIR_ALI = "appUser/repairPay/repairPay"; //维修订单-支付包支付
    String REPAIR_WECHAT = "appUser/repairPay/repairWeChat"; //维修订单-微信支付
    String REPAIR_PROJECT_HORIZONTAL = "appUser/repair/repairType"; //维修保养-维修项目-类型
    String REPAIR_PROJECT_VERTICAL = "appUser/repair/repairList"; //维修保养-类型列表数据
    String CHARGE = "appUser/repair/downCharge"; //维修保养-充电下拉数据
    String REPAIR_CONFIRM = "appUser/repair/okOrder"; //维修保养-确认下单
    String SRORE_DETAILS = "appUser/repair/storeDetails"; //维修保养-门店详情


    /**
     * 维修订单
     */
    String REPAIR_ORDER = "appUser/orderRepair/orderList"; //维修订单-列表
    String REPAIR_DETAILS = "appUser/orderRepair/orderDetails"; //维修订单-列表-详情


    /**
     * 消息
     */
    String MSG = "appUser/onlineOrder/message"; //用户端-消息列表


    /**
     * 版本更新
     */
    String MODIFY_VERSION = "app/version/versionInfo"; //版本更新
}
