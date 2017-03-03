package com.app.nuts.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

import com.app.nuts.base.mvp.AppPresenter;
import com.app.nuts.utils.NetUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import org.simple.eventbus.EventBus;
import javax.inject.Inject;

import es.dmoral.toasty.Toasty;

/**
 * Created by 王立强 on 2017/2/4.
 */

public abstract class BaseAppActivity<P extends AppPresenter> extends RxAppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected BaseApplication mApplication;
    @Inject
    protected P mPresenter;

    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_add_activity_list";//是否加入到activity的list，管理

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mApplication.getAppManager().setCurrentActivity(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mApplication.getAppManager().getCurrentActivity() == this) {
            mApplication.getAppManager().setCurrentActivity(null);
        }
    }

    @Nullable
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (BaseApplication) getApplication();

        if(!NetUtil.isNetworkAvailable()){
            Toasty.error(getApplicationContext(), "网络连接异常，请检查！", Toast.LENGTH_SHORT, true).show();
        }

        //如果intent包含了此字段,并且为true说明不加入到list
        // 默认为false,如果不需要管理(比如不需要在退出所有activity(killAll)时，退出此activity就在intent加此字段为true)
        boolean isNotAdd = false;
        if (getIntent() != null)
            isNotAdd = getIntent().getBooleanExtra(IS_NOT_ADD_ACTIVITY_LIST, false);

        if (!isNotAdd)
            mApplication.getAppManager().addActivity(this);

        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().register(this);//注册到事件主线
        ComponentInject();//依赖注入
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void ComponentInject();


    public void FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mApplication.getAppManager().removeActivity(this);
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this);
        this.mPresenter = null;
        this.mApplication = null;
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return true;
    }
}
