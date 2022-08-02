```yaml
title: Cheet Sheet - Containerd
author: samin
date: 2022-08-02
```

# 修改 root dir

$ vim /etc/containerd/config.toml

```text
# 默认
root = "/var/lib/containerd"
```

$ systemctl restart containerd

# 常用指令

$ crictl version