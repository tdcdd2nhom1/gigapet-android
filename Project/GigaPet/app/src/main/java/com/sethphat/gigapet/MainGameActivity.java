package com.sethphat.gigapet;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import com.sethphat.gigapet.Common.FontChangeCrawler;
import com.sethphat.gigapet.Configs.IntentKey;
import com.sethphat.gigapet.Configs.Setting;
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

        // load font
        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), Setting.Font_Path);
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));

        // retrieve USER_ID
        UserID = getIntent().getIntExtra(IntentKey.USER_ID, 0);
        if (UserID == 0)
            finish();// error

        // retrieve user info
        user = userRepo.GetByID(UserID);
        if (user == null)
            finish(); // error 2

        // ok, let's render the game
        renderGame();
    }

    /**
     * Show info
     * @param view
     */
    public void showInfo(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle("Giới thiệu về game");
        dialog.setMessage("GigaPet v" + this.getString(R.string.game_version) + "\nMột sản phẩm được làm bởi nhóm 1, thành viên:\n" +
                " - Trần Minh Phát - Leader\n" +
                " - Lê Bảo Long - Tester\n" +
                " - Nguyễn Thị Thanh Hương - Graphic reviewer/Tester\n" +
                " - Nguyễn Hoàng Vũ - Graphic designer\n" +
                " - Nguyễn Cao Thọ - Tester\n\n" +
                "Mọi chi tiết, thắc mắc xin liên hệ với Mr.Phát qua:\n" +
                " - Email: phatTranMinh96@gmail.com\n" +
                " - Phone: 0937348373\n\n" +
                "Have fun!");

        dialog.setPositiveButton("Ok", null);
        dialog.setNegativeButton("Cancel", null);

        dialog.show();
    }

    /**
     * Render the game
     */
    private void renderGame()
    {

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
