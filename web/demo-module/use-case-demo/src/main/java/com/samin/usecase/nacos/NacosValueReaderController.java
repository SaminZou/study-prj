package com.samin.usecase.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@NacosPropertySource(dataId = "samin-test.yaml", autoRefreshed = true)
@RestController
public class NacosValueReaderController {

    @NacosValue(value = "${blogTitle:}", autoRefreshed = true)
    private String blogTitle;

    @GetMapping("/blogTitle")
    public String blogTitle() {
        return blogTitle;
    }
}