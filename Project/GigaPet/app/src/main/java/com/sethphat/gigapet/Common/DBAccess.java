package com.sethphat.gigapet.Common;

import android.content.Context;

import com.sethphat.gigapet.SQLHelper.CategoryHelper;
import com.sethphat.gigapet.SQLHelper.ShopItemHelper;
import com.sethphat.gigapet.SQLHelper.UserHelper;
import com.sethphat.gigapet.SQLHelper.UserItemHelper;

public class DBAccess {
    public static UserHelper UserRepo;
    public static UserItemHelper UserItemRepo;
    public static CategoryHelper CateRepo;
    public static ShopItemHelper ShopItem;
}
