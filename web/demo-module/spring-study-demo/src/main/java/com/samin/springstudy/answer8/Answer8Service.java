package com.samin.springstudy.answer8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Answer8Service {

    @Autowired
    @Value("#{answer8BizService1}")
    private Answer8BizService answer8BizService4444;
}