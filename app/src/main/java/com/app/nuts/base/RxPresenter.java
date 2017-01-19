package com.app.nuts.base;

import com.app.nuts.http.Http;
import com.app.nuts.http.HttpService;
import com.app.nuts.utils.Log;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 王立强 on 2016/10/29.
 */

public class RxPresenter<T> implements BasePresenter<T> {

    protected T mView;

    protected CompositeSubscription mCompositeSubscription;

    protected static HttpService httpService;

    //初始化httpService
    static {
        httpService = Http.getHttpService();
    }

    //订阅
    protected void subscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    //取消订阅
    protected void unsubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    //绑定View
    @Override
    public void attachView(T view) {
        Log.e("tag","attachView......");
        this.mView = view;
    }

    //解绑View
    @Override
    public void detachView() {
        Log.e("tag","detachView......");
        this.mView = null;
        unsubscribe();
    }
}
