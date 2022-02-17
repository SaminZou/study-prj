```yaml
title: PostgreSQL 常用指令
author: samin
date: 2021-11-23
```

# 基本概念

## 表空间

一个表空间可以有多个数据库

## 数据库

默认含public模式

## 模式

模式（schema）是对数据库（database）逻辑分割
正常情况给不同用户不同的模式，从而进行权限管理

## 用户/角色

以下命令都可以创建用户：

$ CREATE ROLE samin PASSWORD '123';

$ CREATE USER samin PASSWORD '123';

> 区别：角色是没有登录权限的用户，需要加上 LOGIN

$ CREATE ROLE samin PASSWORD '123' LOGIN;

> CREATE ROLE 和 CREATE USER 的唯一区别是创建的用户是否用登录权限

# 基本命令行使用

## ubuntu环境下新建用户和数据库

\# 进入客户端

$ psql -U <dbUserName> -d <dbName> -h 127.0.0.1 -p 5432

\# 创建用户设置密码

$ create user tech with password 'techdata';

\# 创建数据库

$ create database techdata owner tech;

\# 赋权限

$ grant all privileges on database techdata to tech;

\# 增加用户权限

$ alter user tech createdb createrole;

\# 删除数据库

$ drop database techdata;

\# 删除用户

$ drop user tech;

\# 退出shell

$ \q

## 创建只读用户

\# 创建账号

$ CREATE USER readonlyuser WITH ENCRYPTED PASSWORD '123456';

\# 更新用户默认为只读事务

$ alter user readonlyuser set default_transaction_read_only=on;

\# 所有库的 public 的 USAGE 权限给到只读用户

$ GRANT USAGE ON SCHEMA public to readonlyuser;

\# 授予select权限

$ grant select on all tables in schema public to readonlyuser;

> 要进入到具体数据库操作在哪个db环境下执行就授予那个db的权限

# 主从服务器配置

\# 添加角色用于拷贝数据至从数据库

$ create role dascp login replication encrypted password 'dascp'

\# 修改主服务器pg_hba.conf文件，重启

```properties
host   all all  172.17.0.10/32   trust   # 运行70服务器连接到本机
host replication dascp 172.17.0.10/32   md5   # 运行guoxm用户在70上复制本机数据
```

\# 配置主服务器postgresql.conf文件，重启

```properties
synchronous_standby_names = '*'
listen_addresses = '*'   # 监听所有ip
archive_mode = on   # 开启归档模式
archive_command = 'cp %p /var/lib/postgresql/10/main/%f'   # 归档命令
wal_level = hot_standby    # 热备模式
max_wal_senders = 1   # 最多有1个流复制连接
wal_sender_timeout = 60s    # 流复制超时时间
max_connections = 100   # 最大连接时间，必须要小于从库的配置
```

\# 登录从服务器，测试是否能连同主服务器

$ psql -h 192.168.100.70  -U  postgres

\# 清空从服务器的main文件夹

