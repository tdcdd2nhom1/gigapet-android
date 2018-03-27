package com.sethphat.gigapet.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class UserHelper extends DBHelper {
    // TABLE INFO
    protected final static String TABLE_NAME = "Users";

    // COLUMN INFO
    protected final static String CL_ID = "ID";
    protected final static String CL_PetName = "PetName";
    protected final static String CL_Gold = "Gold";
    protected final static String CL_BackgroundIMG = "BackgroundIMG";
    protected final static String CL_Type = "Type";
    protected final static String CL_Evolution = "Evolution";
    protected final static String CL_Heart = "Heart";
    protected final static String CL_Experience = "Experience";
    protected final static String CL_PetSkin = "PetSkin";
    protected final static String CL_Hunger = "Hunger";
    protected final static String CL_Thirsty = "Thirsty";
    protected final static String CL_Fun = "Fun";
    protected final static String CL_Hygiene = "Hygiene";
    protected final static String CL_Energy = "Energy";
    protected final static String CL_Bladder = "Bladder";
    protected final static String CL_LastTime = "LastTime";

    public UserHelper(Context context) {
        super(context);
    }


}
