package com.samin.spring;

/**
 * 可以用于插件实现的使用
 * <p>
 * Description: @EnableAspectJAutoProxy 注解中 @Import(AspectJAutoProxyRegister.class) 核心实现就是 BeanPostProcessor
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-09
 */
public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(Object bean, String beanName);

    Object postProcessAfterInitialization(Object bean, String beanName);
}