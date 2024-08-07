#!/bin/bash

# 搜索项目中的 "Nexus3.yaml" 安装 Nexus

# $1 项目路径 $2 过滤需要指定模块
mavenProjectDeploy(){
  cd $1
  mvn clean compile package deploy -pl $2 -Dmaven.test.skip=true -DaltDeploymentRepository=<server_id>::default::http://127.0.0.1:8081/repository/maven-releases/
}

mavenProjectDeploy /d/study-prj/web/demo-module mq-demo/mq-common