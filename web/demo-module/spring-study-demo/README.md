# 单例 Bean 是单例模式吗

answer1

单例模式的实现方式：饿汉模式、懒汉模式、双重检测、静态内部类、枚举、线程唯一的单例

Bean 单例模式的范围只针对相同服务类的实例是单例

# Bean 的实例化和 Bean 的初始化有什么区别

answer2

区分 JVM 的类加载（加载、链接（验证、准备、解析）、初始化）

Bean 的实例化为方法 createBeanInstance(beanName, mbd, args)  
Bean 的初始化为方法 initializeBean(beanName, bean ...) 实际为实现 InitializingBean（对应 xml 配置的 init-method）

# Spring AOP 是如何实现的？和 AspectJ 有什么区别

AOP 是一种编程思想（面向切面编程），实现方式有：AspectJ（编译期）、Spring AOP（动态代理，使用的是 AspectJ 定义的注解，所以有引入
AspectJ 包）、aspectwerkz、JBoss 4.0

编译期和动态代理实现主要区别是，编译期相当于在编译时往每个需要执行切面代码的文件中织入对应的代码，动态代理则是在运行期由一个对象管理执行对应代码

# Spring 中的事务是如何实现的

answer3

原理：基于数据库事务和 AOP 机制

1. 使用 @Transactional 注解
2. 利用事务管理器创建一个数据库连接
3. 修改数据库连接的 autocommit 属性为 false，禁止此连接的自动提交（核心步骤）
4. 执行完所有 SQL，没有异常则提交事务
5. Spring 的事务隔离级别对应数据库的隔离级别
6. Spring 事务传播机制的运作

# Spring 的事务传播机制

answer4

- REQUIRED 如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中
- SUPPORTS 使用当前事务，如果当前没有事务，就以非事务方式执行
- MANDATORY 使用当前的事务，如果当前没有事务，就抛出异常
- REQUIRES_NEW 新建事务，如果当前存在事务，把当前事务挂起
- NOT_SUPPORTED 以非事务方式执行操作，如果当前存在事务，就把当前事务挂起
- NEVER 以非事务方式执行，如果当前存在事务，则抛出异常
- NESTED 如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与 REQUIRED 类似的操作

# Spring 事务失效的情况

- 异常捕获异常
- 抛出检查异常（rollbackFor 设置对应的异常）
- 非 public 方法

# Spring Bean 创建的生命周期

answer5

1. 推断构造方法（多个构造函数的情况下，优先无参构造、其次是使用 @Autowired 注定的构造方法）
2. 实例化
3. 填充属性，依赖注入
4. 处理 Aware 回调（实现 BeanFactoryAware、BeanNameAware 等接口实现）
5. 处理 @PostConstruct 注解
6. 处理 InitializingBean 接口
7. 初始化后进行 AOP

# Spring 也适配继承

answer 6

# Spring @Autowired 注解 required = false，即使没有声明依赖 bean，启动也不会报错

answer 7

# Spring # 表达式

answer 8

# Spring Bean 加载顺序和循环依赖的关系

Spring Bean 默认会按照字母顺序加载 Bean，所以当有 AService 和 BService 循环依赖的时候，AService 使用 set 注入，BService
使用构造器方式可以成功处理循环依赖问题，反之报错。

answer 9

# 循环依赖与原型 Bean

answer 10

# Spring Bean 的声明方式

answer 11

- @Component 等注解
- 实现 FactoryBean
- 实现 BeanFactoryPostProcessor 接口，使用 beanFactory.registerSingleton(beanName, class);
- @Configuration 中配置 @Bean

# @Value 在构造器中无效，需要在实体类声明后才能使用赋值

answer 12

# Spring 的 Bean 是否线程安全

需要看 Bean 本身

1. 如果 Bean 是无状态的，那么 Bean 是线程安全的
2. 如果 Bean 是有状态的，那么 Bean 不是是线程安全的

# ApplicationContext 和 BeanFactory 有什么区别

- BeanFactory 是 Spring 的核心组件，生成维护 Bean
- ApplicationContext 实现了除 BeanFactory 之外还实现了诸如 EnvironmentCapable, ListableBeanFactory,
  HierarchicalBeanFactory,
  MessageSource, ApplicationEventPublisher, ResourcePatternResolver 等接口，功能更多

# Spring 容器启动流程

1. 扫描所有 BeanDefinition 对象，存放在一个 Map 中
2. 筛选出非懒加载的单例 BeanDefinition 进行创建 Bean，对于多例 Bean 不需要在启动过程中创建，多例 Bean 会在每次获取 Bean
   时利用 BeanDefinition 去创建
3. 执行 Spring Bean 创建的生命周期
4. 单例 Bean 创建完后，发布一个容器启动事件
5. Spring 启动结束

# SpringBoot 启动做了什么事情

1. 判断应用类型，是否 web 应用，servlet 应用或是 webflux 应用，根据类型创建对应的 Spring 容器
2. 创建 Spring 容器
3. 解析启动类，扫描、导入配置类并解析
4. 启动 Tomcat、Jetty 或 Undertow
5. 调用 ApplicationRunner 或 CommandLineRunner

> 调用 SpringApplicationRunListeners、Banner 打印、执行 ApplicationContextInitializer 等

# @SpringBootApplication 注解的作用

- @SpringBootApplication 是一个复合注解（主要是 @SpringBootConfiguration、@EnableAutoConfiguration、@ComponentScan）
- 根据常用的使用习惯的一个复合配置类
- 语法糖

# spring.factories 文件的作用

- SPI 机制的体现

# SpringBoot 中的自动配置

# Spring MVC 请求处理的流程

1. 启动 Tomcat 过程中，创建 DispatcherServlet 对象，执行初始化逻辑
2. DispatcherServlet 初始化过程中创建 Spring 容器
3. 初始化 HandlerMapping、HandlerAdapter 等
4. SpringMVC 提供了好几个 HandlerMapping，其中有一个叫 RequestMappingHandlerMapping
5. RequestMappingHandlerMapping 加载 Spring 容器中加了 @RequestMapping 的方法
6. 把 path 做为 key，method 做为 value 存到一个 map 中
7. DispatcherServlet 接收到请求后，RequestMappingHandlerMapping 负责根据请求路径从 map 中找到对应的方法
8. 根据 @RequestParam 或 @RequestBody 等解析映射参数
9. 执行方法
10. 获取返回值进行封装
11. 如果方法加了 @ResponseBody，那么会直接把返回值返回给浏览器
12. 如果方法没有加 @ResponseBody，那么会根据返回值找到对应的页面，在服务端进行渲染，再把渲染结果返回给浏览器