```yaml
title: 全新 Linux 服务器环境配置
author: samin
date: 2023-02-07
```

> 这里并非通用配置，是个人对服务器使用上的一些使用习惯

1. 修改网卡配置为固定 ip

> tips:  
> 在 DHCP 方式下连上网络  
> ifconfig 可以查看ip、子网掩码  
> ip route show 可以查看网关地址

2. 安装 openssh-server 并打开 root 远程访问配置

> 敏感服务器可以使用 SSH 公钥 + 禁止密码登录方式配置  

3. 安装 oh-my-zsh 配置主题，安装自动补全、语法高亮插件

4. 修改 vim 配置，增加行数显示

5. 安装 docker 环境，增加 docker-compose 插件，安装 portainer（可防止服务器上操作 docker）

6. 确认时间、时区、NTP 服务正常

7. 安装虚拟组网工具：ZeroTier、Tailscale

8. 查看服务器情况，可以使用本人编写的[脚本](https://github.com/SaminZou/study-prj/blob/master/shelltips/show-server-info.sh)，有问题欢迎指出

> 网上一个不错的开源机器性能跑分脚本（Linux 中的鲁大师😂）  
> $ curl -sL yabs.sh | bash