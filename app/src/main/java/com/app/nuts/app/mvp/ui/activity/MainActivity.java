package com.app.nuts.app.mvp.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.app.nuts.R;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.common.BaseActivity;
import com.app.nuts.app.mvp.ui.adapter.ViewPagerAdapter;
import com.app.nuts.app.mvp.ui.fragment.FindFragment;
import com.app.nuts.app.mvp.ui.fragment.HomeFragment;
import com.app.nuts.app.mvp.ui.fragment.MeFragment;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener, ViewPager.OnPageChangeListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.viewpager)
    ViewPager Viewpager;
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;

    private FragmentManager fm;
    private List<Fragment> mFragments;
    private ViewPagerAdapter adapter;

    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Nuts");
        setSupportActionBar(toolbar);
        bottomNavigationBar.setTabSelectedListener(this);
        Viewpager.addOnPageChangeListener(this);
        bottomNavigationBar.clearAll();
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.home, "首页").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.find, "发现").setActiveColorResource(R.color.colorPrimary))
                .addItem(new BottomNavigationItem(R.drawable.me, "我的").setActiveColorResource(R.color.colorPrimary))
                .setFirstSelectedPosition(0)
                .initialise();

        mFragments = new ArrayList<>();
        fm = getSupportFragmentManager();
        mFragments.add(FIRST, HomeFragment.newInstance());
        mFragments.add(SECOND, FindFragment.newInstance());
        mFragments.add(THIRD, MeFragment.newInstance());
        adapter = new ViewPagerAdapter(fm, mFragments);
        Viewpager.setAdapter(adapter);
        Viewpager.setCurrentItem(0);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    //BottomNavigationBar ---> start
    @Override
    public void onTabSelected(int position) {
        Viewpager.setCurrentItem(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
    //BottomNavigationBar ---> end

    // ==============================================

    //ViewPage ---> start
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        bottomNavigationBar.selectTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
    //ViewPage ---> end
}
