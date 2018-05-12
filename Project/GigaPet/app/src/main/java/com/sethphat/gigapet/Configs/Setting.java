package com.sethphat.gigapet.Configs;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.util.Log;

import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Setting {
    private static boolean isSoundOn = true;
    public static int DefaultGold = 500;
    public static User UserData = null;
    public static String STATE = "DEV"; // LIVE, DEV
    public static String DATE_FORMAT = "dd/MM/yy HH:mm:ss";

    // Category ID
    public static int BACKGROUND_CATEGORY = 4;
    public static int SKIN_CATEGORY = 3;
    public static int DRINK_CATEGORY = 2;
    public static int FOOD_CATEGORY = 1;

    public static boolean isIsSoundOn() {
        return isSoundOn;
    }

    public static void setIsSoundOn(boolean isSoundOn) {
        Setting.isSoundOn = isSoundOn;
    }

    public static String Font_Path = "fonts/carterone.ttf";

    public static Drawable BackgroundImgRes(Context ct, int type)
    {
        return Setting.GetBackgroundImg(ct, type + ".png");
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
            Log.d("FAILED_LOAD_SHOP_IMAGE", ex.getMessage());
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

    /**
     * Background game drawable
     */
    public static Drawable GetCategoryImg(Context ct, String file_name)
    {
        // load image
        try {
            // get input stream
            AssetManager assets = ct.getAssets();
            InputStream ims = assets.open("categories/" + file_name);

            // load image as Drawable
            return Drawable.createFromStream(ims, null);
        }
        catch(IOException ex) {
            Log.d("ASSET_FAILED", ex.getMessage());
            return null;
        }
    }


    public static int GetRandomPetSound(int petType)
    {
        Random rnd = new Random();
        int random = rnd.nextInt(3-1+1) + 1;

        switch (petType)
        {
            case 1:
            {
                switch (random)
                {
                    case 1: return R.raw.eve1;
                    case 2: return R.raw.eve2;
                    case 3: return R.raw.eve3;
                }
            }
            case 2:
            {
                switch (random)
                {
                    case 1: return R.raw.pikachu_01;
                    case 2: return R.raw.pikachu_02;
                    case 3: return R.raw.pikachu_03;
                }
            }
            case 3:
            {
                switch (random)
                {
                    case 1: return R.raw.vulpix1;
                    case 2: return R.raw.vulpix2;
                    case 3: return R.raw.vulpix3;
                }
            }
            default:
                return 0;
        }
    }

    public static AssetFileDescriptor GetMusic(Context ct, String file_name)
    {
        try
        {
            AssetFileDescriptor afd = ct.getAssets().openFd("musics/" + file_name);
            return afd;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
