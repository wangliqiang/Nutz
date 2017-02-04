package com.app.nuts.app.common;

import android.app.Application;

import com.app.nuts.app.di.module.CacheModule;
import com.app.nuts.app.di.module.ServiceModule;
import com.app.nuts.app.mvp.model.api.cache.CacheManager;
import com.app.nuts.app.mvp.model.api.service.ServiceManager;
import com.app.nuts.base.AppManager;
import com.app.nuts.base.di.module.AppModule;
import com.app.nuts.base.di.module.ClientModule;
import com.app.nuts.base.di.module.GlobeConfigModule;
import com.app.nuts.base.di.module.ImageModule;
import com.app.nuts.base.rxerrorhandler.core.RxErrorHandler;
import com.app.nuts.widget.imageloader.ImageLoader;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;

/**
 * Created by 王立强 on 2017/2/4.
 */

@Singleton
@Component(modules = {AppModule.class, ClientModule.class, ServiceModule.class, ImageModule.class,
        CacheModule.class, GlobeConfigModule.class})
public interface AppComponent {
    Application Application();

    //服务管理器,retrofitApi
    ServiceManager serviceManager();

    //缓存管理器
    CacheManager cacheManager();

    //Rxjava错误处理管理类
    RxErrorHandler rxErrorHandler();

    OkHttpClient okHttpClient();

    //图片管理器,用于加载图片的管理类,默认使用glide,使用策略模式,可替换框架
    ImageLoader imageLoader();

    //gson
    Gson gson();

    //用于管理所有activity
    AppManager appManager();
}
