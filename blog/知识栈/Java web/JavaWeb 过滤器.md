```yaml
title: JavaWeb 过滤器
author: samin
date: 2021-10-20
```

# 过滤器和拦截器的区别

过滤器是基于 Servlet，拦截器是基于 Spring 框架的

# Spring 如何注入 Filter

1. web.xml 配置

2. @WebFilter（不是Spring的注解） 和 @Bean 注解

3. FilterRegisterBean（基于 SpringBoot） 和 Filter（Servlet） （推荐使用）

FilterRegisterBean 通过实现 ServletContextInitializer 来打通 SpringBoot 和 Servlet 的关联

# Filter 中的设计模式

> 责任链模式

FilterChain 中的 FilterConfig 数组维护所有Filter
