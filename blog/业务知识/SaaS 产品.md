```yaml
title: SaaS 产品
author: samin
date: 2022-01-08
```

# SaaS 是什么

Software-as-a-Service，意思为软件即服务，即通过网络提供软件服务

> SaaS 是软件的开发、管理、部署都交给第三方，不需要关注技术问题，可以拿来即用（开箱即用）

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS%20vs%20PaasS%20vs%20IaaS.png)

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS%20vs%20PaasS%20vs%20IaaS%202.png)

# 特性

- 互联网特性

用户通过互联网浏览器使用服务

- 多重租赁（ Multi-tenancy ）特性

- 服务特性（ 无需安装购买软件硬件 ）

SaaS使软件以互联网为载体的服务形式被客户使用，所以很多服务合约的签订、服务使用的计量、在线服务质量的保证和服务费用的收取等问题都必须加以考虑。而这些问题通常是传统软件没有考虑到的。

- 可扩展（ Scalable ）特性

可扩展性意味着最大限度地提高系统的并发性，更有效地使用系统资源。比如应用：优化资源锁的持久性，使用无状态的进程，使用资源池来共享线和数据库连接等关键资源，缓存参考数据，为大型数据库分区。

- 数据安全保障

敏感数据保护，平台数据加密，灵活的实现数据的可见性控制

- 丰富的 API 支持

# 成熟度模型

## 每个租户一个库

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS资源模型1.png)

每个租户的数据库表结构可能是不一样的

## 同一个数据库

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS资源模型2.png)

## 分片多租户

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS资源模型3.png)

分片多租户可以通过配置适配前两种方案，非常灵活且可拓展性强

# SaaS 核心 - 多租户特性

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/SaaS%20核心-多租户特性.png)

## 除了生产环境，还能够快速提供用于测试的沙盒（ Sandbox ）