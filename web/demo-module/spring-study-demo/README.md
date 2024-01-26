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

# Spring Bean 创建的生命周期

answer5

1. 推断构造方法（多个构造函数的情况下，优先无参构造、其次是使用 @Autowired 注定的构造方法）
2. 实例化
3. 填充属性，依赖注入
4. 处理 Aware 回调（实现 BeanFactoryAware、BeanNameAware 等接口实现）
5. 处理 @PostConstruct 注解
6. 处理 InitializingBean 接口
7. 初始化后进行 AOP

# Spring 的 Bean 是否线程安全

# ApplicationContext 和 BeanFactory 有什么区别

# Spring 容器启动流程

# @SpringBootApplication 注解的作用

# spring.factories 文件的作用

# SpringBoot 中的自动配置

# SpringBoot 启动做了什么事情

# Spring MVC 请求处理的流程