```yaml
title: Go Module 入门
author: samin
date: 2021-09-07
```

# 背景

Go 1.11 版本后，可以使用 Module 特性管理包

# 常用指令

\# 初始化项目，生成"go.mod"和"go.sum"文件

$ go mod init project-name

\# 根据"go.mod"去加载丢失的包

$ go mod tidy

\# 在"go.mod"文件添加依赖包后执行，可以下载对应版本的依赖包至$GOPATH/pkg/mod中

$ go mod download

\# 清除缓存

$ go clean -modcache

\# 下载依赖包

$ go mod download package-name

# 参数优化

## GOPROXY

### go env

\# 修改国内镜像

$ go env -w GOPROXY=https://mirrors.aliyun.com/goproxy/

### Linux 环境变量 

$ export GOPROXY=https://mirrors.aliyun.com/goproxy/

### GoLand

Settings -> Go -> Go Modules -> Environment 

添加：GOPROXY=https://mirrors.aliyun.com/goproxy/

## GO111MODULE

\# Go 一定要修改的修改的参数，GO111MODULE 是为了抛弃 GOPATH 全面拥抱 Go Module 新特性

$ go env -w GO111MODULE=on

