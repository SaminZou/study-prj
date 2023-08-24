package com.samin.usecase.async;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncUseCaseController {

    private final AsyncUseCaseService asyncUseCaseService;

    @GetMapping("/async/test")
    public String asyncTest() {
        asyncUseCaseService.test();

        return "success";
    }
}
