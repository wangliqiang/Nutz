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
public class MeFragment extends BaseFragment {

    public static MeFragment newInstance() {
        Bundle args = new Bundle();
        MeFragment meFragment = new MeFragment();
        meFragment.setArguments(args);
        return meFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }
}
