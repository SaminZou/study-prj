```yaml
title: 高并发下的 Nginx 性能优化
author: samin
date: 2020-02-07
```

`2019-nCoV肆虐，在家中学习了CSDN提供的Nginx优化课程，本篇文章为课程笔记+学习心得`

# 入门

- 轻量级web服务器，基于restful风格，以URI或者URL为沟通依据，通过HTTP协议提供各种网络服务，免费开源，软代理（F5为硬件代理）
  
- 请求会消耗内存，消耗CPU资源

- 提供了IMAP/POP3/SMTP服务
  
- 2004年10月4日开始，2013年4月24日1.4.0稳定版发布，C语言编写，跨平台，有自己的函数库（除了zlib、PCRE、OpenSSL之外）
  
- 占用内存少（3W并发，10个进程，消耗内存大约150M）
  
- 高并发能力强（实际环境中能达到2W~3W的并发连接数）
  
- 支持Rewrite(根据域名、URL不同，将HTTP请求分到不同的服务器)，内置健康检查
  
- 节省带宽（支持GZIP压缩，可以添加浏览器本地缓存的Header头）
  
- 应用场景
  
    - 功能

        1. 轻量级web服务器
           
        2. 负载、均衡
           
        3. 缓存（一般把静态文件直接放在nginx服务器中）
           
        4. 高并发

    - 应用

        1. 代理服务器
           
        2. IP负载、静态负载
           
        3. 动静分离（动态请求分发后台服务器，静态文件直接nginx服务器中取）
           
        4. 限流、健康监控
    
- linux服务器中应用
  
    - 安装
      
        - gcc-c++ （编译工具）
          
        - pcre pcre-devel（正则）
          
        - zlib zlib-devel（压缩包）
          
        - openssl openssl-devel（认证证书）
          
        - 下载最新版本的tar版本 -> tar指令解压 -> ./configure --prefix=/usr/local/nginx （配置生成Makefile）-> make（编译检查） -> make install（编译输出）
        
    - 常用命令
      
        - $ ./nginx # 启动
          
        - $ ./nginx -t # 测试配置文件是否正确
          
        - $ ./nginx -s stop # 强制停止
          
        - $ ./nginx -s quit # 待nginx进程任务完毕进行停止
          
        - $ ./nginx -s reload # 重启nginx
          
        - $ ps -ef | grep nginx # 查看进程
    
# 实战应用

- 配置文件详解
  
    - 目录
      
        - *_temp目录：存放运行的临时文件
          
        - conf目录：配置文件，主要是nginx.conf
          
        - html目录：存放静态资源
          
        - logs目录：存放访问日志和错误日志文件
          
        - sbin目录：nginx的二进制命令
        
    - nginx.conf文件
      
        - 核心模块
            - HTTP模块（代理、缓存、日志格式定义和第三方模块）
              
            - EVENTS模块（网络连接，如请求时间）
              
            - 全局模块（全局指令，日志路径、PID路径、用户信息等）
            
        - 基础模块
          
            - HTTP全局模块
              
            - HTTP FastCGI模块
              
            - HTTP Gzip模块
              
            - HTTP server模块（一个http可以有多个server）
              
                - HTTP location模块（请求路由，各种页面的处理）
                  
                - HTTP Rewrite模块
          
        - 第三方模块
          
            - HTTP Upstream Request Hash 模块
              
            - Notice 模块
              
            - Http Access Key 模块
    
- 代理模式（正向代理、反向代理、透明代理）
  
    - 透明代理
      
        - 基于路由器层面安装行为功能软件，不需要每个设备单独去装那个功能软件
        
    - 正向代理
      
        - 访问无法访问的资源
          
        - 缓存
          
        - 认证授权
          
        - 代理记录用户行为，对外隐藏用户信息（肉鸡）
      
    - 反向代理
      
        - 保证内网安全
          
        - 负载均衡
      
    - 有upstream关键词为反向代理
    
- web应用集群搭建

- 负载均衡

    - 轮询法（默认）

      ```
      upstream localhost{
          server host1:port;
          server host2:port;
          server host3:port;
      }
      
      server {
          listen 80;
          server_name localhost;
          location / {
              proxy_pass http://localhost;
          }
      }
      ```

    - 加权轮询法（weight），也叫权重法，优先把请求转发给性能高的服务器

      ```
      upstream localhost{
          server host1:port weight=10;
          server host2:port weight=1;
          server host3:port weight=15;
      }
      ```

    - 源地址哈希法（ip_hash），通过对源地址进行哈希函数算法得到一个数值后再对服务器数量进行取模运算，结果为服务器的序号，保证每个请求固定在一台服务器，解决session问题

      ```
      upstream localhost{
          ip_hash;
          server host1:port weight=1;
          server host2:port weight=1;
          server host3:port weight=1;
      }
      ```

    - 最小连接数法（least_conn），提高资源利用率，合理分流

      ```
      upstream localhost{
          least_conn;
          server host1:port weight=1;
          server host2:port weight=1;
          server host3:port weight=1;
      }
      ```

    - Fair（需要下载upstream_fair插件），可以根据页面大小和加载时间长短智能进行负载均衡，根据后台的响应时间长短来分配请求，响应时间短的优先分配任务

      ```
      upstream localhost{
          fair;
          server host1:port weight=1;
          server host2:port weight=1;
          server host3:port weight=1;
      }
      ```

    - hash（需要下载插件），和ip_hash不同的是根据url进行哈希函数，固定到一台服务器，进一步提高后端缓存服务器的效率

      ```
      upstream localhost{
          hash $request_uri;
          server host1:port weight=1;
          server host2:port weight=1;
          server host3:port weight=1;
      }
      ```

