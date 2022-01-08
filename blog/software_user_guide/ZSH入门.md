```yaml
title: ZSH 入门
author: samin
date: 2021-10-01
```

# 安装 zsh + oh-my-zsh

\# Linux 环境安装 zsh

\$  sudo apt update

\$  sudo apt install -y git zsh 

\# 安装 oh-my-zsh

\$ sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

# zsh 设置

\# 编辑 `~/.zshrc`

```properties
ZSH_THEME="ys"

# 取消自动更新
DISABLE_UPDATE_PROMPT=true
```

# 安装插件（可选）

\# 自动补全

\$ git clone https://github.com/zsh-users/zsh-autosuggestions ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-autosuggestions

\# 语法高亮

\$ git clone https://github.com/zsh-users/zsh-syntax-highlighting.git ${ZSH_CUSTOM:-~/.oh-my-zsh/custom}/plugins/zsh-syntax-highlighting

\# 修改 ~/.zshrc 中的 plugins

```properties
# 用空格分隔控件
plugins=( [plugins...] zsh-autosuggestions zsh-syntax-highlighting)
```