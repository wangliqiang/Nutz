package com.app.nuts.app.mvp.model.api.service;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 存放通用的一些API
 * Created by 王立强 on 2017/2/4.
 */
public interface CommonService {
    @GET("top250")
    Observable<String> getMovieInfo(@Query("start") int start, @Query("count") int count);
}
