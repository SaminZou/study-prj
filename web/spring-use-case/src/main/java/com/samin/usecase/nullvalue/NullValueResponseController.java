package com.samin.usecase.nullvalue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class NullValueResponseController {

    @GetMapping("/getInstance1")
    public ParamDTO getInstance1() {
        return ParamDTO.getInstance1();
    }

    @GetMapping("/getInstance2")
    public ParamDTO getInstance2() {
        return ParamDTO.getInstance2();
    }
}
