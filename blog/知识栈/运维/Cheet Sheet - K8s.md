```yaml
title: Cheet Sheet - K8s
author: samin
date: 2021-10-29
```

\# 根据 yaml 文件创建 K8s 资源

$ kubectl apply -f nodeport.yaml

\# 更新重启 K8s 资源

$ kubectl replace --force -f nodeport.yaml

\# 重启 deployment

$ kubectl rollout restart deployment -n <namespace> <deployment-name>

\# 获取所有命名空间下面的所有 pod 以下两句指令效果一样

$ sudo kubectl get pods --all-namespaces

$ sudo kubectl get pods -A

\# -owide 获取更多详情

$ sudo kubectl get pods -A -owide

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

$ sudo kubectl delete pod <pod-name> -n <namespace-name> --force --grace-period=0

\# 查看 deployment

sudo kubectl -n <namespace-name> get deployment

\# 删除 deployment

sudo kubectl -n <namespace-name> delete deployment <pod-name>

\# 查看 node 的标签

$ sudo kubectl get node --show-labels

\# 查看 kubelet 的日志

$ journalctl -u kubelet -f

\# 查看 k3s 的日志

$ journalctl -u k3s -f

\# 获取 service 信息

$ sudo kubectl get svc -n <namespaces>

\# 获取 NodePort 信息

$ kubectl get svc --all-namespaces | grep NodePort

\# 获取 configmaps 的配置内容( Pod 只能使用同一个命名空间的 ConfigMap )

$ kubectl describe cm dmp-config -n <namespace_name>

\# 获取当前默认操作命名空间

$ kubectl config current-context

\# 修改当前命名空间

$ kubectl config set -context--current --namespace=<namespace_name>

\# 实时查看 pod 的 logs 日志

$ kubectl logs -f <pods_name> -n <namespace_name>

\# 修改 deployment

$ sudo kubectl edit deployment <deployment_name> -n <namespace_name>

\# 查看当前节点的运行情况

$ kubectl cluster-info

\# 查看当前节点的运行情况详情

$ kubectl cluster-info dump

\# 暂停 deployment

$ kubectl rollout pause deployment <deployment_name> -n <namespace_name>

\# 恢复 deployment

$ kubectl rollout resume deployment <deployment_name> -n <namespace_name>

\# 进入容器运行指令

$ kubectl exec --stdin --tty shell-demo -- /bin/bash

\# 查看持久化路径的方式

$ kubectl describe pods hello-demo -n ns

$ kubectl get pvc hello-pvc-demo -n ns -oyaml

$ kubectl get pv hello-pv-demo -n ns -oyaml

\# 找密码配置的方式

$ kubectl get sts -n infra

$ kubectl describe sts -n infra rabbitmqcluster-server

$ kubectl edit cm -n infra rabbitmqcluster-server-conf

$ kubectl edit secret -n infra rabbitmqcluster-default-user

$ kubectl get  secret -n infra rabbitmqcluster-default-user -oyaml

\# 获取所有的 K8s 资源对象

$ kubectl api-resources

\# 查看资源字段

$ kubectl explain pod

$ kubectl explain pod.metadata

$ kubectl explain pod.spec

$ kubectl explain pod.spec.containers

[K8s 官方 Cheet Sheet](https://kubernetes.io/docs/reference/kubectl/cheatsheet/)