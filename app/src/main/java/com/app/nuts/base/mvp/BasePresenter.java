package com.app.nuts.base.mvp;

import org.simple.eventbus.EventBus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class BasePresenter<M extends AppModel, V extends BaseView> implements AppPresenter {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeSubscription mCompositeSubscription;

    protected M mModel;
    protected V mView;

    public BasePresenter(M model, V view) {
        this.mModel = model;
        this.mView = view;
        onStart();
    }

    public BasePresenter(V view) {
        this.mView = view;
        onStart();
    }

    public BasePresenter() {
        onStart();
    }

    @Override
    public void onStart() {
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().register(this);//注册eventbus
    }

    @Override
    public void onDestroy() {
        if (useEventBus())//如果要使用eventbus请将此方法返回true
            EventBus.getDefault().unregister(this);//解除注册eventbus
        unSubscribe();//解除订阅
        if (mModel != null) {
            mModel.onDestory();
            this.mModel = null;
        }
        this.mView = null;
        this.mCompositeSubscription = null;
    }

    @Override
    public void unSubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
        }
    }

    /**
     * 是否使用eventBus,默认为使用(true)，
     *
     * @return
     */
    protected boolean useEventBus() {
        return true;
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);//将所有subscription放入,集中处理
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();//保证activity结束时取消所有正在执行的订阅
        }
    }
}
