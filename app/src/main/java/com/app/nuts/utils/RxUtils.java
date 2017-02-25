package com.app.nuts.utils;

import com.app.nuts.base.BaseAppActivity;
import com.app.nuts.base.BaseAppFragment;
import com.app.nuts.base.mvp.BaseView;
import com.trello.rxlifecycle.LifecycleTransformer;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class RxUtils {
    public static <T> Observable.Transformer<T, T> applySchedulers(final BaseView view) {
        return observable -> observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    public static <T> LifecycleTransformer<T> bindToLifecycle(BaseView view) {
        if (view instanceof BaseAppActivity) {
            return ((BaseAppActivity) view).<T>bindToLifecycle();
        } else if (view instanceof BaseAppFragment) {
            return ((BaseAppFragment) view).<T>bindToLifecycle();
        } else {
            throw new IllegalArgumentException("view isn't activity or fragment");
        }

    }
}
