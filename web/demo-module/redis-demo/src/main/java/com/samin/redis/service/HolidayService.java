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
        List<Holiday> all = holidayRepository.findAllByEnabled(true);
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
        return holidayRepository.findAllByEnabled(true);
    }

    @CacheEvict(cacheNames = "HOLIDAY_STATS", allEntries = true)
    public Holiday save(Holiday param) throws Exception {
        List<String> intersection = new ArrayList<>();

        // 校验入参假期和补班否重复
        if (Objects.nonNull(param.getHolidays()) && Objects.nonNull(param.getWeekdays()) && param.getHolidays().length > 0 && param.getWeekdays().length > 0) {
            for (String item : param.getWeekdays()) {
                if (ArrayUtils.contains(param.getHolidays(), item)) {
                    intersection.add(item);
                }
            }
            if (!CollectionUtils.isEmpty(intersection)) {
                throw new Exception("入参配置不能重复");
            }
        }

        List<Holiday> all = holidayRepository.findAllByEnabled(true);
        // 已存在假期和补班
        List<String> specDays = new ArrayList<>();
        for (Holiday item : all) {
            specDays.addAll(Arrays.asList(item.getHolidays()));
            specDays.addAll(Arrays.asList(item.getWeekdays()));
        }
        // 更新操作，去掉当前记录历史配置
        if (Objects.nonNull(param.getId())) {
            Holiday historyHistory = holidayRepository.findHolidayById(param.getId());
            if (Objects.nonNull(historyHistory)) {
                specDays = specDays.stream().filter(item -> !ArrayUtils.contains(historyHistory.getHolidays(), item))
                        .filter(item -> !ArrayUtils.contains(historyHistory.getWeekdays(), item))
                        .collect(Collectors.toList());
            }
        }

        // 校验假期
        if (Objects.nonNull(param.getHolidays()) &&param.getHolidays().length > 0) {
            intersection = new ArrayList<>();
            for (String item : param.getHolidays()) {
                if (specDays.contains(item)) {
                    intersection.add(item);
                }
            }
            if (!CollectionUtils.isEmpty(intersection)) {
                throw new Exception("假期配置存在重复天数");
            }
        }

        // 校验补班
        if (Objects.nonNull(param.getWeekdays()) &&param.getWeekdays().length > 0) {
            intersection = new ArrayList<>();
            for (String item : param.getWeekdays()) {
                if (specDays.contains(item)) {
                    intersection.add(item);
                }
            }
            if (!CollectionUtils.isEmpty(intersection)) {
                throw new Exception("补班配置存在重复天数");
            }
        }

        return holidayRepository.save(param);
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
        // 匹配是否有工作日配置
        List<String> workDays = getWorkDays(all);
        for (String ele : workDays) {
            if (dateStr.equals(ele)) {
                return false;
            }
        }

        // 匹配是否有假期配置
        List<String> holidays = getHolidays(all);
        for (String ele : holidays) {
            if (dateStr.equals(ele)) {
                return true;
            }
        }

        // 匹配通用算法
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
