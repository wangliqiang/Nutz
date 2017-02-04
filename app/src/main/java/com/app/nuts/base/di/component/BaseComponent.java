package com.app.nuts.base.di.component;

import com.app.nuts.BaseApplication;
import com.app.nuts.base.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by 王立强 on 2017/2/4.
 */

@Singleton
@Component(modules={AppModule.class})
public interface BaseComponent {
    void inject(BaseApplication application);
}
