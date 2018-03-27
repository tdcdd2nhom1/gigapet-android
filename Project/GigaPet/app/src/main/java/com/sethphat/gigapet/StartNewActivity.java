package com.sethphat.gigapet;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sethphat.gigapet.Common.FontChangeCrawler;
import com.sethphat.gigapet.Common.OnSwipeTouchListener;

public class StartNewActivity extends AppCompatActivity {

    private int now_pet = 1;
    private int max_pet = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_new_layout);

        // set font
        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "fonts/carterone.ttf");
        fontChanger.replaceFonts((ViewGroup) this.findViewById(android.R.id.content));

        // Get main
        LinearLayout llParent = (LinearLayout) findViewById(R.id.llParent);
        RelativeLayout rlChoosePet = (RelativeLayout) findViewById(R.id.rlChoosePet);
        final ImageView imgPet = (ImageView) findViewById(R.id.imgPet);

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

}
