```yaml
title: Vim基础操作 
author: samin
date: 2021-08-28
```

# 基础操作

h  左，或 Backspace 或方向键
j  下，或 Enter 或 +（要 Shift 键），或方向键
k  上，或 方向键或 -（不必 Shift 键）
l  右，或 Space 或方向键
Ctrl-f    即 PageDown 翻页。
Crtl-b    即 PageUp 翻页。
dd 快速删行
u 撤销
GG 文章末
gg 文章开头
0 行头跳转
v 选择内容
y 复制
P 粘贴
$ 跳至行首
0 跳至行尾

# 优化配置

> 编辑 ~/.vimrc 

```shell
syntax on
set number
set cindent
set tabstop=4
set shiftwidth=4
set expandtab
set smartindent
```

# 列操作

## 总流程

1. 在 普通模式 按 ctrl+v 进入 可视块 模式

2. 使用 hjkl 移动光标选择要编辑的块

3. 执行一些编辑命令

4. 按 esc 退出（非必须）

> 第三步是变化的

## 常用列操作

- 光标前插入内容
  shift+i，然后输入要插入的内容，编辑完后按 esc，vim会自动补全剩下的列

- 光标后插入内容
  shift+a，然后输入要插入的内容，编辑完后按 esc，vim会自动补全剩下的列

- 删除
  按 x 或者 d

- 更改
  按 c，然后输入内容编，辑完后按 esc

- 替换
  r

- 粘贴
  p