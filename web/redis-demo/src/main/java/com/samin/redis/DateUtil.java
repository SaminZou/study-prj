package com.samin.redis;

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
}
