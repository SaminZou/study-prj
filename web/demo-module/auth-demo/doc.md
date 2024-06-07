# Get Start

执行 com.samin.auth.service.UserServiceTest.initUser() 可以插入初始化用户

# 常见需求

- 系统认证授权

- SSO

- 免登录

如第三方想快速集成系统，用于展示用，需要做免登录（也叫隐式登录）

- 支持授权第三方集成

# 业内比较好的中间件

- Keycloak

基于 OAuth2.0 和 OpenID Connect (OIDC) 协议的开源身份和访问管理解决方案。它提供了用户身份验证、单点登录（SSO）、多因素身份验证、授权等功能，并且可以与现有的用户数据库进行集成

- Apereo CAS

开源的企业级单点登录系统，支持多种身份验证协议和多种身份验证方式，可以与多种认证系统进行集成。它还提供了统一的授权管理、访问控制和会话管理等功能

- Shiro

一款轻量级的 Java 安全框架，提供了认证、授权、密码加密、会话管理等功能，可以与多种用户数据库进行集成。它的设计理念是简单易用，可以快速地集成到现有的应用程序中

- Spring Security

是 Spring 框架的安全模块，提供了认证、授权、密码加密、会话管理等功能，可以与多种用户数据库进行集成。它还提供了与 Spring
框架的无缝集成，并且具有丰富的扩展性和灵活性

# 实现 UserDetailsService 接口获取用户信息时推荐抛错

- UsernameNotFoundException：当无法找到指定用户名的用户时，可以抛出此异常。表示用户不存在或未找到。
- DisabledException：当用户被禁用或处于禁用状态时，可以抛出此异常。表示用户被禁用，无法进行身份验证。
- LockedException：当用户被锁定或处于锁定状态时，可以抛出此异常。表示用户被锁定，无法进行身份验证。
- BadCredentialsException：当用户提供的凭据（如密码）无效或不匹配时，可以抛出此异常。表示凭据不正确。
- AccountExpiredException：当用户帐户已过期时，可以抛出此异常。表示用户帐户已过期，无法进行身份验证。
- CredentialsExpiredException：当用户凭据（如密码）已过期时，可以抛出此异常。表示用户凭据已过期，需要重置或更新。