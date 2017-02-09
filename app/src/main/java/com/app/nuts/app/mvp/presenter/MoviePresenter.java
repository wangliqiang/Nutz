package com.app.nuts.app.mvp.presenter;

import android.app.Application;

import com.alibaba.fastjson.JSON;
import com.app.nuts.app.mvp.contract.MovieContract;
import com.app.nuts.app.mvp.entity.MovieInfo;
import com.app.nuts.base.AppManager;
import com.app.nuts.base.mvp.BasePresenter;
import com.app.nuts.base.rxerrorhandler.core.RxErrorHandler;
import com.app.nuts.base.rxerrorhandler.handler.ErrorHandleSubscriber;
import com.app.nuts.base.rxerrorhandler.handler.RetryWithDelay;
import com.app.nuts.utils.RxUtils;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class MoviePresenter extends BasePresenter<MovieContract.Model, MovieContract.View> {
    private RxErrorHandler mErrorHandler;
    private AppManager mAppManager;
    private Application mApplication;
    private MovieInfo movieInfo;
    private boolean isFirst = true;
    private int start = 0;

    @Inject
    public MoviePresenter(MovieContract.Model model, MovieContract.View view, RxErrorHandler handler, AppManager appManager, Application application) {
        super(model, view);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }

    public void getMovieInfo(boolean pullToRefresh) {

        if (pullToRefresh) start = 0;

        boolean isEvictCache = pullToRefresh;//是否驱逐缓存,为ture即不使用缓存,每次上拉刷新即需要最新数据,则不使用缓存

        if (pullToRefresh && isFirst) {//默认在第一次上拉刷新时使用缓存
            isFirst = false;
            isEvictCache = false;
        }

        mModel.getMovieInfo(start, isEvictCache)
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(() -> {
                    if (pullToRefresh)
                        mView.showLoading();//显示上拉刷新的进度条
                    else
                        mView.startLoadMore();//显示下拉加载更多的进度条
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                    if (pullToRefresh)
                        mView.hideLoading();//隐藏上拉刷新的进度条
                    else
                        mView.endLoadMore();//隐藏下拉加载更多的进度条
                })
                .compose(RxUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(String movieInfosStr) {
                        start = start + 10;
                        movieInfo = JSON.parseObject(movieInfosStr, MovieInfo.class);
                        for (MovieInfo.SubjectsBean mi : movieInfo.getSubjects()){
                            movieInfo.getSubjects().add(mi);
                        }
                        mView.showMovieInfo(movieInfo);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.movieInfo = null;
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mApplication = null;
    }
}
