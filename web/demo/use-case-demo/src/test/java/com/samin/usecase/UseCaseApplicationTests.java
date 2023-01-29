package com.samin.usecase;

import java.text.ParseException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UseCaseApplicationTests {

    @Test
    void contextLoads() throws ParseException {
        String time1Str = "20221027170000";
        String time2Str = "20221027171500";
        String time3Str = "20221027173000";

        // true
        System.out.println(isPre15Minute(time1Str ,time2Str));
        // true
        System.out.println(isPre15Minute(time2Str ,time3Str));
        // false
        System.out.println(isPre15Minute(time1Str ,time3Str));
    }

    /**
     * 是否是前一个 15 分钟时段
     *
     * @param date1Str 开始时间
     * @param date2Str 结束时间
     * @return 布尔型结果
     */
    private boolean isPre15Minute(String date1Str, String date2Str) {
        try {
            Date date1 = DateUtils.parseDate(date1Str, "yyyyMMddHHmmss");
            Date date2 = DateUtils.parseDate(date2Str, "yyyyMMddHHmmss");

            long diffMinute = (date2.getTime() - date1.getTime()) / DateUtils.MILLIS_PER_MINUTE;
            return (int) diffMinute == 15;
        } catch (ParseException e) {
            log.error("解析时间出错", e);
            return false;
        }
    }
}
