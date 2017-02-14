package com.app.nuts.app.common;

import com.app.nuts.base.BaseAppFragment;
import com.app.nuts.base.mvp.AppPresenter;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by 王立强 on 2017/2/4.
 */

public abstract class BaseFragment<P extends AppPresenter> extends BaseAppFragment<P> {
    protected App mApp;

    @Override
    protected void ComponentInject() {
        mApp = (App) mActivity.getApplication();
        setupFragmentComponent(mApp.getAppComponent());
    }

    //提供AppComponent(提供所有的单例对象)给子类，进行Component依赖
    protected abstract void setupFragmentComponent(AppComponent appComponent);

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher watcher = App.getRefWatcher(getActivity());//使用leakCanary检测fragment的内存泄漏
        if (watcher != null) {
            watcher.watch(this);
        }
        this.mApp = null;
    }

}
