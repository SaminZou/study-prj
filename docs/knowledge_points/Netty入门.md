```yaml
title: Netty 入门
author: samin
date: 2021-10-25
```

# Netty 由来

## HTTP服务器的原理

1. 创建一个ServerSocket，监听并绑定一个端口

2. 一系列客户端来请求这个端口

3. 服务器使用Accept，获得一个来自客户端的Socket连接对象

4. 启动一个新线程处理连接
   1. 读Socket，得到字节流
   2. 解码协议，得到Http请求对象
   3. 处理Http请求，得到一个结果，封装成一个HttpResponse对象
   4. 编码协议，将结果序列化字节流
   5. 写Socket，将字节流发给客户端

5. 继续循环步骤3

> HTTP服务器之所以称为HTTP服务器，是因为编码解码协议是HTTP协议，如果协议是Redis协议，那它就成了Redis服务器，如果协议是WebSocket，那它就成了WebSocket服务器
> 使用Netty你就可以定制编解码协议，实现自己的特定协议的服务器

## 本质

JBoss做的一个Jar包

## 目的

快速开发高性能、高可靠性的网络服务器和客户端程序

## 优点

提供异步的、事件驱动的网络应用程序框架和工具

> 从最开始的 java.net + java.io 发展到 java.nio 再到现在的 netty 框架

# NIO 是什么

NIO的全称是NoneBlocking IO，非阻塞IO，区别与BIO，BIO的全称是Blocking IO，阻塞IO

> NIO: Non-blocking I/O，在Java领域，也称为New I/O

1. Accept是阻塞的，只有新连接来了，Accept才会返回，主线程才能继

2. Read是阻塞的，只有请求消息来了，Read才能返回，子线程才能继续处理

3. Write是阻塞的，只有客户端把消息收了，Write才能返回，子线程才能继续读取下一个请求

> 所以传统的多线程服务器是BlockingIO模式的，从头到尾所有的线程都是阻塞的。这些线程就干等在哪里，占用了操作系统的调度资源，什么事也不干，是浪费
> 那么NIO是怎么做到非阻塞的呢。它用的是事件机制。它可以用一个线程把Accept，读写操作，请求处理的逻辑全干了
> 如果什么事都没得做，它也不会死循环，它会将线程休眠起来，直到下一个事件来了再继续干活，这样的一个线程称之为NIO线程
> NIO流程如以下伪代码：

```
while true {
    events = takeEvents(fds)  // 获取事件，如果没有事件，线程就休眠
    for event in events {
        if event.isAcceptable {
            doAccept() // 新链接来了
        } elif event.isReadable {
            request = doRead() // 读消息
            if request.isComplete() {
                doProcess()
            }
        } elif event.isWriteable {
            doWrite()  // 写消息
        }
    }
}
```

## Netty 和 NIO 的关系

NIO并不是Java独有的概念，NIO代表的一个词汇叫着IO多路复用。它是由操作系统提供的系统调用，早期这个操作系统调用的名字是select，但是性能低下，后来渐渐演化成了Linux下的epoll和Mac里的kqueue。我们一般就说是epoll，因为没有人拿苹果电脑作为服务器使用对外提供服务。而Netty就是基于Java NIO技术封装的一套框架。为什么要封装，因为原生的Java NIO使用起来没那么方便，而且还有臭名昭著的bug，Netty把它封装之后，提供了一个易于操作的使用模式和接口，用户使用起来也就便捷多了

# 应用场景

有了Netty，你可以实现自己的HTTP服务器，FTP服务器，UDP服务器，RPC服务器，WebSocket服务器，Redis的Proxy服务器，MySQL的Proxy服务器等等

如果你想知道Nginx是怎么写出来的，如果你想知道Tomcat和Jetty是如何实现的，如果你也想实现一个简单的Redis服务器，那都应该好好理解一下Netty，它们高性能的原理都是类似的
