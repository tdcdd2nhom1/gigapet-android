package com.sethphat.gigapet.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.SQLHelper.Template.QueryTemplate;

import java.util.ArrayList;

/**
 * Created by Seth Phat on 3/27/2018.
 */

public class ShopItemHelper extends DBHelper implements QueryTemplate<ShopItem> {
    protected static String TABLE_NAME = "ShopItems";

    // COLUMN
    protected static String CL_ID = "ID";
    protected static String CL_CategoryID = "CategoryID";
    protected static String CL_Name = "Name";
    protected static String CL_Description = "Description";
    protected static String CL_Price = "Price";
    protected static String CL_BackgroundIMG = "BackgroundIMG";
    protected static String CL_TypePet = "TypePet";
    protected static String CL_Image = "Image";

    public ShopItemHelper(Context context) {
        super(context);
    }


    @Override
    public void Insert(ShopItem info) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CL_Name, info.getName());
        values.put(CL_Description, info.getDescription());
        values.put(CL_Price, info.getPrice());
        values.put(CL_BackgroundIMG, info.getBackgroundIMG());
        values.put(CL_TypePet, info.getTypePet());

        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void Update(ShopItem info) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CL_Name, info.getName());
        values.put(CL_Description, info.getDescription());
        values.put(CL_Price, info.getPrice());
        values.put(CL_BackgroundIMG, info.getBackgroundIMG());
        values.put(CL_TypePet, info.getTypePet());

        db.update(TABLE_NAME, values, CL_ID + " = ?", new String[] {Integer.toString(info.getID())});
    }

    @Override
    public void Delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, CL_ID + " = ?", new String[] {Integer.toString(id)});
    }

    @Override
    public ArrayList<ShopItem> GetAll() {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ShopItem> arrItems = new ArrayList<>();

        // query
        Cursor qr = db.rawQuery("SELECT * FROM " + TABLE_NAME, null, null);

        if (qr.moveToFirst())
        {
            do {
                int id = qr.getInt(qr.getColumnIndex(CL_ID));
                int cate_id = qr.getInt(qr.getColumnIndex(CL_CategoryID));
                String name = qr.getString(qr.getColumnIndex(CL_Name));
                String desc = qr.getString(qr.getColumnIndex(CL_Description));
                int price = qr.getInt(qr.getColumnIndex(CL_Price));
                int typePet = qr.getInt(qr.getColumnIndex(CL_TypePet));
                int background = qr.getInt(qr.getColumnIndex(CL_BackgroundIMG));

                // new obj
                ShopItem item = new ShopItem(id, cate_id, name, desc, price, background, typePet);
                arrItems.add(item);
            }
            while (qr.moveToNext());
        }

        qr.close();
        return arrItems;
    }

    @Override
    public ShopItem GetByID(int id) {

        SQLiteDatabase db = getWritableDatabase();
        ShopItem item = null;

        // query
        Cursor qr = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CL_ID + " = ?", new String[] {Integer.toString(id)}, null);

        if (qr.moveToFirst()) {
            int cate_id = qr.getInt(qr.getColumnIndex(CL_CategoryID));
            String name = qr.getString(qr.getColumnIndex(CL_Name));
            String desc = qr.getString(qr.getColumnIndex(CL_Description));
            int price = qr.getInt(qr.getColumnIndex(CL_Price));
            int typePet = qr.getInt(qr.getColumnIndex(CL_TypePet));
            int background = qr.getInt(qr.getColumnIndex(CL_BackgroundIMG));

            // new obj
            item = new ShopItem(id, cate_id, name, desc, price, background, typePet);
        }

        qr.close();
        return item;
    }

    public ArrayList<ShopItem> GetByCategory(int cate_id)
    {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ShopItem> arrItems = new ArrayList<>();

        // query
        Cursor qr = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CL_CategoryID + " = ?", new String[] {Integer.toString(cate_id)}, null);

        if (qr.moveToFirst())
        {
            do {
                int id = qr.getInt(qr.getColumnIndex(CL_ID));
                String name = qr.getString(qr.getColumnIndex(CL_Name));
                String desc = qr.getString(qr.getColumnIndex(CL_Description));
                int price = qr.getInt(qr.getColumnIndex(CL_Price));
                int typePet = qr.getInt(qr.getColumnIndex(CL_TypePet));
                int background = qr.getInt(qr.getColumnIndex(CL_BackgroundIMG));

                // new obj
                ShopItem item = new ShopItem(id, cate_id, name, desc, price, background, typePet);
                arrItems.add(item);
            }
            while (qr.moveToNext());
        }

        qr.close();
        return arrItems;
    }

    public static long countAllInCategory(SQLiteDatabase db, int cate_id)
    {
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return count;
    }
}
