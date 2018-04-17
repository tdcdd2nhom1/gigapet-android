package com.sethphat.gigapet.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sethphat.gigapet.Models.UserItem;
import com.sethphat.gigapet.SQLHelper.Template.QueryTemplate;

import java.util.ArrayList;

/**
 * Created by Seth Phat on 3/27/2018.
 */

public class UserItemHelper extends DBHelper implements QueryTemplate<UserItem> {
    protected final static String TABLE_NAME = "UserItems";

    // column
    protected final static String CL_UserID = "UserID";
    protected final static String CL_ShopItemID = "ShopItemID";
    protected final static String CL_Quantity = "Quantity";

    public UserItemHelper(Context context) {
        super(context);
    }

    @Override
    public void Insert(UserItem info) {
        SQLiteDatabase db = getWritableDatabase();

        // insert
        ContentValues cv = new ContentValues();
        cv.put(CL_UserID, info.getUserID());
        cv.put(CL_ShopItemID, info.getShopItemID());
        cv.put(CL_Quantity, info.getQuantity());

        db.insert(TABLE_NAME, null, cv);
    }

    @Override
    public void Update(UserItem info) {
        SQLiteDatabase db = getWritableDatabase();

        // update
        ContentValues cv = new ContentValues();
        cv.put(CL_Quantity, info.getQuantity());

        db.update(TABLE_NAME, cv, CL_UserID + " = ? AND " + CL_ShopItemID + " = ?",
                        new String[] {Integer.toString(info.getUserID()), Integer.toString(info.getShopItemID())});
    }

    @Override
    public void Delete(int id) {

    }

    public void Delete(int userId, int shopItemId)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, CL_UserID + " = ? AND " + CL_ShopItemID + " = ?",
                new String[] {Integer.toString(userId), Integer.toString(shopItemId)});
    }

    public ArrayList<UserItem> GetAllByUserID(int userId) {
        ArrayList<UserItem> list = new  ArrayList<UserItem>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor qr = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CL_UserID + " = ?", new String[] {Integer.toString(userId)});

        if (qr.moveToFirst())
        {
            do {
                int shopItemId = qr.getInt(qr.getColumnIndex(CL_ShopItemID));
                int quantity = qr.getInt(qr.getColumnIndex(CL_Quantity));

                list.add(new UserItem(userId, shopItemId, quantity));
            }
            while (qr.moveToNext());
        }

        return list;
    }

    // ko xai
    @Override
    public ArrayList<UserItem> GetAll() {
        return null;
    }

    // ko xai
    @Override
    public UserItem GetByID(int user_id) {
        return null;
    }
}
