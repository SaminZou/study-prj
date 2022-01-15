```yaml
title: WSL入门
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

### 配置结构

![](https://github.com/SaminZou/pic-repo/raw/master/BlogPicture/terminal%E9%85%8D%E7%BD%AE.png)

### 优化配置

```json
{
  "$schema": "https://aka.ms/terminal-profiles-schema",
  "defaultProfile": "{2c4de342-38b7-51cf-b940-2309a097f518}",
  "copyOnSelect": false,
  "copyFormatting": false,
  "profiles": {
    "defaults": {
      "launchMode": "fullscreen"
    },
    "list": [
      {
        "guid": "{2c4de342-38b7-51cf-b940-2309a097f518}",
        "hidden": false,
        "name": "ubuntu-20.4",
        "source": "Windows.Terminal.Wsl",
        "closeOnExit": true,
        "fontSize": 18,
        // please attention here, the second part maybe Ubuntu/Ubuntu18/Ubuntu-20.4, or other ... 
        "startingDirectory": "\\\\wsl$\\Ubuntu\\home\\samin",
        "colorScheme": "self-made-wsl2",
        "fontFace": "DejaVu Sans Mono for Powerline"
      },
      {
        "guid": "{61c54bbd-c2c6-5271-96e7-009a87ff44bf}",
        "name": "Windows PowerShell",
        "commandline": "powershell.exe",
        "hidden": false
      },
      {
        "guid": "{0caa0dad-35be-5f56-a8ff-afceeeaa6101}",
        "name": "命令提示符",
        "commandline": "cmd.exe",
        "hidden": false
      },
      {
        "guid": "{b453ae62-4e3d-5e58-b989-0a998ec441b8}",
        "hidden": false,
        "name": "Azure Cloud Shell",
        "source": "Windows.Terminal.Azure"
      }
    ]
  },
  "schemes": [
    {
      "name": "self-made-wsl2",
      "black": "#1e1e1e",
      "red": "#be0f17",
      "green": "#868715",
      "yellow": "#cc881a",
      "blue": "#377375",
      "purple": "#a04b73",
      "cyan": "#578e57",
      "white": "#978771",
      "brightBlack": "#7f7061",
      "brightRed": "#f73028",
      "brightGreen": "#aab01e",
      "brightYellow": "#f7b125",
      "brightBlue": "#719586",
      "brightPurple": "#c77089",
      "brightCyan": "#7db669",
      "brightWhite": "#e6d4a3",
      "background": "#1e1e1e",
      "foreground": "#e6d4a3"
    }
  ],
  "keybindings": [
    {
      "command": {
        "action": "copy",
        "singleLine": false
      },
      "keys": "ctrl+c"
    },
    {
      "command": "paste",
      "keys": "ctrl+v"
    },
    {
      "command": "find",
      "keys": "ctrl+shift+f"
    },
    {
      "command": {
        "action": "splitPane",
        "split": "auto",
        "splitMode": "duplicate"
      },
      "keys": "alt+shift+d"
    }
  ]
}
```

## vim

\# 修改 vim ，添加一些通用配置

$ vim ~/.vimrc

```
syntax on
set nocompatible
set backspace=indent,eol,start
set number
set cindent
set tabstop=4
set shiftwidth=4
set expandtab
set smartindent
:set mouse=a
```

## ZSH

### 字体优化( Windows 环境)

\# 安装字体集合，`注意这几行是在 powershell 里面执行`

$ git clone https://github.com/powerline/fonts.git

$ cd fonts

\# 打开可以执行 `.ps1` 的权限，选择 `y`

$ set-executionpolicy remotesigned

$ .\install.ps1

### 安装 zsh + oh-my-zsh

> Linux 环境安装 zsh
> 
> $ sudo apt update
>
> $ sudo apt install git zsh -y

> Windows 环境安装 zsh
>
> 下载地址：https://packages.msys2.org/package/zsh?repo=msys&variant=x86_64

> 将解压后的所有文件合并至 git 的安装目录

\# 安装 oh-my-zsh

$ sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

### zsh 设置

\# 编辑 `~/.zshrc`

```properties
# 主题配置， `ys`、`agnoster` 比较常用
ZSH_THEME="ys"

# 取消自动更新
DISABLE_UPDATE_PROMPT=true
```

#### agnoster 主题配置

\# 美化 `agnoster` 主题，让显示的文字更短

$ vim ~/.oh-my-zsh/themes/agnoster.zsh-theme

修改 `prompt_context()`：

```shell
prompt_context() {
  prompt_segment green black "%(!.%{%F{yellow}%}.)%n"
}
```

> TIPS: Shell 命令行每行显示的信息术语为 "prompt"
> 
> 运行完
> 
> $ source ~/.zshrc
> 
> 使配置生效

### 安装插件（可选）

\# 自动补全

$ git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions

\# 语法高亮

$ git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting

\# 修改 ~/.zshrc 中的 plugins

```properties
# 用空格分隔控件
plugins=( [plugins...] zsh-autosuggestions zsh-syntax-highlighting)
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