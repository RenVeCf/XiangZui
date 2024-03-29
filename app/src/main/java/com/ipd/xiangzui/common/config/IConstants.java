package com.ipd.xiangzui.common.config;

/**
 * Description ：公共配置类
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/26.
 */
public interface IConstants {
    /**
     * 包名
     */
    String PACKAGE_NAME = "com.ipd.xiangzui";

    /**
     * SharedPreferences
     * 共享参数
     */
    String FIRST_APP = "is_first"; //第一次进应用
    String IS_LOGIN = "is_login"; //已经登录
    String IS_SUPPLEMENT_INFO = "is_supplement_info"; // 1:未认证, 2:认证了
    String USER_ID = "user_id"; //用户标识
    String NAME = "name"; //用户真实姓名
    String NIKE_NAME = "nike_name"; //用户姓名
    String PHONE = "phone"; //用户手机号码
    String SERVICE_PHONE = "15139877951"; //咨询客服号码
    String AVATAR = "avatar"; //头像
    String LATIUDE = "latitude"; //经度
    String LONGTITUDE = "longtitude"; //纬度
    String TOKEN = "is_token"; //token
    String HOSPTIAL_NAME = "hospital_name"; //医院名称
    String PROV = "prov"; //省
    String CITY = "city"; //市
    String DIST = "dist"; //区
    String ADDRESS = "address"; //详细地址
    String SIGN = "40777B235DFE79175B6D921D1B7536C4"; //签名后缀
    int JPUSH_SEQUENCE = 100; //极光精准推送序列


    /**
     * requestCode
     * 请求码
     */
    int REQUEST_CODE_90 = 90;//实名认证头像上传
    int REQUEST_CODE_91 = 91;//实名认证代理人身份证上传
    int REQUEST_CODE_92 = 92;//实名认证医院营业执照上传
    int REQUEST_CODE_93 = 93;//实名认证医疗机构资格证上传
    int REQUEST_CODE_94 = 94;//实名认证医院代理人管理授权书上传
    int REQUEST_CODE_95 = 95;//患者身份证上传
    int REQUEST_CODE_96 = 96;//患者保险同意书上传
    int REQUEST_CODE_97 = 97;//连台新增患者身份证上传
    int REQUEST_CODE_98 = 98;//连台新增患者保险同意书上传
    int REQUEST_CODE_99 = 99;//地址的增删改
    int REQUEST_CODE_100 = 100;//地址选中
    int REQUEST_CODE_101 = 101;//医院信息回跳
    int REQUEST_CODE_102 = 102;//账户的增删改
    int REQUEST_CODE_103 = 103;//手术相关病历上传回跳
    int REQUEST_CODE_104 = 104;//血常规上传回跳
    int REQUEST_CODE_105 = 105;//心电图上传回跳
    int REQUEST_CODE_106 = 106;//凝血功能上传回跳
    int REQUEST_CODE_107 = 107;//传染病指标上传回跳
    int REQUEST_CODE_108 = 108;//资质认证地址的选择
    int REQUEST_CODE_109 = 109;//连台添加患者时的回跳
    int REQUEST_CODE_110 = 110;//补充病历连台回跳
    int REQUEST_CODE_111 = 111;//补充病历回跳
    int REQUEST_CODE_112 = 112;//连台修改患者时的回跳
    int REQUEST_CODE_113 = 113;//钱包回跳刷新
    int REQUEST_CODE_114 = 114;//返回主页刷新

    /**
     * resultCode
     * 返回码
     */
    int RESULT_CODE = 0;
}
