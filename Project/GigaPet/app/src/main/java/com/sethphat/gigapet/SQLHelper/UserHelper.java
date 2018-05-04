package com.sethphat.gigapet.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sethphat.gigapet.Models.User;
import com.sethphat.gigapet.SQLHelper.Template.QueryTemplate;

import java.util.ArrayList;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class UserHelper extends DBHelper implements QueryTemplate<User> {
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
    protected final static String CL_IsSleeping = "IsSleeping";
    protected final static String CL_GoodFeeling = "GoodFeeling";
    protected final static String CL_BadFeeling = "BadFeeling";
    protected final static String CL_LastTime = "LastTime";

    public UserHelper(Context context) {
        super(context);
    }


    @Override
    public void Insert(User info) {
        SQLiteDatabase db = getWritableDatabase();

        // insert
        ContentValues cv = new ContentValues();
        cv.put(CL_PetName, info.getPetName());
        cv.put(CL_Gold, info.getGold());
        cv.put(CL_BackgroundIMG, info.getBackgroundIMG());
        cv.put(CL_Type, info.getType());
        cv.put(CL_Evolution, info.getEvolution());
        cv.put(CL_Heart, info.getHeart());
        cv.put(CL_Experience, info.getExperience());
        cv.put(CL_PetSkin, info.getPetSkin());
        cv.put(CL_Hunger, info.getHunger());
        cv.put(CL_Thirsty, info.getThirsty());
        cv.put(CL_Fun, info.getFun());
        cv.put(CL_Hygiene, info.getHygiene());
        cv.put(CL_Energy, info.getEnergy());
        cv.put(CL_Bladder, info.getBladder());
        cv.put(CL_IsSleeping, info.getIsSleeping());
        cv.put(CL_GoodFeeling, info.getGoodFeeling());
        cv.put(CL_BadFeeling, info.getBadFeeling());
        cv.put(CL_LastTime, info.getLastTime());

        long id = db.insert(TABLE_NAME, null, cv);

        // set id back
        info.setID((int) id);
    }

    @Override
    public void Update(User info) {
        SQLiteDatabase db = getWritableDatabase();

        // update
        ContentValues cv = new ContentValues();
        cv.put(CL_PetName, info.getPetName());
        cv.put(CL_Gold, info.getGold());
        cv.put(CL_BackgroundIMG, info.getBackgroundIMG());
        cv.put(CL_Type, info.getType());
        cv.put(CL_Evolution, info.getEvolution());
        cv.put(CL_Heart, info.getHeart());
        cv.put(CL_Experience, info.getExperience());
        cv.put(CL_PetSkin, info.getPetSkin());
        cv.put(CL_Hunger, info.getHunger());
        cv.put(CL_Thirsty, info.getThirsty());
        cv.put(CL_Fun, info.getFun());
        cv.put(CL_Hygiene, info.getHygiene());
        cv.put(CL_Energy, info.getEnergy());
        cv.put(CL_Bladder, info.getBladder());
        cv.put(CL_IsSleeping, info.getIsSleeping());
        cv.put(CL_GoodFeeling, info.getGoodFeeling());
        cv.put(CL_BadFeeling, info.getBadFeeling());
        cv.put(CL_LastTime, info.getLastTime());

        db.update(TABLE_NAME, cv, "ID = ?", new String[] {Integer.toString(info.getID())});
    }

    @Override
    public void Delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "ID = ?", new String[] {Integer.toString(id)});
    }

    @Override
    public ArrayList<User> GetAll() {
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        // query all
        Cursor qr = db.rawQuery("SELECT * FROM " + TABLE_NAME, null, null);

        if (qr.moveToFirst())
        {
            do {
                // get data and create obj
                User user = new User();
                user.setID(qr.getInt(qr.getColumnIndex(CL_ID)));
                user.setPetName(qr.getString(qr.getColumnIndex(CL_PetName)));
                user.setGold(qr.getInt(qr.getColumnIndex(CL_Gold)));
                user.setBackgroundIMG(qr.getInt(qr.getColumnIndex(CL_BackgroundIMG)));
                user.setEvolution(qr.getInt(qr.getColumnIndex(CL_Evolution)));
                user.setType(qr.getInt(qr.getColumnIndex(CL_Type)));
                user.setHeart(qr.getInt(qr.getColumnIndex(CL_Heart)));
                user.setExperience(qr.getInt(qr.getColumnIndex(CL_Experience)));
                user.setPetSkin(qr.getInt(qr.getColumnIndex(CL_PetSkin)));
                user.setHunger(qr.getInt(qr.getColumnIndex(CL_Hunger)));
                user.setThirsty(qr.getInt(qr.getColumnIndex(CL_Thirsty)));
                user.setHygiene(qr.getInt(qr.getColumnIndex(CL_Hygiene)));
                user.setFun(qr.getInt(qr.getColumnIndex(CL_Fun)));
                user.setEnergy(qr.getInt(qr.getColumnIndex(CL_Energy)));
                user.setBladder(qr.getInt(qr.getColumnIndex(CL_Bladder)));
                user.setIsSleeping(qr.getInt(qr.getColumnIndex(CL_IsSleeping)));
                user.setGoodFeeling(qr.getInt(qr.getColumnIndex(CL_GoodFeeling)));
                user.setBadFeeling(qr.getInt(qr.getColumnIndex(CL_BadFeeling)));
                user.setLastTime(qr.getInt(qr.getColumnIndex(CL_LastTime)));


                list.add(user);

            } while (qr.moveToNext());
        }

        qr.close();

        return list;
    }

    @Override
    public User GetByID(int id) {
        User user = null;
        SQLiteDatabase db = getWritableDatabase();

        // select only one
        Cursor qr = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CL_ID + " = ?", new String[] {Integer.toString(id)});

        if (qr.moveToFirst())
        {
            user = new User();
            user.setID(qr.getInt(qr.getColumnIndex(CL_ID)));
            user.setPetName(qr.getString(qr.getColumnIndex(CL_PetName)));
            user.setGold(qr.getInt(qr.getColumnIndex(CL_Gold)));
            user.setBackgroundIMG(qr.getInt(qr.getColumnIndex(CL_BackgroundIMG)));
            user.setEvolution(qr.getInt(qr.getColumnIndex(CL_Evolution)));
            user.setType(qr.getInt(qr.getColumnIndex(CL_Type)));
            user.setHeart(qr.getInt(qr.getColumnIndex(CL_Heart)));
            user.setExperience(qr.getInt(qr.getColumnIndex(CL_Experience)));
            user.setPetSkin(qr.getInt(qr.getColumnIndex(CL_PetSkin)));
            user.setHunger(qr.getInt(qr.getColumnIndex(CL_Hunger)));
            user.setThirsty(qr.getInt(qr.getColumnIndex(CL_Thirsty)));
            user.setHygiene(qr.getInt(qr.getColumnIndex(CL_Hygiene)));
            user.setFun(qr.getInt(qr.getColumnIndex(CL_Fun)));
            user.setEnergy(qr.getInt(qr.getColumnIndex(CL_Energy)));
            user.setBladder(qr.getInt(qr.getColumnIndex(CL_Bladder)));
            user.setIsSleeping(qr.getInt(qr.getColumnIndex(CL_IsSleeping)));
            user.setGoodFeeling(qr.getInt(qr.getColumnIndex(CL_GoodFeeling)));
            user.setBadFeeling(qr.getInt(qr.getColumnIndex(CL_BadFeeling)));
            user.setLastTime(qr.getInt(qr.getColumnIndex(CL_LastTime)));
        }

        qr.close();
        return user;
    }
}
