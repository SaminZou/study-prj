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
BoldAsFont=yes
FontHeight=20
Scrollbar=none
Term=xterm-256color
BoldAsColour=yes
Black=7,54,66
Red=220,50,47
Green=133,153,0
Yellow=181,137,0
Blue=38,139,210
Magenta=211,54,130
Cyan=42,161,152
White=238,232,213
BoldBlack=0,43,54
BoldRed=203,75,22
BoldGreen=88,110,117
BoldYellow=101,123,131
BoldBlue=131,148,150
BoldMagenta=108,113,196
BoldCyan=147,161,161
BoldWhite=253,246,227
ForegroundColour=192,192,192
BackgroundColour=0,43,54
CursorColour=133,153,0
Columns=80
Rows=25

BoldAsFont=-1
Locale=zh_CN
Charset=UTF-8
Font=Consolas
Transparency=off
CursorType=block
CursorBlinks=yes
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