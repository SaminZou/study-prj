package basic.date.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * JDK8 以前的 Date 类
 *
 * @author samin
 * @date 2023-06-14
 */
public class DateUtils {

    /**
     * 获取当前月份
     *
     * @return
     */
    public static int getMonth() {
        // get(Calendar.MONTH) 方法的月份实际值是 0 ~ 11
        return Calendar.getInstance()
                       .get(Calendar.MONTH) + 1;
    }

    /**
     * 计算月初和月尾
     *
     * @param date
     * @return
     */
    public static Date[] getCurMonthPeriod(Date date) {
        Date[] dates = new Date[2];

        Calendar calendar = Calendar.getInstance();
        if (Objects.nonNull(date)) {
            calendar.setTime(date);
        }

        // 月初
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dates[0] = calendar.getTime();

        // 月尾
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        dates[1] = calendar.getTime();

        return dates;
    }

    /**
     * 如果分钟部分不在 00、15、30、45 这些合法值范围内，可以根据需要进行修正
     *
     * @param minutes
     * @return
     */
    public static int correctMinutes(int minutes) {
        // if (minutes < 0 || minutes > 59) {
        //     // 处理越界的分钟部分
        //     // 修正为最接近的 15 的倍数
        //     return minutes / 15 * 15;
        // }

        // 处理符合要求的分钟部分
        if (minutes % 15 != 0) {
            // 如果不是 00、15、30、45，可以将其修正为最接近的正确值
            return Math.round(minutes / 15.0f) * 15;
        }

        return minutes;
    }

    /**
     * 是否是前一个 15 分钟时段
     *
     * @param date1Str 开始时间
     * @param date2Str 结束时间
     * @return 布尔型结果
     */
    public static boolean isPre15Minute(String date1Str, String date2Str) {
        try {
            Date date1 = parseDate(date1Str);
            Date date2 = parseDate(date2Str);

            long diffMinute = (date2.getTime() - date1.getTime()) / 60000L;
            return (int) diffMinute == 15;
        } catch (ParseException e) {
            System.out.println("解析时间出错: " + e);
            return false;
        }
    }

    public static Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.parse(dateString);
    }

    @Deprecated
    public static int getDateYear(Date date) {
        return date.getYear();
    }

    public static int getDateYearV2(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * Date to LocalDateTime
     *
     * @param currentDate Date 类型实例
     * @return LocalDateTime 类型实例
     */
    public static LocalDateTime dateToLocalDateTime(Date currentDate) {
        return currentDate.toInstant()
                          .atZone(ZoneId.systemDefault())
                          .toLocalDateTime();
    }

    /**
     * 获取前 n 天
     *
     * @param currentDate 当前时间
     * @param n           需要获取的天数
     * @return 前 n 个工作日 / 假期数组
     */
    public static List<Date> getPreviousDays(Date currentDate, int n) {
        List<Date> results = new ArrayList<>();

        LocalDateTime currentLocalDateTime = dateToLocalDateTime(currentDate);
        while (results.size() < n) {
            LocalDateTime tempDateTime = currentLocalDateTime.minus(1, ChronoUnit.DAYS);
            results.add(Date.from(tempDateTime.atZone(ZoneId.systemDefault())
                                              .toInstant()));
            currentLocalDateTime = tempDateTime;
        }

        return results;
    }

    /**
     * 获取前 n 工作日 / 假期
     *
     * @param currentDate 当前时间
     * @param n           需要获取的天数
     * @param isHoliday   是否假期
     * @return 前 n 个工作日 / 假期数组
     */
    public static List<Date> getPreviousDays(Date currentDate, int n, boolean isHoliday) {
        List<Date> results = new ArrayList<>();

        LocalDateTime currentLocalDateTime = dateToLocalDateTime(currentDate);
        while (results.size() < n) {
            DayOfWeek dayOfWeek = DayOfWeek.of(currentLocalDateTime.get(ChronoField.DAY_OF_WEEK));
            LocalDateTime tempDateTime;

            if (isHoliday) {
                switch (dayOfWeek) {
                    case TUESDAY:
                        tempDateTime = currentLocalDateTime.minus(2, ChronoUnit.DAYS);
                        break;
                    case WEDNESDAY:
                        tempDateTime = currentLocalDateTime.minus(3, ChronoUnit.DAYS);
                        break;
                    case THURSDAY:
                        tempDateTime = currentLocalDateTime.minus(4, ChronoUnit.DAYS);
                        break;
                    case FRIDAY:
                        tempDateTime = currentLocalDateTime.minus(5, ChronoUnit.DAYS);
                        break;
                    case SATURDAY:
                        tempDateTime = currentLocalDateTime.minus(6, ChronoUnit.DAYS);
                        break;
                    default:
                        tempDateTime = currentLocalDateTime.minus(1, ChronoUnit.DAYS);
                }
                results.add(Date.from(tempDateTime.atZone(ZoneId.systemDefault())
                                                  .toInstant()));
                currentLocalDateTime = tempDateTime;
            } else {
                switch (dayOfWeek) {
                    case MONDAY:
                        tempDateTime = currentLocalDateTime.minus(3, ChronoUnit.DAYS);
                        break;
                    case SUNDAY:
                        tempDateTime = currentLocalDateTime.minus(2, ChronoUnit.DAYS);
                        break;
                    default:
                        tempDateTime = currentLocalDateTime.minus(1, ChronoUnit.DAYS);
                }
                results.add(Date.from(tempDateTime.atZone(ZoneId.systemDefault())
                                                  .toInstant()));
                currentLocalDateTime = tempDateTime;
            }
        }

        return results;
    }
}
