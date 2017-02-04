package com.app.nuts.app.mvp.model.api.cache;

import com.app.nuts.http.BaseCacheManager;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by 王立强 on 2017/2/4.
 */
@Singleton
public class CacheManager implements BaseCacheManager {
    private CommonCache mCommonCache;

    /**
     * 如果需要添加Cache只需在构造方法中添加对应的Cache,
     * 在提供get方法返回出去,只要在CacheModule提供了该Cache Dagger2会自行注入
     * @param commonCache
     */
    @Inject
    public CacheManager(CommonCache commonCache) {
        this.mCommonCache = commonCache;
    }

    public CommonCache getCommonCache() {
        return mCommonCache;
    }

    /**
     * 这里可以释放一些资源(注意这里是单例，即不需要在activity的生命周期调用)
     */
    @Override
    public void onDestory() {

    }
}
