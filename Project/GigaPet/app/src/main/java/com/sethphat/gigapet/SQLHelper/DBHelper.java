package com.sethphat.gigapet.SQLHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Seth Phat on 3/26/2018.
 */

/**
 * DBHelper (parent) for all tables inside
 * Need to inheritance before using
 */
public abstract class DBHelper extends SQLiteOpenHelper {
    // DB NAME
    private static String DB_NAME = "GigaPet";
    private static int DB_VERSION = 1;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // CREATE DEFAULT DATABASE
        String create_user_table = "CREATE TABLE " + UserHelper.TABLE_NAME + "(" +
                                        UserHelper.CL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                        UserHelper.CL_PetName + " TEXT," +
                                        UserHelper.CL_Gold + " INTEGER," +
                                        UserHelper.CL_BackgroundIMG + " INTEGER," +
                                        UserHelper.CL_Type + " INTEGER," +
                                        UserHelper.CL_Evolution + " INTEGER," +
                                        UserHelper.CL_Heart + " INTEGER," +
                                        UserHelper.CL_Experience + " INTEGER," +
                                        UserHelper.CL_PetSkin + " INTEGER," +
                                        UserHelper.CL_Hunger + " INTEGER," +
                                        UserHelper.CL_Thirsty + " INTEGER," +
                                        UserHelper.CL_Fun + " INTEGER," +
                                        UserHelper.CL_Hygiene + " INTEGER," +
                                        UserHelper.CL_Energy + " INTEGER," +
                                        UserHelper.CL_Bladder + " INTEGER," +
                                        UserHelper.CL_LastTime + " INTEGER" +
                                    ")";

        db.execSQL(create_user_table);

        // CREATE Table Category
        String create_category_table = "CREATE TABLE " + CategoryHelper.TABLE_NAME + "(" +
                                            CategoryHelper.CL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                            CategoryHelper.CL_Name+ " TEXT," +
                                            CategoryHelper.CL_Image+ " TEXT" +
                                        ")";

        db.execSQL(create_category_table);

        // CREATE Table ShopItem
        String create_shopitem_table = "CREATE TABLE " + ShopItemHelper.TABLE_NAME + " (" +
                                            ShopItemHelper.CL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                            ShopItemHelper.CL_Name + " TEXT," +
                                            ShopItemHelper.CL_Description + " TEXT," +
                                            ShopItemHelper.CL_Price + " INTEGER DEFAULT 0," +
                                            ShopItemHelper.CL_BackgroundIMG + " INTEGER DEFAULT 0," +
                                            ShopItemHelper.CL_TypePet + " INTEGER DEFAULT 0," +
                                            ShopItemHelper.CL_Image+ " TEXT" +
                                        ")";

        db.execSQL(create_shopitem_table);

        // CREATE Table UserItem
        String create_useritem_table = "CREATE TABLE " + UserItemHelper.TABLE_NAME + "(" +
                                            UserItemHelper.CL_UserID + " INTEGER," +
                                            UserItemHelper.CL_ShopItemID + " INTEGER," +
                                            UserItemHelper.CL_Quantity + " INTEGER" +
                                        ")";

        db.execSQL(create_useritem_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // UPGRADE BY VERSION
        // if (DB_VERSION == 2)....
    }
}
