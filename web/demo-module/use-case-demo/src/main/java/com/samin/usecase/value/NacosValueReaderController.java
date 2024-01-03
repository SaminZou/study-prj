package com.samin.usecase.value;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosValueReaderController {

    @NacosValue(value = "${blogTitle:}", autoRefreshed = true)
    private String blogTitle;

    @GetMapping("/blogTitle")
    public String blogTitle() {
        return blogTitle;
    }
}