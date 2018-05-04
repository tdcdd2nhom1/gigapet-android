package com.sethphat.gigapet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sethphat.gigapet.Configs.IntentKey;
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.SQLHelper.UserHelper;

public class MainGameActivity extends AppCompatActivity {
    // default config
    private int UserID = 0;
    private int Check_Interval = 1000 * 30; // 30 seconds
    private UserHelper userRepo = new UserHelper(this);

    // data user
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game_layout);

        // retrieve USER_ID
        UserID = getIntent().getIntExtra(IntentKey.USER_ID, 0);
        if (UserID == 0)
            finish();// error

        // retrieve user info
        user = userRepo.GetByID(UserID);
        if (user == null)
            finish(); // error 2

        // ok, let's render the game
    }

    /**
     * Go to Shop page
     *
     * @param view
     */
    public void shopPage(View view) {
        Intent i = new Intent(MainGameActivity.this, ShopPageActivity.class);
        startActivity(i);
    }
}
