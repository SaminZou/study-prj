package com.samin.jobserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class JobSaveVo {

    private Integer id;

    private String name;

    private String description;

    private String appCode;

    private String actionCode;

    private String cron;

    private String paramJson;
}
