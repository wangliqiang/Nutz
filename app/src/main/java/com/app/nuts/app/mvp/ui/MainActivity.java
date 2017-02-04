package com.app.nuts.app.mvp.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.app.nuts.R;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.common.BaseActivity;
import com.app.nuts.app.di.component.DaggerMovieComponent;
import com.app.nuts.app.di.module.MovieModule;
import com.app.nuts.app.mvp.contract.MovieContract;
import com.app.nuts.app.mvp.entity.MovieInfo;
import com.app.nuts.app.mvp.presenter.MoviePresenter;

import java.util.List;

public class MainActivity extends BaseActivity<MoviePresenter> implements MovieContract.View {

    @Override
    protected View initView() {
        return LayoutInflater.from(this).inflate(R.layout.activity_main, null, false);
    }

    @Override
    protected void initData() {
        mPresenter.getMovieInfo();
    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerMovieComponent
                .builder()
                .appComponent(appComponent)
                .movieModule(new MovieModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showMovieInfo(List<MovieInfo> movieInfos) {

    }
}
