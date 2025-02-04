package com.samin.usecase.beanconfig.configwithcomponet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfigWithConfiguration {

    @Bean
    public B getB() {
        B b = new B();
        // 直接调用 @Bean 注解的方法方法 getA()
        b.setA(getA());
        return b;
    }

    @Bean
    public A getA() {
        return new A();
    }
}
