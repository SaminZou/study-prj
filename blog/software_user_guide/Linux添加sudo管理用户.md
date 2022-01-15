```yaml
title: Linux添加sudo管理用户
author: samin
date: 2021-02-07
```

# 添加用户

$ useradd -s /bin/bash <userName>

# 设置密码

$ passwd <userName>

# 添加 sudo 权限

$ sudo vim /etc/sudoers

```
在root下面添加一行一样的

root ALL=(ALL:ALL) ALL
<userName> ALL=(ALL:ALL) ALL
```
