package com.app.nuts.http;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 王立强 on 2017/1/6.
 */

public interface HttpService {

    //登录接口
    @GET("top250")
    Observable<String> getMovie(@Query("start") int start, @Query("count") int count);
}
