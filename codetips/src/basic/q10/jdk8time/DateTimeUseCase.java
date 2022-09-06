package basic.q10.jdk8time;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Java8 时间类
 *
 * @author samin
 * @date 2021-01-14
 */
public class DateTimeUseCase {

    public static void main(String[] args) {
        DateTimeUseCase.showDetailTime();
        DateTimeUseCase.showCycleTime();
        DateTimeUseCase.showTimeOperate();
        DateTimeUseCase.showTimestamp();
        DateTimeUseCase.showTimestamp2();

        // 类型转换
        LocalDateTime dateTime = localDateToLocalDateTime(LocalDate.now());
        System.out.println("今天的午夜时间：" + dateTime);

        // 打印当前日期
        LocalDateTime now = getNowDateTime();
        System.out.printf("当前时间是：%d年%d月%d日\n", now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        // 修改时间
        OffsetDateTime alterTime = OffsetDateTime.now();
        alterTime = alterTime.withYear(2021).withMonth(10).withDayOfMonth(1).withHour(16).withMinute(0).withSecond(0);
        System.out.println("修改后的时间为(2021-10-01 16：00：00): " + alterTime.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 计算时间差
        OffsetDateTime aTime = strToOffsetDateTime("2021-07-08 16:18:20");
        OffsetDateTime bTime = strToOffsetDateTime("2021-07-08 16:19:00");
        OffsetDateTime aTime1 = strToOffsetDateTime("2021-07-08 16:18:20");
        OffsetDateTime bTime1 = strToOffsetDateTime("2021-07-08 16:20:00");
        System.out.println("时间差（小时）：" + timeDiff(aTime, bTime) / 60L / 60L);
        System.out.println("时间差（小时）：" + timeDiff2(aTime, bTime));
        System.out.println("时间差（天）：" + Period.between(aTime.toLocalDate(), bTime.toLocalDate()).getDays());
        System.out.println("时间差（月）：" + Period.between(aTime.toLocalDate(), bTime.toLocalDate()).getMonths());
        // 判断日期的前后，返回布尔值
        System.out.println("aTime 在 bTime 之前：" + aTime.isBefore(bTime));
        System.out.println("aTime 在 bTime 之后：" + aTime.isAfter(bTime));
        // 检查是否闰年
        System.out.println("aTime 是否闰年：" + aTime.toLocalDate().isLeapYear());

        // 今天是周几
        System.out.println("今天是当月的第几天" + LocalDateTime.now().getDayOfWeek().getValue());
        // 今天是当月的第几天
        System.out.println(LocalDateTime.now().getDayOfMonth());

        // 格式化的时间差
        System.out.println(formatTimeBySecond(aTime.until(bTime, ChronoUnit.SECONDS)));
        System.out.println(formatTimeBySecond(aTime1.until(bTime1, ChronoUnit.SECONDS)));

        // 现在的时间
        System.out.printf("现在的时间是: %s", localDateTimeToString(LocalDateTime.now()));
    }

    /**
     * 时区偏移值
     */
    public static final String OFFSET_ID = "+8";

    /**
     * 获取当前时间
     *
     * @return 时分秒
     */
    public static LocalTime getNowTime() {
        return LocalTime.now();
    }

    /**
     * 获取当前时间
     *
     * @return 年月日
     */
    public static LocalDate getNowDate() {
        return LocalDate.now();
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

    public static LocalDateTime getNowDateTime() {
        return LocalDateTime.now();
    }

    public static void showDetailTime() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();

        System.out.format("Year : %d  Month : %d  day : %d %n", year, month, day);
    }

    /**
     * 周期性时间的处理类 MonthDay
     */
    public static void showCycleTime() {
        LocalDate date = LocalDate.of(2021, 5, 12);
        MonthDay today = MonthDay.from(date);

        MonthDay monthDay = MonthDay.of(5, 12);

        if (today.equals(monthDay)) {
            System.out.println("汶川大地震纪念日");
        }
    }

    /**
     * 时间运算
     */
    public static void showTimeOperate() {
        // 增加小时数
        System.out.println("两小时以后：" + LocalDateTime.now().plusHours(2));
        // 增加一周
        System.out.println("一周以后：" + LocalDate.now().plus(1, ChronoUnit.WEEKS));
        // 一年前
        System.out.println("一年前：" + LocalDate.now().minus(1, ChronoUnit.YEARS));

        LocalDate date = LocalDate.of(2020, 2, 29);
        System.out.println(date.plus(1, ChronoUnit.YEARS));
        System.out.println(date.minus(1, ChronoUnit.YEARS));
    }

    /**
     * Clock 用来代替 System.currentTimeInMillis() 和 TimeZone.getDefault()
     */
    public static void showTimestamp() {
        // 根据系统时间返回当前时间并设置为UTC
        System.out.println(Clock.systemUTC());
        System.out.println(Clock.systemUTC().millis());

        // 根据系统时钟区域返回时间
        System.out.println(Clock.systemDefaultZone());
        System.out.println(Clock.systemDefaultZone().millis());
    }

    public static void showTimestamp2() {
        System.out.println(Instant.now().toEpochMilli());
    }

    /**
     * LocalDate to LocalDateTime
     */
    public static LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIDNIGHT);
    }

    /**
     * 字符串转时间
     */
    public static OffsetDateTime strToOffsetDateTime(String text) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parseTime = LocalDateTime.parse(text, df);
        return OffsetDateTime.of(parseTime, ZoneOffset.of(DateTimeUseCase.OFFSET_ID));
    }

    /**
     * LocalDateTime to String
     */
    public static String localDateTimeToString(LocalDateTime ldt) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ldt.format(df);
    }

    /**
     * LocalDateTime to OffsetDateTime
     */
    public static OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime ldt) {
        return OffsetDateTime.of(ldt, ZoneOffset.of(DateTimeUseCase.OFFSET_ID));
    }

    /**
     * OffsetDateTime to String
     */
    public static String offsetDateTimeToString(OffsetDateTime odt) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return odt.toLocalDateTime().format(df);
    }

    /**
     * 计算时间差！谨记大的时间在后面，否则结果为负数
     */
    public static long timeDiff(OffsetDateTime a, OffsetDateTime b) {
        return Duration.between(a, b).getSeconds();
    }

    public static long timeDiff2(OffsetDateTime a, OffsetDateTime b) {
        return a.until(b, ChronoUnit.HOURS);
    }
}
