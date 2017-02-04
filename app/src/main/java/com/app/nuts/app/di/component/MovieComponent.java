package com.app.nuts.app.di.component;

import com.app.nuts.app.mvp.ui.MainActivity;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.di.module.MovieModule;
import com.app.nuts.base.di.scope.ActivityScope;

import dagger.Component;

/**
 * Created by 王立强 on 2017/2/4.
 */

@ActivityScope
@Component(modules = MovieModule.class,dependencies = AppComponent.class)
public interface MovieComponent {
    void inject(MainActivity activity);
}
