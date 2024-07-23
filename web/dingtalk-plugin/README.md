# 创建 docker 镜像

$ docker build -t <repo>/dingtalk-plugin:v1.0.0 .

# 推送 docker 镜像

$ docker push <repo>/dingtalk-plugin:v1.0.0

# Docker Image 使用

docker-compose.yaml

```yaml
version: "3.6"

services:
    dingtalk-plugin:
        container_name: dingtalk-plugin
        restart: always
        image: <repo>/dingtalk-plugin:v1.0.0
        environment:
          - custom.dingtalk-url=https://oapi.dingtalk.com/robot/send?access_token=xxx
        ports:
            - 8090:8090
```