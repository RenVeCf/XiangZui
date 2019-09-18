package com.ipd.xiangzui.api;

import com.ipd.xiangzui.bean.CaptchaBean;
import com.ipd.xiangzui.bean.CaptchaLoginBean;
import com.ipd.xiangzui.bean.HomeBean;
import com.ipd.xiangzui.bean.HospitalNameBean;
import com.ipd.xiangzui.bean.NarcosisListBean;
import com.ipd.xiangzui.bean.PwdLoginBean;
import com.ipd.xiangzui.bean.RegistsBean;
import com.ipd.xiangzui.bean.ResetPwdBean;
import com.ipd.xiangzui.bean.UploadImgBean;
import com.ipd.xiangzui.bean.VerifiedBean;
import com.ipd.xiangzui.bean.VerifiedTypeBean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

import static com.ipd.xiangzui.common.config.UrlConfig.CAPTCHA;
import static com.ipd.xiangzui.common.config.UrlConfig.CAPTCHA_LOGIN;
import static com.ipd.xiangzui.common.config.UrlConfig.HOME;
import static com.ipd.xiangzui.common.config.UrlConfig.HOSPITAL_NAME;
import static com.ipd.xiangzui.common.config.UrlConfig.NARCASIS_LIST;
import static com.ipd.xiangzui.common.config.UrlConfig.PWD_LOGIN;
import static com.ipd.xiangzui.common.config.UrlConfig.REGISTER;
import static com.ipd.xiangzui.common.config.UrlConfig.RESET_PWD;
import static com.ipd.xiangzui.common.config.UrlConfig.UPLOAD_IMG;
import static com.ipd.xiangzui.common.config.UrlConfig.VERIFIED;
import static com.ipd.xiangzui.common.config.UrlConfig.VERIFIED_TYPE;

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

    //上传图片
    @Multipart
    @POST(UPLOAD_IMG)
    Observable<UploadImgBean> getUploadImg(@Query("sign") String sign, @PartMap Map<String, RequestBody> map);

//    //医生端订单-列表-详情
//    @FormUrlEncoded
//    @POST(ORDER_DETAILS)
//    Observable<OrderDetailsBean> getOrderDetails(@FieldMap Map<String, String> map);
//
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
//
//    //医生端订单-列表
//    @FormUrlEncoded
//    @POST(ORDER_LIST)
//    Observable<OrderListBean> getOrderList(@FieldMap Map<String, String> map);
//
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
//
//    //修改用户信息----两个端通用
//    @FormUrlEncoded
//    @POST(MODIFY_USER_INFO)
//    Observable<ModifyUserInfoBean> getModifyUserInfo(@FieldMap Map<String, String> map);
//
//    //修改密码--两个端通用
//    @FormUrlEncoded
//    @POST(MODIFY_USER_PWD)
//    Observable<ModifyUserPwdBean> getModifyUserPwd(@FieldMap Map<String, String> map);
//
//    //钱包--列表--通用
//    @FormUrlEncoded
//    @POST(WALLET)
//    Observable<WalletBean> getWallet(@FieldMap Map<String, String> map);
//
//    //充值-提现记录记录
//    @FormUrlEncoded
//    @POST(FEE_RECORD)
//    Observable<FeeRecordBean> getFeeRecord(@FieldMap Map<String, String> map);
//
//    //充值-支付包
//    @FormUrlEncoded
//    @POST(RECHARGE_ALI_PAY)
//    Observable<RechargeAliPayBean> getRechargeAliPay(@FieldMap Map<String, String> map);
//
//    //充值-微信充值
//    @FormUrlEncoded
//    @POST(RECHARGE_WECHAT_PAY)
//    Observable<RechargeWechatPayBean> getRechargeWechatPay(@FieldMap Map<String, String> map);
//
//    //提现-支付包
//    @FormUrlEncoded
//    @POST(WITHDRAW_ALI_PAY)
//    Observable<WithdrawAliPayBean> getWithdrawAliPay(@FieldMap Map<String, String> map);
//
//    //我的收藏列表
//    @FormUrlEncoded
//    @POST(COLLECTION_LIST)
//    Observable<CollectionListBean> getCollectionList(@FieldMap Map<String, String> map);
//
//    //我的消息
//    @FormUrlEncoded
//    @POST(MSG_LIST)
//    Observable<MsgListBean> getMsgList(@FieldMap Map<String, String> map);
//
//    //意见反馈
//    @FormUrlEncoded
//    @POST(FEED_BACK)
//    Observable<FeedBackBean> getFeedBack(@FieldMap Map<String, String> map);
//
//    //通过用户id查询用户信息--两个端通用
//    @FormUrlEncoded
//    @POST(GET_USER_INFO)
//    Observable<GetUserInfoBean> getGetUserInfo(@FieldMap Map<String, String> map);
//
//    //麻醉方式列表
//    @FormUrlEncoded
//    @POST(ANESTHESIA_LIST)
//    Observable<AnesthesiaListBean> getAnesthesiaList(@FieldMap Map<String, String> map);
}