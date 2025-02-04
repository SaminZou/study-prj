package com.samin.springstudy.answer6;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Answer6Service extends BaseService {

    @PostConstruct
    public void postConstruct() {
        System.out.println("answer6: " + getBizService());
    }
}