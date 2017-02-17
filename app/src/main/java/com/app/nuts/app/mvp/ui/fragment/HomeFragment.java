package com.app.nuts.app.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.nuts.R;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.common.BaseFragment;
import com.app.nuts.app.mvp.ui.activity.MovieActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.movie_btn)
    Button movieBtn;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void setupFragmentComponent(AppComponent appComponent) {

    }

    @OnClick({R.id.movie_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie_btn:
                startActivity(new Intent(getContext(), MovieActivity.class));
                break;
        }
    }
}
