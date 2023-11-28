package com.samin.dingtalk.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Data;

@Data
@ApiModel("告警通知")
public class AlertReq {

    @ApiModelProperty("接受者")
    private String receiver;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("告警信息")
    private List<Alerts> alerts;

    @ApiModelProperty("通用标签")
    private CommonLabels commonLabels;

    @ApiModelProperty("通用注解")
    private CommonAnnotations commonAnnotations;
}
