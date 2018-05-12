package com.sethphat.gigapet.Models;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class UserItem {
    private int UserID;
    private int ShopItemID;
    private int Quantity;
    private ShopItem ShopItemObj;

    // use for foreign
    private ShopItem Item = null;

    public UserItem(int userID, int shopItemID, int quantity) {
        this.UserID = userID;
        ShopItemID = shopItemID;
        Quantity = quantity;
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

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public ShopItem getShopItemObj() {
        return ShopItemObj;
    }

    public void setShopItemObj(ShopItem shopItemObj) {
        ShopItemObj = shopItemObj;
    }
}
