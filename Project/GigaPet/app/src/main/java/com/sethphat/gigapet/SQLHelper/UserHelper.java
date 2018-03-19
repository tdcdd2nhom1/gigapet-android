package com.sethphat.gigapet.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class UserHelper extends SQLiteOpenHelper {
    private final static String TABLE_NAME = "Users";
    private final static int TABLE_VERSION = 1;

    public UserHelper(Context context) {
        super(context, TABLE_NAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Create Database Goes Here
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
