```yaml
title: Redis CPU 占用 100% 过高问题排查
author: samin
date: 2022-02-22
```

# 排查思路

1. 检查最近一次提交的代码业务逻辑编写

2. Redis 所在服务器的运行情况

   - redis 连接数

   - 数据持久化是否阻塞

   - 主从同步情况

   - value 值是否过大

   - redis 慢查询

# TIPS

1. aof 操作非常吃 CPU 资源

2. Redis CPU 使用率超过 95% 需要开始关注

3. 没有及时清理无用的 Key，需要选择合理的缓存清理策略

4. 影响 Redis 服务器性能的操作指令

   - keys：键值枚举，可能会导致 Redis 服务器阻塞

   - smembes：当set中有大量数据时，会导致服务器阻塞。正确做法是先确认set对象中的数据量，取部分数据

   - zrange key 0 -1：当zset中有大量数据时，该操作有阻塞风险

   - hgetall：当Hash表中有大量数据，该操作有阻塞风险

   - lrange key 0 -1：当List列表中有大量数据，该操作有阻塞风险

# 常用指令

\# 查看当前连接数

$ info clients

\# 查看配置的最大连接数

$ CONFIG GET maxclients

\# 查看连接主机信息

$ CLIENT LIST

\# 导出服务器的主机连接信息

$ redis-cli -h 127.0.0.1 CLIENT LIST > connection.log

\# 查看耗时操作

$ INFO commandstats