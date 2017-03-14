package com.app.nuts.app.mvp.model;

import com.app.nuts.app.mvp.contract.MovieContract;
import com.app.nuts.app.mvp.model.api.cache.CacheManager;
import com.app.nuts.app.mvp.model.api.service.ServiceManager;
import com.app.nuts.base.di.scope.ActivityScope;
import com.app.nuts.base.mvp.BaseModel;

import javax.inject.Inject;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import rx.Observable;

/**
 * Created by 王立强 on 2017/2/4.
 */
@ActivityScope
public class MovieModel extends BaseModel<ServiceManager, CacheManager> implements MovieContract.Model {

    int count = 20;

    @Inject
    public MovieModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }


    @Override
    public Observable<String> getMovieInfo(int start, boolean update) {
        Observable<String> movieInfo = mServiceManager.getCommonService()
                .getMovieInfo(start, count);
        return mCacheManager.getCommonCache()
                .getMovieInfo(movieInfo, new DynamicKey(start), new EvictDynamicKey(update))
                .flatMap(stringReply -> Observable.just(stringReply.getData()));
    }
}
