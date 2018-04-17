package com.sethphat.gigapet.SQLHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.SQLHelper.Template.QueryTemplate;

import java.util.ArrayList;

/**
 * Created by Seth Phat on 3/27/2018.
 */

public class CategoryHelper extends DBHelper implements QueryTemplate<Category> {
    protected static String TABLE_NAME = "Categories";

    // column
    protected static String CL_ID = "ID";
    protected static String CL_Name = "Name";
    protected static String CL_Image = "Image";

    public CategoryHelper(Context context) {
        super(context);
    }

    @Override
    public void Insert(Category info) {
        SQLiteDatabase db = getWritableDatabase();

        // set content
        ContentValues values = new ContentValues();
        values.put(CL_Name, info.getName());

        // insert
        db.insert(TABLE_NAME, null, values);
    }

    @Override
    public void Update(Category info) {
        SQLiteDatabase db = getWritableDatabase();

        // set content
        ContentValues values = new ContentValues();
        values.put(CL_Name, info.getName());
        values.put(CL_Image, info.getImage());

        // update
        db.update(TABLE_NAME, values, CL_ID + " = ?", new String[] {Integer.toString(info.getID())});
    }

    @Override
    public void Delete(int id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, CL_ID + " = ?", new String[] {Integer.toString(id)});
    }

    @Override
    public ArrayList<Category> GetAll() {
        ArrayList<Category> allCates = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();

        // query all
        Cursor qr = db.rawQuery("SELECT * FROM " + TABLE_NAME, null, null);

        if (qr.moveToFirst())
        {
            do {
                // get data and create obj
                int id = qr.getInt(qr.getColumnIndex(CL_ID));
                String name = qr.getString(qr.getColumnIndex(CL_Name));
                String image = qr.getString(qr.getColumnIndex(CL_Image));
                Category cate = new Category(id, name, ShopItemHelper.countAllInCategory(db, id), image);

                // add arr
                allCates.add(cate);
            } while (qr.moveToNext());
        }

        qr.close();
        return allCates;
    }

    @Override
    public Category GetByID(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Category cate = null;

        // select only one
        Cursor qr = db.query(TABLE_NAME, new String[] {CL_ID, CL_Name}, CL_ID + " = ?", new String[] {Integer.toString(id)}, null, null, null);

        if (qr.moveToFirst())
        {
            String name = qr.getString(qr.getColumnIndex(CL_Name));
            String image = qr.getString(qr.getColumnIndex(CL_Image));
            cate = new Category(id, name, ShopItemHelper.countAllInCategory(db, id), image);
        }

        qr.close();
        return cate;
    }
}
