package com.app.nuts.presenter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.app.nuts.api.ServerApi;
import com.app.nuts.callback.StringDialogCallback;
import com.app.nuts.model.MovieInfo;
import com.app.nuts.presenter.contract.MovieContract;
import com.app.nuts.utils.Log;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

import java.util.Date;

import okhttp3.Call;
import okhttp3.Response;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by wangliqiang on 2016/10/12.
 */

public class MoviePresenter implements MovieContract.Presenter {

    private MovieContract.View mView;
    private MovieInfo mMovieInfo;

    public MoviePresenter(@NonNull MovieContract.View view) {
        mView = checkNotNull(view, "View is not null");
        mView.setPresenter(this);
    }

    @Override
    public void getMovieInfo(int start, int count) {

        OkGo.get(ServerApi.TOP250)
                .tag(this)
                .params("start", start)
                .params("count", count)
                .execute(new StringCallback() {

                    @Override
                    public void onBefore(BaseRequest request) {
                        super.onBefore(request);
                        mView.showLoading();
                        Log.e("开始：",new Date().getSeconds()+"");
                    }

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        mView.dismissLoading();
                        mMovieInfo = JSON.parseObject(s, MovieInfo.class);
                        mView.showMovieInfo(mMovieInfo);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        mView.dismissLoading();
                    }

                    @Override
                    public void onAfter(@Nullable String s, @Nullable Exception e) {
                        super.onAfter(s, e);
                        mView.dismissLoading();
                        Log.e("结束：",new Date().getSeconds()+"");
                    }
                });
    }
}
