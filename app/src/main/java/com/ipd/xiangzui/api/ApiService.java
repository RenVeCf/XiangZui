package com.ipd.xiangzui.api;

import com.ipd.xiangzui.bean.AccountListBean;
import com.ipd.xiangzui.bean.AddAccountBean;
import com.ipd.xiangzui.bean.AddAddressBean;
import com.ipd.xiangzui.bean.AddFeeBean;
import com.ipd.xiangzui.bean.AddressListBean;
import com.ipd.xiangzui.bean.CancelIsOrderBean;
import com.ipd.xiangzui.bean.CancelOrderBean;
import com.ipd.xiangzui.bean.CaptchaBean;
import com.ipd.xiangzui.bean.CaptchaLoginBean;
import com.ipd.xiangzui.bean.DelAccountBean;
import com.ipd.xiangzui.bean.DelAddressBean;
import com.ipd.xiangzui.bean.FeeRecordBean;
import com.ipd.xiangzui.bean.FeedBackBean;
import com.ipd.xiangzui.bean.GetUserInfoBean;
import com.ipd.xiangzui.bean.HistoricalDemandBean;
import com.ipd.xiangzui.bean.HomeBean;
import com.ipd.xiangzui.bean.HospitalNameBean;
import com.ipd.xiangzui.bean.ModifyAccountBean;
import com.ipd.xiangzui.bean.ModifyAddressBean;
import com.ipd.xiangzui.bean.ModifyMedicalBean;
import com.ipd.xiangzui.bean.ModifyOrderBean;
import com.ipd.xiangzui.bean.ModifyUserInfoBean;
import com.ipd.xiangzui.bean.ModifyUserPwdBean;
import com.ipd.xiangzui.bean.MsgListBean;
import com.ipd.xiangzui.bean.NarcosisListBean;
import com.ipd.xiangzui.bean.OpenInvoiceBean;
import com.ipd.xiangzui.bean.OrderDetailsBean;
import com.ipd.xiangzui.bean.OrderIsOrverBean;
import com.ipd.xiangzui.bean.OrderListBean;
import com.ipd.xiangzui.bean.OrderQuickBean;
import com.ipd.xiangzui.bean.PwdLoginBean;
import com.ipd.xiangzui.bean.RechargeAccountPayBean;
import com.ipd.xiangzui.bean.RechargeAliPayBean;
import com.ipd.xiangzui.bean.RechargeWechatPayBean;
import com.ipd.xiangzui.bean.RefundDepositBean;
import com.ipd.xiangzui.bean.RegistsBean;
import com.ipd.xiangzui.bean.ResetPwdBean;
import com.ipd.xiangzui.bean.SelectFeeBean;
import com.ipd.xiangzui.bean.SendDemandBean;
import com.ipd.xiangzui.bean.SendOrderBean;
import com.ipd.xiangzui.bean.UploadImgBean;
import com.ipd.xiangzui.bean.VerifiedBean;
import com.ipd.xiangzui.bean.VerifiedTypeBean;
import com.ipd.xiangzui.bean.WalletBean;
import com.ipd.xiangzui.bean.WithdrawAccountBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

