```yaml
title: NAT 内网穿透
author: samin
date: 2021-01-24
```

# 说明

- Network Address Translation

- 网络地址转换方法

- 内网穿透

- NAT

# 解决问题


公网IP有限，一般一个人或者一个组织之后有一个对外IP，所以一般搭建局域网，局域网ip连接公网的时候会通过NAT分配一个端口进行通信，所以NAT解决了多个局域ip访问外网的问题。

NAT不仅能解决IP地址不足的问题，而且还能够有效地避免来自网络外部的攻击，隐藏并保护网络内部的计算机。


![示意图](https://raw.githubusercontent.com/SaminZou/pic-repo/master/Network/NAT%E5%86%85%E7%BD%91%E7%A9%BF%E9%80%8F%E7%A4%BA%E6%84%8F%E5%9B%BE.png)

# 常见问题

一个公网出口NAT服务设备最多可同时支持多少内网IP并发访问外网服务

65535（2^16-1）