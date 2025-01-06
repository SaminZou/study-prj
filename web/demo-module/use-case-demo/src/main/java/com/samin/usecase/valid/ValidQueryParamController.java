package com.samin.usecase.valid;

import java.util.List;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
public class ValidQueryParamController {

    @GetMapping("/validQueryParam")
    public String valid(@NotNull(message = "col 不能为空") String col, @NotNull(message = "col2 不能为空") @Min(value = 1, message = "最小值为 1") String col2) {
        return "success: " + col;
    }

    /**
     * 前端入参为：?params=foo,bar
     * 或：?params=foo&params=bar
     *
     * @param params
     * @return
     */
    @GetMapping("/arraysParam")
    public String valid(@RequestParam("params") List<String> params) {
        return "success: " + params;
    }
}
