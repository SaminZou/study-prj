package com.samin.dingtalk.pojo.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

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

    @Data
    public static class Alerts {

        @ApiModelProperty("状态")
        private String status;

        @ApiModelProperty("标签")
        private Labels labels;

        private Annotations annotations;
    }

    @Data
    public static class Labels {

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

    @Data
    public static class Annotations {

        private String summary;
        private String description;
    }

    @Data
    public static class CommonLabels {

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

    @Data
    public static class CommonAnnotations {

        private String summary;
        private String description;
    }
}
