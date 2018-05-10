package com.sethphat.gigapet.Configs;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;

import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.R;

import java.io.IOException;
import java.io.InputStream;

public class Setting {
    private static boolean isSoundOn = true;
    public static int DefaultGold = 500;
    public static User UserData = null;
    public static String STATE = "DEV"; // LIVE, DEV
    public static String DATE_FORMAT = "dd/MM/yy HH:mm:ss";

    public static boolean isIsSoundOn() {
        return isSoundOn;
    }

    public static void setIsSoundOn(boolean isSoundOn) {
        Setting.isSoundOn = isSoundOn;
    }

    public static String Font_Path = "fonts/carterone.ttf";

    @DrawableRes
    public static int BackgroundImgRes(int type)
    {
        switch (type)
        {
            case 1:
                return R.drawable.background_yard;
            default:
                return R.drawable.background_yard;
        }
    }

    /**
     * Generate pet image
     * @param ct
     * @param pet_id
     * @param pet_level
     * @param pet_skin
     * @return
     */
    public static Drawable PetImage(Context ct, int pet_id, int pet_level, int pet_skin)
    {
        // load image
        try {
            // get input stream
            AssetManager assets = ct.getAssets();
            InputStream ims = assets.open("pets/" + pet_id + "/level_" + pet_level + "/" + pet_skin + ".png");

            // load image as Drawable
            return Drawable.createFromStream(ims, null);
        }
        catch(IOException ex) {
            return null;
        }
    }

    /**
     * Get default pet images
     * @param ct
     * @return
     */
    public static Drawable[] DefaultPetImg(Context ct)
    {
        Drawable[] imgs = new Drawable[3];

        imgs[0] = PetImage(ct, 1, 1, 0);
        imgs[1] = PetImage(ct, 2, 1, 0);
        imgs[2] = PetImage(ct, 3, 1, 0);

        for (Drawable img : imgs)
        {
            if (img == null)
                img = ct.getResources().getDrawable(R.drawable.pet_1);
        }

        return imgs;
    }

    /**
     * Shop item drawable
     */
    public static Drawable GetImageShopItem(Context ct, String file_name)
    {
        // load image
        try {
            // get input stream
            AssetManager assets = ct.getAssets();
            InputStream ims = assets.open("shopitem/" + file_name);

            // load image as Drawable
            return Drawable.createFromStream(ims, null);
        }
        catch(IOException ex) {
            return null;
        }
    }

    /**
     * Background game drawable
     */
    public static Drawable GetBackgroundImg(Context ct, String file_name)
    {
        // load image
        try {
            // get input stream
            AssetManager assets = ct.getAssets();
            InputStream ims = assets.open("background/" + file_name);

            // load image as Drawable
            return Drawable.createFromStream(ims, null);
        }
        catch(IOException ex) {
            return null;
        }
    }
}
