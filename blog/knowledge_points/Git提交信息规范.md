```yaml
title: Git Commit Meassge 提交规范
author: samin
date: 2021-01-12
```

# Purpose

record changelog to show the history of git .

# Format of the commit message

commit message = Header(Required) + Body(Option) + Footer(Option)

```
<type>: <subject>
<body>
<footer>
```

## Allow <type> values

| type     | description                |
| -------- | -------------------------- |
| feature  | 新功能                     |
| fix      | 修复bug                    |
| docs     | 文档更新                   |
| style    | 代码的格式，标点符号的更新 |
| refactor | 代码重构                   |
| test     | 更新测试                   |
| chore    | 非 src 或者 测试文件的更新 |
| build    | 构建系统或者包依赖更新     |
| perf     | 性能优化                   |
| ci       | CI 配置，脚本文件等更新    |

PS: The difference between **style** and **refactor** is whether there is a code change.

## <subject> style

- No chinese
- Begin all subject lines with a capital letter
- Do not end the subject line with a period
- Cannot be longer than 50 characters
- There must be a space between the subject and the colon
- These must be separated by a blank line
- Use the first person present tense, eg: "change" nor "changed"

To make it clear to view the changelog in the following ways

$ git log --pretty=format:%s

$ git log --grep feature

## <body> style

- Use the first person present tense, eg: "change" nor "changed"
- Wrap the body at 70 characters
- Use the body to explain what and why vs. how

## <footer> style

To record "Closes" or "BREAKING CHANGE"

# Example

feature: Data exchange SDK module

New SDK for robot to exchange data with AWS.

Closes #CORNERSTONE-95

# Revert

revert: feature: Data exchange SDK module

This revert commit 667ecc1654a317a13331b17617d973392f415f02.

# Recommend

To configure your default editor , it can be nano/emacs/vim

$ git config --global core.editor vim

Use commit command without -m , write the commit message more carefully

$ git commit

# 工具

- commitizen

- cz-conventional-emoji

- cz-conventional-changelog

> cz-conventional-emoji 和 cz-conventional-changelog 二选一，本教程选择了 cz-conventional-emoji 。

# 项目级安装

只介绍项目级的配置，全局配置可以自行百度

## 配置安装 node 环境

自行百度配置好环境

## 配置 package.json

没有这个文件则新建，添加如下内容：

```json
{
  "scripts": {
    "cz": "git-cz"
  },
  "config": {
    "commitizen": {
      "path": "node_modules/cz-conventional-emoji"
    }
  },
  "dependencies": {
    "commitizen": "latest",
    "cz-conventional-emoji": "latest"
  }
}
```

## 执行项目初始化

\$ npm install

## 使用提交脚本即可触发

\$ npm run cz

# 其他相关插件

- commitlint

- husky

- standard-version
