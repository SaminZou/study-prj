package basic.q10.date.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * JDK8 以前的 Date 类
 *
 * @author samin
 * @date 2023-06-14
 */
public class DateUtils {

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
        return currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
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
            results.add(Date.from(tempDateTime.atZone(ZoneId.systemDefault()).toInstant()));
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
                results.add(Date.from(tempDateTime.atZone(ZoneId.systemDefault()).toInstant()));
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
                results.add(Date.from(tempDateTime.atZone(ZoneId.systemDefault()).toInstant()));
                currentLocalDateTime = tempDateTime;
            }
        }

        return results;
    }
}
