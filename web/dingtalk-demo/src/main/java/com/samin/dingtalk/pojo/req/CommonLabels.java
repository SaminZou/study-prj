package com.samin.dingtalk.pojo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CommonLabels {

    @ApiModelProperty("告警名")
    private String alertname;

    @ApiModelProperty("应用名称")
    private String application;

    @ApiModelProperty("报错")
    private String exception;

    @ApiModelProperty("应用分组")
    private String group;

    @ApiModelProperty("实例地址")
    private String instance;

    @ApiModelProperty("抽取任务名")
    private String job;

    @ApiModelProperty("请求方式")
    private String method;

    private String outcome;

    @ApiModelProperty("严重程度")
    private String severity;

    @ApiModelProperty("http请求状态")
    private String status;

    @ApiModelProperty("请求链接")
    private String uri;
}
