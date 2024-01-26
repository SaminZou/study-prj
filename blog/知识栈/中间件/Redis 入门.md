```yaml
title: Redis 入门
author: samin
date: 2022-01-19
```

# 为什么使用 Redis

- 性能

  变化不频繁的数据可以放在redis进行缓存

  题外话：超过7秒的操作应该添加中止操作的选项

- 并发

  大并发情况下，不直接访问数据库，先访问redis缓冲再进行数据库访问

# Redis 快的原因

- 基于内存（需要预估使用大小）

  - 速度快
  - 高吞吐、低延迟
  - 代码实现（不需要 IO 流）

- 单线程与 IO 多路复用

  - 线程上下文切换
  - 性能开销（锁解决数据一致性问题）
  - 复杂度

- 内部数据结构

  - String（简单动态字符串）
  - List（双向链表、压缩列表）
  - Hash（压缩列表、哈希表）
  - Sorted Set（压缩列表、跳表）
  - Set（哈希表、整数数组）

> 瓶颈不在 CPU，而是内存、网络 IO

# 使用 Redis的缺点

- 缓存和数据库双写一致性问题

- 缓存雪崩问题

- 缓存击穿问题

- 缓存穿透问题

- 缓存的并发竞争问题

# 单线程的 Redis 为什么这么快

- 纯内存操作

- 单线程操作，避免了频繁的上下文切换，无法发挥CPU多核优势

- 采用了非阻塞 I/O 多路复用机制（epoll）

# Redis 的数据类型，以及每种数据类型的使用场景

## String

### 底层实现

SDS（Simple Dynamic String，简单动态字符串）是Redis底层所使用的字符串表示，它被用到几乎所有的Redis模块中。

- 优化追加操作，减少了内存重分配次数

- O(1) 算法复杂度获取字符串长度

```
struct sdshdr {
    // buf 已占用长度
    int len;
    // buf 剩余可用长度
    int free;
    // 实际保存字符串数据
    char buf[];
}
```

### 常用指令

set/get操作

```
> SET citystring BEIJING
> GET citystring
```

## Hash

    一般做单点登录，用这种数据结构存储用户信息，以cookiedId为key，设置30分钟超时

HSET / HGET：一次只能操作一对键值对

HMSET / HMGET：一次可以操作多对键值对

```
> HSET testmap A 10
> HGET testmap A

>HMSET testmap A 10 B 20 C 30
>HGET testmap A C

> HGETALL testmap

> HDEL testmap B

> DEL testmap
```

## List

### 底层实现

双端链表

> redis使用压缩列表存储数据，在使用的时候转换为对应的数据结构使用

Redis内部模块常用到双端链表：
- 事务模块使用双端链表来按顺序保存输入的命令
- 服务器模块使用双端链表保存多个客户端
- 订阅/发送模块使用双端链表保存订阅模式的多个客户端
- 事务模块使用双端链表来保存时间事件（time event）

```
typedef struct listNode{
    // 前驱节点
    struct listNode *prev;
    // 后驱节点
    struct listNode *next;
    // 值
    void *value;
} listNode;

typedef struct list {
    // 表头指针
    listNode *head;
    // 表尾指针
    listNode *tail;
    // 节点数量
    unsigned long len;
    // 复制函数
    void *(*dup)(void *ptr);
    // 释放函数
    void (*free)(void *ptr);
    // 比对函数
    int (*match)(void *ptr,void *key);
} list;
```

### 应用场景

- 简单的消息队列功能

### 常用指令

```
> LPUSH citylist BEIJING SHANGHAI
> RPUSH citylist SHENZHEN GUANGZHOU
> LRANGE citylist 0 10
# 获取长度
> LLEN citylsit
```

## Set

集群部署中用作全局去重

```
> SADD cityset SHENZHEN GUANGZHOU
> SADD cityset SHENZHEN GUANGZHOU
> SMEMBERS cityset
# 获取成员数
> SCARD cityset
# 判断是否是集合成员
> SISMEMBER cityset SHENZHEN
```

## Sorted Set

有Score权重参数，集合中的元素安装Score排序，可以用来做排行榜、延时任务、范围查找

