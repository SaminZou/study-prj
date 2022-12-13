package com.samin.redis.entity;

import lombok.Data;

/**
 * Iot 设备
 *
 * @author samin
 * @date 2022-12-12
 */
@Data
public class IotDevice {

    private String ts;

    private Double val;

    private Integer m;

    private Integer f;

    private Integer flag;

    private String tcode;

    private String pcode;
}
