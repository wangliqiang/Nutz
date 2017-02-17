package com.app.nuts.app.mvp.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.nuts.R;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.common.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment {

    public static FindFragment newInstance() {
        Bundle args = new Bundle();
        FindFragment findFragment = new FindFragment();
        findFragment.setArguments(args);
        return findFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_find, container, false);
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }
}
