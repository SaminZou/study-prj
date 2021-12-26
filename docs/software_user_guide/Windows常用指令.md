```yaml
title: Windows 常用指令
author: samin
date: 2021-12-26
```

-- CPU末尾U是低压，H、HQ、M是标压，Y是超低压处理器

-- 查看arp缓存

$ arp -a | findstr 192.168.30.150

-- 类似 Linux 的 ls

$ dir

-- 列出端口使用情况

$ netstat -ano

-- 查看本机被占用或被监听的端口

$ netstat -an

-- 查找对应端口的使用情况

$ netstat -aon|findstr "8080"

-- 查看端口的具体运行情况（PID）

$ tasklist | findstr "21548"

-- 结束进程

$ taskkill /f /t /im <process-name>