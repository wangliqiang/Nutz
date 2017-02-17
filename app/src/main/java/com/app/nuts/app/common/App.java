package com.app.nuts.app.common;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.app.nuts.BuildConfig;
import com.app.nuts.app.di.module.CacheModule;
import com.app.nuts.app.di.module.ServiceModule;
import com.app.nuts.app.mvp.model.api.Api;
import com.app.nuts.base.BaseApplication;
import com.app.nuts.base.di.module.GlobeConfigModule;
import com.app.nuts.http.GlobeHttpHandler;
import com.app.nuts.utils.UiUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import timber.log.Timber;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class App extends BaseApplication {
    private AppComponent mAppComponent;
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(getAppModule())//baseApplication提供
                .clientModule(getClientModule())//baseApplication提供
                .imageModule(getImageModule())//baseApplication提供
                .globeConfigModule(getGlobeConfigModule())//全局配置
                .serviceModule(new ServiceModule())//api
                .cacheModule(new CacheModule())//缓存
                .build();

        if (BuildConfig.DEBUG) {//Timber日志打印
            Timber.plant(new Timber.DebugTree());
        }

        installLeakCanary();
    }

    /**
     * 安装leakCanary检测内存泄露
     */
    protected void installLeakCanary() {
        this.mRefWatcher = true ? LeakCanary.install(this) : RefWatcher.DISABLED;
    }

    /**
     * 获得leakCanary观察器
     *
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context) {
        App application = (App) context.getApplicationContext();
        return application.mRefWatcher;
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppComponent != null)
            this.mAppComponent = null;
        if(mRefWatcher != null)
            this.mRefWatcher = null;
    }


    /**
     * 将AppComponent返回出去,供其它地方使用, AppComponent接口中声明的方法返回的实例, 在getAppComponent()拿到对象后都可以直接使用
     *
     * @return
     */
    public AppComponent getAppComponent() {
        return mAppComponent;
    }


    /**
     * app的全局配置信息封装进module(使用Dagger注入到需要配置信息的地方)
     *
     * @return
     */
    @Override
    protected GlobeConfigModule getGlobeConfigModule() {
        return GlobeConfigModule
                .buidler()
                .baseurl(Api.APP_DOMAIN)
                .globeHttpHandler(new GlobeHttpHandler() {// 这里可以提供一个全局处理http响应结果的处理类,
                    // 这里可以比客户端提前一步拿到服务器返回的结果,可以做一些操作,比如token超时,重新获取
                    @Override
                    public Response onHttpResultResponse(String httpResult, Interceptor.Chain chain, Response response) {
                        //这里可以先客户端一步拿到每一次http请求的结果,可以解析成json,做一些操作,如检测到token过期后
                        //重新请求token,并重新执行请求
                        try {
                            if (!TextUtils.isEmpty(httpResult)) {
                                Log.e(TAG, "httpResult ------>" + httpResult);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return response;
                        }
                        return response;
                    }

                    // 这里可以在请求服务器之前可以拿到request,做一些操作比如给request统一添加token或者header
                    @Override
                    public Request onHttpRequestBefore(Interceptor.Chain chain, Request request) {
                        //如果需要再请求服务器之前做一些操作,则重新返回一个做过操作的的requeat如增加header,不做操作则返回request
                        return request;
                    }
                })
                .responseErroListener((context, e) -> {
                    Timber.tag(TAG).w("------------>" + e.getMessage());
                    UiUtils.SnackbarText("网络连接异常");
                }).build();
    }

}
