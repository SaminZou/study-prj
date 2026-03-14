```yaml
title: WSL 入门
author: samin
date: 2021-02-06
```

# WSL是什么

全程 `Windows Subsystem for Linux` ，也就是 Windows 出了官方的 Linux 系统，不需要借助虚拟机等技术，安装好就一个稳定可以用的 Linux 系统。

跌跌撞撞，已经发展到 WSL2 版本了，WSL2 不是一个 Linux 系统，而是 Windows 给我们提供的一个可以跑 Linux 系统的环境，基于 WSL，我们可以在微软商店装安装需要的 Linux 发行版。

# WSL2 安装

https://docs.microsoft.com/zh-cn/windows/wsl/install-win10

# 锦上添花

## Windows Terminal

现在也已经被微软商店收录了，直接下载使用，直接会把安装好的 Linux 发行版集成进去。

## vim

\# 修改 vim ，添加一些通用配置

$ vim ~/.vimrc

> [参考](https://www.notion.so/vim-zsh-5bf5317ef224458aa08cd17c7abcd56e#c931e98f240841c19baacb11ecea536d)

$ source ~/.vimrc

## ZSH

### 安装 zsh + oh-my-zsh

> Windows 环境安装 zsh
>
> 下载地址：https://packages.msys2.org/package/zsh?repo=msys&variant=x86_64
> 将解压后的所有文件合并至 git 的安装目录（默认安装目录是 C:\Program Files\Git ）

\# 安装 oh-my-zsh

$ sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

### 安装插件（可选）

\# 自动补全

$ git clone https://github.com/zsh-users/zsh-autosuggestions ~/.oh-my-zsh/custom/plugins/zsh-autosuggestions

\# 语法高亮

$ git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ~/.oh-my-zsh/custom/plugins/zsh-syntax-highlighting

$ vim ~/.zshrc

```properties
# 主题配置， `ys`、`agnoster` 比较常用
ZSH_THEME="ys"

# 取消自动更新
DISABLE_UPDATE_PROMPT=true
# 用空格分隔控件
plugins=(git zsh-autosuggestions zsh-syntax-highlighting)
```

### Windows 修改 git bash 的默认指令集为 zsh（可选）

修改 ~/.bash_profile 没有则新增

```shell
# 如果添加了 .bashrc 文件，可以每次触发
source ~/.bashrc
```

修改 ~/.bashrc 没有则新增

```shell
# 修改 zsh 为默认shell
if [ -t 1 ]; then
  exec zsh
fi
```

# 遇到的一些问题

## ping 域名提示 Name or service not known

\# 检查 `/etc/resolv.conf` 文件，去掉内网解析服务器，然后直接重新尝试 ping 命令

$ vim /etc/resolv.conf