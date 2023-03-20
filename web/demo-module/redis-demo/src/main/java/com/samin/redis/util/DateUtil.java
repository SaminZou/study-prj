package com.samin.redis.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date parse(String date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("pattern[" + pattern + "] and date[" + date + "] not match");
        }
    }

    public static String format(Date date, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
