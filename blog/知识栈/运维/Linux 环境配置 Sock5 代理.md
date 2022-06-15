```yaml
title: Linux 环境配置sock5代理
author: samin
date: 2022-06-15
```

# 安装 proxychians 客户端

$ sudo apt-get install proxychains

# 配置 proxychains

$ vim /etc/proxychains.conf

# 在需要代理的指令前加上 proxychains

$ proxychains curl http://172.22.127.110:8080