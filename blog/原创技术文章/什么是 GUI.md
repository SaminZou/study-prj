```yaml
title: 什么是GUI
author: samin
date: 2021-09-09 
```

给大家写一个扫盲贴，讲解一下什么是GUI（Graphical User Interface 图形用户接口）

# 一般只有指令客户端而没有 GUI 客户端

Redis、MySQL、MongoDB这类应用是没有 GUI 窗口的，它们都分为服务端和客户端，服务端在服务器中运行后，就开始监听等待客户端请求，上述三类软件在 Linux 系统中的命令行客户端指令分别是 redis-cli、mysql、mongo （windows 可以配置环境变量，在 cmd 中操作）

通过命令行指令可以看到这些应用服务端的运行情况，数据内容，进行增删查改操作等。

# 想看数据怎么办

其实不单是普通用户或者是实施人员，程序员也不喜欢通过大量的命令行去操作得出结果，所以很多公司或者开发者会根据服务端开发的 Api 去开发 GUI 客户端，用于可视化操作操作服务器。一般的 GUI 又可以分为网页版和 PC 桌面版（B/S架构、C/S架构），比如 mysql 数据库的 PC 端可视化软件 Navicat，网页版的有 phpmyadmin。

redis 对应的 GUI 客户端为 RedisDesktopManager，MongoDB 对应的 GUI 客户端为 Robo 3t。这些不是全部，市面上有很多对应的 GUI 客户端。

# 你们删除了 Navicat，并没删除数据

MySQL 和 Navicat 是互相独立的应用，MySQL 服务端提供的才是数据持久化的功能，Navicat 是用来查看可视化操作服务端数据的工具，所以删除了 Navicat，不用担心造成数据遗失。