package com.app.nuts.app.common;

import com.app.nuts.base.BaseAppActivity;
import com.app.nuts.base.mvp.AppPresenter;

/**
 * Created by 王立强 on 2017/2/4.
 */

public abstract class BaseActivity<P extends AppPresenter> extends BaseAppActivity<P> {
    protected App mApp;

    @Override
    protected void ComponentInject() {
        mApp = (App) getApplication();
        setupActivityComponent(mApp.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.mApp = null;
    }
}
