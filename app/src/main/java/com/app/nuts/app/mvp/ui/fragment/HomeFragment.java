package com.app.nuts.app.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.app.nuts.R;
import com.app.nuts.app.common.App;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.common.BaseFragment;
import com.app.nuts.app.mvp.ui.activity.MovieActivity;
import com.app.nuts.app.mvp.ui.adapter.MovieInfoAdapter;
import com.app.nuts.widget.banner.RecyclerViewBanner;
import com.app.nuts.widget.imageloader.ImageLoader;
import com.app.nuts.widget.imageloader.glide.GlideImageConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.id.list;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.movie_btn)
    Button movieBtn;
    @BindView(R.id.recyclerViewBanner)
    RecyclerViewBanner recyclerViewBanner;
    private App app;
    private ImageLoader imageLoader;

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
        app = (App) view.getContext().getApplicationContext();
        imageLoader = app.getAppComponent().imageLoader();
        //设置banner
        setBanner();
        return view;
    }

    /**
     * set Banner
     */
    public void setBanner() {
        List<Banner> banners = new ArrayList<>();
        banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487221110004&di=d6043e4b0c90ddf3ea5096c3d8eb8f58&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F067%2F5116EPAUD762_1000x500.jpg"));
        banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487221129421&di=c085432cf7c15836f8a6479138740f39&imgtype=0&src=http%3A%2F%2Fimage85.360doc.com%2FDownloadImg%2F2015%2F05%2F0517%2F53199602_2.jpg"));
        banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487221161254&di=fbb99c5dad3d5a2a2c8b0b44e8c0e081&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2013%2F255%2FP52AOTE73EIG_1000x500.jpg"));
        recyclerViewBanner.isShowIndicatorPoint(true);
        recyclerViewBanner.setRvBannerDatas(banners);
        recyclerViewBanner.setOnSwitchRvBannerListener((position, bannerView) ->
                imageLoader.loadImage(getContext(),
                        GlideImageConfig
                                .builder()
                                .url(banners.get(position % banners.size()).getUrl())
                                .placeholder(R.mipmap.ic_launcher)
                                .imagerView(bannerView)
                                .build()));
        recyclerViewBanner.setOnRvBannerClickListener(position -> {
            //banner点击事件
        });
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

    private class Banner {

        String url;

        public Banner(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}
