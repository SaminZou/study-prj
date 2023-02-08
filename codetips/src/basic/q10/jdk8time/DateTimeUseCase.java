package basic.q10.jdk8time;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * Java8 时间类
 *
 * @author samin
 * @date 2021-01-14
 */
public class DateTimeUseCase {

    public static void main(String[] args) throws InterruptedException {
        // 判断是否假期 false false true true
        DayOfWeek dayOfWeek = DayOfWeek.of(DateTimeUtils.strToLocalDate("2023-02-08").get(ChronoField.DAY_OF_WEEK));
        System.out.println(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
        dayOfWeek = DayOfWeek.of(DateTimeUtils.strToLocalDate("2023-02-10").get(ChronoField.DAY_OF_WEEK));
        System.out.println(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
        dayOfWeek = DayOfWeek.of(DateTimeUtils.strToLocalDate("2023-02-11").get(ChronoField.DAY_OF_WEEK));
        System.out.println(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
        dayOfWeek = DayOfWeek.of(DateTimeUtils.strToLocalDate("2023-02-12").get(ChronoField.DAY_OF_WEEK));
        System.out.println(dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);

        DateTimeUseCase.showTimeOperate();

        System.out.println(":-----------------------");
        DateTimeUseCase.showDetailTime();

        System.out.println(":-----------------------");
        DateTimeUseCase.showCycleTime();

        // 以下两个解析内容一致
        System.out.println(LocalDateTime.parse("2022-11-07 24:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(LocalDateTime.parse("2022-11-08 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 类型转换
        LocalDateTime dateTime = localDateToLocalDateTime(LocalDate.now());
        System.out.println("今天的午夜时间：" + dateTime);

        // 打印当前日期
        LocalDateTime now = LocalDateTime.now();
        System.out.printf("当前时间是：%d年%d月%d日\n", now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        // 修改时间
        OffsetDateTime alterTime = OffsetDateTime.now();
        alterTime = alterTime.withYear(2021).withMonth(10).withDayOfMonth(1).withHour(16).withMinute(0).withSecond(0);
        System.out.println("修改后的时间为(2021-10-01 16：00：00): " + alterTime.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // 计算时间差
        OffsetDateTime aTime = DateTimeUtils.strToOffsetDateTime("2021-07-08 16:18:20");
        OffsetDateTime bTime = DateTimeUtils.strToOffsetDateTime("2021-07-08 16:19:00");
        OffsetDateTime aTime1 = DateTimeUtils.strToOffsetDateTime("2020-01-01 23:00:00");
        OffsetDateTime bTime1 = DateTimeUtils.strToOffsetDateTime("2020-01-02 01:00:00");
        System.out.println("时间差（小时）：" + timeDiff(aTime, bTime) / 60L / 60L);
        System.out.println("时间差（小时）：" + timeDiff2(aTime, bTime));
        // 天的时间差有两种精度，如 A 为 "2020-01-01 23:00" B 为 "2020-01-02 01:00"，按照日历计算时间差为 1 天，按照 24 小时制时间差不足 1 天结果为 0
        System.out.println("时间差（天）：" + DAYS.between(aTime1.toLocalDate(), bTime1.toLocalDate()));
        System.out.println("时间差（天）：" + Period.between(aTime1.toLocalDate(), bTime1.toLocalDate()).getDays());
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

        // 现在的时间
        System.out.printf("现在的时间是: %s", localDateTimeToString(LocalDateTime.now()));
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

        OffsetDateTime now = DateTimeUtils.strToOffsetDateTime("2022-09-08 00:00:00");

        // 使用运算后必须赋值，否则原值不变
        now = now.plusWeeks(2);
        now = now.with(ChronoField.DAY_OF_WEEK, 2);
        // 2022-09-20 00:00:00
        System.out.println("adjust day of week:" + now);
    }

    /**
     * LocalDate to LocalDateTime
     */
    public static LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIDNIGHT);
    }

    /**
     * LocalDateTime to String
     */
    public static String localDateTimeToString(LocalDateTime ldt) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return ldt.format(df);
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
