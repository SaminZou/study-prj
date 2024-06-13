package com.samin.springstudy.answer11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Answer11PrintBean {

    @Autowired
    private Answer11AService aService;
    @Autowired
    private Answer11BService bService;

    @PostConstruct
    public void init() {
        System.out.println("Answer11PrintBean aService: " + aService);
        System.out.println("Answer11PrintBean bService: " + bService);
    }
}