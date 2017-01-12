package com.app.nuts;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.app.nuts.utils.FakeCrashLibrary;
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
        }else{
            Timber.plant(new CrashReportingTree());
        }
        /**
         * 初始化LeakCanary
         */
        LeakCanary.install(this);
    }

    /** A tree which logs important information for crash reporting. */
    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
