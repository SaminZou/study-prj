package com.samin.usecase.springutils;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.util.*;

/**
 * spring 自带工具类用例
 *
 * @author samin
 * @date 2023-07-05
 */
@Service
public class SpringUtilsService {

    public void assertUseCase() {
        User user = null;
        Assert.notNull(user, "user 为空");

        user = new User();
        Assert.hasLength(user.getName(), "用户名为空");

        Assert.isInstanceOf(User.class, new Object(), "不是 User 类");
    }

    public void objectUtilsUseCase() {
        User user = new User();
        System.out.println("对象为：" + ObjectUtils.nullSafeClassName(user));
    }

    public void stringUtilsUseCase() {
        // Not Empty
        // 结果为 true
        System.out.println(StringUtils.hasLength("  "));
        // Not Blank
        // 结果为 false，空格不算内容
        System.out.println(StringUtils.hasText("  "));
    }

    public void collectionUtils() {
        System.out.println(CollectionUtils.isEmpty(new ArrayList<>()));
    }

    public void resourceUtils() {
        // 判断字符串是否是一个合法的 URL 字符串
        System.out.println(ResourceUtils.isUrl("/d/test"));
    }

    /**
     * IO 类
     */
    public void streamUtils() {

    }

    /**
     * 反射
     */
    public void reflectionUtils() {

    }

    /**
     * AOP
     */
    public void aopUtils() {
        // isAopProxy() 判断是不是 Spring 代理对象
        // isJdkDynamicProxy() 判断是不是 jdk 动态代理对象
        // isCglibProxy() 判断是不是 CGLIB 代理对象
        // getTargetClass() 获取被代理的目标 class
    }

    public void aopContext() {
        // currentProxy() 获取当前对象的代理对象
    }
}
