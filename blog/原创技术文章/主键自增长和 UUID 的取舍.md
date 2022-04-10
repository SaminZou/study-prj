```yaml
title: 主键自增长和 UUID 的取舍
author: samin_saminzou
date: 2021-03-14
```

# 前言

一般常见的主键值，会有三种设计方式，分别是自增长数值主键、雪花id（18位不连续的long类型数值）、uuid

MySQL 官方是推荐使用自增id

# 测试 DCL

```sql
-- 自增长主键
create table user_test_auto_key
(
    id        int UNSIGNED not null auto_increment,
    user_name varchar(64) default '',
    PRIMARY KEY (id)
)ENGINE=INNODB
```

```sql
-- 雪花id
create table user_test_random_key
(
    id        bigint(64) not null,
    user_name varchar(64) default '',
    PRIMARY KEY (id)
)ENGINE=INNODB
```

```sql
-- uuid
create table user_test_uuid_key
(
    id        varchar(36) not null,
    user_name varchar(64) default '',
    PRIMARY KEY (id)
)ENGINE=INNODB
```

# 性能比较

## 实验框架

spring boot + mybatis-plus + junit

## 实验结果

库表中数据量越多，往后插入大量数据的时间花费差距越大，总体性能从优开始排序为：

自增长 > 雪花id > uuid

# 自增长快的原因

1. 存储页的使用填充率大，页的浪费少

2. 新插入的数据减少了寻址的时间

3. 减少了页分裂和碎片的产生

# 自增长的缺点

1. 通过自增长id，别人容易分析你的数据情况

2. 对于高并发的负载，容易导致间隙锁的竞争

3. auto_increment锁机制会总成自增锁的抢夺，有一定的性能损失（需要调优 innodb_autoinc_lock_mode 配置项）

