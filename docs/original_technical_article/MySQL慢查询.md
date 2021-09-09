```yaml
title: MySQL慢查询
author: samin
date: 2021-09-09 
```

# 查看相关参数

-- 查看慢查询开启情况，以及存储路径

mysql > show variables like 'slow_query%';

-- 查看慢查询的触发时间，单位秒

mysql > show variables like 'long_query_time';

# 配置方法

## 全局变量设置（数据库重启失效）

-- 开启慢查询

mysql > set global slow_query_log='ON';

-- 设置存放路径

mysq l> set global slow_query_log_file='/usr/local/mysql/data/slow.log';

-- 设置触发时间，单位秒

mysql> set global long_query_time=5;

## 修改配置文件（永久生效）

-- 修改my.cnf，加入如下内容：

```
[mysqld]
slow_query_log = ON
slow_query_log_file = /usr/local/mysql/data/slow.log
long_query_time = 5
```

# 测试

-- 创建一条慢查询，注意以下语句的遍历条件必须查出有记录，sleep函数才会生效

mysql > select *,sleep(10) from \`tableName\` where id = '1';

语句将会被记录到log日志文件中