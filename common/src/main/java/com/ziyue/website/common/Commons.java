package com.ziyue.website.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Commons {

    public static final String OK_MSG = "ok";
    public static final int OK_STATUS = 200;
    public static final int ERROR_STATUS = 405;

    private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat format = new SimpleDateFormat(Commons.TIME_FORMAT);
    public static String TIME_STAMP() {
        return format.format(System.currentTimeMillis());
    }

    public static String VERSION() {
        try {
            InputStream in = Commons.class.getClass().getResourceAsStream("/version");
            @Cleanup
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            return bufferedReader.readLine();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }
}

