package basic.q10.jdk8time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class TimeOperate {

    public static void main(String[] args) {
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
}
