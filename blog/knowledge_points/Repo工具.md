```yaml
title: repo 
author: samin
date: 2021-06-22
```

# How to use repo upload

# 什么是 repo ？

Repo 是管理 git 的一款工具，是一个python脚本

# 如何安装 repo ？

$ touch repo

$ chown samin:admin repo

\# 获取脚本

curl https://storage.googleapis.com/git-repo-downloads/repo > ~/bin/repo

chmod a+x repo

\# 可以修改 `/etc/profile` 增加全局变量

# 基本指令

\# 初始化 repo

$ repo init -u git地址

\# 查看 repo 状态

$ repo status

\# repo 拉取代码

$ repo sync

> -j ： 多任务，一般8核心可以开到16任务,过多会起反作用
> -c： 只下载当前分支代码
> -d： 让工程回退到manifest指定的版本
> -f： 如果某个工程同步失败，继续同步
> repo sync -c -j4

\# 上传代码

$ repo upload

\# 查看差异

$ repo diff

\# repo 创建仓库

$ repo start 自定义分支名

\# 查看仓库

$ repo branches

\# 同步完代码以后，为所有文件夹创建本地分支

$ repo start 自定义分支名 --all

> 一般是: $ repo statrt master --all

\# 删除指定本地分支

$ repo abandon 已创建的本地分支名

\# 获取init的帮助

