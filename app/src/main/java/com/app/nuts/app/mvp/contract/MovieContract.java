package com.app.nuts.app.mvp.contract;

import com.app.nuts.app.mvp.entity.MovieInfo;
import com.app.nuts.base.mvp.AppModel;
import com.app.nuts.base.mvp.BaseView;

import java.util.List;

import rx.Observable;

/**
 * Created by 王立强 on 2017/2/4.
 */

public interface MovieContract {
    //对于经常使用的关于UI的方法可以定义到BaseView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {
        void showMovieInfo(List<MovieInfo> movieInfos);
    }
    //Model层定义接口,外部只需关心model返回的数据,无需关心内部细节,及是否使用缓存
    interface Model extends AppModel {
        Observable<String> getMovieInfo(int start, int count);
    }
}