package com.samin.idempotence.vo;

import com.samin.idempotence.itf.RequestKeyParam;
import lombok.Data;

import java.util.List;

@Data
public class BizAddReq {

    @RequestKeyParam
    private String a;

    @RequestKeyParam
    private String b;

    private List<Long> arrs;
}