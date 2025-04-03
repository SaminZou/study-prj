nginx web 容器镜像制作模板

# V1

1. 把需要的静态文件夹命名为 web

2. 执行：docker build -t 127.0.0.1:5000/web:master .

# V2

背景：有需要较频繁更新的静态资源，每次打包导致部署繁琐，不适用 V1 版本

1. 执行构建镜像：

$ docker build --no-cache -t 127.0.0.1:5000/web:master .

2. 启动容器：

$ docker-compose up -d

使用 sudo 的时候， $PWD 实效处理方案：

$ sudo PWD=$PWD docker-compose up -d

3. 更改宿主机中的 html 文件夹可以镜像静态资源更新操作