# 结构

当有很多功能的时候建议将 autoconfigure 和 starter 分开，如果功能单一的话可以把它合并到一起

> 如 springboot，所有的配置代码在 spring-boot-autoconfigure 中，starter 中无代码，使用 pom.xml 配置来聚合模块需要用到的包用于触发模块加载

# 两种方式让 starter 生效

## 被动

通过 SpringBoot 的 SPI 机制，在 META-INF 下新建一个 spring.factories，按规范指向类

## 主动

按规范编写一个注解类，在需要启动的类上面开启注解，use case 见 CustomLoadConfirm