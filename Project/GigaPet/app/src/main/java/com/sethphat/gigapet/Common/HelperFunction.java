package com.sethphat.gigapet.Common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelperFunction {

    public static int time()
    {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    /**
     * Convert unix timestamp to your format
     * @param format
     * @param unix_time
     * @return
     */
    public static String date(String format, int unix_time)
    {
        Date date = new Date(unix_time * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