```
> ZADD cityzset 1 BEIJING
> ZADD cityzset 2 SHANGHAI
> ZADD cityzset 3 GUANGZHOU
# 获取成员数
> ZCARD cityzset
# 计算区间内的成员
> ZCOUNT cityzset 1 2
# 增加计数，键为"BEIJING"的值加上5
> ZINCRBY cityzset 5 BEIJING
# 返回分数区间的数据
> ZRANGE cityzset 0 100
```

# 设置和查询超时时间

\# 设置超时时间（单位秒）

$ EXPIRE testkey 10

\# 查询超时时间

$ TTL testkey

# 常用指令

\# 获取所有键值

$ keys *

\# 查看键值类型

$ type [key]

# Redis 的过期策略以及内存淘汰机制

默认采用**定期删除+惰性删除策略**（不能做定时删除是因为太消耗CPU资源）

定期删除默认是每100ms随机抽取是否有过期的key，并不是全部检查一次，所以需要配合惰性删除，惰性删除就是在你请求key的时候检查一次是否过期，过期则删除。

所以如果长时间没随机删除过期数据，也没请求触发，会导致内存越来越高，需要引入**淘汰策略**

> config get maxmemory-policy 查询淘汰策略，默认是 "noeviction"，表示当运行内存超过最大设置内存时，不淘汰任何数据，但新增操作会报错

```
# redis.conf
# maxmemory-policy volatile-lru
```

- noeviction：当内存不足以容纳新写入数据时，新写入操作会报错。应该没人用吧
- allkeys-lru：当内存不足以容纳新写入数据时，在键空间中，移除最近最少使用的 Key。推荐使用，目前项目在用这种
- allkeys-random：当内存不足以容纳新写入数据时，在键空间中，随机移除某个 Key。应该也没人用吧，你不删最少使用 Key，去随机删
- volatile-lru：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，移除最近最少使用的 Key。这种情况一般是把 Redis 既当缓存，又做持久化存储的时候才用。不推荐
- volatile-random：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，随机移除某个 Key。依然不推荐
- volatile-ttl：当内存不足以容纳新写入数据时，在设置了过期时间的键空间中，有更早过期时间的 Key 优先移除。不推荐
- volatile-lfu：根据 LFU 算法删除所有的键，直到腾出可用空间。如果没有可删除的键对象，且内存还是不够用时，则报错
- allkeys-lfu：随机删除设置了过期时间的键，直到腾出可用空间。如果没有可删除的键对象，且内存还是不够用时，则报错

# Redis 和数据库双写一致性问题

- 最终一致性

  先更新数据库，再删除缓存，可能存在删除缓存失败的问题，提供一个补偿措施即可，比如消息队列。

- 强一致性

  无法做到

# 如何应对缓存击穿、缓存雪崩、缓存穿透问题

三者导致的结果都是不能替数据库承担大量请求从而导致数据库异常

## 缓存雪崩

某时刻缓存大面积失效，同一时刻来了大量请求，导致大量请求直接请求数据库，从而使数据库连接异常

> 都有一个统一解决方案，请求数据库做互斥锁，拿到才能进行 DB 操作，但是严重影响吞吐量

**解决方案：**

Redis 方面：

- 搭建 Redis 集群，所有集群同一个键值同时失效的可能性极低
- 给缓存的失效时间，加上一个随机值，避免集体失效
- 双缓存。我们有两个缓存，缓存 A 和缓存 B。缓存 A 的失效时间为 20 分钟，缓存 B 不设失效时间。自己做缓存预热操作。  然后细分以下几个小点：从缓存 A 读数据库，有则直接返回；A 没有数据，直接从 B 读数据，直接返回，并且异步启动一个更新线程，更新线程同时更新缓存 A 和缓存 B

其他方面：

- 服务器增加熔断机制，友好提示“系统拥挤”
- 数据库容灾能力，如分库分表、读写分离

## 缓存击穿

属于雪崩的特例，击穿是指某一个热点 Key 突然失效，导致大量请求直接请求数据库，从而使数据库连接异常

**解决方案：**

- 热点数据可以不设置过期时间

## 缓存穿透

请求缓存中不存在的数据，导致大量请求直接请求数据库，从而使数据库连接异常

