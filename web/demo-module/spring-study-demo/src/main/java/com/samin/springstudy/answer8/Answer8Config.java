package com.samin.springstudy.answer8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Answer8Config {

    @Bean
    public Answer8BizService answer8BizService1() {
        return new Answer8BizService();
    }

    @Bean
    public Answer8BizService answer8BizService2() {
        return new Answer8BizService();
    }
}