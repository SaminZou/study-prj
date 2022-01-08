```yaml
title: docker镜像备份导入
author: samin
date: 2021-09-01
```

# 导入与导出

- 若是只想备份images，使用save、load即可

- 若是在启动容器后，容器内容有变化，需要备份，则使用export、import

# images

```shell
# 打包镜像
$ docker sava -o nginx.tar nginx:latest
# 恢复镜像
$ docker load -i nginx.tar
# 批量打包所有镜像
$ docker save $(docker images | grep -v REPOSITORY | awk 'BEGIN{OFS=":";ORS=" "}{print $1,$2}') -o all.tar
```