package com.sethphat.gigapet.SQLHelper;

import android.content.Context;

/**
 * Created by Seth Phat on 3/27/2018.
 */

public class UserItemHelper extends DBHelper {
    protected final static String TABLE_NAME = "UserItems";

    // column
    protected final static String CL_UserID = "UserID";
    protected final static String CL_ShopItemID = "ShopItemID";
    protected final static String CL_Quantity = "Quantity";

    public UserItemHelper(Context context) {
        super(context);
    }
}
