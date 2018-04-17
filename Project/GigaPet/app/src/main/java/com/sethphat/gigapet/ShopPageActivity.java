package com.sethphat.gigapet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class ShopPageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shop_page_layout);

    }

    /**
     * Start new game
     *
     * @param view
     */
    public void backMain(View view) {
        Intent i = new Intent(ShopPageActivity.this, MainGameActivity.class);
        startActivity(i);
    }


}

