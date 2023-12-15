package com.samin.redis.service;

import com.samin.redis.entity.Holiday;
import com.samin.redis.entity.HolidayDeleteReq;
import com.samin.redis.entity.HolidayStatsVo;
import com.samin.redis.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
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

    public static final String CACHE_NAME_HOLIDAY = "HOLIDAY_STATS";
    private final HolidayRepository holidayRepository;
    private final CacheManager cacheManager;

    public boolean isExist(String specTime) {
        return !(CollectionUtils.isEmpty(holidayRepository.findByHolidays(specTime)) && CollectionUtils.isEmpty(holidayRepository.findByWorkdays(specTime)));
    }

    @Cacheable(cacheNames = "HOLIDAY_STATS", key = "#p0")
    public HolidayStatsVo stats(String specTime) {
        HolidayGroupBo holidayGroupBo = getHolidayGroupBo();
        HolidayStatsVo vo = new HolidayStatsVo();

        int holidaysCount = 0;
        // 遍历计算每一天
        LocalDate localDate = LocalDate.parse(specTime + "0101", DateTimeFormatter.ofPattern(Holiday.DATE_FORMAT));
        while (localDate.get(ChronoField.YEAR) == Integer.parseInt(specTime)) {
            if (holidayGroupBo.isHoliday(localDate)) {
                holidaysCount += 1;
            }
            localDate = localDate.plusDays(1);
        }

        vo.setHolidaysCount(holidaysCount);
        vo.setWeekdaysCount(localDate.lengthOfYear() - holidaysCount);

        return vo;
    }

    public List<Holiday> findAll() {
        return holidayRepository.findAllByEnabled(true);
    }

    /**
     * 删除指定年份缓存，key 如何使用变量实例
     *
     * @param req
     * @throws Exception
     */
    @CacheEvict(cacheNames = CACHE_NAME_HOLIDAY, key = "#req.year", beforeInvocation = true)
    public void delete(HolidayDeleteReq req) {
        log.info("删除{}年缓存", req.getYear());
    }

    @CacheEvict(cacheNames = "HOLIDAY_STATS", allEntries = true)
    public Holiday save(Holiday param) throws Exception {
        // 校验入参假期和补班否重复
        if (Objects.nonNull(param.getHolidays()) && Objects.nonNull(param.getWorkdays()) && param.getHolidays().length > 0 && param.getWorkdays().length > 0) {
            if (isIntersect(Arrays.asList(param.getHolidays()), param.getWorkdays())) {
                throw new Exception("入参配置不能重复");
            }
        }

        List<Holiday> all = holidayRepository.findAllByEnabled(true);
        // 已存在假期和补班
        List<String> specDays = new ArrayList<>();
        for (Holiday item : all) {
            specDays.addAll(Arrays.asList(item.getHolidays()));
            specDays.addAll(Arrays.asList(item.getWorkdays()));
        }
        // 更新操作，去掉当前记录历史配置
        if (Objects.nonNull(param.getId())) {
            Holiday historyHistory = holidayRepository.findHolidayById(param.getId());
            if (Objects.nonNull(historyHistory)) {
                specDays = specDays.stream()
                        .filter(item -> !ArrayUtils.contains(historyHistory.getHolidays(), item))
                        .filter(item -> !ArrayUtils.contains(historyHistory.getWorkdays(), item))
                        .collect(Collectors.toList());
            }
        }

        // 校验假期
        if (Objects.nonNull(param.getHolidays()) && param.getHolidays().length > 0) {
            if (isIntersect(specDays, param.getHolidays())) {
                throw new Exception("假期配置存在重复天数");
            }
        }

        // 校验补班
        if (Objects.nonNull(param.getWorkdays()) && param.getWorkdays().length > 0) {
            if (isIntersect(specDays, param.getWorkdays())) {
                throw new Exception("补班配置存在重复天数");
            }
        }

        return holidayRepository.save(param);
    }

    private boolean isIntersect(List<String> days, String[] days2) {
        List<String> intersection = new ArrayList<>();

        for (String item : days2) {
            if (days.contains(item)) {
                intersection.add(item);
            }
        }

        return !CollectionUtils.isEmpty(intersection);
    }

    @CacheEvict(cacheNames = "HOLIDAY_STATS", allEntries = true)
    public void deleteById(Integer id) {
        try {
            holidayRepository.deleteById(id);
        } catch (Exception e) {
            log.error("假期删除失败");
        }
    }

    /**
     * 判断缓存是否存在
     *
     * @param year
     * @return
     */
    public boolean isCache(int year) {
        Cache cache = cacheManager.getCache("HOLIDAY_STATS");
        if (Objects.nonNull(cache)) {
            Object cachedValue = cache.get("HOLIDAY_STATS:" + year);
            return Objects.nonNull(cachedValue);
        }
        return false;
    }

    private HolidayGroupBo getHolidayGroupBo() {
        List<Holiday> all = findAll();
        return new HolidayGroupBo(all);
    }

    private static class HolidayGroupBo {

        List<String> holidays;
        List<String> workdays;

        public HolidayGroupBo(List<Holiday> all) {
            this.holidays = getHolidays(all);
            this.workdays = getWorkDays(all);
        }

        private List<String> getWorkDays(List<Holiday> all) {
            List<String> result = new ArrayList<>();
            all.forEach(e -> result.addAll(Arrays.asList(e.getWorkdays())));
            return result;
        }

        private List<String> getHolidays(List<Holiday> all) {
            List<String> result = new ArrayList<>();
            all.forEach(e -> result.addAll(Arrays.asList(e.getHolidays())));
            return result;
        }

        private boolean isHoliday(LocalDate date) {
            String dateStr = date.format(DateTimeFormatter.ofPattern(Holiday.DATE_FORMAT));

            // 匹配是否有工作日配置
            if (this.workdays.contains(dateStr)) {
                return false;
            }

            // 匹配是否有假期配置
            if (this.holidays.contains(dateStr)) {
                return true;
            }

            // 通用匹配算法
            DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
            return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
        }
    }
}
