package com.app.nuts.base.rxerrorhandler.core;

import android.content.Context;

import com.app.nuts.base.rxerrorhandler.handler.ErrorHandlerFactory;
import com.app.nuts.base.rxerrorhandler.handler.listener.ResponseErroListener;

import static com.app.nuts.base.rxerrorhandler.utils.Preconditions.checkNotNull;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class RxErrorHandler {
    public final String TAG = this.getClass().getSimpleName();
    private ErrorHandlerFactory mHandlerFactory;

    private RxErrorHandler(Builder builder) {
        this.mHandlerFactory = builder.errorHandlerFactory;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ErrorHandlerFactory getmHandlerFactory() {
        return mHandlerFactory;
    }

    public static final class Builder {
        private Context context;
        private ResponseErroListener responseErroListener;
        private ErrorHandlerFactory errorHandlerFactory;

        private Builder() {
        }

        public Builder with(Context context) {
            this.context = context;
            return this;
        }

        public Builder responseErroListener(ResponseErroListener responseErroListener) {
            this.responseErroListener = responseErroListener;
            return this;
        }

        public RxErrorHandler build() {
            checkNotNull(context,"context is required");
            checkNotNull(responseErroListener,"responseErroListener is required");


            this.errorHandlerFactory = new ErrorHandlerFactory(context, responseErroListener);

            return new RxErrorHandler(this);
        }
    }
}
