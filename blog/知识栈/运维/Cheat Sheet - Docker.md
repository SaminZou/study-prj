```yaml
title: Cheat Sheet - Docker
author: samin
date: 2021-10-29
```

# Build

\# 在当前路径根据 Dockerfile 文件进行镜像构件
$ docker build -t myimage:1.0 .

\# 在当前路径根据 Dockerfile 文件进行镜像构件，去掉缓存机制（如 nginx 镜像构建，nginx.conf 修改后重新构建不生效问题）
$ docker build --no-cache -t myimage:1.0 .

$ docker image ls / docker images

$ docker image rm alpine:3.4 / docker rmi alpine:3.4

# Share

$ docker pull myimage:1.0

$ docker tag myimage:1.0 myrepo/myimage:2.0

$ docker push myrepo/myimage:2.0

# Run

\# 调试技巧！可以进入镜像内部调试，运行结束 docker 实例也会删除
$ docker run -it --rm <image-name> bash
$ docker-compose run --rm <service-name> bash

$ docker run --name web -p 5000:80 alpine:3.9

$ docker run -d --rm redis

```shell
# 比如本机上只有 Python 2.7，但想用 Python 3 开发，如果同时安装 Python 2 和 Python 3 很容易就会把系统搞乱
# 可以采用 docker 开发 Python 3 的应用

$ docker pull python:alpine

# --rm 参数表示容器结束后自动删除删除
$ docker run -it --rm -v ${PWD}:/tmp python:alpine sh
```

$ docker stop web

$ docker stop $(docker ps -aq)

$ docker kill web

$ docker network ls

$ docker container ls / docker ps

\# Delete all running and stopped containers

$ docker rm -f $(docker ps -aq)

$ docker logs --tail 100 web

# More

\# 官方建议操作，把非 root 操作用户加入到 docker 用户组

$ sudo usermod -aG docker ${USER}

\# 查看 docker 的基本信息

$ docker info

\# * 查看镜像、容器的占用空间

$ docker system df

\# 删除没有被引用的挂载

$ docker volume prune

\# 删除状态为 Exited 容器

$ docker container prune

\# 打印所有没有运行实例的 images

$ sudo docker images --quiet

\# 删除所有没有运行实例的 images

$ sudo docker rmi --force $(sudo docker images --quiet)

\# 删除虚悬镜像

$ docker image prune

\# 瘦身

$ docker system prune

\# 彻底瘦身

$ docker system prune --all --force --volumes

> 虚悬镜像是没有作用的，占用内存空间。
> 虚悬镜像怎么来呢？一般是我们下载镜像，依赖一些中间镜像，然后我们删除了下载的镜像，但是只是删除了上层镜像，依赖的镜像没有删除。
> 这样没有依赖的中间镜像就成了虚悬镜像，是可以删除的

\# 查看容器端口映射情况

$ docker port <container_name>

\# docker和本机之间的文件传输，可以反过来传输

\# docker to host

$ docker cp <container_id>:/file/path /host/path

\# host to docker

$ docker cp /host/path <container_id>:/file/path

\# 停留查看日志，去掉-f属性是查看最新的消息，不会停留

$ docker logs -f

\# 启动所有的容器命令

$ docker start $(docker ps -a | awk '{ print $1}' | tail -n +2)

\# 关闭所有的容器命令

$ docker stop $(docker ps -a | awk '{ print $1}' | tail -n +2)

\# 删除所有的容器命令

$ docker rm $(docker ps -a | awk '{ print $1}' | tail -n +2)

\# 删除所有的镜像

$ docker rmi $(docker images | awk '{print $3}' |tail -n +2)

\# 容器详情

$ docker inspect <container_name>

| RootFS 可以查看分层信息，Docker 会检查是否有重复的层，如果本地已经存在就不会重复下载，如果层被其他镜像共享就不会删除，这样就可以节约磁盘和网络成本

\# 有容器日志

$ cat /var/lib/docker/containers/<container_id>/<container_id>.log-json.log

