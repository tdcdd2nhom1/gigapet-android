package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class ShopItem {
    public int ID;
    public String Name;
    public String Description;
    public int Price;

    public ShopItem(int ID, String name, String description, int price) {
        this.ID = ID;
        Name = name;
        Description = description;
        Price = price;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }
}
