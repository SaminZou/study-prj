```yaml
title: Jenkins + Gerrit 完成 SpringBoot 项目的自动化部署
author: zouxuanmin
date: 2021-06-17
```
# 1 环境搭建

## 1.1 docker安装

- macos、windows 是安装 Docker Desktop

- linux 通过加载镜像源，通过内置包管理工具安装

详情查看官方文档

## 1.2 搭建 Jenkins

```bash
#!/bin/bash

# Jenkins environment setup command line

docker run -itd --name jenkins --restart=always \
-p 8080:8080 \
-p 50000:50000 \
--dns 114.114.114.114 \
--dns 8.8.8.8 \
-v ~/jenkins_home:/var/jenkins_home \
-e JAVA_OPTS=-Dhudson.util.ProcessTree.disable=true \
jenkins/jenkins:lts
```

### 1.2.1 知识点

#### jenkins 管理员密码

存放地址为 jenkins_home 的 secrets/initialAdminPassword

#### jenkins_home 值得关注的文件夹

- workspace 存放用于操作代码拉取、maven编译的项目代码

- jobs 用于存放保留job的一些产物，如测试报告、artifact存档等

### 1.2.2  Jenkins 需要用到的插件

- Blue Ocean

- Gerrit Trigger

- Publish Over SSH

- JUnit Plugin

### 1.2.3 创建pipeline

配置好 `Gerrit event`

```groovy
node() {
    def mvnHome
    
    // 配置只拉取变更的代码
    stage('Checkout') {
        checkout([$class: 'GitSCM',
        branches: [[name: '$GERRIT_REFSPEC']],
        doGenerateSubmoduleConfigurations: false,
        extensions: [],
        submoduleCfg: [],
        userRemoteConfigs: [[credentialsId: 'e947704f-0202-4935-a549-0ef24320322a',
        refspec: 'refs/changes/*:refs/changes/*',
        url: 'http://jenkins_test@192.168.43.140:8081/a/test_project']]])
    }
    
    // maven 构建程序，构建jar包
    stage('Build'){
        try {
            sh label: '', script: 'sh /var/jenkins_home/maven/bin/mvn clean package surefire:test'    
        } catch(Exception err) {
            junit 'target/surefire-reports/*.xml'
            currentBuild.result = 'FAILURE'
            throw err
        }
    }
    
    // 调用有docker环境的server进行docker镜像打包及镜像推送
    stage('Build-Docker-Image'){
        sshPublisher(publishers: [sshPublisherDesc(configName: 'samin-server', 
        transfers: [sshTransfer(cleanRemote: false, 
        excludes: '', 
        execCommand: '''sh /home/samin/workspace/docker-images-build.sh''',
        execTimeout: 120000, 
        flatten: false, 
        makeEmptyDirs: false, 
        noDefaultExcludes: false,
        patternSeparator: '[, ]+',
        remoteDirectory: '', 
        remoteDirectorySDF: false, 
        removePrefix: '', 
        sourceFiles: '')], 
        usePromotionTimestamp: false, 
        useWorkspaceInPromotion: false, 
        verbose: false)])
    }
    
    // 停止远程服务器的服务
    stage('Terminal'){
        sshPublisher(publishers: [sshPublisherDesc(configName: 'samin-server', 
        transfers: [sshTransfer(cleanRemote: false, 
        excludes: '', 
        execCommand: '''sh /home/samin/workspace/stop.sh && sh /home/samin/workspace/docker-app-stop.sh''',
        execTimeout: 120000, 
        flatten: false, 
        makeEmptyDirs: false, 
        noDefaultExcludes: false,
        patternSeparator: '[, ]+',
        remoteDirectory: '', 
        remoteDirectorySDF: false, 
        removePrefix: '', 
        sourceFiles: '')], 
        usePromotionTimestamp: false, 
        useWorkspaceInPromotion: false, 
        verbose: false)])
    }
    
    // 发送最新包到服务器
    stage('Deploy'){
        sshPublisher(publishers: [sshPublisherDesc(configName: 'samin-server', 
        transfers: [sshTransfer(cleanRemote: false, 
        excludes: '',
        execCommand: '',
        execTimeout: 120000, 
        flatten: false, 
        makeEmptyDirs: false,
        noDefaultExcludes: false, 
        patternSeparator: '[, ]+', 
        remoteDirectory: 'workspace',
        remoteDirectorySDF: false,
        removePrefix: 'target/', 
        sourceFiles: 'target/TestProject.jar')], 
        usePromotionTimestamp: false,
        useWorkspaceInPromotion: false, 
        verbose: false)])
    }
    
    // 运行项目
    stage('Run') {
        sshPublisher(publishers: [sshPublisherDesc(configName: 'samin-server', 
        transfers: [sshTransfer(cleanRemote: false,
        excludes: '',
        execCommand: 'sh /home/samin/workspace/start.sh && sh /home/samin/workspace/docker-app-start.sh',
        execTimeout: 10000,
        flatten: false,
        makeEmptyDirs: false,
        noDefaultExcludes: false,
        patternSeparator: '[, ]+',
        remoteDirectory: '',
        remoteDirectorySDF: false,
        removePrefix: '',
        sourceFiles: '')],
        usePromotionTimestamp: false,
        useWorkspaceInPromotion: false,
        verbose: false)])
    }
    
    // 操作最终结果
    stage('Results') {
        junit 'target/surefire-reports/*.xml'
        archiveArtifacts 'target/*.jar'
    }
}
```

