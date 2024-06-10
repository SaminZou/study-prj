package com.samin;

import com.samin.spring.ApplicationContext;
import com.samin.study.AppConfig;
import com.samin.study.service.UserService;

/**
 * Bean 的生命周期
 * <p>
 * Description: Bean 的生命周期
 * <p>
 * Created By: Samin
 * <p>
 * Created Date: 2024-06-09
 */
public class Runner {

    public static void main(String[] args) {

        // 启动 Spring，扫描 -> 创建非懒加载单例 bean
        ApplicationContext context = new ApplicationContext(AppConfig.class);

        // 单例
        // System.out.println(context.getBean("userService"));
        // System.out.println(context.getBean("userService"));
        // System.out.println(context.getBean("userService"));

        UserService userService = (UserService) context.getBean("userService");
        userService.test();
    }
}