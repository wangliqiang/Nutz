package com.app.nuts.http;

import com.app.nuts.App;
import com.app.nuts.utils.NetworkUtil;
import com.app.nuts.utils.SpUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import timber.log.Timber;

/**
 * Created by 王立强 on 2017/1/6.
 */

public class Http {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static OkHttpClient client;
    private static HttpService httpService;
    private static Retrofit retrofit;

    /**
     * @return retrofit的底层利用反射的方式, 获取所有的api接口的类
     */
    public static HttpService getHttpService() {
        if (httpService == null) {
            httpService = getRetrofit().create(HttpService.class);
        }
        return httpService;
    }

    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            synchronized (Http.class) {
                //添加一个log拦截器,打印所有的log
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(message -> Timber.tag("OkHttp").e(message));
//                //可以设置请求过滤的水平,body,basic,headers
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                //设置 请求的缓存的大小跟位置
                File cacheFile = new File(App.getContext().getCacheDir(), "cache");
                Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小

                //获取OkHttp实例
                client = new OkHttpClient
                        .Builder()
                        //设置公共参数
//                        .addInterceptor(addQueryParameterInterceptor())
                        //设置头
//                        .addInterceptor(addHeaderInterceptor())
                        .addInterceptor(httpLoggingInterceptor)
                        .cache(cache)
                        .connectTimeout(601, TimeUnit.SECONDS)
                        .readTimeout(601, TimeUnit.SECONDS)
                        .writeTimeout(601, TimeUnit.SECONDS)
                        .build();
                //获取Retrofit实例
                retrofit = new Retrofit
                        .Builder()
                        .baseUrl(BASE_URL)
                        .client(client)
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .addConverterFactory(ScalarsConverterFactory.create())//解析String
                        .addConverterFactory(GsonConverterFactory.create())//解析JSON
                        .build();
            }
        }
        return retrofit;
    }

    /**
     * 设置公共参数
     */
    private static Interceptor addQueryParameterInterceptor() {
        Interceptor addQueryParameterInterceptor = chain -> {
            Request originalRequest = chain.request();
            Request request;
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("", "")
                    .addQueryParameter("", "")
                    .build();
            request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        };
        return addQueryParameterInterceptor;
    }

    /**
     * 设置头
     */
    private static Interceptor addHeaderInterceptor() {
        Interceptor headerInterceptor = chain -> {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    // Provide your custom header here
                    .header("token","")
                    .method(originalRequest.method(), originalRequest.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
        return headerInterceptor;
    }

    /**
     * 设置缓存
     */
    private static Interceptor addCacheInterceptor() {
        Interceptor cacheInterceptor = chain -> {
            Request request = chain.request();
            if (!NetworkUtil.isNetworkAvailable(App.getContext())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetworkUtil.isNetworkAvailable(App.getContext())) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时 ,意思就是不读取缓存数据,只对get有用,post没有缓冲
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Retrofit")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为4周  只对get有用,post没有缓冲
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" +
                                maxStale)
                        .removeHeader("token")
                        .build();
            }
            return response;
        };
        return cacheInterceptor;
    }
}
