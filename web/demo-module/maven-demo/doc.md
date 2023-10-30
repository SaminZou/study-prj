# 背景

只需要上传 consumer 模块

# 使用以下指令上传 consumer

mvn clean compile package deploy -pl consumer -am -Dmaven.test.skip=true -DaltDeploymentRepository=samin-dev::
default::http://host:port/repository/maven-releases/

> -pl 指定只编译上传 consumer 包到 maven 仓库
> -am 可以把相关依赖包同时编译上传（maven-demo 和 common 项目）

# 其他项目引入 consumer 模块

实际上，引入 consumer 的同时，maven-demo 及 consumer 项目也会同时被导入到本地仓库