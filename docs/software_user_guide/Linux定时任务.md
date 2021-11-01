```yaml
title: Linux 定时任务
author: samin
date: 2021-11-01
```

# cron表达式

## 格式

秒 分 时 日 月 周 年(可选)

## 特殊字符

? : 表示不确定的值

, : 指定数个值

\- : 指定一个值的范围

/ : 指定一个值的增加幅度。n/m表示从n开始，每次增加m

L : 用在日表示一个月中的最后一天，用在周表示该月最后一个星期X

W : 指定离给定日期最近的工作日(周一到周五)

# : 表示该月第几个周X。6#3表示该月第3个周五

|字段名|允许的值|特殊字符支持|
|---|---|---|
|秒|0-59|, - * /|
|分|0-59|, - * /|
|小时|0-23|, - * /|
|日|1-31|, - * ? / L W C|
|月|1-12 or JAN-DEC|, - * /|
|周|1-7 or SUN-SAT|, - * ? / L C #|
|年(可选字段)|empty, 1970-2099|, - * /|

## 常用实例

每隔5秒执行一次 : */5 * * * * ?

每隔1分钟执行一次 : 0 */1 * * * ?

每天23点执行一次 : 0 0 23 * * ?

每天凌晨1点执行一次 : 0 0 1 * * ?

每月1号凌晨1点执行一次 : 0 0 1 1 * ?

每月最后一天23点执行一次 : 0 0 23 L * ?

每周星期天凌晨1点实行一次 : 0 0 1 ? * L

在26分、29分、33分执行一次 : 0 26,29,33 * * * ?

每天的0点、13点、18点、21点都执行一次 : 0 0 0,13,18,21 * * ?

# 步骤

## 查看定时任务

$ vi /etc/crontab

## 查看定时任务状态

$ systemctl status crond

## 每次添加了定时任务重启服务

$ systemctl restart crond

# 每日备份docker容器数据例子

1. 编写脚本

```shell
#!/bin/bash
BACKUP_TIME="MRO_"$(date +%Y%m%d%H%M%S)
docker exec -d mr-mongodb bash -c "mongodump --db dash5 --out /bitnami/${BACKUP_TIME}"
docker exec -d mr-postgresql bash -c "pg_dump -h 127.0.0.1 -U dasmr -d dasmr \
> /var/lib/postgresql/${BACKUP_TIME}"
# 记录日志
LOG_TIME="备份任务执行成功："$(date +%Y/%m/%d-%H:%M:%S)
echo $LOG_TIME >> /root/mrDataBackupLog.txt
```

2. 在配置中添加脚本为定时任务

```shell
SHELL=/bin/bash
PATH=/sbin:/bin:/usr/sbin:/usr/bin
MAILTO=root

# For details see man 4 crontabs

# Example of job definition:
# .---------------- minute (0 - 59)
# |  .------------- hour (0 - 23)
# |  |  .---------- day of month (1 - 31)
# |  |  |  .------- month (1 - 12) OR jan,feb,mar,apr ...
# |  |  |  |  .---- day of week (0 - 6) (Sunday=0 or 7) OR sun,mon,tue,wed,thu,fri,sat
# |  |  |  |  |
# *  *  *  *  * user-name  command to be executed
# 每天6、13、22点执行程序
0 6 * * * root /root/mr-data-backup.sh
0 13 * * * root /root/mr-data-backup.sh
0 22 * * * root /root/mr-data-backup.sh
```

> 本教程运行于 Centos7，不同系统可能有细节不同