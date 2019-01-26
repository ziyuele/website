package com.ziyue.website.common;

import java.text.SimpleDateFormat;

public class Commons {

    public static final String OK_MSG = "ok";
    public static final int OK_STATUS = 200;
    public static final int ERROR_STATUS = 405;

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat format = new SimpleDateFormat(Commons.TIME_FORMAT);
    public static String TIME_STAMP() {
        return format.format(System.currentTimeMillis());
    }
}

