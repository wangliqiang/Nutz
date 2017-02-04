package com.app.nuts.base.mvp;

import com.app.nuts.http.BaseCacheManager;
import com.app.nuts.http.BaseServiceManager;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class BaseModel<S extends BaseServiceManager,C extends BaseCacheManager> implements Model {

    protected S mServiceManager;//服务管理类,用于网络请求
    protected C mCacheManager;//缓存管理类,用于管理本地或者内存缓存

    public BaseModel(S serviceManager, C cacheManager) {
        this.mServiceManager = serviceManager;
        this.mCacheManager = cacheManager;
    }

    @Override
    public void onDestory() {
        if (mServiceManager != null) {
            mServiceManager = null;
        }
        if (mCacheManager != null) {
            mCacheManager = null;
        }
    }
}
