package com.app.nuts.app.mvp.model.api.service;


import com.app.nuts.app.mvp.entity.MovieInfo;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 王立强 on 2017/2/4.
 */
public interface MovieService {

    @GET("/top250")
    Observable<List<MovieInfo>> getMovieInfo(@Query("start") int start, @Query("count") int count);


}
