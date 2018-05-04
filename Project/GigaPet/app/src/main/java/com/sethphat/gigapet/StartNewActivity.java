package com.sethphat.gigapet;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sethphat.gigapet.Adapter.SlidingImageAdapter;
import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Common.FontChangeCrawler;
import com.sethphat.gigapet.Common.HelperFunction;
import com.sethphat.gigapet.Common.OnSwipeTouchListener;
import com.sethphat.gigapet.Configs.IntentKey;
import com.sethphat.gigapet.Configs.Setting;
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.SQLHelper.UserHelper;

import java.time.Instant;
import java.util.Set;

public class StartNewActivity extends AppCompatActivity {

    private int now_pet = 1;
    private int max_pet = 3;

    // control
    EditText edtName;
    private ViewPager mPager;
    private int currentPage = 0;
    private int NUM_PAGES = 0;

    // pet image
    private Drawable[] petImgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_new_layout);

        // get default imgs
        petImgs  = Setting.DefaultPetImg(this);

        // set font
        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), Setting.Font_Path);
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));

        // controls
        edtName = (EditText) findViewById(R.id.edtName);

        // ViewPager init
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImageAdapter(this,petImgs));
    }

    private @DrawableRes int getPetImg()
    {
        switch (now_pet)
        {
            case 1: return R.drawable.pet_1;
            case 2: return R.drawable.dog_sprite;
            case 3: return R.drawable.pet_3;
            default: return R.drawable.pet_1;
        }
    }

    /**
     * Action start new game
     * @param view
     */
    public void startNewGame(View view) {
        if (edtName.getText().toString().isEmpty())
        {
            Toast.makeText(this, R.string.err_mess_input_name, Toast.LENGTH_SHORT).show();
            return;
        }

        // start new game here
        User obj = new User();

        // set default data
        obj.setBackgroundIMG(0);
        obj.setGold(Setting.DefaultGold);
        obj.setEvolution(1);
        obj.setHeart(0);
        obj.setExperience(0);
        obj.setPetSkin(0);
        obj.setHunger(90);
        obj.setThirsty(90);
        obj.setFun(90);
        obj.setHygiene(90);
        obj.setEnergy(90);
        obj.setBladder(90);
        obj.setLastTime(HelperFunction.time());

        // set user data
        obj.setPetName(edtName.getText().toString());
        obj.setType(mPager.getCurrentItem() + 1);

        // insert
        DBAccess.UserRepo.Insert(obj);


        // open page
        if (obj.getID() > 0) {
            Intent i = new Intent(StartNewActivity.this, MainGameActivity.class);
            i.putExtra(IntentKey.USER_ID, obj.getID());
            startActivity(i);
            finish();
        }
        else {
            Toast.makeText(this, R.string.err_mess_failed_new_game, Toast.LENGTH_SHORT).show();
        }
    }
}