## 1.3 搭建Gerrit

```bash
#!/bin/bash

# Gerrit environment setup command line

docker run -itd --name gerrit --restart=always \
-p 8081:8080 \
-p 29418:29418 \
-v ~/docker-data/gerrit/git-volume:/var/gerrit/git \
-v ~/docker-data/gerrit/index-volume:/var/gerrit/index \
-v ~/docker-data/gerrit/cache-volume:/var/gerrit/cache \
-e CANONICAL_WEB_URL=http://10.42.1.31:8081 \
gerritcodereview/gerrit
```

### 1.3.1 关键步骤

- 创建项目

- 创建两个用户，一个供 Jenkins 操作使用，一个模拟开发者使用

- 创建 label verified

$ git init

$ git remote add origin ssh://admin@ip:port/All-Projects

$ git fetch origin refs/meta/config:config

$ git checkout config

\# 修改 `projet.config`

```
[label "Verified"]
       function = MaxWithBlock
       value = -1 Fails
       value =  0 No score
       value = +1 Verified
```

\$ git add . && git commit -m 'update config'

\$ git push origin HEAD:refs/meta/config

### 1.3.2 知识点

- Label: Code-Review

  主要是针对代码审查后的结果，一共5个等级

- Label: Verified

  主要针对的是编译和单元测试的结果，一个3个值

## 1.4 搭建registry2

```bash
#!/bin/bash

# registry2

docker run -itd --name registry --restart=always \
-p 5000:5000 \
-v ~/docker-data/registry:/var/lib/registry \
-e REGISTRY_STORAGE_DELETE_ENABLED="true" \
registry:2
```

> \# 需要配置 `/etc/docker/daemon.json` 把进行的 `registry` 推送请求设置为不需要https验证
> { "insecure-registries":["ip:port"]}
> $ systemctl restart docker.service

### 1.4.1 registry仓库验证正确性

\# 查看仓库
$ curl http://127.0.0.1:5000/v2/_catalog

\# 查看tag
$ curl http://127.0.0.1:5000/v2/test_project/tags/list

# 2 `test_project` 项目配置

创建一个 `test_project`项目，`pom.xml` 关键点插件的配置如下

```xml
<!-- 构建单元测试的xml测试报告 -->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <includes>
            <include>**/*Test.java</include>
        </includes>
        <excludes>
            <exclude>**/Test*.java</exclude>
        </excludes>
    </configuration>
</plugin>

<!-- docker操作插件 -->
<plugin>
    <groupId>com.spotify</groupId>
    <artifactId>docker-maven-plugin</artifactId>
    <version>1.2.2</version>
    <configuration>
        <forceTags>true</forceTags>
        <imageName>192.168.43.140:5000/test_project:latest</imageName>
        <baseImage>java</baseImage>
        <maintainer>822085977@qq.com</maintainer>
        <workdir>/ROOT</workdir>
        <entryPoint>["java", "-jar", "${project.build.finalName}.jar"]</entryPoint>
        <resources>
            <resource>
                <targetPath>/ROOT</targetPath>
                <directory>${project.build.directory}</directory>
                <include>${project.build.finalName}.jar</include>
            </resource>
        </resources>
    </configuration>
</plugin>
```

# 3 结果验证

\# jar包发布

$ curl http://127.0.0.1:8090/hello

\# docker容器发布

$ curl http://127.0.0.1:8091/hello

# 4 附录

使用到的 shell 脚本如下：

**docker-images-build.sh**

```bash
#!/bin/bash

export JAVA_HOME=/usr/local/jdk

# 此处省略拉取最新代码步骤

# 进行docker构建和推送
cd /home/samin/prj/test_project
/usr/local/maven/bin/mvn clean package docker:build docker:push
```

**docker-app-start.sh**

```bash
#!/bin/bash

docker pull 192.168.43.140:5000/test_project:latest

docker run -itd --name test_project-app --restart=always \
-p 8091:8090 \
192.168.43.140:5000/test_project:latest
```

**docker-app-stop.sh**

```bash
#!/bin/bash

docker rm -f test_project-app
docker rmi -f 192.168.43.140:5000/test_project:latest
```

**start.sh**

```bash
#!/bin/sh

nohup /usr/local/jdk/bin/java -jar /home/samin/workspace/TestProject.jar > /home/samin/workspace/api.log 2>&1 &
```

**stop.sh**

```bash
#!/bin/sh

BAK_FILE_NAME=TestProject_$(date +%Y%m%d%H%M%S).jar
APPID=$(/usr/local/jdk/bin/jps | grep 'TestProject.jar' | awk '{print $1}')

if [ "$APPID" != "" ]
  then
    kill -15 $APPID
    mkdir -p backup
    cp TestProject.jar backup/$BAK_FILE_NAME
    echo "TestProject is shutdown !"
fi
```