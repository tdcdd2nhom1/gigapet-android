package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class Category {
    private int ID;
    private String Name;
    private int TotalItems;

    public Category(int ID, String name, int totalItems) {
        this.ID = ID;
        Name = name;
        TotalItems = totalItems;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getTotalItems() {
        return TotalItems;
    }

    public void setTotalItems(int totalItems) {
        TotalItems = totalItems;
    }
}
