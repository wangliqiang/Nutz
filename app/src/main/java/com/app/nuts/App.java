package com.app.nuts;

import android.app.Application;
import android.content.Context;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by 王立强 on 2016/10/9.
 */

public class App extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        /**
         * 初始化LeakCanary
         */
        LeakCanary.install(this);
    }

    /**
     * 设置全局的Context
     *
     * @return
     */
    public static Context getmContext() {
        return mContext;
    }
}
