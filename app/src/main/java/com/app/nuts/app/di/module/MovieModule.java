package com.app.nuts.app.di.module;

import com.app.nuts.app.mvp.contract.MovieContract;
import com.app.nuts.app.mvp.model.MovieModel;
import com.app.nuts.base.di.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by 王立强 on 2017/2/4.
 */
@Module
public class MovieModule {
    private MovieContract.View view;

    /**
     * 构建Module时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     * @param view
     */
    public MovieModule(MovieContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    MovieContract.View provideMovieView(){
        return this.view;
    }

    @ActivityScope
    @Provides
    MovieContract.Model provideMovieModel(MovieModel model){
        return model;
    }
}
