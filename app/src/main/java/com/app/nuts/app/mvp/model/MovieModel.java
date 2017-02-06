package com.app.nuts.app.mvp.model;

import com.app.nuts.app.mvp.contract.MovieContract;
import com.app.nuts.app.mvp.entity.MovieInfo;
import com.app.nuts.app.mvp.model.api.cache.CacheManager;
import com.app.nuts.app.mvp.model.api.service.ServiceManager;
import com.app.nuts.base.di.scope.ActivityScope;
import com.app.nuts.base.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by 王立强 on 2017/2/4.
 */
@ActivityScope
public class MovieModel extends BaseModel<ServiceManager, CacheManager> implements MovieContract.Model {
    public static final int USERS_PER_PAGE = 10;

    @Inject
    public MovieModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }


    @Override
    public Observable<List<MovieInfo>> getMovieInfo(int start, int count) {
        Observable<List<MovieInfo>> movieInfo = mServiceManager.getCommonService()
                .getMovieInfo(start, USERS_PER_PAGE);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getMovieInfo(movieInfo, start, count)
                .flatMap(new Func1<Reply<List<MovieInfo>>, Observable<List<MovieInfo>>>() {
                    @Override
                    public Observable<List<MovieInfo>> call(Reply<List<MovieInfo>> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }
}
