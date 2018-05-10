package com.sethphat.gigapet;

import android.app.Service;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Common.FontChangeCrawler;
import com.sethphat.gigapet.Common.HelperFunction;
import com.sethphat.gigapet.Common.OnSwipeTouchListener;
import com.sethphat.gigapet.Configs.IntentKey;
import com.sethphat.gigapet.Configs.PetExperience;
import com.sethphat.gigapet.Configs.SQLiteAccess;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.SQLHelper.UserHelper;
import com.sethphat.gigapet.databinding.MainGameLayoutBinding;

import java.util.Timer;
import java.util.TimerTask;

public class MainGameActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
    // default config
    private int UserID = 0;
    private int MAX_STATUS = 100;
    private int DEPRESS_BELOW_STATUS = 20; // if the stats below this one, depression of the pet will come up.
    private int MAX_FEELING = 5;
    private int MAX_GOOD_FEELING = 20;
    private int TOILET_TIME = 1000 * 10; // 10 seconds
    private int BATH_TIME = 1000 * 30; //30 seconds
    private int Check_Interval = 1000 * 60; // 60 seconds, -2 per stats
    private int STATS_DOWN_NUM = 2;
    private boolean isRendered = false;
    public static int REQUEST_TO_EAT = 69;
    public static int REQUEST_TO_DRINK = 70;
    public static int REQUEST_TO_BACKGROUND = 71;
    private Timer timer = null;

    // data user
    private User user = null;

    // layout data binding
    MainGameLayoutBinding binding;
    private GestureDetector gestureScanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main_game_layout);
        binding = DataBindingUtil.setContentView(this, R.layout.main_game_layout);
        gestureScanner = new GestureDetector(this, this);

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

        // show welcome message
        Toast.makeText(this, R.string.welcome_mess, Toast.LENGTH_SHORT).show();

        // update info from the last login to now
        UpdateFromLastOpen();

        // ok, let's render the game
        renderGame();

        // cheat game
        fullStatus();
        evolutionStatus();
    }

    /**
     * Show info
     * @param view
     */
    public void showInfo(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(R.string.intro_title);

        // string builder
        String mess = getString(R.string.introduction);
        mess = mess.replace("{version}", getString(R.string.game_version));

        dialog.setMessage(mess);

        dialog.setPositiveButton("Ok", null);

        dialog.show();
    }

    /**
     * Settings game
     * @param view
     */
    public void showSetting(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(R.string.setting);

        // view
        View v = getLayoutInflater().inflate(R.layout.dialog_setting, null);

        // find item
        RadioButton rdbOn = v.findViewById(R.id.rdbOn);
        final RadioButton rdbOff = v.findViewById(R.id.rdbOff);

        // check current status
        if (Setting.isIsSoundOn())
            rdbOn.setChecked(true);
        else
            rdbOff.setChecked(true);

        dialog.setView(v);
        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean isOn = true;
                if (rdbOff.isChecked())
                    isOn = false;

                Setting.setIsSoundOn(isOn);
            }
        });

        dialog.setNegativeButton("Cancel", null);
        dialog.show();
    }

    /**
     * Render the game
     */
    private void renderGame()
    {
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


        // EVENT:
        binding.imgPet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: Play pet sound
            }
        });
        binding.btnEat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set data
                Setting.UserData = user;

                // send intent
                Intent intent = new Intent(MainGameActivity.this, EatActivity.class);
                startActivityForResult(intent, REQUEST_TO_EAT);
            }
        });
        binding.btnDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set data
                Setting.UserData = user;

                // send intent
                Intent intent = new Intent(MainGameActivity.this, DrinkActivity.class);
                startActivityForResult(intent, REQUEST_TO_DRINK);
            }
        });

        // finalization render
        isRendered = true;
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
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int BadFeeling = 0;
                boolean isUpLevel = false;
                boolean isEvolution = false;

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

                // set bad feeling
                if (user.getGoodFeeling() > 0)
                {
                    user.setGoodFeeling(user.getGoodFeeling() - BadFeeling >= 0 ? user.getGoodFeeling() - BadFeeling : 0);
                    BadFeeling = BadFeeling - user.getGoodFeeling();
                }
                user.setBadFeeling(user.getBadFeeling() + BadFeeling);

                // checking bad feeling
                if (user.getBadFeeling() >= MAX_FEELING)
                {
                    user.setHeart(user.getHeart() > 0 ? user.getHeart()-1 : 0);
                    Toast.makeText(MainGameActivity.this, R.string.bad_feeling_notice, Toast.LENGTH_SHORT).show();
                }

                // Up level processing - Up heart
                if (PetExperience.isUpLevel(user.getHeart(), user.getGoodFeeling()))
                {
                    // update value
                    user.setGoodFeeling(user.getGoodFeeling() - PetExperience.getExp(user.getHeart()));
                    user.setHeart(user.getHeart() + 1);

                    // flags
                    isUpLevel = true;
                    isEvolution = true;

                    // evolution
                    switch (user.getHeart())
                    {
                        case 3:
                            user.setEvolution(2);
                            break;
                        case 7:
                            user.setEvolution(3);
                            break;
                        default:
                            isEvolution = false;
                            break;
                    }
                }

                // db update
                DBAccess.UserRepo.Update(user);

                // Declare final boolean
                final boolean finalIsEvolution = isEvolution;
                final boolean finalIsUpLevel = isUpLevel;

                // run task UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // update screen on thread
                        MainGameActivity.this.setCurrentStatus();

                        if (finalIsEvolution)
                        {
                            MainGameActivity.this.EvolutionDialog();
                        }
                        else if (finalIsUpLevel)
                        {
                            MainGameActivity.this.UpLevelDialog();
                        }
                    }
                });

                // log to know status
                Log.d("STATUS_CHANGED", "Changed pet status now!!!");
            }
        }, 0, Check_Interval);
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
     * Go to toilet
     * @param view
     */
    public void goToToilet(View view) {

    }

    /**
     * Go to bathroom
     * @param view
     */
    public void goToBath(View view) {

    }

    /**
     * On result activity back
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // get back the newest data
        user = DBAccess.UserRepo.GetByID(UserID);

        // Just render again this activity :D
        renderGame();
    }

    /**
     * State if pause - resume activity
     */
    @Override
    protected void onPause() {
        super.onPause();

        // stop timer
        if(timer != null)
            timer.cancel();

        Log.d("SYSTEM_TRACKING", "Paused Activity");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // only resume if the page already rendered (which mean don't cost us an both time running, annoying ehhh)
        if (isRendered)
            AutoUpdateStats();

        Log.d("SYSTEM_TRACKING", "Resume Activity");
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

        Log.d("FINALIZATION", "GAME FINISHED AND SAVED!");

        // run parent destroy, then end this madness plzzz
        super.onDestroy();
    }


    /**
     * Uplevel (heart) dialog
     */
    private void UpLevelDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(R.string.up_level_title);
        dialog.setMessage(R.string.up_level_body);
        dialog.setPositiveButton("Ok", null);

        dialog.show();
    }

    /**
     * Evolution dialog
     */
    private void EvolutionDialog()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        dialog.setTitle(R.string.evo_title);
        dialog.setMessage(R.string.evo_body);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // re-render the game
                MainGameActivity.this.renderGame();
            }
        });

        dialog.show();
    }

    // Cheating functions down here

    // full status cheat
    private void fullStatus()
    {
        if (Setting.STATE.equals("DEV") == false)
            return;

        binding.llMainGame.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                user.setBladder(MAX_STATUS);
                user.setFun(MAX_STATUS);
                user.setHygiene(MAX_STATUS);
                user.setThirsty(MAX_STATUS);
                user.setHunger(MAX_STATUS);
                user.setEnergy(MAX_STATUS);

                // db update
                DBAccess.UserRepo.Update(user);

                // Update GUI
                setCurrentStatus();

                Log.d("CHEAT_ACTIVATED", "CHEATED #1!");
                return true;
            }
        });
    }

    // evolution cheats
    private void evolutionStatus()
    {
        if (Setting.STATE.equals("DEV") == false)
            return;

        binding.txtPetName.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // increase 100 GOOD FEELING
                user.setGoodFeeling(user.getGoodFeeling() + 100);

                // db update
                DBAccess.UserRepo.Update(user);
                Log.d("CHEAT_ACTIVATED", "CHEATED #2!");
                return true;
            }
        });
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

}
