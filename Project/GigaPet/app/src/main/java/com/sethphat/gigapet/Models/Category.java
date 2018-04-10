package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class Category {
    private int ID;
    private String Name;
    private String Image;
    private long TotalItems;

    public Category(int ID, String name, long totalItems, String img) {
        this.ID = ID;
        Name = name;
        TotalItems = totalItems;
        Image = img;
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

    public long getTotalItems() {
        return TotalItems;
    }

    public void setTotalItems(long totalItems) {
        TotalItems = totalItems;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
