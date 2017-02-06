package com.app.nuts.app.mvp.model;

import com.app.nuts.app.mvp.contract.MovieContract;
import com.app.nuts.app.mvp.model.api.cache.CacheManager;
import com.app.nuts.app.mvp.model.api.service.ServiceManager;
import com.app.nuts.base.di.scope.ActivityScope;
import com.app.nuts.base.mvp.BaseModel;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by 王立强 on 2017/2/4.
 */
@ActivityScope
public class MovieModel extends BaseModel<ServiceManager, CacheManager> implements MovieContract.Model {

    @Inject
    public MovieModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }


    @Override
    public Observable<String> getMovieInfo(int start, int count) {
        Observable<String> movieInfo = mServiceManager.getCommonService()
                .getMovieInfo(start, count);
        return movieInfo;
    }
}