import static com.ipd.xiangzui.common.config.UrlConfig.ACCOUNT_LIST;
import static com.ipd.xiangzui.common.config.UrlConfig.ADDRESS_LIST;
import static com.ipd.xiangzui.common.config.UrlConfig.ADD_ACCOUNT;
import static com.ipd.xiangzui.common.config.UrlConfig.ADD_ADDRESS;
import static com.ipd.xiangzui.common.config.UrlConfig.ADD_FEE;
import static com.ipd.xiangzui.common.config.UrlConfig.CANCEL_IS_ORDER;
import static com.ipd.xiangzui.common.config.UrlConfig.CANCEL_ORDER;
import static com.ipd.xiangzui.common.config.UrlConfig.CAPTCHA;
import static com.ipd.xiangzui.common.config.UrlConfig.CAPTCHA_LOGIN;
import static com.ipd.xiangzui.common.config.UrlConfig.DEL_ACCOUNT;
import static com.ipd.xiangzui.common.config.UrlConfig.DEL_ADDRESS;
import static com.ipd.xiangzui.common.config.UrlConfig.FEED_BACK;
import static com.ipd.xiangzui.common.config.UrlConfig.FEE_RECORD;
import static com.ipd.xiangzui.common.config.UrlConfig.GET_USER_INFO;
import static com.ipd.xiangzui.common.config.UrlConfig.HISTORICAL_DEMAND;
import static com.ipd.xiangzui.common.config.UrlConfig.HOME;
import static com.ipd.xiangzui.common.config.UrlConfig.HOSPITAL_NAME;
import static com.ipd.xiangzui.common.config.UrlConfig.MODIFY_ACCOUNT;
import static com.ipd.xiangzui.common.config.UrlConfig.MODIFY_ADDRESS;
import static com.ipd.xiangzui.common.config.UrlConfig.MODIFY_MEDICAL_RECORD;
import static com.ipd.xiangzui.common.config.UrlConfig.MODIFY_ORDER;
import static com.ipd.xiangzui.common.config.UrlConfig.MODIFY_USER_INFO;
import static com.ipd.xiangzui.common.config.UrlConfig.MODIFY_USER_PWD;
import static com.ipd.xiangzui.common.config.UrlConfig.MSG_LIST;
import static com.ipd.xiangzui.common.config.UrlConfig.NARCASIS_LIST;
import static com.ipd.xiangzui.common.config.UrlConfig.OPEN_INVOICE;
import static com.ipd.xiangzui.common.config.UrlConfig.ORDER_DETAILS;
import static com.ipd.xiangzui.common.config.UrlConfig.ORDER_IS_ORVER;
import static com.ipd.xiangzui.common.config.UrlConfig.ORDER_LIST;
import static com.ipd.xiangzui.common.config.UrlConfig.ORDER_QUICK;
import static com.ipd.xiangzui.common.config.UrlConfig.PWD_LOGIN;
import static com.ipd.xiangzui.common.config.UrlConfig.RECHARGE_ACCOUNT_PAY;
import static com.ipd.xiangzui.common.config.UrlConfig.RECHARGE_ALI_PAY;
import static com.ipd.xiangzui.common.config.UrlConfig.RECHARGE_WECHAT_PAY;
import static com.ipd.xiangzui.common.config.UrlConfig.REFUND_DEPOSIT;
import static com.ipd.xiangzui.common.config.UrlConfig.REGISTER;
import static com.ipd.xiangzui.common.config.UrlConfig.RESET_PWD;
import static com.ipd.xiangzui.common.config.UrlConfig.SELECT_FEE;
import static com.ipd.xiangzui.common.config.UrlConfig.SEND_DEMAND;
import static com.ipd.xiangzui.common.config.UrlConfig.SEND_ORDER;
import static com.ipd.xiangzui.common.config.UrlConfig.UPLOAD_IMG;
import static com.ipd.xiangzui.common.config.UrlConfig.VERIFIED;
import static com.ipd.xiangzui.common.config.UrlConfig.VERIFIED_TYPE;
import static com.ipd.xiangzui.common.config.UrlConfig.WALLET;
import static com.ipd.xiangzui.common.config.UrlConfig.WITHDRAW_ACCOUNT;

/**
 * Description ：请求配置
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */
public interface ApiService {

    //获取短信接口
    @FormUrlEncoded
    @POST(CAPTCHA)
    Observable<CaptchaBean> getCaptcha(@FieldMap Map<String, String> map);

    //注册
    @FormUrlEncoded
    @POST(REGISTER)
    Observable<RegistsBean> getRegists(@FieldMap Map<String, String> map);

    //验证码登录
    @FormUrlEncoded
    @POST(CAPTCHA_LOGIN)
    Observable<CaptchaLoginBean> getCaptchaLogin(@FieldMap Map<String, String> map);

    //密码登陆
    @FormUrlEncoded
    @POST(PWD_LOGIN)
    Observable<PwdLoginBean> getPwdLogin(@FieldMap Map<String, String> map);

    //重置密码
    @FormUrlEncoded
    @POST(RESET_PWD)
    Observable<ResetPwdBean> getResetPwd(@FieldMap Map<String, String> map);

