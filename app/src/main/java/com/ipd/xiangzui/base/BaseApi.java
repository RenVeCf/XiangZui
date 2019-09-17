package com.ipd.xiangzui.base;

import android.text.TextUtils;

import com.ipd.xiangzui.bean.NullDataBean;
import com.ipd.xiangzui.utils.ApplicationUtil;
import com.ipd.xiangzui.utils.NetWorkUtil;
import com.ipd.xiangzui.utils.SPUtil;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.ipd.xiangzui.common.config.IConstants.TOKEN;

/**
 * Description ：网络对象层
 * Author ： MengYang
 * Email ： 942685687@qq.com
 * Time ： 2018/8/27.
 */

public class BaseApi {

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 15000;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 15000;

    /**
     * 无超时及缓存策略的Retrofit
     *
     * @param baseUrl
     * @return retrofit
     */
    public Retrofit getSimpleRetrofit(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())//请求结果转换为基本类型，一般为String
                .addConverterFactory(new NullOnEmptyConverterFactory())//Retrofit 后台返回没有数据的时候调用，不是null，是状态为OK，但什么返回数据都没有
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//适配RxJava2.0,
                // RxJava1.x则为RxJavaCallAdapterFactory.create()
                .build();
        return retrofit;
    }

    /**
     * 使用OkHttp配置了超时及缓存策略的Retrofit
     *
     * @param baseUrl
     * @return retrofit
     */
    public Retrofit getRetrofit(String baseUrl) {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);// Level.BODY貌似在上传文件是有bug，改成.Head就OK了
        //缓存
        File cacheFile = new File(ApplicationUtil.getContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request build = chain.request();
                //请求头
                build = build.newBuilder()
                        .addHeader("Content-Type", "application/json; charset=utf-8")
                        .addHeader("token", SPUtil.get(ApplicationUtil.getContext(), TOKEN, "") + "")
                        .build();
                return chain.proceed(build);
            }
        };
        //创建一个OkHttpClient并设置超时时间
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(mRewriteCacheControlInterceptor)//没网的情况下
                .addNetworkInterceptor(mRewriteCacheControlInterceptor)//有网的情况下
                .addInterceptor(headerInterceptor)
                .addInterceptor(logInterceptor)
                .cache(cache)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())//请求结果转换为基本类型，一般为String
                .addConverterFactory(new NullOnEmptyConverterFactory())//Retrofit 后台返回没有数据的时候调用，不是null，是状态为OK，但什么返回数据都没有
                .addConverterFactory(GsonConverterFactory.create())//请求的结果转为实体类
                // RxJava1.x则为RxJavaCallAdapterFactory.create()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//适配RxJava2.0,
                .build();
        return retrofit;
    }

    /**
     * Retrofit2.0 后台返回没有数据的时候调用
     *
     * @return
     */
    class NullOnEmptyConverterFactory extends Converter.Factory {
        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            final Converter<ResponseBody, ?> delegate = retrofit.nextResponseBodyConverter(this, type, annotations);
            return new Converter<ResponseBody, Object>() {

                @Override
                public Object convert(ResponseBody body) throws IOException {
                    if (body.contentLength() == 0) return new NullDataBean();
                    return delegate.convert(body);
                }
            };
        }
    }

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    private final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();//拦截器获取请求
            String cacheControl = request.cacheControl().toString();//服务器的缓存策略
            if (!NetWorkUtil.isNetConnected(ApplicationUtil.getContext())) {//断网时配置缓存策略
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl) ?
                                CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE)
                        //                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }

            Response originalResponse = chain.proceed(request);
            if (NetWorkUtil.isNetConnected(ApplicationUtil.getContext())) {//在线缓存
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", cacheControl)//应用服务端配置的缓存策略
                        //                        .header("Cache-Control", "public, max-age=" + 60 * 2)//有网的时候连接服务器请求,缓存(时间)
                        .build();
            } else {//离线缓存
                /**
                 * only-if-cached:(仅为请求标头)
                 *　 请求:告知缓存者,我希望内容来自缓存，我并不关心被缓存响应,是否是新鲜的.
                 * max-stale:
                 *　 请求:意思是,我允许缓存者，发送一个,过期不超过指定秒数的,陈旧的缓存.
                 *　 响应:同上.
                 * max-age:
                 *   请求:强制响应缓存者，根据该值,校验新鲜性.即与自身的Age值,与请求时间做比较.如果超出max-age值,
                 *   则强制去服务器端验证.以确保返回一个新鲜的响应.
                 *   响应:同上.
                 */
                //需要服务端配合处理缓存请求头，不然会抛出： HTTP 504 Unsatisfiable Request (only-if-cached)
                //                KLog.e("离线缓存"+CACHE_STALE_SEC+"秒");
                return originalResponse.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)//CACHE_STALE_SEC
                        .build();
            }
        }
    };
}