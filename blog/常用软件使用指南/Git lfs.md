```yaml
title: Git lfs
author: samin
date: 2023-09-24
```

lfs（Large File Storage）

# Windows 环境

- 安装 git lfs 扩展包

- git 添加插件

$ git lfs install

- 添加 lfs 匹配

$ git lfs track "*.pdf"

- 配置持久化

$ git add .gitattributes

- 正常添加文件

$ git add test.pdf 
$ git commit -m 'Add pdf file' 
$ git push

- 卸载

$ git lfs uninstall