    //首页数据
    @FormUrlEncoded
    @POST(HOME)
    Observable<HomeBean> getHome(@FieldMap Map<String, String> map);

//    //职称-列表
//    @FormUrlEncoded
//    @POST(TITLE_LIST)
//    Observable<TitleListBean> getTitleList(@FieldMap Map<String, String> map);

    //用户信息认证-修改保存
    @FormUrlEncoded
    @POST(VERIFIED)
    Observable<VerifiedBean> getVerified(@FieldMap Map<String, String> map);

    //通过id查询用户是否认证信息
    @FormUrlEncoded
    @POST(VERIFIED_TYPE)
    Observable<VerifiedTypeBean> getVerifiedType(@FieldMap Map<String, String> map);

    //通过id查询用户是否医院地址、名称
    @FormUrlEncoded
    @POST(HOSPITAL_NAME)
    Observable<HospitalNameBean> getHospitalName(@FieldMap Map<String, String> map);

    //麻醉方式列表
    @FormUrlEncoded
    @POST(NARCASIS_LIST)
    Observable<NarcosisListBean> getNarcosisList(@FieldMap Map<String, String> map);

    //加急费-取消费用用信息查询
    @FormUrlEncoded
    @POST(SELECT_FEE)
    Observable<SelectFeeBean> getSelectFee(@FieldMap Map<String, String> map);

    //加急费-取消费用用信息查询
    @FormUrlEncoded
    @POST(SEND_ORDER)
    Observable<SendOrderBean> getSendOrder(@FieldMap Map<String, String> map);

    //上传图片
    @Multipart
    @POST(UPLOAD_IMG)
    Observable<UploadImgBean> getUploadImg(@Query("sign") String sign, @PartMap Map<String, RequestBody> map);

    //医生端订单-列表-详情
    @FormUrlEncoded
    @POST(ORDER_DETAILS)
    Observable<OrderDetailsBean> getOrderDetails(@FieldMap Map<String, String> map);

    //历史需求
    @FormUrlEncoded
    @POST(HISTORICAL_DEMAND)
    Observable<HistoricalDemandBean> getHistoricalDemand(@FieldMap Map<String, String> map);

    //发布需求
    @FormUrlEncoded
    @POST(SEND_DEMAND)
    Observable<SendDemandBean> getSendDemand(@FieldMap Map<String, String> map);

    //待接单-加价
    @FormUrlEncoded
    @POST(ADD_FEE)
    Observable<AddFeeBean> getAddFee(@FieldMap Map<String, String> map);

    //进行中-确认结束
    @FormUrlEncoded
    @POST(ORDER_IS_ORVER)
    Observable<OrderIsOrverBean> getOrderIsOrver(@FieldMap Map<String, String> map);

    //待接单-修改订单
    @FormUrlEncoded
    @POST(MODIFY_ORDER)
    Observable<ModifyOrderBean> getModifyOrder(@FieldMap Map<String, String> map);

    //待接单-取消订单
    @FormUrlEncoded
    @POST(CANCEL_ORDER)
    Observable<CancelOrderBean> getCancelOrder(@FieldMap Map<String, String> map);

    //待接单-提交加急
    @FormUrlEncoded
    @POST(ORDER_QUICK)
    Observable<OrderQuickBean> getOrderQuick(@FieldMap Map<String, String> map);

    //以接单-补充病历
    @FormUrlEncoded
    @POST(MODIFY_MEDICAL_RECORD)
    Observable<ModifyMedicalBean> getModifyMedical(@FieldMap Map<String, String> map);

