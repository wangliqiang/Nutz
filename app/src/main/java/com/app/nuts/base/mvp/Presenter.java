package com.app.nuts.base.mvp;

import rx.Subscription;

/**
 * Created by 王立强 on 2017/2/4.
 */

public interface Presenter {
    void onStart();
    void onDestroy();
    void unSubscribe(Subscription subscription);
}
