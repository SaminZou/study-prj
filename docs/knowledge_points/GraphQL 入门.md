```yaml
title: GraphQL 入门 
author: samin
date: 2021-04-16
```

# 官网文档

https://graphql.cn/learn/

# 是什么

GraphQL 是一个用于 API 的查询语言，是一个使用基于类型系统来执行查询的服务端运行时（类型系统由你的数据定义）。GraphQL 并没有和任何特定数据库或者存储引擎绑定，而是依靠你现有的代码和数据支撑。GraphQL不是一种框架，和 restful 一样是一种风格。

# 后端如何配合

识别传参后，一样是多步查询，只不过是把数据拼接的操作放到了服务端。

# 对比 rest 的优点


- 过度获取
- 多个请求请求多项资源
- 针对嵌套数据的瀑布式网络请求
- 每个客户端都需要知道每个服务的位置

# 文献

https://tomoya92.github.io/2019/04/03/spring-boot-graphql/