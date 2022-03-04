```yaml
title: Redis 监控指标
author: samin
date: 2021-07-05
```

# 性能指标：Performance

| Name | Description |
| :---: | :---: |
| latencyRedis | 响应一个请求的时间 |
| instantaneous_ops_per_sec | 平均每秒处理请求总数 |
| hi rate(calculated) | 缓存命中率（计算出来的） |

# 内存指标: Memory

| Name | Description |
| :---: | :---: |
| used_memory | 已使用内存 |
| mem_fragmentation_ratio | 内存碎片率 |
| evicted_keys | 由于最大内存限制被移除的key的数量 |
| blocked_clients | 由于BLPOP,BRPOP,or BRPOPLPUSH而备阻塞的客户端 |

# 基本活动指标：Basic activity

| Name | Description |
| :---: | :---: |
| connected_clients | 客户端连接数 |
| connected_slave | slave数量 |
| master_last_io_seconds_ago | 最近一次主从交互之后的秒数 |
| keyspace | 数据库中的key值总数 |

# 持久性指标: Persistence

| Name | Description |
| :---: | :---: |
| rdb_last_save_time | 最后一次持久化保存磁盘的时间戳 |
| rdb_changes_sice_last_save | 自最后一次持久化以来数据库的更改数 |

# 错误指标：Error

| Name | Description |
| :---: | :---: |
| rejected_connections | 由于达到maxclient限制而被拒绝的连接数 |
| keyspace_misseskey | 值查找失败(没有命中)次数 |
| master_link_down_since_seconds | 主从断开的持续时间（以秒为单位) |

# 监控方式

- showlog

1.get：获取慢查询日志 2.len：获取慢查询日志条目数 3.reset：重置慢查询日志

> 相关配置：
>
> slowlog-log-slower-than 1000 # 设置慢查询的时间下线，单位：微秒
>
>slowlog-max-len 100 # 设置慢查询命令对应的日志显示长度，单位：命令数

- info

server:服务器运行的环境参数

clients:客户端相关信息

memory：服务器运行内存统计数据

persistence：持久化信息

stats：通用统计数据

Replication：主从复制相关信息

CPU：CPU使用情况

cluster：集群信息

Keypass：键值对统计数量信息

