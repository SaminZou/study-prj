package com.samin.jobcenter.bean;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobDTO {

    private Integer logId;

    private LocalDateTime processTime;

    private String actionCode;

    private String paramJson;
}
