package com.samin.springstudy.answer5;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class Answer5Service implements BeanFactoryAware, InitializingBean {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Answer5Service beanFactoryAware");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("Answer5Service @PostConstruct");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Answer5Service InitializingBean");
    }
}
