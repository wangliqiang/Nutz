package com.app.nuts.app.mvp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;
import com.app.nuts.R;
import com.app.nuts.app.common.AppComponent;
import com.app.nuts.app.common.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.movie_btn)
    Button movieBtn;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.animation_view)
    LottieAnimationView animationView;
    @BindView(R.id.intro_btn)
    Button introBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Nuts");
        setSupportActionBar(toolbar);
    }


    @Override
    protected void setupActivityComponent(AppComponent appComponent) {

    }

    @OnClick({R.id.movie_btn, R.id.intro_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie_btn:
                startActivity(new Intent(this, MovieActivity.class));
                break;
            case R.id.intro_btn:
                startActivity(new Intent(this, AppIntroActivity.class));
                break;
        }
    }
}
