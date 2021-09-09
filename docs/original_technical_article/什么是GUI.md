```yaml
title: 什么是GUI
author: samin
date: 2021-09-09 
```

给大家写一个扫盲贴，讲解一下什么是GUI（Graphical User Interface 图形用户接口）

# 一般只有指令客户端而没有GUI客户端

redis、mysql、mongodb这类应用是没有GUI窗口的，它们都分为服务端和客户端，服务端在服务器中运行后，就开始监听等待客户端请求，上述三类软件在linux系统中的客户端指令分别是redis-cli、mysql、mongo。（windows可以配置环境变量，在cmd中操作）

通过指令可以看到这些应用服务端的运行情况，数据内容，进行增删查改操作等。

# 想看数据怎么办

其实不单是普通用户或者是实施人员，程序员也不喜欢通过大量的命令行去操作得出结果，所以很多公司或者开发者会根据服务端开发的api去开发GUI客户端，用于可视化操作操作服务器。一般的GUI又可以分为网页版和PC桌面版（B/S架构、C/S架构），比如mysql数据库的PC端可视化软件navicat，网页版的有phpmyadmin。

redis对应的GUI客户端为RedisDesktopManager，mongodb对应的GUI客户端为Robo 3t。这些不是全部，市面上有很多对应的GUI客户端。

# 你们删除了navicat，并没删除数据

mysql和navicat是互相独立的应用，mysql服务端提供的才是数据持久化的功能，navicat是用来查看可视化操作服务端数据的工具，所以删除了navicat，不用担心造成数据遗失。