\# 进入容器 shell

$ docker exec -it <container_id> bash

\# 当前 shell 执行 docker 指令  

$ docker exec -d e34b bash -c "pg_dump -h 127.0.0.1 -U daship daship> /var/lib/postgresql/backup20190703.bak"

\# 当前 shell 执行 docker 指令并打印结果到当前控制台

$ docker exec -i minio bash -c "echo hello world"

# 不需要敲 sudo 的方法，创建一个 docker 组

$ sudo groupadd docker

\# 添加当前用户到docker组，设置好后，重启shell

$ sudo usermod -aG docker $USER

\# 查看容器挂载详情

$ docker inspect <container_name> | grep Mounts -A 20

\# 查看容器的 IP 地址

$ docker inspect <container_name> |grep IPAddress

\# 查看 docker 容器的运行情况

$ docker stats <container>

\# 查看 docker 容器运行情况，只返回当前结果

$ docker stats <container> --no-stream

# 导入与导出

## 镜像

save & load

\# 打包

$ docker sava -o nginx.tar nginx:latest

\# 打包并使用 pixz 压缩

$ docker save nginx:latest | pixz > nginx.tar.xz

\# 导出镜像

$ docker load -i nginx.tar

\# 导出所有镜像

$ docker save $(docker images | grep -v REPOSITORY | awk 'BEGIN{OFS=":";ORS=" "}{print $1,$2}') -o all.tar

\# 回放镜像的构建过程

$ docker history <image_name/image_id>

## 容器

export & import

# 打开 docker api 

\# 查找docker.service的位置

$ find / -name docker.service 或 $ systemctl status docker.service

\# 创建目录

$ sudo mkdir /etc/systemd/system/docker.service.d

\# 创建配置文件

$ sudo vim /etc/systemd/system/docker.service.d/http-proxy.conf
```
[Service]

ExecStart=
ExecStart=/usr/bin/dockerd -H tcp://0.0.0.0:2375 -H unix:///var/run/docker.sock
```

\# 刷新配置

$ sudo systemctl daemon-reload

\# 重启服务

$ sudo systemctl restart docker

\# 查看 2375 端口是否打开

$ netstat -ano | grep 2375

\# 访问http://ip:2375/version 验证

# 一般需要挂载的三类数据

- log
  日志文件

- data
  数据文件

- conf
  配置文件

# overlay2 目录迁移

[Docker overlay2 目录迁移](https://gaudy-feels-700.notion.site/overlay2-a95bb80ea0ab42b9a3e633c5eed2f8ed)

# registry2

查看私有仓库列表api: /v2/_catalog

查看镜像版本号api: /v2/<name>/tags/list

## 例子

http://192.168.123.67:5000/v2/_catalog

http://192.168.123.67:5000/v2/sso-prj/sso/tags/list

# docker-compose

## 启动 yml 实例

$ sudo docker-compose up -d

## 打印运行实例

$ sudo docker-compose ps

## 停止 yml 实例

$ sudo docker-compose stop <container_id>

## 删除 yml 实例（需要先停止）

$ sudo docker-compose rm <container_id>

# docker 容器镜像瘦身

COPY、ADD、RUN 指令执行会增加层，层的概念和git相似，会占用空间，所以执行这些命令尽量使用 && 连接 

# 导入与导出

- 若是只想备份images，使用save、load即可

- 若是在启动容器后，容器内容有变化，需要备份，则使用export、import

# 本地操作远程服务器的 docker 服务

1. 打开远程 docker 的 api 服务

2. docker context create 指令添加远程 docker api

3. docker context ls 查看可管理的 docker 服务列表

4. docker context use 切换需要管理的上下文配置

## 实践

```shell
# 打包镜像
$ docker sava -o nginx.tar nginx:latest
# 恢复镜像
$ docker load -i nginx.tar
# 批量打包所有镜像
$ docker save $(docker images | grep -v REPOSITORY | awk 'BEGIN{OFS=":";ORS=" "}{print $1,$2}') -o all.tar
```