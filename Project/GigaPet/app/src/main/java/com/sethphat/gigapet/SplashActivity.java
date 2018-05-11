package com.sethphat.gigapet;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Configs.SQLiteAccess;
import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.SQLHelper.CategoryHelper;
import com.sethphat.gigapet.SQLHelper.ShopItemHelper;
import com.sethphat.gigapet.SQLHelper.UserHelper;
import com.sethphat.gigapet.SQLHelper.UserItemHelper;

public class SplashActivity extends AppCompatActivity {
    private final int TIME_OUT = 3000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);

        // solving db (singleton)
        DBAccess.UserRepo = new UserHelper(this);
        DBAccess.CateRepo = new CategoryHelper(this);
        DBAccess.ShopItem = new ShopItemHelper(this);
        DBAccess.UserItemRepo = new UserItemHelper(this);


        SQLiteAccess.CreateDummyData();

        // splash event
        HandlerSplash();
    }


    public void HandlerSplash() {
        // logo
        ImageView imgLogo = (ImageView) findViewById(R.id.imgLogo);

        // create an animation
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(1500);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        imgLogo.startAnimation(animation);

        // run handler to move another activity
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
