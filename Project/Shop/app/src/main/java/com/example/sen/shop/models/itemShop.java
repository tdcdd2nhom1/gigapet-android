package com.example.sen.shop.models;

/**
 * Created by Sen on 07-Apr-18.
 */

public class itemShop {

    private String itemName;
    private int iconShop;
    private long ID;

    public itemShop(String itemName, int iconShop, long ID) {
        this.itemName = itemName;
        this.iconShop = iconShop;
        this.ID = ID;
    }

    public itemShop() {
        itemName = "";
    }
    public itemShop(String iName, int iCon) {
        itemName = iName;
        iconShop = iCon;
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

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }
}
