package com.sethphat.gigapet.SQLHelper;

import android.content.Context;

/**
 * Created by Seth Phat on 3/27/2018.
 */

public class ShopItemHelper extends DBHelper {
    protected static String TABLE_NAME = "ShopItems";

    // COLUMN
    protected static String CL_ID = "ID";
    protected static String CL_Name = "Name";
    protected static String CL_Description = "Description";
    protected static String CL_Price = "Price";
    protected static String CL_BackgroundIMG = "BackgroundIMG";
    protected static String CL_TypePet = "TypePet";

    public ShopItemHelper(Context context) {
        super(context);
    }
}
