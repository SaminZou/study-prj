```yaml
title: dockerignore
author: samin
date: 2021-12-28
```

# 背景

在进行 `docker build` 指令的时候，发现 `Sending build context to Docker daemon xxx GB` 越来越大

# 原因

# 解决方案

编写 .dockerignore 文件，内容是去除一些不必要的镜像构建文件

# Sample

假设有一个java项目，文件结构为：

- src
- target
- .gitignore
- Dockerfile
- Makefile
- pom.xml
- README.md

.dockerignore 的内容将会是：

```text
src
.gitignore
Dockerfile
Makefile
pom.xml
README.md
```

因为一般构建只会用到 target 包里面的 jar 文件

> .dockerignore 可以使用类似正则表达式一样的匹配规则，具体方法查看官方文档