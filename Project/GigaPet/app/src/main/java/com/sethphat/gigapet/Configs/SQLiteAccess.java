package com.sethphat.gigapet.Configs;

import android.content.Context;

import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.SQLHelper.CategoryHelper;

public class SQLiteAccess {

    /**
     * Create default stupid data
     */
    public static void CreateDummyData() {
        // dummy data for category
        DBAccess.CateRepo.Insert(new Category(0, "Food", 0, "R.drawable.foodtruck"));
        DBAccess.CateRepo.Insert(new Category(0, "Drink", 0, "R.drawable.icecream"));
        DBAccess.CateRepo.Insert(new Category(0, "Background", 0, "R.drawable.foodtruck"));
        DBAccess.CateRepo.Insert(new Category(0, "Clothes/Skins", 0, "R.drawable.foodtruck"));

        // dummy data for Background

    }

}