$ sudo rm -rf 10/main/*

\# 开始基础备份，从主服务器上同步数据

$ pg_basebackup -F p --progress -D 10/main/ -h 172.17.0.10 -p 5432 -U dascp --password

\# 编辑recovery.conf用于从库恢复从主库获取的数据，保存在从数据库的main文件夹中

```properties
standby_mode = on   #表示该节点是从库
primary_conninfo = 'host=172.17.0.10 post=5432 user=dascp password=dascp' #从机信息和连接用户
recovery_target_timelint = 'latest' #说明恢复到最新状态
```

\# 编辑从数据库的postgresql.conf文件，重启

```properties
wal_level = hot_standby    #热备模式
max_connections = 300   #最大连接时间，必须要小于从库的配置
hot_standby = on #说明这台机器不仅用于数据归档，还可以用于数据查询
max_standby_streaming_delay = 30s #流备份的最大延迟时间
wal_receiver_status_interval = 10s  #向主机汇报本机状态的间隔时间
hot_standby_feedback = on #r出现错误复制，向主机反馈
```

\# 验证是否能够异步同步数据库信息，登录主数据库查看是否有记录
$ select client_addr,sync_state from pg_stat_replication;

# psql 导入 pg_dump 导出的文件

\# 说明
--file : 备份文件所在路径
--username : 数据库用户名
--host : 数据库ip
--port : 数据库端口

\# jinyu 数据库导入指令，最后一个参数为数据库名称
$ psql --file=/home/test/jinyu-test-20220217 --username=postgres --host=127.0.0.1 --port=5432 jinyu

# 一些TIPS

- postgresql本身是大小写不敏感的，如果要设置大写字段，在建表的时候带双引号，可是这样增加开发难度，需要把语句里面字段都加引号，所以应该尽量避免双引号

- 备份恢复：
  $ pg_dump -h 172.168.10.249 -U daship -d daship> /root/backup20190703.bak
  $ psql -h localhost -U daship -d daship< /root/backup20190703.bak

- 备份的时候排除某些表
  $ pg_dump -h 172.168.10.249 -U daship -d daship
  --exclude-table=cms_contents --exclude-table=cms_contents_id_seq > /root/backup20190703.bak

- 永久修改时区
  修改 `postgresql.conf` ，重启服务器

```properties
log_timezone = 'PRC'
timezone = 'PRC'
```

- postgres创建表有两个默认模板，template1和template0，建库默认用的是template1模板

# 常用 SQL

## 创建表

```sql
CREATE TABLE 表名称 (
列名1 数据类型,
列名2 数据类型,
列名3 数据类型,
....
);

-- use case
CREATE TABLE user (
id integer,
user_name varchar(255),
email varchar(255),
age integer,
address varchar(255)
)

-- CONSTRAINT
-- 各种约束的使用示例
CREATE TABLE user (
id integer NOT NULL PRIMARY KEY,
corp_id integer REFERENCES corp (id),
user_name text UNIQUE,
age numeric CHECK (age > 0)
);
```

## 计算平均时间
```sql
SELECT AVG
    ( cost_time )
FROM
    (
    SELECT
        pu.response_time - de.delivery_time cost_time
    FROM
        das_delivery de
        LEFT JOIN das_push pu ON de.delivery_id = pu.delivery_id
    WHERE
        de.delivery_time BETWEEN '2019-09-17 00:00:00'
    AND '2019-09-17 23:59:59'
    ) temp_result;
```

## 修改表

ALTER TABLE 用来添加，删除或修改现有表中的列，也可以用来添加和删除现有表上的各种制约因素

```sql
-- 现有表中添加一个新的列
ALTER TABLE table ADD column datatype;

-- 现有表中删除一个新的列
ALTER TABLE table DROP COLUMN column;

-- 现有表中更改数据类型的列
ALTER TABLE table MODIFY COLUMN column datatype;

-- 现有表中一列添加NOT NULL约束
ALTER TABLE table MODIFY column datatype NOT NULL;

-- 现有表中添加唯一约束
ALTER TABLE table ADD CONSTRAINT MyUniqueConstraint UNIQUE(column1, column2...);

-- 现有表中添加CHECK约束
ALTER TABLE table ADD CONSTRAINT MyUniqueConstraint CHECK (CONDITION);

-- 现有表中添加PRIMARY KEY约束
ALTER TABLE table ADD CONSTRAINT MyPrimaryKey PRIMARY KEY (column1, column2...);

-- 现有表中删除约束
ALTER TABLE table DROP CONSTRAINT MyUniqueConstraint;

-- 现有表中删除主键
ALTER TABLE table DROP CONSTRAINT MyPrimaryKey;

-- DROP TABLE语句是用来删除表定义及其所有相关的数据表的索引，规则，触发器和约束
DROP TABLE table;

-- TRUNCATE TABLE命令用于从现有的表删除完整的数据。在每个表上的DELETE（删除）具有相同的效果，但是，因为它没有实际扫描的表，它的速度快
TRUNCATE TABLE table;
```

```sql
-- 支持最大连接

$ show max_connections;

-- 为管理员保留连接数

$ show superuser_reserved_connections;
-- 把数据库中的所有的空闲会话全部kill掉

$ select pg_terminate_backend(pid) from pg_stat_activity where pid<>pg_backend_pid() and state='idle';

-- 查看当前连接情况

$ select datname,pid,application_name,state from pg_stat_activity;

-- 查看当前连接数

$ select count(1) from pg_stat_activity;
-- 查看数据库剩余连接数

$ select max_conn-now_conn as resi_conn from (select setting::int8 as max_conn,(select count(*) from pg_stat_activity) as now_conn from pg_settings where name = 'max_connections') t;

-- 修改最大连接数（需要重启） shell指令

$ alter system set max_connections= 300;

-- 列出所有模式

$ select schemaname from pg_tables where schemaname not like 'pg%' and schemaname   != 'information_schema' group by schemaname;

-- 列出表名

$ select tablename from pg_tables;

-- 列出无模式隔离的所有表

$ select tablename from pg_tables where schemaname='public';

-- 列出所有数据库

$ select datname from pg_database;

-- 查看数据库时区

$ show timezone;

-- 清空 public 模式，重建

DROP SCHEMA public CASCADE;
CREATE SCHEMA public;

-- 查看下一个自增主键
SELECT NEXTVAL('message_config_id_seq');

-- 重置自增主键，一般 truncate 一个有自增主键的表会导致这个问题
SELECT SETVAL('message_config_id_seq', MAX(id)) FROM message_config;
```

# 性能优化

-- 查看执行计划

$ EXPLAIN ANALYZE [SQL]

## 连接池数量计算公式

连接数 = ( (核心数 * 2) + 有效磁盘数 )

## 保留关键字

https://www.postgresql.org/docs/9.5/sql-keywords-appendix.html

# 数据类型

## 声明格式

numeric(precision, scale)

## 说明

一个numeric类型的标度 (scale)是小数部分的位数，精度(precision) 是全部数据位的数目，也就是小数点两边的位数总和。 因此数字 23.5141 的精度为 6 而标度为 4 。你可以认为整数的标度为零

## 特别注意

- numeric(precision) 没有声明小数部分，只能输入整数

- 整数可以输入位数等于 `precision - scale`

- 如果关心移植性， 那你最好总是明确声明精度和标度

# 函数

```sql
CREATE OR REPLACE FUNCTION visitTrend(varchar, varchar)
    RETURNS TABLE
            (
                visit_date varchar,
                visit_sum  bigint
            )
    LANGUAGE sql
AS
$function$
select to_char(create_time, 'yyyy-mm-dd') visit_date, count(visitor_sysno) visit_sum
from gateway_visitor_record
where create_time >= to_date($1, 'yyyy-mm-dd')
  and create_time <= to_date($2, 'yyyy-mm-dd')
group by to_char(create_time, 'yyyy-mm-dd');
$function$;
```

```sql
CREATE OR REPLACE FUNCTION visitTrend(startTime varchar,endTime  varchar)
    RETURNS TABLE
            (
                visit_date varchar,
                visit_sum  bigint
            )
    LANGUAGE sql
AS
$function$
select to_char(create_time, 'yyyy-mm-dd') visit_date, count(visitor_sysno) visit_sum
from gateway_visitor_record
where create_time >= to_date(startTime, 'yyyy-mm-dd')
  and create_time <= to_date(endTime, 'yyyy-mm-dd')
group by to_char(create_time, 'yyyy-mm-dd');
$function$;
```
```sql
CREATE OR REPLACE FUNCTION visitTrend(varchar, varchar)
    RETURNS SETOF visitor_temp
    LANGUAGE sql
AS
$function$
select to_char(create_time, 'yyyy-mm-dd') visit_date, count(visitor_sysno) visit_sum
from gateway_visitor_record
where create_time >= to_date($1, 'yyyy-mm-dd')
  and create_time <= to_date($2, 'yyyy-mm-dd')
group by to_char(create_time, 'yyyy-mm-dd');
$function$;
```

# 函数的稳定性


- volatile

- stable

- immutable

> stable 和 immutable 类型只能执行查询方法

# 查看数据库的存储情况

select pg_size_pretty(pg_database_size('数据库名'));
