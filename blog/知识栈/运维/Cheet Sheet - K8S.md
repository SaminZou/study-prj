```yaml
title: Cheet Sheet - K8S
author: samin
date: 2021-10-29
```

\# 根据 yaml 文件创建 K8S 资源

$ kubectl apply -f nodeport.yaml

\# 获取所有命名空间下面的所有 pod 以下两句指令效果一样

$ sudo kubectl get pods --all-namespaces

$ sudo kubectl get pods -A

\# 查看 pv

$ sudo kubectl get pv

\# 查看 pvc

$ sudo kubectl get pvc

\# 通过 depolyment.yaml 创建应用

$ sudo kubectl create -f deployment.yaml

\# k3s 默认使用 containerd 控制容器，对应的指令为 crictr

\# 查看运行容器

$ sudo kubectl get deployments -n <namespaces>

\# 强制删除 pods

$ sudo kubectl -n <namespace-name> delete pod <pod-name> --force --grace-period=0

\# 查看 deployment

sudo kubectl -n <namespace-name> get deployment

\# 删除 deployment

sudo kubectl -n <namespace-name> delete deployment <pod-name>

\# 查看 node 的标签

$ sudo kubectl get node --show-labels

\# 查看 kubelet 的日志

$ journalctl -u kubelet -f

\# 获取 service 信息

$ sudo kubectl get svc -n <namespaces>

\# 获取 NodePort 信息

$ kubectl get svc --all-namespaces | grep NodePort

\# 获取 configmaps 的配置内容( Pod 只能使用同一个命名空间的 ConfigMap )

$ kubectl describe cm dmp-config -n <namespace_name>

[K8S 官方 Cheet Sheet](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)