package com.app.nuts;

import android.app.Application;
import android.content.Context;
import com.squareup.leakcanary.LeakCanary;

import timber.log.Timber;

/**
 * Created by 王立强 on 2016/10/9.
 */

public class App extends Application {

    private static Context mContext;

    /**
     * 全局Context
     * @return
     */
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
        /**
         * 初始化LeakCanary
         */
        LeakCanary.install(this);
    }

}
