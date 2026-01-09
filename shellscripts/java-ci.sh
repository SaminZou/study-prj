#!/bin/bash

set -e

# 项目根目录（脚本所在目录）
PROJECT_DIR="/home/ubuntu/app-be"

# Maven 本地仓库（宿主机）
MAVEN_REPO="/home/ubuntu/.m2"

# 构建命令
MVN_CMD="mvn clean package -DskipTests"

cd $PROJECT_DIR

git pull

echo "使用 Docker + Maven(JDK21) 开始构建..."
echo "项目目录: $PROJECT_DIR"

# 针对 JDK21 的编译，JDK17 用 maven:3.9.9-eclipse-temurin-17
docker run --rm \
  -v "$PROJECT_DIR":/build \
  -v "$MAVEN_REPO":/root/.m2 \
  -w /build \
  maven:3.9.9-eclipse-temurin-21 \
  bash -c "$MVN_CMD"

echo "✅ 构建完成"

docker build -t app-be:latest $PROJECT_DIR

cd $PROJECT_DIR

# 启动指令
