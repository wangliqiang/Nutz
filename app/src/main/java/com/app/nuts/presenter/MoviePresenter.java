package com.app.nuts.presenter;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSON;
import com.app.nuts.base.RxPresenter;
import com.app.nuts.common.CommonTransformer;
import com.app.nuts.model.MovieInfo;
import com.app.nuts.presenter.contract.MovieContract;

import rx.Subscription;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by wangliqiang on 2016/10/12.
 */

public class MoviePresenter extends RxPresenter implements MovieContract.Presenter {

    private MovieContract.View mView;
    private MovieInfo mMovieInfo;

    public MoviePresenter(@NonNull MovieContract.View view) {
        mView = checkNotNull(view, "View is not null");
        mView.setPresenter(this);
    }


    @Override
    public void getMovieInfo(int start, int count) {
        Subscription subscription = httpService.getMovie(start, count)
                .doOnSubscribe(() -> {
                    if (mMovieInfo == null) {
                        mView.showLoading();
                    }
                })
                .compose(new CommonTransformer<>())
                .subscribe(s -> {
                    mView.dismissLoading();
                    mMovieInfo = JSON.parseObject(s, MovieInfo.class);
                    mView.showMovieInfo(mMovieInfo);
                }, throwable -> {
                    mView.dismissLoading();
                    mView.showError();
                    throwable.printStackTrace();
                });
        subscribe(subscription);
    }
}
