package com.app.nuts.app.mvp.model.api.service;


import com.app.nuts.app.mvp.entity.User;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 存放关于用户的一些api
 * Created by 王立强 on 2017/2/4.
 */
public interface UserService {

    String HEADER_API_VERSION = "Accept: application/vnd.github.v3+json";

    @Headers({HEADER_API_VERSION})
    @GET("/users")
    Observable<List<User>> getUsers(@Query("since") int lastIdQueried, @Query("per_page") int perPage);


}
