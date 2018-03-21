package com.sethphat.gigapet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.sethphat.gigapet.Common.OnSwipeTouchListener;

public class StartNewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_new_layout);

        // Get main
        RelativeLayout rlChoosePet = (RelativeLayout) findViewById(R.id.rlChoosePet);

        // action change pet when swipe left - right
        rlChoosePet.setOnTouchListener(new OnSwipeTouchListener(StartNewActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
            }

            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
            }
        });
    }
}
