package com.samin.usecase.valid;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Req {

    @NotNull(message = "param1 不能为空")
    @Size(min = 1, message = "最小为 1")
    private List<String> param1;
}
