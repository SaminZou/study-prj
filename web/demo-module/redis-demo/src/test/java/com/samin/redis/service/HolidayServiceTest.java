package com.samin.redis.service;

import com.samin.redis.entity.Holiday;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class HolidayServiceTest {

    @Autowired
    private HolidayService holidayService;

    @Test
    void contextLoads() throws Exception {
        Holiday holiday = Holiday.getInstance();
        holiday.setHolidays(new String[]{"2023-04-29", "2023-04-30", "2023-05-01", "2023-05-02", "2023-05-03"});
        holiday.setWeekdays(new String[]{"2023-04-23", "2023-05-06"});
        holiday.setHolidayType(3);
        holiday.setRemark("劳动节");
        holidayService.save(holiday);

        // ERROR 入参重复
        holiday = Holiday.getInstance();
        holiday.setHolidays(new String[]{"2023-05-15", "2023-05-16", "2023-05-17"});
        holiday.setWeekdays(new String[]{"2023-05-16", "2023-05-18", "2023-05-19"});
        holidayService.save(holiday);

        // ERROR 假期重复
        holiday = Holiday.getInstance();
        holiday.setHolidays(new String[]{"2023-05-15"});
        holidayService.save(holiday);

        // ERROR 补班重复
        holiday = Holiday.getInstance();
        holiday.setWeekdays(new String[]{"2023-05-18"});
        holidayService.save(holiday);

        // ERROR 假期与补班重复
        holiday = Holiday.getInstance();
        holiday.setHolidays(new String[]{"2023-05-19"});
        holidayService.save(holiday);

        // 补班与假期重复
        holiday = Holiday.getInstance();
        holiday.setWeekdays(new String[]{"2023-05-17"});
        holidayService.save(holiday);
    }
}