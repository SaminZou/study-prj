package com.samin.study.service;

import com.samin.spring.Autowired;
import com.samin.spring.BeanNameAware;
import com.samin.spring.Component;
import com.samin.spring.InitializingBean;

@Component("userService")
//@Scope("prototype")
public class UserService implements BeanNameAware, InitializingBean {

    @Autowired
    private BizService bizService;

    /**
     * dubbo 需要使用到 beanName
     */
    private String beanName;

    private String val;

    public void test() {
        System.out.println(beanName);
        System.out.println(bizService);
        System.out.println(val);
    }

    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void afterPropertiesSet() {
        this.val = "val";
    }
}