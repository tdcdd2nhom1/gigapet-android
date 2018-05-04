package com.sethphat.gigapet.Configs;

import android.content.Context;

import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.SQLHelper.CategoryHelper;

public class SQLiteAccess {
    public static CategoryHelper cateHelper;

    public static void InitHelper(Context ct) {
        cateHelper = new CategoryHelper(ct);
    }

    /**
     * Create default stupid data
     */
    public static void CreateDummyData() {
        // dummy data for category
        cateHelper.Insert(new Category(1, "Food", 0, "R.drawable.foodtruck"));
        cateHelper.Insert(new Category(2, "Drink", 0, "R.drawable.water"));
        cateHelper.Insert(new Category(3, "Background", 0, "R.drawable.background"));
        cateHelper.Insert(new Category(4, "Clothes/Skins", 0, "R.drawable.skin"));
    }

}
