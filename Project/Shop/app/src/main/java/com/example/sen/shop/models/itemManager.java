package com.example.sen.shop.models;

import com.example.sen.shop.R;

import java.util.ArrayList;

/**
 * Created by minhc_000 on 12/08/2015.
 */
public class itemManager {
    private ArrayList Items;

    //private List<itemShop> Items = new ArrayList<>();

    public itemManager() {
        Items = new ArrayList();
    }

    public ArrayList getProducts() {
        return Items;
    }

    public void setProducts(ArrayList products) {
        Items = products;
    }

    public void generateProducts() {
        Items.clear();
        Items.add(new itemShop("Food", R.mipmap.food));

    }

    private static itemManager itemManager;

    public static itemManager get() {
        if (itemManager == null)
            itemManager = new itemManager();
        return itemManager;
    }
}
