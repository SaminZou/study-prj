#!/bin/bash

# 服务器地址
APP_SERVER_HOST=192.168.1.110
APP_SERVER_PORT=22
APP_SERVER_USER=samin
TARGET_JAR_FILE=app.jar
START_UP_SCRIPT=/mnt/start-spring-boot-app.sh

# 1. 记录当前 commitid
CURRENT_COMMIT=$(git rev-parse HEAD)
echo "当前 commitid: $CURRENT_COMMIT"

# 2. 拉取最新代码
git pull

# 3. 判断是否有更新
#LATEST_COMMIT=$(git rev-parse HEAD)
#if [ "$CURRENT_COMMIT" == "$LATEST_COMMIT" ]; then
#  echo "代码没有更新，退出脚本"
#  exit 0
#else
#  echo "代码有更新，继续执行后续操作"
#fi

# 4. 编译项目
mvn clean package -DskipTest=true

# 5. 判断是否成功打包产物
if [ -d "target" ]; then
  echo "target 目录存在"
  if ls target/*.jar 1> /dev/null 2>&1; then
    echo "target 目录中存在 .jar 文件"
  else
    echo "target 目录中没有找到 .jar 文件"
    exit 1
  fi
else
  echo "target 目录不存在"
  exit 1
fi

# 6. 重命名
JAR_FILE=$(ls target/*.jar)
mv "$JAR_FILE" target/$TARGET_JAR_FILE
echo "已将 $JAR_FILE 重命名为 $TARGET_JAR_FILE"

# 7. 上传包
scp -P $APP_SERVER_PORT target/$TARGET_JAR_FILE $APP_SERVER_USER@$APP_SERVER_HOST:/mnt

# 8. 部署应用
ssh -p $APP_SERVER_PORT $APP_SERVER_USER@$APP_SERVER_HOST $START_UP_SCRIPT