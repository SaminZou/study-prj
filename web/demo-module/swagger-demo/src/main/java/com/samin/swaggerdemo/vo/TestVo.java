package com.samin.swaggerdemo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("测试实体")
public class TestVo {

    @ApiModelProperty("名称")
    public String name;
}