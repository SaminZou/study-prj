package com.samin.coding.Q26;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/** SimpleDateFormat 是线程不安全的 */
public class SimpleDateFormatTest {

    public static void main(String[] args) {
        // 展示错误现象
        showTheError();
        // 正确处理之后
        showTheFixed();
    }

    private static void showTheFixed() {
        ThreadLocal<SimpleDateFormat> map =
                ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(9, 20, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(100));

        // 两种错误：1.数据冲突，导致时间错误；2.引发 java.lang.NumberFormatException 错误
        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(
                    () -> {
                        String dateNowFirstFormatStr = map.get().format(new Date());
                        try {
                            Date parseDate = map.get().parse(dateNowFirstFormatStr);
                            String dateNowAgainFormatStr = map.get().format(parseDate);
                            System.out.println(dateNowFirstFormatStr.equals(dateNowAgainFormatStr));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    });
        }
        poolExecutor.shutdown();
    }

    private static void showTheError() {
        // 复用同一个 SimpleDateFormat
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(9, 20, 10, TimeUnit.MINUTES, new LinkedBlockingQueue<>(100));

        for (int i = 0; i < 100; i++) {
            poolExecutor.execute(
                    () -> {
                        String dateNowFirstFormatStr = simpleDateFormat.format(new Date());
                        try {
                            Date parseDate = simpleDateFormat.parse(dateNowFirstFormatStr);
                            String dateNowAgainFormatStr = simpleDateFormat.format(parseDate);
                            System.out.println(dateNowFirstFormatStr.equals(dateNowAgainFormatStr));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    });
        }
        poolExecutor.shutdown();
    }
}
