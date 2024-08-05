# Docker 安装

首先我们要知道，我们安装社区版的 docker-ce 核心模块为 docker-engine

在 [安装列表](https://docs.docker.com/engine/install/) 里面找到对应的服务器文档，使用 repository 方式（就是系统自带的包管理工具安装）

# 修改 Docker 镜像

国内使用 Docker 默认的官方镜像速度会比较慢，所以一般修改为国内镜像，如阿里云、腾讯云等

[腾讯云的加速镜像](https://cloud.tencent.com/document/product/1207/45596)

# 镜像的坑

发现国内的 docker 镜像源很多常用的镜像版本老旧，或者说没有通用版本，建议修改使用国内镜像源

## 解决方案，为 docker daemon 配置代理，直接使用官方镜像源

https://docs.docker.com/config/daemon/proxy/

# Docker Compose（可选）

这是 docker 官方出的服务管理工具，可以使用声明式的文件进行服务的使用

[安装文档](https://docs.docker.com/compose/install/)