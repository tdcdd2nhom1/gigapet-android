package com.sethphat.gigapet;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
    ImageView imgGif1 ;
    ImageView imgGif2 ;
    ImageView imgGif3 ;
    private final int TIME_OUT = 5000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);

        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(1000);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        imgLogo.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, HomePageActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }


}
