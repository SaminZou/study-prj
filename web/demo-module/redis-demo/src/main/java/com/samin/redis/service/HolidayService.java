package com.samin.redis.service;

import com.samin.redis.entity.Holiday;
import com.samin.redis.entity.HolidayStatsVo;
import com.samin.redis.repository.HolidayRepository;
import com.samin.redis.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.*;
import java.util.stream.Collectors;

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
    public Holiday save(Holiday holiday) throws Exception {
        // 检查假期和工作日配置是否重复
        List<String> intersection = new ArrayList<>();
        for (String item : holiday.getWeekdays()) {
            if (ArrayUtils.contains(holiday.getHolidays(), item)) {
                intersection.add(item);
            }
        }
        if (!CollectionUtils.isEmpty(intersection)) {
            throw new Exception("假期和补班设置不能重复");
        }

        // 防止假期和工作日重复
        List<Holiday> all = holidayRepository.findAll();
        // 已存在假期
        List<String> holidays = getHolidays(all);
        // 已存在补班
        List<String> workDays = getWorkDays(all);

        // 校验假期是否重复
        if (holiday.getHolidays().length > 0) {
            // 更新时，去掉校验历史设置参数
            if (Objects.nonNull(holiday.getId())) {
                Holiday historyHistory = holidayRepository.findHolidayById(holiday.getId());
                if (Objects.nonNull(historyHistory) && historyHistory.getHolidays().length > 0) {
                    holidays = holidays.stream()
                            .filter(item -> !ArrayUtils.contains(historyHistory.getHolidays(), item))
                            .collect(Collectors.toList());
                }
            }

            // 校验假期和就假期间是否重复
            intersection = new ArrayList<>();
            for (String item : holiday.getHolidays()) {
                if (holidays.contains(item)) {
                    intersection.add(item);
                }
            }
            if (!CollectionUtils.isEmpty(intersection)) {
                throw new Exception("假期设置区间存在重复配置天数");
            }

            // 校验假期和就补班间是否重复
            intersection = new ArrayList<>();
            for (String item : holiday.getWeekdays()) {
                if (holidays.contains(item)) {
                    intersection.add(item);
                }
            }
            if (!CollectionUtils.isEmpty(intersection)) {
                throw new Exception("假期设置区间存在重复配置天数");
            }

            // 校验补班是否重复
            if (holiday.getWeekdays().length > 0) {
                // 更新时，去掉校验历史设置参数
                if (Objects.nonNull(holiday.getId())) {
                    Holiday historyHistory = holidayRepository.findHolidayById(holiday.getId());
                    if (Objects.nonNull(historyHistory) && Objects.nonNull(historyHistory.getWeekdays())) {
                        workDays = workDays.stream()
                                .filter(item -> !ArrayUtils.contains(historyHistory.getWeekdays(), item))
                                .collect(Collectors.toList());
                    }
                }

                // 校验补班和补班间是否重复
                intersection = new ArrayList<>();
                for (String item : holiday.getWeekdays()) {
                    if (workDays.contains(item)) {
                        intersection.add(item);
                    }
                }
                if (!CollectionUtils.isEmpty(intersection)) {
                    throw new Exception("补班设置区间存在重复配置天数");
                }

                // 校验补班和假期是否重复
                intersection = new ArrayList<>();
                for (String item : holiday.getHolidays()) {
                    if (workDays.contains(item)) {
                        intersection.add(item);
                    }
                }
                if (!CollectionUtils.isEmpty(intersection)) {
                    throw new Exception("补班和假期存在重复配置天数");
                }
            }
        } else {
            // 校验补班是否重复
            if (holiday.getWeekdays().length > 0) {
                // 更新时，去掉校验历史设置参数
                if (Objects.nonNull(holiday.getId())) {
                    Holiday holidayHistory = holidayRepository.findHolidayById(holiday.getId());
                    if (Objects.nonNull(holidayHistory) && Objects.nonNull(holidayHistory.getWeekdays())) {
                        workDays = workDays.stream()
                                .filter(item -> !ArrayUtils.contains(holidayHistory.getWeekdays(), item))
                                .collect(Collectors.toList());
                    }
                }

                // 校验补班和补班间是否重复
                intersection = new ArrayList<>();
                for (String item : holiday.getWeekdays()) {
                    if (workDays.contains(item)) {
                        intersection.add(item);
                    }
                }

                if (!CollectionUtils.isEmpty(intersection)) {
                    throw new Exception("补班设置区间存在重复配置天数");
                }
            }
        }

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
        LocalDate currentLocalDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        DayOfWeek dayOfWeek = DayOfWeek.of(currentLocalDate.get(ChronoField.DAY_OF_WEEK));
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
