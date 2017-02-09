package com.app.nuts.base.mvp;

/**
 * Created by 王立强 on 2017/2/4.
 */

public interface BaseView {
    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);
}
