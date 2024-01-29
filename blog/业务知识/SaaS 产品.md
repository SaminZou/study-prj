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

共享平台，数据隔离

- 服务特性（ 无需安装购买软件硬件 ）

SaaS使软件以互联网为载体的服务形式被客户使用，所以很多服务合约的签订、服务使用的计量、在线服务质量的保证和服务费用的收取等问题都必须加以考虑。而这些问题通常是传统软件没有考虑到的。

- 可扩展（ Scalable ）特性

可扩展性意味着最大限度地提高系统的并发性，更有效地使用系统资源。比如应用：优化资源锁的持久性，使用无状态的进程，使用资源池来共享线和数据库连接等关键资源，缓存参考数据，为大型数据库分区。

签订租户定制化需求无限扩展

- 数据安全保障

敏感数据保护，平台数据加密，灵活的实现数据的可见性控制

- 丰富的 API 支持

# 模型

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/%E5%A4%9A%E7%A7%9F%E6%88%B7%E4%B8%9A%E5%8A%A1%E6%A8%A1%E5%9E%8B.png)

- 解决方案：为了解决客户的某类型业务问题，SaaS供应商一般都将产品和服务组合在一起，为客户提供整体的打包方案

- 产品能力：能够帮助客户实现场景解决方案闭环的能力

- 资源域：支撑运行产品能力集的虚拟资源视图

- 云资源：支撑运行产品能力集的物理资源（计算、存储、网络、容器等）

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

# 传统软件和 SaaS 软件的区别

- 开发方式

- 交付方式
  - 传统软件本地化部署，消耗 IT 资源
  - SaaS 软件一般是开箱即用

- 数据安全性
  - 传统软件所有数据掌握在自己手里，一般有自己的机房和运维团队
  - SaaS 软件的数据存放在 SaaS 平台供应商的服务器上，数据不受自己约束，对运维细节也无法控制，存在一定数据隐私和安全隐患

- 商业模式
  - 传统软件为一次性收费，研发、安装、维护等
  - SaaS 软件为按需收费

- 集成性
  - 传统软件集成性强，因为基本是定制化开发
  - SaaS 软件给出通用的解决方案，一般给出对应的解决方案，对于集成需求，一般是对外提供 API 让第三方去集成而不是集成第三方