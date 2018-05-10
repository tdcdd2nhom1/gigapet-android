package com.sethphat.gigapet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BackgroundActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background_layout);
    }

    public void backToPrevious(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        // set final
        setResult(69);
        finishActivity(MainGameActivity.REQUEST_TO_BACKGROUND);
    }
}
