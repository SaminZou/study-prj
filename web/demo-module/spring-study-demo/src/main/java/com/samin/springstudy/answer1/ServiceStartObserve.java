package com.samin.springstudy.answer1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceStartObserve implements CommandLineRunner {

    @Bean
    public SingleService singleService1() {
        return new SingleService();
    }

    @Override
    public void run(String... args) throws Exception {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(
                ServiceStartObserve.class, SingleService.class);
        // 观测是否单例
        System.out.println(applicationContext.getBean("singleService"));
        System.out.println(applicationContext.getBean("singleService"));
        System.out.println(applicationContext.getBean("singleService1"));
        System.out.println(applicationContext.getBean("singleService1"));
    }
}
