```yaml
title: OAuth2.0 认证授权
author: samin
date: 2022-02-10
```

# OAuth2.0 是什么

开放授权（OAuth）是一个开放标准，允许用户让第三方应用访问该用户在某一网站上存储的私密的资源（如照片，视频，联系人列表），而无需将用户名和密码提供给第三方应用。`OAuth允许用户提供一个令牌，而不是用户名和密码来访问他们存放在特定服务提供者的数据。`每一个令牌授权一个特定的网站（例如，视频编辑网站)在特定的时段（例如，接下来的2小时内）内访问特定的资源（例如仅仅是某一相册中的视频）。这样，OAuth让用户可以授权第三方网站访问他们存储在另外服务提供者的某些特定信息，而非所有内容。

> OAuth 2.0 是为了授权而不是认证；OIDC 协议是 OAuth 2.0 协议的下一代，是身份认证协议升级的不二之选

| 认证（Authentication） | 授权（Authorization）|
| 证明你是你 | 确定用户是否有权限 |
| 需要用户提供证书（credentials）证明自己身份 | 根据规则判断用户是否有权限 |
| 发生在授权之前 | 发生在认证之后 |
| 例子：学生进入图书馆，需要出示学生证，表明是自己 | 进入图书馆后，学生能够进入自习室，但不能进入没有开发的区域 |

# OIDC 是什么

OpenID Connect 是 OAuth2.0 的超集，是基于 OAuth2.0 的认证授权协议

OpenID Connect 是在OAuth2.0 协议基础上增加了身份验证层 （identity layer）

# 主要元素

- Third-party application：第三方应用程序，本文中又称"客户端"（client）
- Authorization server：认证服务器，即服务提供商专门用来处理认证的服务器
- Resource server：资源服务器，即服务提供商存放用户生成的资源的服务器。它与认证服务器，可以是同一台服务器，也可以是不同的服务器
- Resource Owner：资源所有者，本文中又称"用户"（user）
- HTTP service：HTTP服务提供商，本文中简称"服务提供商"

## Demo

第三方应用：京东
资源服务器：QQ
资源所有者：用户
认证授权服务器：QQ 授权服务器负责用户的身份认证和授权，管理第三方应用、受保护资源、资源所有者之间的关系

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/OpenID Connect工作流程.jpg)

# 授权方式

> 区分应用场景和用法

- 授权码模式（authorization code）

![](https://raw.githubusercontent.com/SaminZou/pic-repo/master/BlogPicture/oauth2授权码方式.png)

- 简化模式（implicit）
- 密码模式（resource owner password credentials）
- 客户端模式（client credentials）

> https://www.ruanyifeng.com/blog/2019/04/oauth-grant-types.html

一般微博、QQ第三方登录采用授权码模式；spring开发者常用密码模式或者客户端模式；简化模式不常用。

项目中使用 spring security 框架搭建 Oauth2 认证。token 的存储一般选择使用 Redis，一是性能比较好，二是自动过期的机制，符合 token 的特性。

## client模式

没有用户的概念，直接与认证服务器交互，用配置中的客户端信息去申请 accessToken，客户端有自己的 client_id,client_secret 对应于用户的 username,password，而客户端也拥有自己的 authorities，当采取 client 模式认证时，对应的权限也就是客户端自己的 authorities。

## password模式

自己本身有一套用户体系，在认证时需要带上自己的用户名和密码，以及客户端的 client_id,client_secret。此时，accessToken 所包含的权限是用户本身的权限，而不是客户端的权限。

# Spring security

AuthenticationManager 和 AuthenticationProvider 以及 UserDetailsService 的关系

## password模式

### 参数

- username  
- password  
- grant_type=password  
- scope=select  
- client_id  
- client_secret  

## 返回参数

- access_token  
- token_type:"bearer"  
- refresh_token  
- expires_in  
- scope:"select"  

## client模式

### 参数

- grant_type=client_credentials
- scope=select
- client_id
- client_secret

### 返回参数

- access_token
- token_type:"bearer"
- expires_in
- scope:"select"

认证服务器：配置客户端资源（数据库中获取或者内存中获取）、存token、加密方式

资源服务器：权限认证

spring security oauth2 框架使用数据库载入客户端信息，很重要的一点是数据表结构要按照官方提供的 schema，否则无法载入数据

# 为什么得到授权码之后，还要再访问一次，将授权码换成访问令牌（access_token）

- Back channel
  高度安全的，服务器与服务器之间的通信
- Front channel
  安全性相对较低，浏览器/移动 app 与服务器的通信

参考：

https://www.jianshu.com/p/103e2dc6593b

http://www.ruanyifeng.com/blog/2014/05/oauth_2_0.html

https://blog.csdn.net/u013815546/article/details/76977239

https://www.cnblogs.com/heartlifes/p/7493403.html