package com.app.nuts.base;

import com.app.nuts.BaseApplication;
import com.app.nuts.base.mvp.Presenter;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import javax.inject.Inject;

import butterknife.Unbinder;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class BaseActivity<P extends Presenter> extends RxAppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected BaseApplication mApplication;
    private Unbinder mUnbinder;
    @Inject
    protected P mPresenter;

    private static final String LAYOUT_LINEARLAYOUT = "LinearLayout";
    private static final String LAYOUT_FRAMELAYOUT = "FrameLayout";
    private static final String LAYOUT_RELATIVELAYOUT = "RelativeLayout";
    public static final String IS_NOT_ADD_ACTIVITY_LIST = "is_add_activity_list";//是否加入到activity的list，管理
}
