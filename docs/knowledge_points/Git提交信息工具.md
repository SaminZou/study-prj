```properties
title: Git Commit Meassge 提交规范
author: samin
date: 2021-01-12
```

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
