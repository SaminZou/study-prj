```yaml
title: MySQL 小表驱动大表
author: samin
date: 2023-12-20
```

# 是什么

用小的数据集驱动大的数据集

# 原理

假设 A 有 20 条数据，B 有 20 万条数据，需要进行联表操作，就会产生驱动选择问题

- 比较操作都是 20 * 20 万次，但是结合优化查询策略，选择小表驱动大表
- 小表驱动大表，大表索引生效，查询时间为 O(LogN)，大表全表扫描磁盘块查询速度快

```sql
-- join_buffer_size 的大小影响 join 语句的执行性能
show variables like '%buffer%';
```

> 驱动表有索引不会使用到索引，被驱动表会使用到索引

结论：驱动表的选择原则为数据集尽量小

## 小表驱动大表

```java
for ( A 20 条数据 ){
    for ( B 20 万条数据 ){
    }   
}
```
外层循环 20 次

## 大表驱动小表

```java
for ( B 20 万条数据 ){
    for ( A 20 条数据 ){
    }   
}
```

外层循环 20 万次

小表驱动大表的主要目的是通过减少表连接创建的次数，加快查询速度

# 判断驱动表与非驱动表

1. LEFT JOIN 左连接，左边为驱动表，右边为被驱动表
2. RIGHT JOIN 右连接，右边为驱动表，左边为被驱动表
3. INNER JOIN 内连接，MySQL 会选择数据量比较小的表作为驱动表，大表作为被驱动表
4. 可通过 EXPLANIN 查看 SQL 语句的执行计划，EXPLANIN 分析的第一行的表即是驱动表

# exists 和 in 的使用场景

场景：从 A 中查询 B 表中有关联的数据

结论：

- B 表的数据较小用 in

```sql
select * from A where id in(select id from B)
```

- A 表的数据较小，用 Exists

```sql
select *
from A
where exists(select 1 from b where b.id = a.id)
```