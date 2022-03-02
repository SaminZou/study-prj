```yaml
title: Git 入门
author: samin
date: 2021-10-02
```

# 代码管理

代码管理的重要性也显现出来。如果一家企业的代码管理工作没有做好，极有可能会出现以下几种情况
- 版本混乱、合并冲突，代码管理权责不明

- 评审低效，项目过程不可控

- 多地域、多人员共同开发 协作难度大

- 研发流程工具不互通，切换成本高

- 版本发布慢，停机时间长，不满足产品快速迭代的需求

- 存在孤岛式的技术研发团队， 开发人员缺少外部的代码灵感与开源项目参考

- 团队与团队间缺少开源共享，导致重复开发，浪费人力

> 安全性 > 可用性（可访问性和用户体验）> 可维护性 > 简单性（开发者体验）> 简洁性（代码量）> 性能

# 基础

\# 初始化本地项目  
$ git init

\# 克隆项目  
$ git clone <url>

\# 所有修改内容添加到缓存区
$ git add .

\# 提交修改
$ git commit -m ""

\# add 和 commit 操作二合一
$ git commit -am ""

\# 查看缓存区内容
$ git status

\# 撤销合并

$ git merge --abort

\# 暂存文件，可以切换分支

$ git stash

\# 恢复暂存的东西

$ git stash pop 

# Tips

- 空目录无法add，如果想add一个空目录，在它下面touch一个.gitignore文件

# 配置

使用 `git config` 可以配置 Git 的环境变量，这些变量可以存放在以下三个不同的地方：

- /etc/gitconfig 文件：系统中对所有用户都普遍适用的配置。若使用 git config 时用 `--system` 选项，读写的就是这个文件

- ~/.gitconfig 文件：用户目录下的配置文件只适用于该用户。若使用 git config 时用 `--global` 选项，读写的就是这个文件

- .git/config（当前项目）文件：这里的配置仅仅针对当前项目有效

> 每一个级别的配置都会覆盖上层的相同配置，所以 .git/config 里的配置会覆盖/etc/gitconfig 中的同名变量

## 常用指令

-- 取消git的文件权限改变造成的filemode变化

$ git config core.filemode false

-- 修改LOF

$ git config --global core.autocrlf true

-- 提交时转换为LF，检出时不转换

$ git config --global core.autocrlf input

-- 中文显示数字乱码问题

$ git config --global core.quotepath false

-- 解决每次push都需要输入账号密码的问题，配置记录账号密码

$ git config --global credential.helper store

# 代码回退

git checkout  
git revert  
git reset  
git restore  
git rm  

git reset 和 git checkout 既可用于提交也可用于单个文件的修改，而 git revert 只能用在提交层面。如果你只需要处理尚未合并到协作远程工作的本地提交，你可以使用这三者中任何一条命令。如果是协同工作且需要撤销远程分支中的提交，那么就用 git revert

\# 撤销最近提交以来暂存区和非暂存区的改动

$ git reset –-hard <commit_id>

\# 从 my_commit 中撤销非暂存区的改动

$ git checkout my commit

\# 撤销 my_commit 中的更改，当用 revert 撤销改动时，它会产生新的提交

$ git revert my commit

# 修改历史 commit message

\# 这里的 n 需要确切的数字，比如 1 代表最近一次提交， 3 表示最近三次提交，以此类推

$ git rebase -i HEAD~n

\# 修改需要修改的 commit id 前的 'pick' 为 'r' ，保存

\# 强制推送修改内容后提交

$ git push -f

# 垃圾回收

$ git gc --prune=now

# 提交信息

\# 统计提交次数和作者

$ git log | grep "^Author: " | awk '{print $2}' | sort | uniq -c | sort -k1,1nr

\# 每次修改的文件列表, 及文件修改的统计

$ git log --stat

\# 每次修改的文件列表, 显示状态

$ git log --name-status

\# 每次修改的文件列表

$ git log --name-only

\# 每次修改的文件列表

$ git whatchanged

\# 每次修改的文件列表, 及文件修改的统计

git whatchanged --stat

\# 显示本次文件修改内容
git status -s

# 修改已经提交的邮箱作者信息

```shell
#!/bin/sh

git filter-branch -f --env-filter '

OLD_EMAIL="zouxuanmin@megvii.com"
CORRECT_NAME="samin"
CORRECT_EMAIL="822085977@qq.com"

if [ "$GIT_COMMITTER_EMAIL" = "$OLD_EMAIL" ]
then
export GIT_COMMITTER_NAME="$CORRECT_NAME"
export GIT_COMMITTER_EMAIL="$CORRECT_EMAIL"
fi
if [ "$GIT_AUTHOR_EMAIL" = "$OLD_EMAIL" ]
then
export GIT_AUTHOR_NAME="$CORRECT_NAME"
export GIT_AUTHOR_EMAIL="$CORRECT_EMAIL"
fi
' --tag-name-filter cat -- --branches --tags
```

# cherry-pick

\# 复制任何分支的 commit 到当前分支，指令只需要一个或多个 commit_id 参数即可

$ git cherry-pick <commit_id1> <commit_id2>

# 项目配置和全局配置

可以用文本格式修改，也可以用命令行的方式来修改

\# 项目配置

$ git config user.name=samin

\# 全局配置

$ git config --global user.name=samin

## 项目配置

$ vim .git/config

## 全局配置

一些通用配置可以放在全局配置中

$ vim ~/.gitconfig

```
[user]
    name=samin
    email=822085977@qq.com
[core]
    filemode=false
    bare=false
    logallrefupdates=true
    symlinks=false
    ignorecase=true
    quotepath=false
    autocrlf=input
    editor=vim
[credential]
    helper=store
```