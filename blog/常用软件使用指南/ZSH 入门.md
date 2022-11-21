```yaml
title: ZSH 入门
author: samin
date: 2021-10-01
```

# 安装 zsh + oh-my-zsh

\# Linux 环境安装 zsh

$  sudo apt update

$  sudo apt install -y git zsh 

\# 安装 oh-my-zsh

$ sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

\# 自动补全

$ git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions

\# 语法高亮

$ git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting

$ vim ~/.zshrc

```properties
ZSH_THEME="ys"

# 取消自动更新
DISABLE_UPDATE_PROMPT=true

# 用空格分隔控件
plugins=(git zsh-autosuggestions zsh-syntax-highlighting)
```

$ source ~/.zshrc