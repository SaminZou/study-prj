package basic.q10.jdk8time;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 时间工具类
 *
 * @author samin
 * @date 2022-09-29
 */
public class DateTimeUtils {

    /**
     * 时区偏移值
     */
    public static final String OFFSET_ID = "+8";

    public static void main(String[] args) {
        // 前 5 天
        System.out.println(getPreviousDays(new Date(), 5));
        // 前 5 个工作日
        System.out.println(getPreviousDays(new Date(), 5, false));
        // 前 5 个假期
        System.out.println(getPreviousDays(new Date(), 5, true));

        // 格式化的时间差
        OffsetDateTime aTime = strToOffsetDateTime("2021-07-08 16:18:20");
        OffsetDateTime bTime = strToOffsetDateTime("2021-07-08 16:20:00");
        System.out.println(formatTimeBySecond(aTime.until(bTime, ChronoUnit.SECONDS)));
        System.out.println(formatTimeBySecond(aTime.until(bTime, ChronoUnit.SECONDS)));
    }

    /**
     * 字符串转时间
     */
    public static OffsetDateTime strToOffsetDateTime(String text) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parseTime = LocalDateTime.parse(text, df);
        return OffsetDateTime.of(parseTime, ZoneOffset.of(OFFSET_ID));
    }

    public static LocalDate strToLocalDate(String text) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(text, df);
    }

    public static String formatTimeBySecond(long secondTime) {
        String result;

        long days = secondTime / (60L * 60L * 24L);
        long hours = (secondTime % (60L * 60L * 24L)) / (60L * 60L);
        long minutes = (long) Math.ceil((secondTime % (60D * 60D)) / 60D);

        if (days > 0) {
            result = days + "天" + hours + "小时" + minutes + "分钟";
        } else if (hours > 0) {
            result = hours + "小时" + minutes + "分钟";
        } else {
            result = minutes + "分钟";
        }

        return result;
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
     * Date to LocalDateTime
     *
     * @param currentDate Date 类型实例
     * @return LocalDateTime 类型实例
     */
    private static LocalDateTime dateToLocalDateTime(Date currentDate) {
        return currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }
}
