package com.samin.redis.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class HolidayStatsVo implements Serializable {

    private Integer holidaysCount;

    private Integer weekdaysCount;
}
