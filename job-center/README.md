# 架构图

![](job-center-architecture.png)

# 结构

- job-server

- job-admin

- job-worker

- job-sdk

# 说明

- 定时任务精度 5 秒

- 定时任务管理

- 定时任务执行日志管理

- 通过 SDK 完成 worker 工程构建

- 定时任务 CRON、逻辑删除、启停用、指定触发应用、指定入参等特性支持

- 通过 RabbitMQ 持久化每一条定时任务实例，保证定时任务的顺序执行

- worker 认证 ❌

- worker 可视管理 ❌

  - 维护一张表用于记录 worker 添加
  - 启动成功推送 worker 注册消息
  - 下线由 server 探测感知

- job admin 登录 ❌

- job admin 用户管理 ❌

- job admin 管理界面 ❌