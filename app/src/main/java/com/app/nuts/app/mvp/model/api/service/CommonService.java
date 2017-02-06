package com.app.nuts.app.mvp.model.api.service;

import com.app.nuts.app.mvp.entity.MovieInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 存放通用的一些API
 * Created by 王立强 on 2017/2/4.
 */
public interface CommonService {

    String V2 = "/v2/movie";

    @GET(V2+"/top250")
    Observable<String> getMovieInfo(@Query("start") int start, @Query("count") int count);
}
