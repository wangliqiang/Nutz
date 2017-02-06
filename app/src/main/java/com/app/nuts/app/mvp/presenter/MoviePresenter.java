package com.app.nuts.app.mvp.presenter;

import android.app.Application;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.app.nuts.app.mvp.contract.MovieContract;
import com.app.nuts.app.mvp.entity.MovieInfo;
import com.app.nuts.base.AppManager;
import com.app.nuts.base.mvp.BasePresenter;
import com.app.nuts.base.rxerrorhandler.core.RxErrorHandler;
import com.app.nuts.base.rxerrorhandler.handler.ErrorHandleSubscriber;
import com.app.nuts.base.rxerrorhandler.handler.RetryWithDelay;
import com.app.nuts.utils.RxUtils;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

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
    private List<MovieInfo> movieInfo = new ArrayList<>();

    @Inject
    public MoviePresenter(MovieContract.Model model, MovieContract.View view, RxErrorHandler handler, AppManager appManager, Application application) {
        super(model, view);
        this.mApplication = application;
        this.mErrorHandler = handler;
        this.mAppManager = appManager;
    }

    public void getMovieInfo(int start, int count) {
        mModel.getMovieInfo(start, count)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(() -> {

                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {

                })
                .compose(RxUtils.bindToLifecycle(mView))
                .subscribe(new ErrorHandleSubscriber<String>(mErrorHandler) {
                    @Override
                    public void onNext(String movieInfosStr) {
                        movieInfo = JSON.parseArray(movieInfosStr,MovieInfo.class);
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
