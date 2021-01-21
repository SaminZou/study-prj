```properties
title: Git 标签
author: samin
date: 2021-01-21
```

# 解决问题

可以定位对应具体哪个 commitid 为哪个版本。

一般发版常用做法是通过 tag 定位版本，或者也可以拉一个特定分支来做为版本记录。具体需要看产品管理如何取舍。

# 常用操作

\# 查看本地标签，以下操作效果一样

\$ git tag

\$ git tag -l

\$ git tag -list

\# 查看远程仓库所有标签，以下操作效果一样

\$ git ls-remote --tags

\$ git ls-remote --tag

\# 给当前分支打标签

\$ git tag <tagName>

\# 给固定某个 commit 打标签，以下操作效果一样

\$ git tag <tagName> -m 'tag information' <commitid>

\$ git tag <tagName> <commitid> -m 'tag information'

\# 删除本地标签，以下操作效果一样

\$ git tag --delete <tagName>

\$ git tag -d <tagName>

\$ git tag --d <tagName>

\# 推送所有标签到远程，以下操作效果一样

\$ git push origin --tags

\$ git push origin --tag

\$ git push --tags

\$ git push --tag

\# 推送某个标签

\$ git push origin <tagName>

\# 查看某一个标签

\$ git show <tagName>

\# 删除远程标签，以下操作效果一样

\$ git push -d origin <tagName>

\$ git push --delete origin <tagName>

\$ git push origin -d <tagName>

\$ git push origin --delete <tagName>

\$ git push origin :<tagName>

\# 根据某个 commit 创建分支

\$ git checkout <commitid> -b <branchName>