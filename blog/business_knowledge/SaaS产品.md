```yaml
title: SaaS 产品
author: samin
date: 2022-01-08
```

# SaaS 是什么

Software-as-a-Service，意思为软件即服务，即通过网络提供软件服务

# 特性

- 互联网特性

用户通过互联网浏览器使用服务

- 多重租赁（ Multi-tenancy ）特性

- 服务特性

SaaS使软件以互联网为载体的服务形式被客户使用，所以很多服务合约的签订、服务使用的计量、在线服务质量的保证和服务费用的收取等问题都必须加以考虑。而这些问题通常是传统软件没有考虑到的。

- 可扩展（ Scalable ）特性

可扩展性意味着最大限度地提高系统的并发性，更有效地使用系统资源。比如应用：优化资源锁的持久性，使用无状态的进程，使用资源池来共享线和数据库连接等关键资源，缓存参考数据，为大型数据库分区。

# 成熟度模型

## 每个租户一个库

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS资源模型1.png)

每个租户的数据库表结构可能是不一样的

## 同一个数据库

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS资源模型2.png)

## 分片多租户

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS资源模型3.png)

分片多租户可以通过配置适配前两种方案，非常灵活且可拓展性强