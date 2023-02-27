# 两种方式让 starter 生效

## 被动

通过 SpringBoot 的 SPI 机制，在 META-INF 下新建一个 spring.factories，按规范指向类

## 主动

按规范编写一个注解类，在需要启动的类上面开启注解，use case 见 CustomLoadCofirm