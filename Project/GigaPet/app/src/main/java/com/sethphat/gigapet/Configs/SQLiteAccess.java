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

        Category infoTruck = new Category(1, "Food", 99, "http://via.placeholder.com/60x60");
        Category infoDrink = new Category(2, "Drink", 99, "http://via.placeholder.com/60x60");
        Category infoBackground = new Category(3, "Background", 99, "http://via.placeholder.com/60x60");
        Category infoSkin = new Category(4, "Clothes/Skins", 99, "http://via.placeholder.com/60x60");


        DBAccess.CateRepo.Insert(infoTruck);
        DBAccess.CateRepo.Insert(infoDrink);
        DBAccess.CateRepo.Insert(infoBackground);
        DBAccess.CateRepo.Insert(infoSkin);

    }

}
