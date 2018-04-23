package com.sethphat.gigapet.Configs;

import com.sethphat.gigapet.Models.User;

public class Setting {
    private static boolean isSoundOn = true;
    public static int DefaultGold = 500;
    public static User UserData = null;

    public static boolean isIsSoundOn() {
        return isSoundOn;
    }

    public static void setIsSoundOn(boolean isSoundOn) {
        Setting.isSoundOn = isSoundOn;
    }

    public static String Font_Path = "fonts/carterone.ttf";

}
