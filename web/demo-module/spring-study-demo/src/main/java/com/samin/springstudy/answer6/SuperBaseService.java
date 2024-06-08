package com.samin.springstudy.answer6;

import org.springframework.beans.factory.annotation.Autowired;

public class SuperBaseService {

    @Autowired
    private BizService bizService;

    public BizService getBizService() {
        return bizService;
    }
}