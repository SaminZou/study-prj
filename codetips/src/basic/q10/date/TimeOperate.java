package basic.q10.date;

import static java.time.temporal.ChronoUnit.DAYS;

import basic.q10.date.utils.DateTimeUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

/**
 * 时间操作用例
 *
 * @author samin
 * @date 2023-06-28
 */
public class TimeOperate {

    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int dayOfWeek = date.getDayOfWeek()
                            .getValue();
        System.out.format("Year : %d  Month : %d  day : %d %d %n", year, month, day, dayOfWeek);

        System.out.println(":-----------------------");
        // 增加小时数
        System.out.println("两小时以后：" + LocalDateTime.now()
                                                        .plusHours(2));
        // 增加一周
        System.out.println("一周以后：" + LocalDate.now()
                                                  .plus(1, ChronoUnit.WEEKS));
        // 一年前
        System.out.println("一年前：" + LocalDate.now()
                                                .minus(1, ChronoUnit.YEARS));

        System.out.println(":-----------------------");
        LocalDate specDate = LocalDate.of(2020, 2, 29);
        // 增加一年
        System.out.println(specDate.plus(1, ChronoUnit.YEARS));
        // 减少一年
        System.out.println(specDate.minus(1, ChronoUnit.YEARS));
        // 使用运算后必须赋值，否则原值不变
        OffsetDateTime now = DateTimeUtils.stringToOffsetDateTime("2022-09-08 00:00:00");
        // 增加两周
        now = now.plusWeeks(2);
        // 设置星期几
        now = now.with(ChronoField.DAY_OF_WEEK, 2);
        // 2022-09-20 00:00:00
        System.out.println("adjust day of week:" + now);

        System.out.println(":-----------------------");
        // 计算时间差
        OffsetDateTime aTime = DateTimeUtils.stringToOffsetDateTime("2021-07-08 16:18:20");
        OffsetDateTime bTime = DateTimeUtils.stringToOffsetDateTime("2021-07-08 16:19:00");
        OffsetDateTime cTime = DateTimeUtils.stringToOffsetDateTime("2020-01-01 23:00:00");
        OffsetDateTime dTime = DateTimeUtils.stringToOffsetDateTime("2020-01-02 01:00:00");
        System.out.println("时间差（小时）：" + DateTimeUtils.timeDiff(aTime, bTime));
        System.out.println("时间差（小时）：" + DateTimeUtils.timeDiff2(aTime, bTime));
        // 天的时间差有两种精度，如 A 为 "2020-01-01 23:00" B 为 "2020-01-02 01:00"，按照日历计算时间差为 1 天，按照 24 小时制时间差不足 1 天结果为 0
        System.out.println("时间差（天）：" + DAYS.between(cTime.toLocalDate(), dTime.toLocalDate()));
        System.out.println("时间差（天）：" + Period.between(cTime.toLocalDate(), dTime.toLocalDate())
                                                 .getDays());
        System.out.println("时间差（月）：" + Period.between(aTime.toLocalDate(), bTime.toLocalDate())
                                                 .getMonths());
        // 判断日期的前后，返回布尔值
        System.out.println("aTime 在 bTime 之前：" + aTime.isBefore(bTime));
        System.out.println("aTime 在 bTime 之后：" + aTime.isAfter(bTime));
    }
}
