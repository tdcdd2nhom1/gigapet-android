package com.sethphat.gigapet.Configs;

public class Setting {
    private static boolean isSoundOn = true;
    public static int DefaultGold = 500;

    public static boolean isIsSoundOn() {
        return isSoundOn;
    }

    public static void setIsSoundOn(boolean isSoundOn) {
        Setting.isSoundOn = isSoundOn;
    }
}
