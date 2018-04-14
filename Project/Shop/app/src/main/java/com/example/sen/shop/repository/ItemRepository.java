package com.example.sen.shop.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sen.shop.models.itemShop;

import java.util.ArrayList;

/**
 * Created by minhc_000 on 13/08/2015.
 */
public class ItemRepository {
    static final String TAB_ITEM = "item_Shop";
    static final String COL_ITEMID = "item_ID";
    static final String COL_ITEMNAME = "item_Name";
    static final String COL_ICON = "item_Icon";

    public static String getCreateTableSQL() {
        String sql = "CREATE TABLE " + TAB_ITEM + "(" +
                COL_ITEMID + " integer primary key autoincrement, " +
                COL_ITEMNAME + " text, " +
                COL_ICON + " text)";
        return sql;
    }

    private DatabaseHelper dbhelper;

    public ItemRepository(Context context) {
        dbhelper = new DatabaseHelper(context);
    }

    private ContentValues MakeProductContentValues(itemShop item) {
        ContentValues cv = new ContentValues();
        cv.put(COL_ITEMNAME, item.getItemName());
        cv.put(COL_ICON, item.getIconShop());
        return cv;
    }

    public void addItem(itemShop item) {
        ContentValues cv = MakeProductContentValues(item);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        long id = db.insert(TAB_ITEM, null, cv);
        if (id != -1) item.setID(id);
    }

    public void updateItem(itemShop item) {
        ContentValues cv = MakeProductContentValues(item);
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.update(TAB_ITEM, cv, COL_ITEMID + "=?",
                new String[]{String.valueOf(item.getID())});
    }

    public void deleteItembyID(long id) {
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        db.delete(TAB_ITEM, COL_ITEMID + " = " + id, null);
    }

    public void getID() {
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql = "SELECT " +COL_ITEMID+ " FROM " + TAB_ITEM;
        Cursor cursor = db.rawQuery(sql, null);

        cursor.close();
    }



    public ArrayList<itemShop> getAllItem() {
        ArrayList<itemShop> items = new ArrayList<itemShop>();
        SQLiteDatabase db = dbhelper.getReadableDatabase();
        String sql = "SELECT * FROM " + TAB_ITEM;
        Cursor cursor = db.rawQuery(sql, null);
        int indexItemID = cursor.getColumnIndex(COL_ITEMID);
        int indexItemName = cursor.getColumnIndex(COL_ITEMNAME);
        int indexItemIcon = cursor.getColumnIndex(COL_ICON);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                itemShop item = new itemShop();
                item.setItemName(cursor.getString(indexItemName));
                item.setIconShop(cursor.getInt(indexItemIcon));
                item.setID(cursor.getLong(indexItemID));
                items.add(item);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return items;
    }
}
