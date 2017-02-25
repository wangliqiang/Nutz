package com.app.nuts.app.common;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by 王立强 on 2017/2/25.
 */

public class CommonSchedulers {
    public static <T> Observable.Transformer<T, T> io_main(){
        return tObservable -> tObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
