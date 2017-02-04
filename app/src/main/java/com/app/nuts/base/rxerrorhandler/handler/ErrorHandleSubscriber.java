package com.app.nuts.base.rxerrorhandler.handler;

import com.app.nuts.base.rxerrorhandler.core.RxErrorHandler;

import rx.Subscriber;

/**
 * Created by 王立强 on 2017/2/4.
 */

public abstract class ErrorHandleSubscriber<T> extends Subscriber<T> {
    private ErrorHandlerFactory mHandlerFactory;

    public ErrorHandleSubscriber(RxErrorHandler rxErrorHandler){
        this.mHandlerFactory = rxErrorHandler.getmHandlerFactory();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        mHandlerFactory.handleError(e);
    }

}

