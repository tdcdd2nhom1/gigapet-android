package com.sethphat.gigapet;

import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_new_layout);

        // set font
        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), Setting.Font_Path);
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));

        // Get main
        LinearLayout llParent = (LinearLayout) findViewById(R.id.llParent);
        RelativeLayout rlChoosePet = (RelativeLayout) findViewById(R.id.rlChoosePet);
        final ImageView imgPet = (ImageView) findViewById(R.id.imgPet);
        edtName = (EditText) findViewById(R.id.edtName);

        // action change pet when swipe left - right
        rlChoosePet.setOnTouchListener(new OnSwipeTouchListener(StartNewActivity.this) {
            @Override
            public void onSwipeLeft() {
                now_pet++;
                if (now_pet > max_pet)
                    now_pet = 1;

                // change image
                imgPet.setImageResource(getPetImg());
            }

            @Override
            public void onSwipeRight() {
                now_pet--;
                if (now_pet < 1)
                    now_pet = max_pet;

                // change image
                imgPet.setImageResource(getPetImg());
            }
        });
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
        obj.setEvolution(0);
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
        obj.setType(now_pet);

        // insert
        UserHelper userRepo = new UserHelper(this);
        userRepo.Insert(obj);


        // open page
        if (obj.getID() > 0) {
            Intent i = new Intent(StartNewActivity.this, MainGameActivity.class);
            i.putExtra(IntentKey.USER_ID, obj.getID());
            startActivity(i);
        }
        else {
            Toast.makeText(this, R.string.err_mess_failed_new_game, Toast.LENGTH_SHORT).show();
        }
    }
}
