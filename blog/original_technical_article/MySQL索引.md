```yaml
title: MySQL索引
author: samin
date: 2021-09-09 
```

# 索引分类
1. 普通索引
```sql
CREATE TABLE `table` (
   `id` int(11) NOT NULL AUTO_INCREMENT ,
   `title` char(255) CHARACTER NOT NULL ,
   `content` text CHARACTER NULL ,
   `time` int(10) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`),
   INDEX index_name (title) USING BTREE
)
```

2. 唯一索引  
   与前面的普通索引类似，不同的就是：索引列的值必须唯一，但允许有空值。如果是组合索引，则列值的组合必须唯一。
```sql
CREATE TABLE `table` (
   `id` int(11) NOT NULL AUTO_INCREMENT ,
   `title` char(255) CHARACTER NOT NULL ,
   `content` text CHARACTER NULL ,
   `time` int(10) NULL DEFAULT NULL ,
   UNIQUE indexName (title(length))
);
```

3. 主键索引
```sql
CREATE TABLE `table` (
   `id` int(11) NOT NULL AUTO_INCREMENT ,
   `title` char(255) NOT NULL ,
   PRIMARY KEY (`id`)
);
```

4. 组合索引   
   只有在查询条件中使用了创建索引时的第一个字段，索引才会被使用。使用组合索引时遵循最左前缀集合
```sql
ALTER TABLE `table` ADD INDEX name_city_age (name,city,age);
```  

5. 全文索引  
   主要用来查找文本中的关键字，而不是直接与索引中的值相比较。fulltext索引跟其它索引大不相同，它更像是一个搜索引擎，而不是简单的where语句的参数匹配。fulltext索引配合match against操作使用，而不是一般的where语句加like。它可以在create table，alter table ，create index使用，不过目前只有char、varchar，text 列上可以创建全文索引。值得一提的是，在数据量较大时候，现将数据放入一个没有全局索引的表中，然后再用CREATE index创建fulltext索引，要比先为一张表建立fulltext然后再将数据写入的速度快很多。
```sql
CREATE TABLE `table` (
   `id` int(11) NOT NULL AUTO_INCREMENT ,
   `title` char(255) CHARACTER NOT NULL ,
   `content` text CHARACTER NULL ,
   `time` int(10) NULL DEFAULT NULL ,
   PRIMARY KEY (`id`),
   FULLTEXT (content)
);
```


# 索引方式

MySQL目前主要有以下几种索引方式：FULLTEXT，HASH，BTREE，RTREE

1. FULLTEXT  
   目前只有MyISAM引擎支持

2. HASH

3. BTREE  
   - **Innodb里**，有两种形态：一是primary key形态，其leaf node里存放的是数据，而且不仅存放了索引键的数据，还存放了其他字段的数据。二是secondary index，其leaf node和普通的BTREE差不多，只是还存放了指向主键的信息

   - **MyISAM里**，主键和其他的并没有太大区别。不过和Innodb不太一样的地方是在MyISAM里，leaf node里存放的不是主键的信息，而是指向数据文件里的对应数据行的信息

4. RTREE  
   RTREE在MySQL很少使用，仅支持geometry数据类型，支持该类型的存储引擎只有MyISAM、BDb、InnoDb、NDb、Archive几种。

# 数据引擎

- MyISAM：默认的MySQL插件式存储引擎，它是在Web、数据仓储和其他应用环境下最常使用的存储引擎之一。注意，通过更改STORAGE_ENGINE配置变量，能够方便地更改MySQL服务器的默认存储引擎。

- InnoDB：用于事务处理应用程序，具有众多特性，包括ACID事务支持。(提供行级锁)

- BDB：可替代InnoDB的事务引擎，支持COMMIT、ROLLBACK和其他事务特性。

- Memory：将所有数据保存在RAM中，在需要快速查找引用和其他类似数据的环境下，可提供极快的访问。

- Merge：允许MySQL DBA或开发人员将一系列等同的MyISAM表以逻辑方式组合在一起，并作为1个对象引用它们。对于诸如数据仓储等VLDB环境十分适合。

- Archive：为大量很少引用的历史、归档、或安全审计信息的存储和检索提供了完美的解决方案。

- Federated：能够将多个分离的MySQL服务器链接起来，从多个物理服务器创建一个逻辑数据库。十分适合于分布式环境或数据集市环境。

- Cluster/NDB：MySQL的簇式数据库引擎，尤其适合于具有高性能查找要求的应用程序，这类查找需求还要求具有最高的正常工作时间和可用性。

- Other：其他存储引擎包括CSV（引用由逗号隔开的用作数据库表的文件），Blackhole（用于临时禁止对数据库的应用程序输入），以及Example引擎（可为快速创建定制的插件式存储引擎提供帮助）。