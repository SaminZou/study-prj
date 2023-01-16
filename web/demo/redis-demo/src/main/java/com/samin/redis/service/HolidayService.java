package com.samin.redis.service;

import com.samin.redis.entity.Holiday;
import com.samin.redis.entity.HolidayStatsVo;
import com.samin.redis.repository.HolidayRepository;
import com.samin.redis.util.DateUtil;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 *
 * @author samin
 * @date 2022-11-22
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    @Cacheable(cacheNames = "HOLIDAY_STATS", key = "#p0")
    public HolidayStatsVo stats(String specTime) {
        List<Holiday> all = holidayRepository.findAll();
        HolidayStatsVo vo = new HolidayStatsVo();

        int holidaysCount = 0;
        int weekdaysCount = 0;
        // 遍历计算每一天
        Date indexDate = DateUtil.parse(specTime + "0101", "yyyyMMdd");
        while (StringUtils.equals(specTime, DateUtil.format(indexDate, "yyyy"))) {
            if (isHoliday(all, indexDate)) {
                holidaysCount += 1;
            } else {
                weekdaysCount += 1;
            }
            indexDate = DateUtils.addDays(indexDate, 1);
        }

        vo.setHolidaysCount(holidaysCount);
        vo.setWeekdaysCount(weekdaysCount);

        return vo;
    }

    public List<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    @CacheEvict(cacheNames = "HOLIDAY_STATS", allEntries = true)
    public Holiday save(Holiday holiday) {
        // TODO 防止假期和工作日重复
        return holidayRepository.save(holiday);
    }

    @CacheEvict(cacheNames = "HOLIDAY_STATS", allEntries = true)
    public void deleteById(Integer id) {
        try {
            holidayRepository.deleteById(id);
        } catch (Exception e) {
            log.error("假期删除失败");
        }
    }

    public boolean isHoliday(List<Holiday> all, Date date) {
        String dateStr = DateUtil.format(date, "yyyyMMdd");
        // 匹配数据库特定数据
        List<String> workDays = getWorkDays(all);
        for (String ele : workDays) {
            if (dateStr.equals(ele)) {
                return false;
            }
        }

        List<String> holidays = getHolidays(all);
        for (String ele : holidays) {
            if (dateStr.equals(ele)) {
                return true;
            }
        }

        // 通用假期算法
        LocalDateTime currentLocalDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        DayOfWeek dayOfWeek = DayOfWeek.of(currentLocalDateTime.get(ChronoField.DAY_OF_WEEK));
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public List<String> getWorkDays(List<Holiday> all) {
        List<String> result = new ArrayList<>();
        all.forEach(e -> result.addAll(Arrays.asList(e.getWeekdays())));
        return result;
    }

    public List<String> getHolidays(List<Holiday> all) {
        List<String> result = new ArrayList<>();
        all.forEach(e -> result.addAll(Arrays.asList(e.getHolidays())));
        return result;
    }
}
