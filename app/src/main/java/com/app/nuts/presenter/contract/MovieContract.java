package com.app.nuts.presenter.contract;

import com.app.nuts.base.BasePresenter;
import com.app.nuts.base.BaseView;
import com.app.nuts.model.MovieInfo;

/**
 * Created by 王立强 on 2016/10/12.
 */

public interface MovieContract {
    interface View extends BaseView<Presenter> {

        void showMovieInfo(MovieInfo movieInfo);

        void showLoading();

        void dismissLoading();

        void showError();
    }

    interface Presenter extends BasePresenter {
        void getMovieInfo(int start, int count);
    }
}
