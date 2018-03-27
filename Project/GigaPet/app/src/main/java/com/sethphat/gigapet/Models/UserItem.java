package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class UserItem {
    private int UserID;
    private int ShopItemID;
    private int Quantity;

    // use for foreign
    private ShopItem Item = null;

    public UserItem(int userID, int shopItemID, int quantity) {
        this.UserID = userID;
        ShopItemID = shopItemID;
        Quantity = quantity;
    }

    public int getID() {
        return UserID;
    }

    public void setID(int ID) {
        this.UserID = ID;
    }

    public int getShopItemID() {
        return ShopItemID;
    }

    public void setShopItemID(int shopItemID) {
        ShopItemID = shopItemID;
    }

    public ShopItem getItem() {
        return Item;
    }

    public void setItem(ShopItem item) {
        Item = item;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
