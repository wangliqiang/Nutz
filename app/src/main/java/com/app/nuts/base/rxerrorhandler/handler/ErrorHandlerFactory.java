package com.app.nuts.base.rxerrorhandler.handler;

import android.content.Context;

import com.app.nuts.base.rxerrorhandler.handler.listener.ResponseErroListener;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class ErrorHandlerFactory {
    public final String TAG = this.getClass().getSimpleName();
    private Context mContext;
    private ResponseErroListener mResponseErroListener;

    public ErrorHandlerFactory(Context mContext, ResponseErroListener mResponseErroListener) {
        this.mResponseErroListener = mResponseErroListener;
        this.mContext = mContext;
    }

    /**
     *  处理错误
     * @param throwable
     */
    public void handleError(Throwable throwable) {
        mResponseErroListener.handleResponseError(mContext, (Exception) throwable);
    }
}
