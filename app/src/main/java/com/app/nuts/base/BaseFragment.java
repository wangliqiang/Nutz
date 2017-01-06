package com.app.nuts.base;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by 王立强 on 2016/10/31.
 */

public class BaseFragment<T extends BasePresenter> extends Fragment {

    protected T mPresenter;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(mContext != null){
            this.mContext = context;
        }else{
            this.mContext = getActivity();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter = null;
    }
}
