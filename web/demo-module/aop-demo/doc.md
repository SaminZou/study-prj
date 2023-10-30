# 1 声明切面配置类

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

```java
@Aspect
@Component
public class testAspect{

}
```

# 2 注入切入点编写方法

五种注解：@Befor、@AfterReturning、@After、@AfterThrowing、@Around

```java
/**
* 添加业务逻辑方法切入点
*/
@Pointcut("execution(* com.samin.aop.controller.*.insert*(..))")
public void operateServiceCall() {
}
```

# 3 对切面方法使用前后的一些处理

```java
@AfterReturning(value="operateServiceCall()", argNames="rtv", returning="rtv")
public void operateServiceCallAfter() throws Throwable{
}
```

**注：2、3步可以合成为**

```java
@AfterReturning(value="execution(* com.samin.project.*.service.insert*(..))", argNames="rtv", returning="rtv")
public void operateServiceCallAfter() throws Throwable{
}
```

# 4 五种注解的执行时刻

```java
try{
// 前置通知； @Before

// 环绕通知； @Around

// 返回通知，成功执行之后； @AfterReturning
}
catch (Exception e){
// 异常通知，抛出异常之后；@AfterThrowing
}
finally{
// 后置通知,方法执行之后； @After
}
```

# 5 pointcut expression

\* 匹配任意数量的字符
\+ 匹配置顶类及其子类
.. 一般用于匹配任意数的子包或参数
&& 与操作符
|| 或操作符
! 非操作符