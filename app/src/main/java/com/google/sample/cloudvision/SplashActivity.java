package com.google.sample.cloudvision;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 로딩화면 연결
        setContentView(R.layout.splash_activity);
        startLoading();
    }

    private void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 로딩이 끝나고 메인화면을 띄웁니다.
                Intent intent = new Intent(getApplicationContext(),home_activity_navigation.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}