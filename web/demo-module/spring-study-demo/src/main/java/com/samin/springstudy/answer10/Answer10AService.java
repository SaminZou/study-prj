package com.samin.springstudy.answer10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@Service
public class Answer10AService {

    @Autowired
    private Answer10BService Answer10BService;
}