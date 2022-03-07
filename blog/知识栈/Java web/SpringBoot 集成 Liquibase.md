```yaml
title: SpringBoot 集成 Liquibase 
author: samin
date: 2021-04-20
```

# 是什么

## 官网

https://www.liquibase.org/

## 官网描述

Track, version, and deploy database changes

## 特性

1. Flexible database change

   Easily define changes in **SQL, XML, JSON, or YAML.**

2. Version control for your database

   **Order changes** and **standardize** development.

3. Built for developers

   Control when, where, and how database changes are deployed.

4. Rollbacks

   Undo database changes, either automatically or via custom rollback SQL.

# 解决问题

1. 顺序执行更改SQL（可编排）
2. 可根据数据库版本进行回滚、升级

对数据库可以进行管理

# 入门知识点

## changeset

常用的操作标签，记录数据库发生的变化

## changelog

对 changeset 进行编排

## preconditions

前置条件，达到才会继续数据库的升级

## context

用于控制更改发布在不同的环境

# 使用规范

1. 使用 Liquibase 先更新开发环境，不能直接用 DDL 修改数据库
2. 使用 Liquibase 升级测试环境数据库版本
3. 使用 Liquibase 发布正式环境的数据库版本

# Datical

基于 Liquibase 商业版工具，有 web 管理界面，提供了更多的遍历特性
支持 DDL 转换为 changeSet

# 集成方式

## 使用 Liquibase 提供的命令行

官网下载安装包，解压配置环境变量后，使用命令行进行操作

## Maven 插件

liquibase-maven-plugin

## SpringBoot 方式集成

# SpringBoot 集成 Liquibase

## 先决条件

SpringBoot 项目  
Maven 包管理工具

## 添加包引用

```xml
<dependency>
   <groupId>org.liquibase</groupId>
   <artifactId>liquibase-core</artifactId>
   <version>3.10.3</version>
</dependency>
```

## 目录结构

```
sql  
├── rollback
│   └── init-rollback.sql
├── update
│   └── init.sql
└── init.xml 
liquibase-chage-master.xml
``` 

## application 配置

```yaml
# spring.datasource 配置数据源
# 配置 Liquibase 的主入口
spring:
  liquibase:
    # 是否启用（on/off，true/false）
    enabled: true
    change-log: classpath:liquibase-chage-master.xml
```

## 增加 changeset

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <changeSet id="init-1" author="samin">
        <!-- relativeToChangelogFile 表示开启相对路径查找 update/init.sql -->
        <sqlFile path="update/init.sql" relativeToChangelogFile="true"/>
        <rollback>
            <sqlFile path="rollback/init-rollback.sql" relativeToChangelogFile="true"/>
        </rollback>
    </changeSet>
</databaseChangeLog>
```

> sqlFile 是其中一种管理数据库相关的方式，还可以使用 Liquibase 的 DSL 来编写。
> 使用 DSL 编写的好处是可以做到适配每一种数据库。

## 增加入口文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<databaseChangeLog xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
                   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                   xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

    <!-- 加入到入口，表示增加改集合 sql -->
    <include file="sql/init.xml" relativeToChangelogFile="true"/>
    <!-- 文件多可以用这种方式 -->
    <!-- <includeAll file="sql" relativeToChangelogFile="true"/> -->
</databaseChangeLog>
```

> 内部的 changeset 无法保证执行顺序，所以推荐每个 xml 只执行一个 sql 在入口文件处 通过 <include /> 标签控制执行顺序

## 开启项目，数据库中会新增两张控制表

- databasechangelog

追踪 chageset 的执行情况

- databasechangeloglock

确保一次只运行一个Liquibase实例

# [回滚操作](https://segmentfault.com/a/1190000021208813)

# 迁移问题

如果是新项目，直接忽略此章节

## Liquibase 提供的 generateChangeLog 命令

```shell
liquibase --driver=com.mysql.jdbc.Driver \
--classpath=./mysql-connector-java-5.1.29.jar \
--changeLogFile=liquibase/db.changelog.xml \
--url="jdbc:mysql://127.0.0.1:3306/test" \
--username=root \
--password=yourpass \
generateChangeLog
```

## 用数据库工具生成 sql 文件

建议使用这种方式，因为 generateChangeLog 命令不支持存储过程、函数、触发器
