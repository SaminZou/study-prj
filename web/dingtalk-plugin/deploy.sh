#!/bin/bash

DEV_REPO_URL=127.0.0.1:5000
REMOTE_REPO_URL=registry.aliyuncs.com/biz

mvn package -Dmaven.test.skip=true

docker build -t $DEV_REPO_URL/dingtalk-plugin:$1 .

docker push $DEV_REPO_URL/dingtalk-plugin:$1

docker tag $DEV_REPO_URL/dingtalk-plugin:$1 $REMOTE_REPO_URL/dingtalk-plugin:$1

docker push $REMOTE_REPO_URL/dingtalk-plugin:$1