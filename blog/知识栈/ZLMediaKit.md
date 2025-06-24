```yaml
title: ZLMediaKit
author: samin
date: 2023-04-13
```

# ZLMediaKit 是什么不是什么

ZLMediaKit 是一个基于 C++11 的高性能运营级流媒体服务框架，支持多种协议 (RTSP/RTMP/HLS/HTTP-FLV/Websocket-FLV/GB28181/MP4),支持协议互转

- 它是一套高性能的流媒体服务框架，目前支持rtmp/rtsp/hls/http-flv流媒体协议
- 它采用的模型是多线程IO多路复用非阻塞式编程 (linux下采用epoll、其他平台采用select)

ZLMediaKit 不是一个视频播放器或者视频编辑器，而是一个流媒体服务框架

# 端口（默认配置）

- RTSP : 554

- RTMP : 1935