package com.samin.redis.controller;


import com.samin.redis.entity.Holiday;
import com.samin.redis.entity.HolidayDeleteReq;
import com.samin.redis.entity.HolidayStatsVo;
import com.samin.redis.service.HolidayService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制类
 *
 * @author samin
 * @date 2022-11-23
 */
@RequiredArgsConstructor
@RestController
public class HolidayController {

    private final HolidayService holidayService;

    @PostMapping("/holiday/stats/{specTime}")
    public HolidayStatsVo stats(@PathVariable("specTime") String specTime) {
        return holidayService.stats(specTime);
    }

    @PostMapping("/holiday/list")
    public List<Holiday> list() {
        return holidayService.findAll();
    }

    @PostMapping("/holiday/update")
    public Holiday save(@RequestBody Holiday holiday) throws Exception {
        return holidayService.save(holiday);
    }

    @PostMapping("/holiday/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        holidayService.deleteById(id);
    }

    @PostMapping("/holiday/delete/cache")
    public void deleteCache(@RequestBody HolidayDeleteReq req) {
        holidayService.delete(req);
    }

    @PostMapping("/holiday/isExist/{specTime}")
    public boolean isExist(@PathVariable("specTime") String specTime) {
        return holidayService.isExist(specTime);
    }

    @PostMapping("/holiday/isCache/{specTime}")
    public boolean isCache(@PathVariable("specTime") Integer specTime) {
        return holidayService.isCache(specTime);
    }
}