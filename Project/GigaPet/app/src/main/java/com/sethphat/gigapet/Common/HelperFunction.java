package com.sethphat.gigapet.Common;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;

import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.Models.UserItem;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