**解决方案：**

- 异步更新策略，无论Key是否有值都直接返回（ value设置为 null ）。Value值维护一个失效时间，过期异步读库更新缓存。需要做**缓存预热**（项目启动时加载数据）
- 加入请求是否有效的拦截机制，利用**布隆过滤器**，内部维护合法有效的key。

### 布隆过滤器

#### 特点

- 快速检索

- 内存空间需求非常小

#### 优缺点

- 优点：空间效率和查询时间都远远超过一般的算法

- 缺点：有一定的误识别率，删除困难

#### 结构

布隆过滤器本质上是一个 n 位的二进制数组，用0和1表示

#### 工作流程

- 通过三个哈希函数对商品id计算哈希值

- 然后，在布隆数组中查找访问对应的位值，0或1

- 判断，三个值中，只要有一个不是1，那么我们认为数据是不存在的

> 注意：布隆过滤器只能精确判断数据不存在情况，对于存在我们只能说是可能，因为存在Hash冲突情况，当然这个概率非常低

#### 如何减少布隆过滤器的误判

- 增加二进制位数组的长度。这样经过hash后数据会更加的离散化，出现冲突的概率会大大降低

- 增加Hash的次数，变相的增加数据特征，特征越多，冲突的概率越小

#### 布隆过滤器维护

- 难以删除，所以设置一个定时任务，每隔一段时间重新创建一个布隆过滤器数据

- 布隆过滤器增加一个等长的数组，存储计数器，主要解决冲突问题，每次删除时对应的计数器减一，如果结果为0，更新主数组的二进制值为0

#### 应用场景

- 解决 Redis 缓存穿透

- 网页爬虫对URL的去重，避免爬取相同的URL地址

- 反垃圾邮件，从数十亿个垃圾邮件列表中判断某邮箱是否垃圾邮箱

# 如何解决Redis的并发竞争Key问题

多个子系统同时 set 一个 key

很多答案推荐使用redis事务机制，但是由于redis在集群环境做了数据分片操作的情况下，事务中的多个key操作不一定在一个server上，所以redis的事务机制不合理

**解决方案：**

- 并发操作无顺序要求

  使用分布式锁

- 并发操作有顺序要求

  使用分布式锁同时在操作的数据中加上时间戳，比较时间戳来判断操作的先后顺序

  eg：有A、B、C三个操作，数据分别含有{valueA 3:00}、{valueB 3:05}、{valueC 3:10}，我们希望的顺序是A>B>C，那么即时是先执行了B，轮到A的时候判断发现早于B的时间戳，则不进行set操作

# redis判断键是否存在

setnx

# redis-cli操作

\# 进入redis的控制台并登录

$ redis-cli -h 127.0.0.1 -p 6379 -a password

\# 输入密码
$ auth <password>

\# 查看密码及修改密码

$ config get requirepass

$ config set requirepass

\# 选择数据库，同时清空数据库

$ select 1

$ flushdb

\# 退出

$ quit

# 常用指令

\# 键值对数量

$ DBSIZE

\# 清空所有库

$ FLUASHALL

\# 清空当前库

$ FLUSHDB

# 内存情况

## 指令

\# 显示 redis 信息

$ INFO

\# 显示 redis 内存信息

$ info memory

## 主要关注的参数

- used_memory
  Redis分配器分配的内存（单位是字节Byte）+ 虚拟内存（swap）；不包括内存碎片、进程本身需要的内存。used_memory_human只是更友好的显示；

- used_memory_rss
  Redis进程占据操作系统的内存总量，包括：Redis分配器分配的内存+进程本身需要的内存+内存碎片；不包括虚拟内存。

- mem_fragmentation_ratio
  内存碎片比率，该值就是used_memory_rss / used_memory的比值，通常mem_fragmentation_ratio 是 > 1 的。

- mem_fragmentation_ratio

  >1，且值越大，内存碎片比例越大

- mem_fragmentation_ratio

  <1，证明Redis使用了虚拟内存，也证明Redis内存不足了，且虚拟内存的媒介是磁盘，比内存速度慢很多，所以应及时排查并处理。处理方式例如：增加Redis节点、增加Redis服务器内容、优化应用
  
