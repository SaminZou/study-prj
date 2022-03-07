```yaml
title: Cheet Sheet - K8S
author: samin
date: 2021-10-29
```

# 获取所有命名空间
$ sudo kubectl get pods --all-namespaces
# 等同以下指令
$ sudo kubectl get pods -A

# 查看 pv
$ sudo kubectl get pv

# 查看 pvc
$ sudo kubectl get pvc

# 通过 depolyment.yaml 创建应用
$ sudo kubectl create -f deployment.yaml

k3s 默认使用 containerd 控制容器，对应的指令为 crictr

# 查看运行容器
$ sudo kubectl get deployments -n <namespaces>