    //以接单-取消订单
    @FormUrlEncoded
    @POST(CANCEL_IS_ORDER)
    Observable<CancelIsOrderBean> getCancelIsOrder(@FieldMap Map<String, String> map);

//    //医学专栏-列表
//    @FormUrlEncoded
//    @POST(SPECIAL_COLUMN)
//    Observable<SpecialColumnBean> getSpecialColumn(@FieldMap Map<String, String> map);
//
//    //专栏详情
//    @FormUrlEncoded
//    @POST(SPECIAL_COLUMN_DETAILS)
//    Observable<SpecialColumnDetailsBean> getSpecialColumnDetails(@FieldMap Map<String, String> map);
//
//    //医院专栏点击收藏
//    @FormUrlEncoded
//    @POST(SPECIAL_COLUMN_COLLECTION)
//    Observable<SpecialColumnCollectionBean> getSpecialColumnCollection(@FieldMap Map<String, String> map);
//
//    //线下活动-列表
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_LIST)
//    Observable<OfflineActivitiesListBean> getOfflineActivitiesList(@FieldMap Map<String, String> map);
//
//    //活动详情
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_DETAILS)
//    Observable<OfflineActivitiesDetailsBean> getOfflineActivitiesDetails(@FieldMap Map<String, String> map);
//
//    //活动详情--购买页面
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_DETAILS_PAY)
//    Observable<OfflineActivitiesDetailsPayBean> getOfflineActivitiesDetailsPay(@FieldMap Map<String, String> map);
//
//    //免费报名
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_FREE)
//    Observable<OfflineActivitiesFreeBean> getOfflineActivitiesFree(@FieldMap Map<String, String> map);
//
//    //支付包报名
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_ALI_PAY)
//    Observable<OfflineActivitiesAliPayBean> getOfflineActivitiesAliPay(@FieldMap Map<String, String> map);
//
//    //微信报名
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_WECHAT_PAY)
//    Observable<OfflineActivitiesWechatPayBean> getOfflineActivitiesWechatPay(@FieldMap Map<String, String> map);
//
//    //余额报名
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_BALANCE_PAY)
//    Observable<OfflineActivitiesBalancePayBean> getOfflineActivitiesBalancePay(@FieldMap Map<String, String> map);
//
//    //我的活动列表
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_MY)
//    Observable<OfflineActivitiesMyBean> getOfflineActivitiesMy(@FieldMap Map<String, String> map);
//
//    //活动-取消报名
//    @FormUrlEncoded
//    @POST(OFFLINE_ACTIVITES_CANCEL)
//    Observable<OfflineActivitiesCancelBean> getOfflineActivitiesCancel(@FieldMap Map<String, String> map);

    //医院端-订单列表
    @FormUrlEncoded
    @POST(ORDER_LIST)
    Observable<OrderListBean> getOrderList(@FieldMap Map<String, String> map);

