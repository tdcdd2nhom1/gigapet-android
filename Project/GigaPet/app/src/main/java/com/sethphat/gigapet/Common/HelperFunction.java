package com.sethphat.gigapet.Common;

import android.content.Context;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.sethphat.gigapet.MainGameActivity;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.Models.UserItem;
import com.sethphat.gigapet.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class HelperFunction {

    public static int time()
    {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /**
     * Convert unix timestamp to your format
     * @param format
     * @param unix_time
     * @return
     */
    public static String date(String format, int unix_time)
    {
        Date date = new Date(unix_time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static void SetBlinkAnimation(View v, int time){
        // create an animation
        Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(time);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);

        v.startAnimation(animation);
    }

    public static void SetRotateAnimation(Context ct, View v, int time)
    {
        final Animation a = AnimationUtils.loadAnimation(ct, R.anim.rorate_image);
        a.setDuration(time);
        a.setRepeatMode(Animation.INFINITE);
        a.setRepeatMode(Animation.REVERSE);

        v.startAnimation(a);
    }

    public static void ClearAnimation(View v)
    {
        v.clearAnimation();
    }

    public static UserItem GetDefaultBackground(int user_id)
    {
        UserItem item = new UserItem(user_id, 0, 1);
        item.setShopItemObj(new ShopItem(0, 4, "Default Background", "Basic background [Free]", 0, 1, 0, 0, 0));

        return item;
    }

    /**
     * Check if user have bad feeling
     * @param user
     * @return
     */
    public static boolean IsHaveBadFeeling(User user)
    {
        // solving bad feeling
        int depress = MainGameActivity.PUBLIC_DEPRESS;
        if (user.getHunger() < depress)
            return true;
        if (user.getThirsty() < depress)
            return true;
        if (user.getHygiene() < depress)
            return true;
        if (user.getFun() < depress)
            return true;
        if (user.getBladder() < depress)
            return true;
        if (user.getEnergy() < depress && user.getIsSleeping() == 0)
            return true;

        return false;
    }

    /**
     * Random
     * @param min
     * @param max
     * @return
     */
    public static int RandomInt(int min, int max)
    {
        Random rnd = new Random();
        return rnd.nextInt(max - min + 1) + min;
    }
}
