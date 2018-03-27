package com.sethphat.gigapet.SQLHelper;

import android.content.Context;

/**
 * Created by Seth Phat on 3/27/2018.
 */

public class CategoryHelper extends DBHelper {
    protected static String TABLE_NAME = "Categories";

    // column
    protected static String CL_ID = "ID";
    protected static String CL_Name = "Name";

    public CategoryHelper(Context context) {
        super(context);
    }
}
