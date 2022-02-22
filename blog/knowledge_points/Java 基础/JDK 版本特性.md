```yaml
title: JDK版本特性 
author: samin
date: 2021-09-27
```

# JDK6

- Desktop类（它允许一个Java应用程序启动本地的另一个应用程序去处理URI或文件请求）

- 使用JAXB2来实现对象与XML之间的映射

- 轻量级 Http Server API

- 插入式注解处理API（lombok框架基于这个特性实现）

- STAX（是JDK6中一种处理XML文档的API）

# JDK7

- switch 支持String字符串类型

- try-with-resources，资源自动关闭

- 整数类型如（byte，short，int，long）能够用二进制来表示

- 数字常量支持下划线

- 泛型实例化类型自动推断,即”<>”；如：Map<String,String> map8 = new HashMap<>()

- 一个catch中捕获多个异常类型，用（|）分隔开

- 增强的文件系统

- Fork/join 框架

# JDK8

- lambada表达式

- 函数式接口

- 方法引用

- 接口类方法支持default和static方法
  - 如果一个类要实现两个接口，那么两个接口中不能有同名的default方法，要么重写，要么删除其中一个接口的方法；

  - 如果一个类继承和实现一个方法中，父类的一个普通方法和接口类的一个default方法同名，父类的普通方法会覆盖接口类的default方法；

  - default方法不能声明Object中有的方法，比如equals()

- Stream API

  - 流水线
  - 自动并行

> stream 流并没有原生 for 写法快，只是让程序更好理解罢了，具体的分析可以查阅 stream 的实现原理（ 实际应用也不用担心 stream 方式性能，并不比 for 慢多少 ）

- Optional

- Date Time API（如LocalDate）

- 重复注解

- Base64

- JVM的新特性（如元空间Metaspace代替持久代）

# JDK11

- 目录结构有所变化

- JShell工具——cmd输入jshell运行，新手学习Java更适合交互式编程语言

- 模块化，新增关键字：module、exports、requires

- 多版本兼容jar包，可以向上兼容以前jdk版本开发的jar包

- 接口类方法支持private修饰符

- 钻石操作符的升级，添加了匿名内部类的功能 后面添加了大括号{}可以做一些细节的操作，如：Map<String,String> map9 = new HashMap<>(){};

- 在java8的基础上进一步升级 直接在try括号中直接写入 变量就好，如果有**多个**流，就用分号隔开

- 特殊标识符增加限制，如：String _ = "test"; 在java以前可以用，java9开始用不了

- String底层存储结构更换，以前是char[]，现在变成了byte[]

- Stream API 新方法的添加

- 引入HttpClient，不需要通过maven添加HttpClient

