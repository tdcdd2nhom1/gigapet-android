package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class ShopItem {
    private int ID;
    private int CategoryID;
    private String Name;
    private String Description;
    private int Price;
    private int BackgroundIMG = 0; // if != 0 => this is Background
    private int TypePet = 0; // if 0 => anyone can use

    public ShopItem(int ID, int categoryID, String name, String description, int price, int backgroundIMG, int typePet) {
        this.ID = ID;
        CategoryID = categoryID;
        Name = name;
        Description = description;
        Price = price;
        BackgroundIMG = backgroundIMG;
        TypePet = typePet;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
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

    public int getBackgroundIMG() {
        return BackgroundIMG;
    }

    public void setBackgroundIMG(int backgroundIMG) {
        BackgroundIMG = backgroundIMG;
    }

    public int getTypePet() {
        return TypePet;
    }

    public void setTypePet(int typePet) {
        TypePet = typePet;
    }
}
