package basic.q10.date.utils;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * 时间工具类
 *
 * @author samin
 * @date 2022-09-29
 */
public class DateTimeUtils {

    public static final String DATETIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";

    /**
     * 字符串转时间
     */
    public static OffsetDateTime stringToOffsetDateTime(String text) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN);
        LocalDateTime parseTime = LocalDateTime.parse(text, df);
        return localDateTimeToOffsetDateTime(parseTime);
    }

    public static OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime localDateTime) {
        // 时区偏移值
        String OFFSET_ID = "+8";
        return OffsetDateTime.of(localDateTime, ZoneOffset.of(OFFSET_ID));
    }

    public static LocalDate strToLocalDate(String text) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(text, df);
    }

    /**
     * LocalDateTime to String
     */
    public static String localDateTimeToString(LocalDateTime ldt) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN);
        return ldt.format(df);
    }

    /**
     * String to LocalDateTime
     */
    public static LocalDateTime localDateTimeToString(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATETIME_FORMAT_PATTERN);
        return LocalDateTime.parse(date, df);
    }

    /**
     * String to LocalDateTime
     */
    public static LocalDateTime localDateTimeToString(String date, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(date, df);
    }

    /**
     * String to LocalDate
     */
    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        return LocalDate.parse(date, df);
    }

    /**
     * String to LocalDate
     */
    public static LocalDate stringToLocalDate(String date, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(date, df);
    }

    /**
     * 计算时间差！谨记大的时间在后面，否则结果为负数
     */
    public static long timeDiff(OffsetDateTime a, OffsetDateTime b) {
        return Duration.between(a, b)
                       .getSeconds() / 60L / 60L;
    }

    public static long timeDiff2(OffsetDateTime a, OffsetDateTime b) {
        return a.until(b, ChronoUnit.HOURS);
    }

    public static boolean isWeekend(String date) {
        DayOfWeek dayOfWeek = DayOfWeek.of(strToLocalDate(date).get(ChronoField.DAY_OF_WEEK));
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
