package com.samin.usecase.valid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ValidReq {

    @NotNull(message = "col 不能为空")
    private String col;

    @NotNull(message = "col2 不能为空")
    @Min(value = 1, message = "最小值为 1")
    private Integer col2;
}