- 配置日志（所有工具分析问题的主要手段）

    - 通过日志可以知道用户地域来源、跳转来源、使用终端、某个URL访问量等信息
      
    - 通过错误日志，可以知道系统某个服务或server的性能瓶颈
      
    - 日志到根目录的logs/access.log文件中，默认使用"main"日志格式

- 动静分离（缓存）
  
    - 静态文件是可以暴露的，一般可以直接放在nginx服务器中供访问

    - 动态资源文件一般在类似tomcat的服务器中间件中，所以进行反向代理

      ```
      location ~ .*\.(html|htm|gif|jpg|jpeg|bmp|png|ico|txt|js|css) $ {
          root /opt/nginx/static;
          expires 30d;
      }
      ```

- 热部署（nginx本身热部署，web应用热部署）

#### 高并发配置

- 架构分析

    - 高并发：通过设计能够同时并行处理很多请求
      
    - 指标：响应时间、吞吐量（单位时间内处理的请求数量）、每秒查询率QPS、并发用户数
      
    - 提升并发能力：
      
        - 垂直扩展：增强单机硬件性能（最快，有效，治标不治本）；提升单机架构性能；优化业务逻辑；提高代码质量
          
        - 水平扩展：增加服务器数量，线性扩充系统性能

- 配置限流

    - 限流方式（前两种只能对单一IP限流，客户端限流）

        - limit_conn_zone

          ```
          http {
              # 开辟一个为one空间的内存进行请求处理
              limit_conn_zone $binary_remote_addr zone=one:10m;
              server {
                  # 并发处理数
                  limit_conn one 10;
              }
          }
          ```

        - limit_req_zone

          ```
          http {
              # 开辟一个为req_one空间的内存进行请求处理，rate=1r/s每秒钟每个地址只能请求一次
              limit_req_zone $binary_remote_addr zone=req_one:10m rate=1r/s;
              server {
                  # burst令牌桶算法，一共120块令牌，请求用完会释放资源，120块令牌用完后，多出的请求503
                  limit_req zone=req_one burst=120;
              }
          }
          ```

        - ngx_http_upstream_module（后台限流，推荐）

          ```
          upstream localhost{
              # 设置最大连接数，以前只在商业版本，后面版本开放了
              server host1:port weight=1 max_conns=10;
          }
          ```

    - 测试工具：Ab

        - httpd-tools-y
        - ab -c 10 -n 1000 http://localhost (-c 并发用户数 -n 单个用户请求次数)

- 安全配置

    - 版本安全

      ```
      http {
          # 返回前端显示server信息时候，不带版本号
          server_tokens off;
      }
      ```

    - IP安全

      ```
      # 白名单
      location / {
          allow 192.168.110.1;
          deny all;
      }
      
      # 黑名单
      location / {
          deny 192.168.110.1;
          allow all;
      }
      ```

    - 文件安全

      ```
      location /logs {
          # 列举文件目录
          autoindex on;
          root /usr/local/nginx/;
      }
      
      # 配置可以下载log文件
      location ^/logs~*\.(log|txt)$ {
          add_header Content-Type text/plain;
          root /usr/local/nginx/;
      }
      ```

    - 连接安全（开启HTTPS）

- 进程数、并发连接数

    - worker_processes 2; # 调整和CPU数量一致
      
    - events{ worker_connection 2048; } #  每个worker最大并发连接数，一般是1024*CPU核数
      
    - 系统优化
      
        - ulimit -a # 查看所有属性值
          
        - ulimit -Hn 10000 # 临时设置硬限制
          
        - ulimit -Sn 10000 # 设置软限制
          
        - 设置 "/etc/security/limits.conf"可以永久生效

  ```
  # 修改文件数
  * soft nofile 10240
  * hard nofile 10240
  
  # 修改进程数
  * soft noproc 60000
  * hard noproc 60000
  
  -----------------------------------------------------------------------------
  # 设置重启生效
  $ vi /etc/pam.d/login
  # 加入 session required /lib/security/pam_limits.so
  ```

- 长连接（http协议）

    - nginx长连接短连接，可以增强服务器的容灾能力
      
    - 条件：client到nginx是长连接；nginx到server是长连接
      
    - 配置：client的header请求携带"keep-alive"；nginx设置支持keep-alive
    
```
events {
    # 注意时间不能太大，防止大量无效的http让nginx崩溃
    keepalive_timeout 60;
}

http {
    # 也可以设置keepalive_timeout
    keepalive_timeout 60;
}
```

- 压缩

    - gzip可以节约带宽，提高响应速度，但是消耗了nginx性能
      
    - gzip可以配置http、server、location模块

- 状态监控

```
location /NginxStatus {
    # 打开监控
    stub_status on;
    # 是否记录日志，一般关闭
    access_log off;
}

# 如果配置好重启出现"stub_status"标签不存在，在解压包源文件中执行
# $ ./configure --prefix=/usr/local/nginx/ --with-http_stub_status_module

# $ ps -ef | grep nginx | wc -1 # 查看Nginx并发进程数
# netstat -n | awk '/^tcp/ {++S[$NF]} END {for(a in S) print a,S[a]}'
```

- 总结
  
    - nginx集群使用keepalived做为入口
      
    - 根据地域进行服务器选择，CDN加速
      
    - ELK