package com.samin.redis.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class HolidayStatsVo implements Serializable {

    private Integer holidaysCount;

    private Integer weekdaysCount;
}
