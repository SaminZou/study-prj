package com.samin.springstudy.answer7;

import com.samin.springstudy.answer6.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Answer7Service extends BaseService {

    @Autowired(required = false)
    private Answer7BizService answer7BizService;

    @PostConstruct
    public void postConstruct() {
        System.out.println("answer7: " + answer7BizService);
    }
}