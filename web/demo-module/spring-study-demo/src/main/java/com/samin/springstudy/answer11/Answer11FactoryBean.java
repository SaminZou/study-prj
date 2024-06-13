package com.samin.springstudy.answer11;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class Answer11FactoryBean implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return new Answer11BService();
    }

    @Override
    public Class<?> getObjectType() {
        return Answer11BService.class;
    }
}