package com.samin.usecase.valid;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Req {

    @NotNull(message = "param1 不能为空")
    @Size(min = 1, message = "最小为 1")
    private List<String> param1;

    /**
     * 自动把对应格式的字符串转成 LocalDateTime
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime testTime;
}
