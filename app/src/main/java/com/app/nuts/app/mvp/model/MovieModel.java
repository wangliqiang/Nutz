package com.app.nuts.app.mvp.model;

import com.app.nuts.app.mvp.contract.MovieContract;
import com.app.nuts.app.mvp.entity.User;
import com.app.nuts.app.mvp.model.api.cache.CacheManager;
import com.app.nuts.app.mvp.model.api.service.ServiceManager;
import com.app.nuts.base.di.scope.ActivityScope;
import com.app.nuts.base.mvp.BaseModel;

import java.util.List;

import javax.inject.Inject;

import io.rx_cache.DynamicKey;
import io.rx_cache.EvictDynamicKey;
import io.rx_cache.Reply;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by 王立强 on 2017/2/4.
 */
@ActivityScope
public class MovieModel extends BaseModel<ServiceManager, CacheManager> implements MovieContract.Model {
    public static final int USERS_PER_PAGE = 10;

    @Inject
    public MovieModel(ServiceManager serviceManager, CacheManager cacheManager) {
        super(serviceManager, cacheManager);
    }


    @Override
    public Observable<List<User>> getUsers(int lastIdQueried, boolean update) {
        Observable<List<User>> users = mServiceManager.getUserService()
                .getUsers(lastIdQueried, USERS_PER_PAGE);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mCacheManager.getCommonCache()
                .getUsers(users
                        , new DynamicKey(lastIdQueried)
                        , new EvictDynamicKey(update))
                .flatMap(new Func1<Reply<List<User>>, Observable<List<User>>>() {
                    @Override
                    public Observable<List<User>> call(Reply<List<User>> listReply) {
                        return Observable.just(listReply.getData());
                    }
                });
    }

    @Override
    public void onDestory() {

    }
}
