package com.samin.spring;

/**
 * 初始化
 * <p>
 * Description: 一般用于类的自定义属性赋值注入
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-09
 */
public interface InitializingBean {

    void afterPropertiesSet();
}