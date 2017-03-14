package com.app.nuts.app.mvp.model.api.cache;

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
    Observable<Reply<String>> getMovieInfo(Observable<String> movieInfo, DynamicKey start, EvictProvider evictProvider);

}
