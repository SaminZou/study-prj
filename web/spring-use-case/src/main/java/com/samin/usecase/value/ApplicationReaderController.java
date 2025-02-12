package com.samin.usecase.value;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationReaderController {

    @Value(value = "${custom.order_test}")
    private String orderTest;

    @GetMapping("/orderTest")
    public String orderTest() {
        return orderTest;
    }
}
