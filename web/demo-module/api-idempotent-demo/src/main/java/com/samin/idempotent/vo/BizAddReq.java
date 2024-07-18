package com.samin.idempotent.vo;

import com.samin.idempotent.itf.RequestKeyParam;
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