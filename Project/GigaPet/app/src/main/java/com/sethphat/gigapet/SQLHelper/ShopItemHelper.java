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
    protected static String CL_Evolution = "Evolution";
    protected static String CL_Recover = "Recover";

    public ShopItemHelper(Context context) {
        super(context);
    }


    @Override
    public void Insert(ShopItem info) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CL_Name, info.getName());
        values.put(CL_CategoryID, info.getCategoryID());
        values.put(CL_Description, info.getDescription());
        values.put(CL_Price, info.getPrice());
        values.put(CL_BackgroundIMG, info.getBackgroundIMG());
        values.put(CL_TypePet, info.getTypePet());
        values.put(CL_Evolution, info.getEvolution());
        values.put(CL_Recover, info.getRecover());
        values.put(CL_Image, info.getImage());

        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void Update(ShopItem info) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CL_Name, info.getName());
        values.put(CL_CategoryID, info.getCategoryID());
        values.put(CL_Description, info.getDescription());
        values.put(CL_Price, info.getPrice());
        values.put(CL_BackgroundIMG, info.getBackgroundIMG());
        values.put(CL_TypePet, info.getTypePet());
        values.put(CL_Evolution, info.getEvolution());
        values.put(CL_Recover, info.getRecover());
        values.put(CL_Image, info.getImage());

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
                int evolution = qr.getInt(qr.getColumnIndex(CL_Evolution));
                int recover = qr.getInt(qr.getColumnIndex(CL_Recover));
                String img = qr.getString(qr.getColumnIndex(CL_Image));

                // new obj
                ShopItem item = new ShopItem(id, cate_id, name, desc, price, background, typePet, evolution, recover);
                item.setImage(img);
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
            int evolution = qr.getInt(qr.getColumnIndex(CL_Evolution));
            int recover = qr.getInt(qr.getColumnIndex(CL_Recover));
            String img = qr.getString(qr.getColumnIndex(CL_Image));

            // new obj
            item = new ShopItem(id, cate_id, name, desc, price, background, typePet, evolution, recover);
            item.setImage(img);
        }

        qr.close();
        return item;
    }

    public ArrayList<ShopItem> GetByCategory(int cate_id, int type_pet, int evo)
    {
        SQLiteDatabase db = getWritableDatabase();
        ArrayList<ShopItem> arrItems = new ArrayList<>();

        // query
        Cursor qr;
        if (type_pet == 0 && evo == 0)
            qr = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CL_CategoryID + " = ?",
                    new String[] {Integer.toString(cate_id)}, null);
        else if (type_pet != 0 && evo == 0)
            qr = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CL_CategoryID + " = ? AND ("+CL_TypePet+" = ? OR " + CL_TypePet + " = 0)",
                    new String[] {cate_id+"", type_pet+""}, null);
        else
            qr = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CL_CategoryID + " = ? AND ("+CL_TypePet+" = ? OR "+CL_TypePet+" = 0) AND " + CL_Evolution + " <= ?",
                    new String[] {cate_id+"", type_pet+"", evo+""}, null);

        if (qr.moveToFirst())
        {
            do {
                int id = qr.getInt(qr.getColumnIndex(CL_ID));
                String name = qr.getString(qr.getColumnIndex(CL_Name));
                String desc = qr.getString(qr.getColumnIndex(CL_Description));
                int price = qr.getInt(qr.getColumnIndex(CL_Price));
                int typePet = qr.getInt(qr.getColumnIndex(CL_TypePet));
                int background = qr.getInt(qr.getColumnIndex(CL_BackgroundIMG));
                int evolution = qr.getInt(qr.getColumnIndex(CL_Evolution));
                int recover = qr.getInt(qr.getColumnIndex(CL_Recover));
                String img = qr.getString(qr.getColumnIndex(CL_Image));

                // new obj
                ShopItem item = new ShopItem(id, cate_id, name, desc, price, background, typePet, evolution, recover);
                item.setImage(img);
                arrItems.add(item);
            }
            while (qr.moveToNext());
        }

        qr.close();
        return arrItems;
    }

    public static long countAllInCategory(SQLiteDatabase db, int cate_id)
    {
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME, CL_CategoryID + " = ?", new String[] {cate_id+""});
        return count;
    }
}