- mem_allocator

Redis使用的内存分配器，在编译时指定；可以选的分配器如下：

- jemalloc（默认）
- tcmalloc
- libc

## 内存碎片
内存碎片是Redis在进行分配内存、回收物理内存产生的。

内存碎片的产生与以下有关：
- 对数据进行的操作
- 数据的特点
- 使用的内存分配器，不同的分配器，对于内存碎片的处理方式也不同。jemalloc对于控制内存碎片就做的很好

内存碎片已经很大的时候，可以使用安全重启的方式减小内存碎片：因为Redis重启会从备份文件中读取数据，在内存中重新对数据进行分配，以此减小内存碎片

# 解决“竞态条件”

- 乐观锁：版本号控制，CAS（check and set）先检查版本号再修改更新，用于写操作比较少的情况，因为冲突多也意味着retry多，反而降低系统性能

- 悲观锁：先锁数据，再进行修改，行锁和表锁，读锁和写锁

# 持久化

## RDB （快照）

`fork()` 一个子进程，使用 `Copy On Write` 机制进行数据备份

> `COW` 机制原理是子进程指向父进程的物理地址，即使父进程数据修改也只是修改了逻辑地址指向，不影响数据快照复制

## AOF ( Append Only File - 仅追加文件 ) 

- 命令追加：命令写入aof_buf缓冲区

- 文件写入：调用flushAppendOnlyFile函数，考虑是否要将aof_buf缓冲区写入AOF文件中

- 文件同步：考虑是否将内存缓冲区的数据真正写入到硬盘

## 两者的适用场景

- rdb对redis的性能几乎没有任何影响，使用空闲IO执行备份

- 数据集越大，rdb的启动效率更高

- rdb在备份过程中如果出现宕机，重启后无法恢复数据

- aof带来更高的数据安全，可以恢复更近的数据

## RDB和AOF的备份设置

### RDB

数据保存在dump.rdb中

可以直接调用 `save` 或者 `bgsave` 指令

|指令|save|bgsave|
|---|---|---|
|IO类型|同步|异步|
|阻塞|是|是（阻塞发生在fork）|
|复杂度|O(n)|O(n)|
|优点|不会消耗额外内存|不阻塞客户端命令|
|缺点|阻塞客户端命令|需要fork消耗内存|

服务器配置( redis.conf 文件)：

\# 在`time`秒之后，如果至少有`keyNums`个key发生改变，则dump内存快照
save [time] [keyNums]

### AOF

appendfsync no | everysec | always # 不同步 | 每秒同步一次 | 每次有数据修改发生时都写入AOF文件


### 对比

|| RDB | AOF |
|---|---|---|
|启动优先级|低|高|
|体积|小|大|
|恢复速度|快|慢|
|数据安全性|丢数据|根据策略决定|
|轻重|重|轻|

### RDB与AOF同时开启，默认加载AOF的配置文件

# Redis 运维

## Redis 集群

集群保证的是高并发的情况下，更多的服务器可以提供服务，但是集群会导致数据的分散，整个 redis 集群会分成一堆数据槽，不同的 key 会放到不同的槽中。

## Redis 主从

备份机制，请求操作的是主库，同时同步到从库，如果主库坏了，从库可以直接替换继续服务。

## Redis 哨兵

保障HA，在故障情况下自动切换主服务，哨兵时刻监控”主从集群“，如果主库死了，它会告诉你新的老大是谁。

# Redis 6.x 新特性

\# 新建账号密码配置访问权限

$ ACL SETUSER mcd_test on >test123456 ~mcd_test:* +@all

\# 新建账号密码设置所有权限

$ ACL SETUSER mcd_test on >test123456 ~* +@all

\# 删除账号

$ ACL DELUSER mcd_test

\# ACL 列表

$ ACL LIST

\# 获取账号信息

$ ACL GETUSER mcd_test

# Redis 与 Memcached 的区别

Redis 计算向数据移动

Memcached 数据向计算移动

> 假设设计获取用户分数需求。使用 Redis 可以使用 Hash 结构通过代码直接获取，使用 Memcached 则需要通过大 Key 获取所有数据反序列化后遍历获取对应的用户对象。