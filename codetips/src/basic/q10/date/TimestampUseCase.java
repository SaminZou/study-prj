package basic.q10.date;

import java.time.Clock;
import java.time.Instant;

/**
 * 时间戳用例
 * <p> Clock 用来代替 System.currentTimeInMillis() 和 TimeZone.getDefault()
 *
 * @author samin
 * @date 2023-02-08
 */
public class TimestampUseCase {

    public static void main(String[] args) throws InterruptedException {
        // 根据系统时间返回当前时间并设置为 UTC
        System.out.println(Clock.systemUTC());
        System.out.println(Clock.systemUTC()
                .millis());

        // 根据系统时钟区域返回时间
        System.out.println(Clock.systemDefaultZone());
        System.out.println(Clock.systemDefaultZone()
                .millis());

        // 毫秒
        System.out.println(Instant.now()
                .toEpochMilli());
        // 秒数部分
        System.out.println("秒：" + Instant.now()
                .getEpochSecond());
        // 纳秒部分
        System.out.println(Instant.now()
                .getNano());

        // 统计代码执行时间
        long startTime = Instant.now()
                .toEpochMilli();
        Thread.sleep(3000L);
        System.out.println("执行时间为：" + ((Instant.now()
                .toEpochMilli() - startTime) / 1000L));
    }
}
