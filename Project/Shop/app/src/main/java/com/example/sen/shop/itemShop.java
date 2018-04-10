package com.example.sen.shop;

import java.io.Serializable;

/**
 * Created by Sen on 07-Apr-18.
 */

public class itemShop {

    private String itemName;
    private int iconShop;

    public itemShop(String itemName, int iconShop) {
        this.itemName = itemName;
        this.iconShop = iconShop;
    }


    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getIconShop() {
        return iconShop;
    }

    public void setIconShop(int iconShop) {
        this.iconShop = iconShop;
    }
}
