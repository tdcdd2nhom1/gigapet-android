package com.sethphat.gigapet;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Common.FontChangeCrawler;
import com.sethphat.gigapet.Common.HelperFunction;
import com.sethphat.gigapet.Configs.IntentKey;
import com.sethphat.gigapet.Configs.SQLiteAccess;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.SQLHelper.UserHelper;
import com.sethphat.gigapet.databinding.MainGameLayoutBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainGameActivity extends AppCompatActivity {
    // default config
    private int UserID = 0;
    private int MAX_STATUS = 100;
    private int DEPRESS_BELOW_STATUS = 20; // if the stats below this one, depression of the pet will come up.
    private int MAX_FEELING = 5;
    private int Check_Interval = 1000 * 60; // 60 seconds, -2 per stats
    private int STATS_DOWN_NUM = 2;
    private Timer timer = null;

    // data user
    private User user = null;

    // layout data binding
    MainGameLayoutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main_game_layout);
        binding = DataBindingUtil.setContentView(this, R.layout.main_game_layout);

        // load font
        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), Setting.Font_Path);
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));

        // retrieve USER_ID
        UserID = getIntent().getIntExtra(IntentKey.USER_ID, 0);
        if (UserID == 0)
            finish();// error

        // retrieve user info
        user = DBAccess.UserRepo.GetByID(UserID);
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
        // update info first
        UpdateFromLastOpen();

        // set pet info
        binding.txtPetName.setText(user.getPetName() + "");
        binding.txtMoney.setText(user.getGold() + "");

        // render heart
        binding.llHeartPanel.removeAllViews();
        for (int i = 1; i <= user.getHeart(); i++)
        {
            ImageView newImg = new ImageView(this);
            newImg.setImageResource(R.drawable.heart);
            binding.llHeartPanel.addView(newImg);
        }

        // set max status and current status
        setMaxStatus();
        setCurrentStatus();

        // set background image
        if (user.getIsSleeping() > 0)
            binding.llMainGame.setBackgroundResource(R.drawable.background_sleep);
        else
            binding.llMainGame.setBackgroundResource(Setting.BackgroundImgRes(user.getBackgroundIMG()));

        // set pet image
        binding.imgPet.setImageDrawable(Setting.PetImage(this, user.getType(), user.getEvolution(), user.getPetSkin()));

        // okay then, running interval check
        AutoUpdateStats();
    }

    /**
     * Set max status
     */
    private void setMaxStatus()
    {
        binding.pgbBladder.setMax(MAX_STATUS);
        binding.pgbEnergy.setMax(MAX_STATUS);
        binding.pgbFun.setMax(MAX_STATUS);
        binding.pgbThirsty.setMax(MAX_STATUS);
        binding.pgbHunger.setMax(MAX_STATUS);
        binding.pgbHygiene.setMax(MAX_STATUS);
    }

    /**
     * Current status
     */
    private void setCurrentStatus()
    {
        // set current status from DB
        binding.pgbHygiene.setProgress(user.getHygiene());
        binding.pgbHunger.setProgress(user.getHunger());
        binding.pgbThirsty.setProgress(user.getThirsty());
        binding.pgbFun.setProgress(user.getFun());
        binding.pgbEnergy.setProgress(user.getEnergy());
        binding.pgbBladder.setProgress(user.getBladder());
    }

    /**
     * Update status from last open
     */
    private void UpdateFromLastOpen()
    {
        // we calculate the time now to the time user left
        int timeLength =HelperFunction.time() - user.getLastTime();

        // we need to calculate it into minutes :D
        int minutes = timeLength / Check_Interval;

        // the status will be decrease?
        int decrease = STATS_DOWN_NUM * minutes;

        // because max is MAX_STATUS
        if (decrease > MAX_STATUS)
            decrease = MAX_STATUS;

        // ok now we decreasing...
        user.setHunger(user.getHunger() < decrease ? 0 : user.getHunger() - decrease);
        user.setThirsty(user.getThirsty() < decrease ? 0 : user.getThirsty() - decrease);
        user.setBladder(user.getBladder() < decrease ? 0 : user.getBladder() - decrease);
        user.setHygiene(user.getHygiene() < decrease ? 0 : user.getHygiene() - decrease);
        user.setFun(user.getFun() < decrease ? 0 : user.getFun() - decrease);

        // set energy (100 minutes to full :D)
        if (user.getIsSleeping() > 0)
        {
            user.setEnergy((user.getEnergy() + (decrease / 2) > 100) ? 100 : user.getEnergy() + (decrease / 2));
            if (user.getEnergy() > 100)
                user.setIsSleeping(0);
        }
        else
            user.setEnergy(user.getEnergy() < decrease ? 0 : user.getEnergy() - decrease);

        // save into db
        DBAccess.UserRepo.Update(user);
    }

    /**
     * Automatically update stats
     */
    private void AutoUpdateStats()
    {
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                int BadFeeling = 0;

                // set
                user.setHunger((user.getHunger() - 2 >= 0) ? user.getHunger() - 2 : 0);
                user.setThirsty((user.getThirsty() - 2 >= 0) ? user.getThirsty() - 2 : 0);
                user.setHygiene((user.getHygiene() - 2 >= 0) ? user.getHygiene() - 2 : 0);
                user.setFun((user.getFun() - 2 >= 0) ? user.getFun() - 2 : 0);
                user.setBladder((user.getBladder() - 2 >= 0) ? user.getBladder() - 2 : 0);

                // sleep or no sleep
                if (user.getIsSleeping() == 0)
                    user.setEnergy(user.getEnergy() - 2);
                else
                    user.setEnergy(user.getEnergy() + 1);

                // solving bad feeling
                if (user.getHunger() < DEPRESS_BELOW_STATUS)
                    BadFeeling++;
                if (user.getThirsty() < DEPRESS_BELOW_STATUS)
                    BadFeeling++;
                if (user.getHygiene() < DEPRESS_BELOW_STATUS)
                    BadFeeling++;
                if (user.getFun() < DEPRESS_BELOW_STATUS)
                    BadFeeling++;
                if (user.getBladder() < DEPRESS_BELOW_STATUS)
                    BadFeeling++;
                if (user.getEnergy() < DEPRESS_BELOW_STATUS && user.getIsSleeping() == 0)
                    BadFeeling++;

                user.setBadFeeling(user.getBadFeeling() + BadFeeling);

                // checking bad feeling
                if (user.getBadFeeling() >= MAX_FEELING)
                {
                    user.setHeart(user.getHeart() > 0 ? user.getHeart()-1 : 0);
                    Toast.makeText(MainGameActivity.this, R.string.bad_feeling_notice, Toast.LENGTH_SHORT).show();
                }

                // db update
                DBAccess.UserRepo.Update(user);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // update screen on thread
                        MainGameActivity.this.setCurrentStatus();
                    }
                });

                // log to know status
                Log.d("STATUS_CHANGED", "Changed pet status now!!!");
            }
        };

        timer.schedule(task, 0, Check_Interval);
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

    /**
     * State if pause - resume activity
     */
    @Override
    protected void onPause() {
        super.onPause();
        if(timer != null)
            timer.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AutoUpdateStats();
    }

    /**
     * Destroy, save the time...
     */
    @Override
    protected void onDestroy() {
        // update the last access time
        user.setLastTime(HelperFunction.time());

        // db update
        DBAccess.UserRepo.Update(user);

        // run parent destroy, then end this madness plzzz
        super.onDestroy();
    }
}
