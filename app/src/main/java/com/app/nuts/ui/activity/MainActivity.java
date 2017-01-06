package com.app.nuts.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.app.nuts.R;
import com.app.nuts.base.BaseActivity;
import com.app.nuts.ui.activity.douban.MovieActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.simplemvp)
    Button simplemvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        toolbar.setTitle("Nuts");
        setSupportActionBar(toolbar);
    }

    @OnClick({R.id.simplemvp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.simplemvp:
                startActivity(new Intent(this, MovieActivity.class));
                break;
            default:
                break;
        }
    }
}
