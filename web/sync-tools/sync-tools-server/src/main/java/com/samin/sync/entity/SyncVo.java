package com.samin.sync.entity;

import lombok.Data;

@Data
public class SyncVo {

    private String payload;

    private int onlineCount;
}