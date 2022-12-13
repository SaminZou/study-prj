package com.samin.redis.service;

import com.samin.redis.DateUtil;
import com.samin.redis.entity.Holiday;
import com.samin.redis.entity.HolidayStatsVo;
import com.samin.redis.repository.HolidayRepository;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 用户服务类
 *
 * @author samin
 * @date 2022-11-22
 */
@RequiredArgsConstructor
@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;

    @Cacheable(cacheNames = "HOLIDAY_STATS", key = "#p0")
    public HolidayStatsVo stats(String specTime) {
        HolidayStatsVo vo = new HolidayStatsVo();

        int holidaysCount = 0;
        int weekdaysCount = 0;
        // 第一天
        String firstDay = specTime + "0101";
        // 最后一天
        String lastDay = specTime + "1231";

        // 遍历计算每一天
        Date firstDate = DateUtil.parse(firstDay, "yyyyMMdd");
        //        while (!firstDay.equals(lastDay)) {
        //            if (isHoliday(firstDate) == 1) {
        //                holidayDaysNum += 1;
        //            } else {
        //                workDaysNum += 1;
        //            }
        //            firstDate = DateUtils.addDays(firstDate, 1);
        //            firstDay = DateUtil.format(firstDate, "yyyyMMdd");
        //        }

        //        // 处理最后一天
        //        if (isHoliday(DateUtil.parse(lastDay, "yyyyMMdd")) == 1) {
        //            holidayDaysNum += 1;
        //        } else {
        //            workDaysNum += 1;
        //        }

        //        DaysStatisticsDTO dto = new DaysStatisticsDTO();
        //        dto.setHolidayDaysNum(holidayDaysNum);
        //        dto.setWorkDaysNum(workDaysNum);

        //        resp.setData(dto);

        return vo;
    }

    public List<Holiday> findAll() {
        return holidayRepository.findAll();
    }

    @CacheEvict(cacheNames = "HOLIDAY_STATS", allEntries = true)
    public Holiday save(Holiday holiday) {
        return holidayRepository.save(holiday);
    }

    @CacheEvict(cacheNames = "HOLIDAY_STATS", allEntries = true)
    public void deleteById(Integer id) {
        try {
            holidayRepository.deleteById(id);
        } catch (Exception e) {
        }
    }
}
