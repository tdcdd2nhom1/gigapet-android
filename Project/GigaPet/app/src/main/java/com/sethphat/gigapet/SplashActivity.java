package com.sethphat.gigapet;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sethphat.gigapet.Configs.SQLiteAccess;

public class SplashActivity extends AppCompatActivity {
    ImageView imgGif1 ;
    ImageView imgGif2 ;
    ImageView imgGif3 ;
    private final int TIME_OUT = 5000; // 5 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);


        SQLiteAccess.InitHelper(this);
        //SQLiteAccess.CreateDummyData();

        imgGif1 = (ImageView) findViewById(R.id.imgGif1);
        imgGif2 = (ImageView) findViewById(R.id.imgGif2);
        imgGif3 = (ImageView) findViewById(R.id.imgGif3);

        Glide.with(this).load(R.drawable.psy).into(imgGif1);
        Glide.with(this).load(R.drawable.psy).into(imgGif2);
        Glide.with(this).load(R.drawable.psy).into(imgGif3);

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
