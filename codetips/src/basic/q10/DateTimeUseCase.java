package basic.q10;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Java8 时间类
 *
 * @author samin
 * @date 2021-01-14
 */
public class DateTimeUseCase {

    /** 时区偏移值 */
    public static final String OFFSET_ID = "+8";

    /** LocalDate to LocalDateTime */
    public static LocalDateTime localDateToLocalDateTime(LocalDate date) {
        return LocalDateTime.of(date, LocalTime.MIDNIGHT);
    }

    /** 获取当前时间 */
    public static LocalDateTime getNowDateTime() {
        return LocalDateTime.now();
    }

    /** 字符串转时间 */
    public static OffsetDateTime strToOffsetDateTime(String text) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parseTime = LocalDateTime.parse(text, df);
        return OffsetDateTime.of(parseTime, ZoneOffset.of(DateTimeUseCase.OFFSET_ID));
    }

    /** LocalDateTime to OffsetDateTime */
    public static OffsetDateTime localDateTimeToOffsetDateTime(LocalDateTime ldt) {
        return OffsetDateTime.of(ldt, ZoneOffset.of(DateTimeUseCase.OFFSET_ID));
    }

    /** 计算时间差 */
    public static long timeDiff(OffsetDateTime a, OffsetDateTime b) {
        return Duration.between(a, b).getSeconds();
    }

    public static void main(String[] args) {
        Long timestamp = Clock.systemDefaultZone().millis();
        System.out.println("替代 System.currentTimeMillis 的 Clock 获取当前的时间戳：" + timestamp);

        // 增加小时数
        LocalDateTime.now().plusHours(2);
        // 增加一周
        LocalDate.now().plus(1, ChronoUnit.WEEKS);
        // 一年前
        LocalDate.now().minus(1, ChronoUnit.YEARS);

        // 类型转换
        LocalDateTime dateTime = localDateToLocalDateTime(LocalDate.now());
        System.out.println("今天的午夜时间：" + dateTime);

        // 打印当前日期
        LocalDateTime now = getNowDateTime();
        System.out.printf(
                "当前时间是：%d年%d月%d日\n", now.getYear(), now.getMonthValue(), now.getDayOfMonth());

        // 计算时间差
        OffsetDateTime aTime = strToOffsetDateTime("2020-02-28 00:00:00");
        OffsetDateTime bTime = strToOffsetDateTime("2020-03-01 00:00:00");
        // 显示小时
        System.out.println("时间差：" + timeDiff(aTime, bTime) / 60 / 60);
        // 判断日期的前后，返回布尔值
        System.out.println("aTime 在 bTime 之前：" + aTime.isBefore(bTime));
        System.out.println("aTime 在 bTime 之后：" + aTime.isAfter(bTime));
        // 检查是否闰年
        System.out.println("aTime 是否闰年：" + aTime.toLocalDate().isLeapYear());
    }
}
