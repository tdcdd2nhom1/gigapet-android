package com.sethphat.gigapet.Configs;

import android.content.Context;

import com.sethphat.gigapet.Common.DBAccess;
import com.sethphat.gigapet.Models.Category;
import com.sethphat.gigapet.Models.ShopItem;
import com.sethphat.gigapet.SQLHelper.CategoryHelper;

public class SQLiteAccess {

    /**
     * Create default stupid data
     */
    public static void CreateDummyData() {
        // Category Dummy
        Category eatCate = new Category(0, "Food/Đồ ăn", 99, "groceries.png");
        Category drinkCate = new Category(0, "Drink/Nước uống", 99, "glass.png");
        Category backgroundCate = new Category(0, "Background/Hình nền", 99, "abc.png");
        Category skinCate = new Category(0, "Clothes/Skins", 99, "shoe.png");


        DBAccess.CateRepo.Insert(eatCate);
        DBAccess.CateRepo.Insert(drinkCate);
        DBAccess.CateRepo.Insert(skinCate);
        DBAccess.CateRepo.Insert(backgroundCate);

        // Shop Item dummy
        ShopItem food1 = new ShopItem(0, 1, "Apple", "A food that help your pet less hungry", 50, 0, 0, 0, 5);
        food1.setImage("apple.png");
        DBAccess.ShopItem.Insert(food1);
        ShopItem food2 = new ShopItem(0, 1, "Banana", "Delicious banana from Soraka", 80, 0, 0, 0, 15);
        food2.setImage("banana.png");
        DBAccess.ShopItem.Insert(food2);
        ShopItem food3 = new ShopItem(0, 1, "Sandwich", "Wow, it's look so good", 150, 0, 0, 0, 30);
        food3.setImage("sandwich.png");
        DBAccess.ShopItem.Insert(food3);

        ShopItem background1 = new ShopItem(0, 4, "Grass", "A peacefully grass scene", 40, 2, 0, 0, 0);
        background1.setImage("2.png");
        DBAccess.ShopItem.Insert(background1);

        ShopItem drink1 = new ShopItem(0, 2, "Apple Juice", "A fresh apple juice for your pet", 20, 0, 0, 0, 5);
        drink1.setImage("applejuice.png");
        DBAccess.ShopItem.Insert(drink1);

        // Skin/Clothes
        int[][] pets = {
                {4, 3, 3},
                {10, 3, 3},
                {5, 3, 5}
        };

        int[][] prices = {
                {75, 150, 300},
                {75, 150, 300},
                {75, 150, 300},
        };

        for (int i = 0; i < pets.length; i++)
        {
            for (int j = 0; j < pets[i].length; j++)
            {
                int numSkin = pets[i][j];
                int price = prices[i][j];
                for (int x = 1; x <= numSkin; x++)
                {
                    ShopItem skin = new ShopItem(0, 3, "Skin " + x + " - Evo: " + (i+1), "Super pet skin!!", price, x, i+1, j+1, 0);
                    skin.setImage("skin.png");
                    DBAccess.ShopItem.Insert(skin);
                }
            }
        }
    }

}
