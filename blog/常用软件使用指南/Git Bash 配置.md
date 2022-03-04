```yaml
title: Git Bash 配置 
author: samin
date: 2021-03-11
```

# 前言

git bash 是针对windows环境开发的一套交互软件，以下配置都是优化配置。

# 配色主题配置

## 配置文件

$ vim ~/.minttyrc

## 用例

```properties
# 字体
# 字体
Font=Consolas
# 字体大小
FontHeight=16
# 背景色
BackgroundColour=0,0,0
BoldAsFont=no
# 本地化
Locale=zh_CN
# 编码
Charset=UTF-8
# 长
Columns=120
# 宽
Rows=30
```

# 去掉冗长的"用户@主机"（ 使用 oh-my-zsh 不需要配置 ）

\# 没有则新增

$ vim ~/.bash_profile

```shell
# 修改显示
parse_git_branch() {
  git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ (\1)/'
}
# Show User @ Name (still with git branch name)
# export PS1="\u@\h \W\[\033[32m\]\$(parse_git_branch)\[\033[00m\] $ "
# Or hide User @ Name (still with git branch name)
export PS1="\W\[\033[32m\]\$(parse_git_branch)\[\033[00m\] $ "
```

# Windows 安装 ZSH

下载地址：https://packages.msys2.org/package/zsh?repo=msys&variant=x86_64

将解压后的所有文件合并至 git 的安装目录

配置好 zsh 后，可以继续安装[oh-my-zsh](WSL 入门.md)