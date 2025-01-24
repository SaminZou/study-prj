```yaml
title: Docker 文件挂载
author: samin
date: 2025-01-23
```

# 背景

mysql5.7 为例，且容器中已有结构： /etc/my.cnf 文件、/var/lib/mysql 文件夹

# 关于文件夹

文件夹的挂载是最容易理解的，容器中文件夹有什么，只要配置映射宿主机文件夹，那么就会把容器文件夹中所有的文件映射到宿主机中。

如 /var/lib/mysql 存储的都是数据库核心数据内容，假设配置了 /path/to/mysql:/var/lib/mysql，启动容器，宿主机中 /path/to/mysql 就会有 /var/lib/mysql 的全量文件

# 关于文件

这个是需要注意的点，操作比较危险‼️

和文件夹挂载一样，宿主机中必须存在文件才能

## bad taste

如容器中有 /etc/my.cnf 文件，如果想要映射到宿主机，那么在宿主机中必须有这个文件，我们一般的操作会是

$ touch /path/to/my.cnf

但是这样做会出现的问题是，一旦启动镜像，容器中的 /etc/my.cnf 文件会被宿主机中覆盖

## good taste

正确做法应该是先不挂载文件启动一次镜像，执行操作

$ docker cp <mysql5.7-container-name>:/etc/my.cnf /path/to

通过 docker 指令把文件复制到宿主机中，然后再修改 docker-compose 的文件映射，这样就可以无损的完成文件映射

> 如果在很了解 mysql 的配置文件或者有备份配置文件的情况下，可以直接在第一次启动容器的时候就挂载使用文件。