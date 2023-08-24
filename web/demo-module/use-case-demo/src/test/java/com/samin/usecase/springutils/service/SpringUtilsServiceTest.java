package com.samin.usecase.springutils.service;

import com.samin.usecase.springutils.SpringUtilsService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class SpringUtilsServiceTest {

    @Autowired
    private SpringUtilsService springUtilsService;

    @Test
    void assertUseCase() {
        springUtilsService.assertUseCase();
    }

    @Test
    void objectUtilsUseCase() {
        springUtilsService.objectUtilsUseCase();
    }

    @Test
    void stringUtilsUseCase() {
        springUtilsService.stringUtilsUseCase();
    }

    @Test
    void resourceUtils() {
        springUtilsService.resourceUtils();
    }
}