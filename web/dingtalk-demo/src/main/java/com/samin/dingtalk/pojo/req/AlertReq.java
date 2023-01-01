package com.samin.dingtalk.pojo.req;

import java.util.List;
import lombok.Data;

@Data
public class AlertReq {

    private String receiver;

    private String status;

    private List<Alerts> alerts;

    private CommonLabels commonLabels;

    private CommonAnnotations commonAnnotations;
}
