package com.app.nuts;

import android.app.Application;
import android.content.Context;

import com.app.nuts.base.AppManager;
import com.app.nuts.base.di.component.DaggerBaseComponent;
import com.app.nuts.base.di.module.AppModule;
import com.app.nuts.base.di.module.ClientModule;
import com.app.nuts.base.di.module.GlobeConfigModule;
import com.app.nuts.base.di.module.ImageModule;

import javax.inject.Inject;

import static com.app.nuts.base.rxerrorhandler.utils.Preconditions.checkNotNull;

/**
 * Created by 王立强 on 2017/2/4.
 */

public abstract class BaseApplication extends Application{
    static private BaseApplication mApplication;
    private ClientModule mClientModule;
    private AppModule mAppModule;
    private ImageModule mImagerModule;
    private GlobeConfigModule mGlobeConfigModule;
    @Inject
    protected AppManager mAppManager;
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        this.mAppModule = new AppModule(this);//提供application
        DaggerBaseComponent
                .builder()
                .appModule(mAppModule)
                .build()
                .inject(this);
        this.mImagerModule = new ImageModule();//图片加载框架默认使用glide
        this.mClientModule = new ClientModule(mAppManager);//用于提供okhttp和retrofit的单例
        this.mGlobeConfigModule = checkNotNull(getGlobeConfigModule(), "lobeConfigModule is required");
    }

    /**
     * 程序终止的时候执行
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mClientModule != null)
            this.mClientModule = null;
        if (mAppModule != null)
            this.mAppModule = null;
        if (mImagerModule != null)
            this.mImagerModule = null;
        if (mAppManager != null) {//释放资源
            this.mAppManager.release();
            this.mAppManager = null;
        }
        if (mApplication != null)
            this.mApplication = null;
    }

    /**
     * (将app的全局配置信息封装进module使用Dagger注入到需要配置信息的地方)
     * @return
     */
    protected abstract GlobeConfigModule getGlobeConfigModule();

    public ClientModule getClientModule() {
        return mClientModule;
    }

    public AppModule getAppModule() {
        return mAppModule;
    }

    public ImageModule getImageModule() {
        return mImagerModule;
    }

    public AppManager getAppManager() {
        return mAppManager;
    }

    public static Context getContext() {
        return mApplication;
    }
}
