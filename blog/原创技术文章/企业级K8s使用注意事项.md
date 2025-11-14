```yaml
title: 企业级K8s使用注意事项
author: samin
date: 2025-11-14
```

下面从 **“企业生产环境”+“K3s（轻量 K8s）运维专家”**角度，给你整理一份**必须关注的 K3s 启动关键配置清单**。这些配置都已在大量企业 K3s 集群落地中验证过，是稳定性、安全性、可维护性最核心的部分。

---

# 🔥 **企业生产中最重要的 K3s 启动关键配置**

下面这些是 **除了日志轮转** 外，最值得优先配置的 K3s 启动参数。

---

# 1️⃣ **集群存储后端（非常关键）**

默认情况：

* 单机 / 小 cluster → sqlite
* 企业生产 → 一定要用 **外部 etcd** 或 MySQL/PostgreSQL

### 推荐（外部 etcd）：

```bash
--datastore-endpoint="etcd:https://etcd-1:2379,https://etcd-2:2379,https://etcd-3:2379"
--datastore-cafile=/etc/k3s/etcd/ca.crt
--datastore-certfile=/etc/k3s/etcd/etcd-client.crt
--datastore-keyfile=/etc/k3s/etcd/etcd-client.key
```

> 企业级要求高可用 → 外置 etcd 是必须的。

---

# 2️⃣ **集群高可用（多 master 必配）**

你的集群有 3 master → 一定要确保 Control Plane HA 生效。

```bash
--cluster-init      # 仅第一个 master 使用
```

其余 master：

```bash
--server https://<master1-ip>:6443
```

---

# 3️⃣ **Cgroup 驱动统一（Docker/containerd best practice）**

K3s 默认使用 containerd，推荐 cgroup v1 或 v2 统一。

```bash
--kubelet-arg="cgroup-driver=systemd"
```

避免 kubelet/containerd cgroup mismatch。

---

# 4️⃣ **控制容器镜像加速 + 私有仓库配置**

企业强烈建议启用 registry mirror + 私有仓库认证。

```bash
--registry /etc/rancher/k3s/registries.yaml
```

`registries.yaml` 示例：

```yaml
mirrors:
  "docker.io":
    endpoint:
      - https://mirror.ccs.tencentyun.com
configs:
  my.registry.com:
    auth:
      username: user
      password: pass
```

---

# 5️⃣ **禁用无用组件（减少资源消耗）**

K3s 默认包含很多内置组件（traefik、local-storage）。

企业环境往往使用：

* Istio / ingress-nginx（替代 Traefik）
* Ceph / Cinder / 云存储（替代 local-path）

所以建议禁用：

```bash
--disable traefik
--disable local-storage
--disable servicelb
```

---

# 6️⃣ **网络相关（CNI / kube-proxy / MTU）**

生产环境必须确保 MTU、CNI 配置正确（尤其云环境）。

使用 Calico 示例：

```bash
--flannel-backend=none
--disable-network-policy
```

自定义 MTU（腾讯云内网 MTU 常为 1500）：

```bash
--kube-apiserver-arg="advertise-address=<node-ip>"
--kubelet-arg="network-plugin=cni"
```

---

# 7️⃣ **TLS、API Server 安全配置（企业合规重点）**

### 限制可访问 API server 的 IP：

```bash
--tls-san <LB-IP>
--tls-san <cluster-domain>
```

### 自定义证书：

```bash
--server=https://my-k3s-api:6443
```

---

# 8️⃣ **容器运行时优化（GC、镜像大小限制）**

与日志轮转同样重要：

```bash
--kubelet-arg="image-gc-high-threshold=80"
--kubelet-arg="image-gc-low-threshold=60"
```

避免磁盘被旧镜像占满。

---

# 9️⃣ **Kubelet 资源保留（避免节点被打爆）**

企业生产中最容易忽略的一项。

强烈建议为 kubelet 设定 node-reserve：

```bash
--kubelet-arg="system-reserved=cpu=500m,memory=1Gi"
--kubelet-arg="kube-reserved=cpu=300m,memory=512Mi"
```

可避免节点资源耗尽导致：

* kubelet OOM → Pod 全挂
* coredns/cni 宕机 → 集群不可用

---

# 🔟 **开机自动重启、文件路径、离线模式**

企业会做 systemd override 来设置：

```bash
Restart=always
RestartSec=5
```

离线环境必要参数：

```bash
--airgap-extra-registry
--airgap-extra-images
```

---

# 📌 补充：K3s 关键配置全集（生产常用）

### 主节点常用参数：

```
--cluster-init
--datastore-endpoint
--write-kubeconfig-mode=644
--disable traefik
--node-ip
--tls-san
--kube-apiserver-arg=
--kube-controller-manager-arg=
```

### Worker/Agent 常用参数：

```
--server=https://master-ip:6443
--token=<token>
--node-ip
--kubelet-arg="cgroup-driver=systemd"
--containerd-logs-max-size=20Mi
--containerd-logs-max-files=3
```

---

# 🎯 **企业生产 K3s 标准推荐配置模板**

👉 **Master：**

```bash
/usr/local/bin/k3s server \
  --cluster-init \
  --datastore-endpoint="etcd:https://etcd:2379" \
  --registry /etc/rancher/k3s/registries.yaml \
  --disable traefik \
  --disable servicelb \
  --kubelet-arg="cgroup-driver=systemd" \
  --image-service-endpoint=/run/k3s/containerd/containerd.sock \
  --containerd-logs-max-size=20Mi \
  --containerd-logs-max-files=3 \
  --kubelet-arg="image-gc-high-threshold=80" \
  --kubelet-arg="system-reserved=cpu=500m,memory=1Gi" \
  --kube-reserved=cpu=300m,memory=512Mi \
  --tls-san=<lb-ip> \
  --node-ip=<master-ip>
```

👉 **Worker：**

```bash
/usr/local/bin/k3s agent \
  --server=https://<master-ip>:6443 \
  --token=<cluster-token> \
  --node-ip=<worker-ip> \
  --kubelet-arg="cgroup-driver=systemd" \
  --containerd-logs-max-size=20Mi \
  --containerd-logs-max-files=3 \
  --kubelet-arg="image-gc-high-threshold=80"
```

---

# 🏁 总结（企业生产 K3s 启动必配项）

高度重要配置如下：

| 类别       | 关键项                                    |
| -------- | -------------------------------------- |
| **稳定性**  | 日志轮转、image GC、资源保留                     |
| **高可用**  | cluster-init / datastore-endpoint      |
| **网络**   | CNI、flannel、MTU、IP 广播                  |
| **安全**   | TLS SAN、自定义证书、限访问源                     |
| **性能**   | cgroup-driver、镜像加速、禁用无用组件              |
| **可运维性** | registry 配置、kubelet 调优、systemd restart |

---
