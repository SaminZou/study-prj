```yaml
title: SpringBoot 项目搭建
author: samin
date: 2021-06-01
```

# 环境

- IDEA

- Maven

- Spring Initializr

# 步骤

## README.md

不管创建什么项目，都需要创建这个业内标准的基础说明文件，旨在简明扼要的阐述如下内容：

- 项目介绍

- 开发环境

- 技术栈
  
- 构建运行
  
- 领域模型
  
- 架构图
  
- FAQ

## 构建执行脚本

### 构建脚本

- 基于 maven / gradle 等版本管理工具的脚本命令

- 编写 build.sh（install.sh） 脚本

### 执行脚本

- 基于 maven / gradle 等版本管理工具的脚本命令

- 编写 run.sh 脚本

# 目录结构

使用 Maven 标准的文件结构即可

通用包：common / configuration / exception / loggin / utils

# 自动化测试

## 分类

- 单元测试

    src/test/java

- 集成测试

    src/componentTest/java

- API 测试

    src/apiTest/java

# 日志处理

# 异常处理

- 向客户端提供格式统一的异常返回
  
- 异常信息中应该包含足够多的上下文信息，最好是结构化的数据以便于客户端解析
  
- 不同类型的异常应该包含唯一标识，以便客户端精确识别

# 定时任务

# 分布式锁

# 消息队列

# 代码风格

编写 checkstyle.xml ，使用 CheckStyle 控制

# 静态代码检查

- Checkstyle
  
    代码格式、规范编码风格
  
- Spotbugs
  
    Findbugs 类型工具
  
- Dependency check
  
    OWASP提供的Java类库安全性检查
  
- Sonar
  
    用于代码持续改进的跟踪

# 健康检测

# API 文档

# 数据库管理

建议使用 Liquibase 管理数据库版本

# 多环境构建


- local

    本地启动，开发用

- dev

    一般用于接口联调

- test / qa

    用于测试

- uat / staging

    验收环境

- prod

    生产环境

# CORS

解决跨域问题。一般也可以不配置，通过部署手段解决。

# 常用工具包


- Guava
  
    Google 出品的常用类库
 
- Apache Commons
  
    Apache 出品的常用类库

- Mockito
  
    用于单元测试的mock

- DBUnit
  
    测试中管理数据库测试数据

- Rest Assured
  
    用于 Rest API 测试

- Jackson 2
  
    Json数据的序列化和反序列化

- jjwt
  
    Jwt token认证

- Lombok
  
    节省常用代码如 setter、getter、constructor 的编写

- Feign
  
    声明式 Rest 客户端

- Tika
  
    用于准确检测文件类型

- itext
  
    生成 pdf 文件

- poi

    Office 文件类型的操作类

- zxing
  
    生成二维码

- Xstream
  
    比 Jaxb 更轻量级的 XML 处理库

- hutool

    最近比较火比较全的工具包


