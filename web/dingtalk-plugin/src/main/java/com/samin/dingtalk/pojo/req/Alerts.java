package com.samin.dingtalk.pojo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Alerts {

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("标签")
    private Labels labels;

    private Annotations annotations;
}
