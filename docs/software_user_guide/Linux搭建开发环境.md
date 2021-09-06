```yaml
title: Linux搭建开发环境 
author: samin
date: 2021-05-14
```

# 解压包

\# 一般来说，都是网上下载 Linux 用的压缩包
\$ tar -C /usr/local -xzvf xxx.tar.gz

如Go为例：

\$ tar -C /usr/local -xzvf go1.13.1.linux-amd64.tar.gz

# 配置环境变量

\#编辑文本加入（文末插入）  
\$ sudo vi /etc/profile

```shell
# java env    
export JAVA_HOME=/usr/local/jdk    
export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
export PATH=$PATH:$JAVA_HOME/bin

# maven env
export MAVEN_HOME=/usr/local/maven
export PATH=$PATH:$MAVEN_HOME/bin

# go env
export GOROOT=/usr/local/go
export GOPATH=/home/bruce/goProject
export GOBIN=$GOPATH/bin
export PATH=$PATH:$GOROOT/bin     
export PATH=$PATH:$GOPATH/bin
```
\# 退出vi编辑器，使环境变量设置立即生效  
\$ source /etc/profile

> 如果使用的是 zsh，修改的是 .zshrc 文件

# 验证

\$ go version

\$ java -version

\$ node -v

# Tips

- maven的本地仓库需要配置

- 对于开发环境linux一般安装在/usr目录下，同时在/etc/profile中配置环境变量；

- 配置好java环境后，tomcat可能还是无法检测到JAVA_HOME变量，可以在catalina.sh中修改，主要不是最后，到关键字“$var must be set to either true or false.”所在行数，上面添加JAVA_HOME配置
