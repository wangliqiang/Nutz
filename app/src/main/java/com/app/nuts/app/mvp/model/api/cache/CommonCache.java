package com.app.nuts.app.mvp.model.api.cache;

import com.app.nuts.app.mvp.entity.MovieInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictProvider;
import io.rx_cache.LifeCache;
import io.rx_cache.Reply;
import rx.Observable;

/**
 * Created by 王立强 on 2017/2/4.
 */
public interface CommonCache {
    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<MovieInfo>>> getMovieInfo(Observable<List<MovieInfo>> movieInfo, int start, int count);

}
