package basic.date;

import basic.date.utils.DateTimeUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Java8 时间类
 *
 * @author samin
 * @date 2021-01-14
 */
public class DateTimeUseCase {

    public static void main(String[] args) throws InterruptedException {
        // 上两个整点的开始和结束时间
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startOfLastHour = now.truncatedTo(ChronoUnit.HOURS)
                                           .minusHours(2);
        LocalDateTime endOfLastHour = startOfLastHour.plusHours(1)
                                                     .minusNanos(1);
        LocalDateTime startOfCurrentHour = now.truncatedTo(ChronoUnit.HOURS)
                                              .minusHours(1);
        LocalDateTime endOfCurrentHour = startOfCurrentHour.plusHours(1)
                                                           .minusNanos(1);
        System.out.println("上两个整点的开始和结束时间：");
        System.out.println(startOfLastHour + " ~ " + endOfLastHour);
        System.out.println(startOfCurrentHour + " ~ " + endOfCurrentHour);

        // 格式化的时间差
        OffsetDateTime aTime = DateTimeUtils.stringToOffsetDateTime("2021-07-08 16:18:20");
        OffsetDateTime bTime = DateTimeUtils.stringToOffsetDateTime("2021-07-08 16:20:00");
        System.out.println(formatTimeBySecond(aTime.until(bTime, ChronoUnit.SECONDS)));

        System.out.println(":-----------------------");
        // 判断是否周末 false false true true
        System.out.println("判断是否周末：");
        System.out.println(DateTimeUtils.isWeekend("2023-02-08"));
        System.out.println(DateTimeUtils.isWeekend("2023-02-10"));
        System.out.println(DateTimeUtils.isWeekend("2023-02-11"));
        System.out.println(DateTimeUtils.isWeekend("2023-02-12"));

        System.out.println(":-----------------------");
        // LocalDate to LocalDateTime
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = LocalDateTime.of(date, LocalTime.MIDNIGHT);
        System.out.println("今天的午夜时间：" + dateTime);

        System.out.println(":-----------------------");
        // 指定时间为 2021-5-12
        DateTimeUseCase.checkCycleTime(2021, 5, 12);

        System.out.println(":-----------------------");
        // 修改时间
        OffsetDateTime alterTime = OffsetDateTime.now();
        alterTime = alterTime.withYear(2021)
                             .withMonth(10)
                             .withDayOfMonth(1)
                             .withHour(16)
                             .withMinute(0)
                             .withSecond(0);
        System.out.println("修改后的时间为(2021-10-01 16：00：00): " + alterTime.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        System.out.println(":-----------------------");
        // 检查是否闰年
        System.out.println("aTime 是否闰年：" + aTime.toLocalDate()
                                                    .isLeapYear());

        System.out.println(":-----------------------");
        // 测试时间是否重叠
        DateTimeBo time1 = new DateTimeBo();
        time1.startDate = DateTimeUtils.stringToLocalDate("20230110", "yyyyMMdd");
        time1.endDate = DateTimeUtils.stringToLocalDate("20230120", "yyyyMMdd");
        DateTimeBo time2 = new DateTimeBo();
        time2.startDate = DateTimeUtils.stringToLocalDate("20230101", "yyyyMMdd");
        time2.endDate = DateTimeUtils.stringToLocalDate("20230105", "yyyyMMdd");
        System.out.println("两个时间是否重叠：" + isOverlap(time1, time2));
    }

    /**
     * 周期性时间的处理类 MonthDay
     */
    public static void checkCycleTime(int year, int mouth, int day) {
        LocalDate date = LocalDate.of(year, mouth, day);

        // 获取指定时间的月日
        MonthDay monthAndDay = MonthDay.from(date);
        System.out.println(monthAndDay.format(DateTimeFormatter.ofPattern("MM-dd")));

        // 指定节日月日
        MonthDay memorialDay = MonthDay.of(5, 12);

        // 判断是否为纪念日
        if (monthAndDay.equals(memorialDay)) {
            System.out.println("汶川大地震纪念日");
        }
    }

    /**
     * 是否存在时间重叠
     *
     * @param time1 时间段对象参数
     * @param time2 时间段对象参数
     * @return
     */
    public static boolean isOverlap(DateTimeBo time1, DateTimeBo time2) {
        return time1.startDate.isBefore(time2.endDate) && time1.endDate.isAfter(time2.startDate);
    }

    public static class DateTimeBo {

        public LocalDate startDate;
        public LocalDate endDate;
    }

    /**
     * 格式化时间差
     *
     * @param secondTime 秒数
     * @return 格式化后的时间差字符串
     */
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
}
