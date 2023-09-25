```yaml
title: Vim 基础操作 
author: samin
date: 2021-08-28
```

# 基础操作

h  左，或 Backspace 或方向键
j  下，或 Enter 或 +（要 Shift 键），或方向键
k  上，或 方向键或 -（不必 Shift 键）
l  右，或 Space 或方向键
Ctrl-f    即 PageDown 翻页。
Ctrl-b    即 PageUp 翻页。
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

[参考](https://www.notion.so/vim-zsh-5bf5317ef224458aa08cd17c7abcd56e#c931e98f240841c19baacb11ecea536d)

> ~/.vimrc文件是vim的配置文件，在每次vim编辑任何文件的时候都会自动读取并设置相关的信息，所以不需要进行 source

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

# 替换

测试文本：

```text
test 123 ddd
test 123 ddd
test 123 ddd
test 123 ddd
```

:%s/ddd/456/g

修改后文本

```text
test 123 456
test 123 456
test 123 456
test 123 456
```

# 常规操作 

ggVG = window 场景 CTRL + A = 全选