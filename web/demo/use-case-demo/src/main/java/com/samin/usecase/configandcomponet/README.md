# 总结

@Configuration 中所有带 @Bean 注解的方法都会被动态代理，因此调用该方法返回的都是同一个实例

> 调用 @Configuration 类中的 @Bean 注解的方法，返回的是同一个示例；而调用 @Component 类中的 @Bean 注解的方法，返回的是一个新的实例

# 分析

```java
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Configuration {
    String value() default "";
}
```

从定义来看， @Configuration 注解本质上还是 @Component

# 测试

A 和 B 的声明方式和 C 和 D 的声明方式一样，注册 Bean 的分别使用了 @Configuration 和 @Component

结果可以运行代码查看