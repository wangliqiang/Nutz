package com.app.nuts.app.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.nuts.R;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.common.BaseActivity;
import com.app.nuts.app.mvp.ui.fragment.FindFragment;
import com.app.nuts.app.mvp.ui.fragment.HomeFragment;
import com.app.nuts.app.mvp.ui.fragment.MeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.container)
    FrameLayout container;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    private int mCurrentPosition = 0;

    private SupportFragment[] mFragments = new SupportFragment[3];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Nuts");
        setSupportActionBar(toolbar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            mFragments[FIRST] = HomeFragment.newInstance();
            mFragments[SECOND] = FindFragment.newInstance();
            mFragments[THIRD] = MeFragment.newInstance();

            loadMultipleRootFragment(R.id.container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD]);

        } else {
            mFragments[FIRST] = findFragment(HomeFragment.class);
            mFragments[SECOND] = findFragment(FindFragment.class);
            mFragments[THIRD] = findFragment(MeFragment.class);
        }
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator() {
        return super.onCreateFragmentAnimator();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                showHideFragment(mFragments[0], mFragments[mCurrentPosition]);
                mCurrentPosition = 0;
                return true;
            case R.id.navigation_dashboard:
                showHideFragment(mFragments[1], mFragments[mCurrentPosition]);
                mCurrentPosition = 1;
                return true;
            case R.id.navigation_notifications:
                showHideFragment(mFragments[2], mFragments[mCurrentPosition]);
                mCurrentPosition = 2;
                return true;
        }
        return false;
    };
}
