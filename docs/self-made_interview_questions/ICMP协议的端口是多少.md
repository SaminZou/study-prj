```properties
title: ICMP 协议的端口是多少
author: samin
date: 2021-01-14
```

# 说明

ICMP协议走的是IP协议，不要和 TCP/UDP 协议搞混了，端口是 TCP/UDP 的概念。一般运维的思维是用 ICMP 协议判断主机的可达性，再用 telnet 命令判断 TCP 协议的连通性。

# 进阶问题

1. ping 命令为什么不能加端口？

因为 ping 命令是为了检测主机的可达性，调用的就是 ICMP 协议，没有端口的概念。

2. telnet 命令为什么可以有端口，和 ping 命令的区别？

telnet 命令原理就是建立一个 socket 连接，需要提供 ip 和 port，是 TCP 协议的内容，和 ping 命令调用的目的和使用协议不一样。

# 心得

懂协议之间的关系，可以更好排查开发中遇到的问题，并且可以用这些特性，开发出更多功能强大的应用。
