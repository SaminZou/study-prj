FROM nginx:1.22.0

LABEL maintainer="822085977@qq.com"

# 安装 vim
# RUN apt-get update && apt-get install vim -y

# 复制本地文件到镜像指定目录中
ADD nginx.conf /etc/nginx/nginx.conf
ADD conf.d /etc/nginx/conf.d

# 指定挂载路径
VOLUME /usr/share/nginx/html
VOLUME /etc/nginx/conf.d

# 指定容器启动时监听的端口，可选项，没有声明在 docker-compose.yml 中直接声明创建也是可以正常进行端口映射
EXPOSE 80