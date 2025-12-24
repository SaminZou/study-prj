package com.samin.mybatis.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class PageReq extends UserQueryVO {

    @Min(value = 1, message = "页码不能小于1")
    private int page = 1;

    @Min(value = 1, message = "每页大小不能小于1")
    @Max(value = 100, message = "每页大小不能大于100")
    private int size = 10;
}
