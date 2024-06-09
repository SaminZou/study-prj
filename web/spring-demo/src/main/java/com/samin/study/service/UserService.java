package com.samin.study.service;

import com.samin.spring.Autowired;
import com.samin.spring.BeanNameAware;
import com.samin.spring.Component;

@Component("userService")
//@Scope("prototype")
public class UserService implements BeanNameAware {

    @Autowired
    private BizService bizService;

    /**
     * dubbo 需要使用到 beanName
     */
    private String beanName;

    public void test() {
        System.out.println(beanName);
        System.out.println(bizService);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}