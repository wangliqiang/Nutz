package com.app.nuts.app.mvp.model.api.service;

import com.app.nuts.http.BaseServiceManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 王立强 on 2017/2/4.
 */
@Singleton
public class ServiceManager implements BaseServiceManager {
    private CommonService mCommonService;
    private MovieService mMovieService;

    /**
     * 如果需要添加service只需在构造方法中添加对应的service,在提供get方法返回出去,只要在ServiceModule提供了该service
     * Dagger2会自行注入
     * @param commonService
     */
    @Inject public ServiceManager(CommonService commonService,MovieService movieService){
        this.mCommonService = commonService;
        this.mMovieService = movieService;
    }

    public CommonService getCommonService() {
        return mCommonService;
    }

    public MovieService getMovieService() {
        return mMovieService;
    }

    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestory() {

    }
}
