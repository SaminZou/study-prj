```yaml
title: PostgreSQL 和 MySQL 中 schema 的区别
author: samin
date: 2021-05-07
```

# 背景

公司同事对于 schema 的概念比较模糊。
PostgreSQL、MySQL、SQL Server、Oracle 都有 schema（模式）的概念，并且在实际应用中体现的作用不大一样，这里重点分析 PostgreSQL 和 MySQL 的 schema。

# 区别

## PostgreSQL

schema 在同一个数据库中可以创建多个，每个 schema 可以拥有相同表名的表。

假设有 `a_schema` 和 `b_schema` ，里面都可以存在 `test_table` 这张表，并且在同一个数据库中可以通过语句查看到不同 schema 里面的数据。

```sql
select column1
from a_schema.test_table
union all
select column1
from b_schema.test_table;
```

## MySQL

与 PostgreSQL 不大一样，在 MySQL 的 database 和 schema 是同一个概念，所以在 MySQL中不会特别提及 schema。

MySQL 中，可以同时操作多个数据库（ 和 schema 的概念一致 ）

```sql
select column1
from a_database1.test_table
union all
select column1
from a_database2.test_table;
```

## 示意图

![PostgreSQL和MySQL中schema的区别](https://raw.githubusercontent.com/SaminZou/pic-repo/master/Database/PostgreSQL%E5%92%8CMySQL%E7%9A%84schema%E5%8C%BA%E5%88%AB.jpg)

# PostgreSQL 中 schema 的优势

1. 对于需要统一编码规则、排序规则等配置，可以做到配置一次，达到多个 schema 复用配置的效果，换成 MySQL 的 schema 来实现，则需要配置多次。
2. 可以减少 root 权限的使用，通过数据库的权限管理，让用户自己在特定数据库管理 schema。
3. 在一个数据库中可以通过不同的 schema 创建相同的表。