//    //医生端订单-已接单-点击结束手术-提交数据
//    @FormUrlEncoded
//    @POST(IS_ORDER_OPERATION_END)
//    Observable<IsOrderOperationEndBean> getIsOrderOperationEnd(@FieldMap Map<String, String> map);
//
//    //医生端订单-进行中--点击结束手术
//    @FormUrlEncoded
//    @POST(ING_OPERATION_END)
//    Observable<IngOperationEndBean> getIngOperationEnd(@FieldMap Map<String, String> map);
//
//    //医生端订单-已接单-开始手术
//    @FormUrlEncoded
//    @POST(OPERATION_START)
//    Observable<OperationStartBean> getOperationStart(@FieldMap Map<String, String> map);
//
//    //医生端订单-已接单-到达地点
//    @FormUrlEncoded
//    @POST(IS_ARRIVALS)
//    Observable<IsArrivalsBean> getIsArrivals(@FieldMap Map<String, String> map);
//
//    //医生端订单-已接单-取消订单
//    @FormUrlEncoded
//    @POST(ORDER_CANCEL)
//    Observable<OrderCancelBean> getOrderCancel(@FieldMap Map<String, String> map);
//
//    //医生端订单-接单
//    @FormUrlEncoded
//    @POST(GET_ORDER)
//    Observable<GetOrderBean> getGetOrder(@FieldMap Map<String, String> map);

    //修改用户信息----两个端通用
    @FormUrlEncoded
    @POST(MODIFY_USER_INFO)
    Observable<ModifyUserInfoBean> getModifyUserInfo(@FieldMap Map<String, String> map);

    //修改密码--两个端通用
    @FormUrlEncoded
    @POST(MODIFY_USER_PWD)
    Observable<ModifyUserPwdBean> getModifyUserPwd(@FieldMap Map<String, String> map);

    //钱包--列表--通用
    @FormUrlEncoded
    @POST(WALLET)
    Observable<WalletBean> getWallet(@FieldMap Map<String, String> map);

    //充值-提现记录记录
    @FormUrlEncoded
    @POST(FEE_RECORD)
    Observable<FeeRecordBean> getFeeRecord(@FieldMap Map<String, String> map);

    //医院端-地址列表
    @FormUrlEncoded
    @POST(ADDRESS_LIST)
    Observable<AddressListBean> getAddressList(@FieldMap Map<String, String> map);

    //医院端-添加地址
    @FormUrlEncoded
    @POST(ADD_ADDRESS)
    Observable<AddAddressBean> getAddAddress(@FieldMap Map<String, String> map);

    //医院端-修改地址
    @FormUrlEncoded
    @POST(MODIFY_ADDRESS)
    Observable<ModifyAddressBean> getModifyAddress(@FieldMap Map<String, String> map);

    //医院端-删除地址
    @FormUrlEncoded
    @POST(DEL_ADDRESS)
    Observable<DelAddressBean> getDelAddress(@FieldMap Map<String, String> map);

    //医院端-对公账户列表
    @FormUrlEncoded
    @POST(ACCOUNT_LIST)
    Observable<AccountListBean> getAccountList(@FieldMap Map<String, String> map);

    //医院端-对公账户添加
    @FormUrlEncoded
    @POST(ADD_ACCOUNT)
    Observable<AddAccountBean> getAddAccount(@FieldMap Map<String, String> map);

    //医院端-对公账户修改
    @FormUrlEncoded
    @POST(MODIFY_ACCOUNT)
    Observable<ModifyAccountBean> getModifyAccount(@FieldMap Map<String, String> map);

    //医院端-对公账户删除
    @FormUrlEncoded
    @POST(DEL_ACCOUNT)
    Observable<DelAccountBean> getDelAccount(@FieldMap Map<String, String> map);

    //提现-对公转账
    @FormUrlEncoded
    @POST(WITHDRAW_ACCOUNT)
    Observable<WithdrawAccountBean> getWithdrawAccount(@FieldMap Map<String, String> map);

    //对公-退还保证金
    @FormUrlEncoded
    @POST(REFUND_DEPOSIT)
    Observable<RefundDepositBean> getRefundDeposit(@FieldMap Map<String, String> map);

    //充值-支付包
    @FormUrlEncoded
    @POST(RECHARGE_ALI_PAY)
    Observable<RechargeAliPayBean> getRechargeAliPay(@FieldMap Map<String, String> map);

    //充值-微信充值
    @FormUrlEncoded
    @POST(RECHARGE_WECHAT_PAY)
    Observable<RechargeWechatPayBean> getRechargeWechatPay(@FieldMap Map<String, String> map);

    //充值-对公转账
    @FormUrlEncoded
    @POST(RECHARGE_ACCOUNT_PAY)
    Observable<RechargeAccountPayBean> getRechargeAccountPay(@FieldMap Map<String, String> map);

//    //提现-支付包
//    @FormUrlEncoded
//    @POST(WITHDRAW_ALI_PAY)
//    Observable<WithdrawAliPayBean> getWithdrawAliPay(@FieldMap Map<String, String> map);
//
//    //我的收藏列表
//    @FormUrlEncoded
//    @POST(COLLECTION_LIST)
//    Observable<CollectionListBean> getCollectionList(@FieldMap Map<String, String> map);

    //我的消息
    @FormUrlEncoded
    @POST(MSG_LIST)
    Observable<MsgListBean> getMsgList(@FieldMap Map<String, String> map);

    //医生端-申请开票
    @FormUrlEncoded
    @POST(OPEN_INVOICE)
    Observable<OpenInvoiceBean> getOpenInvoice(@FieldMap Map<String, String> map);

    //意见反馈
    @FormUrlEncoded
    @POST(FEED_BACK)
    Observable<FeedBackBean> getFeedBack(@FieldMap Map<String, String> map);

    //通过用户id查询用户信息--两个端通用
    @FormUrlEncoded
    @POST(GET_USER_INFO)
    Observable<GetUserInfoBean> getGetUserInfo(@FieldMap Map<String, String> map);

//    //麻醉方式列表
//    @FormUrlEncoded
//    @POST(ANESTHESIA_LIST)
//    Observable<AnesthesiaListBean> getAnesthesiaList(@FieldMap Map<String, String> map);
}