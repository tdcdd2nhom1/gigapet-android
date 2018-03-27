package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class ShopItem {
    private int ID;
    private String Name;
    private String Description;
    private int Price;
    private int BackgroundIMG = 0; // if != 0 => this is Background
    private int TypePet = 0; // if 0 => anyone can use

    public ShopItem(int ID, String name, String description, int price, int type) {
        this.ID = ID;
        Name = name;
        Description = description;
        Price = price;
        TypePet = type;
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
