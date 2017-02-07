package com.great.logistics.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.great.logistics.R;
import com.great.logistics.base.BaseActivity;
import com.great.logistics.view.SplashView;

/**
 * Created by 洪彬 on 2017/2/6.
 */
public class SplashActivity extends BaseActivity {

    private SplashView splashView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    private void initView(){
        splashView = (SplashView) findViewById(R.id.id_splash_view);
        splashView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splashView.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        splashView.recycle();
    }
}
