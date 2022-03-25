```yaml
title: Keycloak 入门
author: samin
date: 2022-02-22
```

# Keycloak 是什么

# 支持访问控制机制

- Attribute-based access control (ABAC) 基于属性
- Role-based access control (RBAC) 基于角色
- User-based access control (UBAC) 基于用户
- Context-based access control (CBAC) 基于上下文
- Rule-based access control 基于规则
- Time-based access control 基于时间

# 重要概念

Users: 用户，使用并需要登录系统的对象

Roles: 角色，用来对用户的权限进行管理

Clients: 客户端，需要接入Keycloak并被Keycloak保护的应用和服务

Realms: 领域，领域管理着一批用户、证书、角色、组等，一个用户只能属于并且能登陆到一个域，域之间是互相独立隔离的， 一个域只能管理它下面所属的用户

# 授权处理流程

## 资源管理（Resource Management）

create resource server -> create resource -> create and associate scope

资源服务、资源、范围关系：

资源服务包含资源，资源包含范围

## 权限和策略管理（Permission and Policy Management）

策略是用来定义满足哪些条件才可以访问资源，策略本身不与资源关联，要想策略起作用就需要在相应资源配置权限，将资源及策略进行关联

## 策略执行（Policy Enforcement）

策略执行需要在资源服务内集成一个Policy Enforcement Point（PEP，资源执行点），以便与Keycloak服务进行通信获取相关的权限信息等，从而决定哪些资源可以被访问

# 开发方式

1. 新建域
2. 新建角色
3. 新建用户
4. 绑定用户角色
5. 新建客户端
6. 配置客户端，打开授权
7. 授权面板（设置、资源、授权范围、策略、权限、评估、导出设置）

## 设置

- Policy Enforcement Mode：指定授权服务器接受到请求时策略如何执行

  - Enforcing：当资源没有配置关联的策略时，请求默认被拒绝访问，这也是默认的选项
  - Permissive：当资源没有配置关联的策略时，请求允许访问
  - Disabled：禁用所有资源的所有访问策略
  
- Decision Strategy：表示权限最终是如何计算的策略，以决定相应的资源是否能获得授权

  - Affirmative ：至少一个权限计算做出正向决定
  - Unanimous：所有的权限计算都要做出正向决定

## 资源

录入 api 粒度的数据，注意 scope 的配置

## 授权范围

## 策略

Policies下主要配置哪些策略，配置的策略用来在权限设置中与资源进行关联，Keycloak本身支持很多策略，详细的策略说明可参考官方文档，使用最常使用的基于角色(Role Based)的策略

## 权限

Permission 下就是用来配置资源、策略如何进行关联，并且在有多个策略关联时采用何种策略（Decision Strategy）最终决定资源是否能被授权，Decision Strategy的配置项与上文 Settings 下的意义相同

## 评估

模拟请求配置情况

# 国际化配置

选择 "Master" 领域，领域配置 -> 主题 -> 开启国际化 -> 默认语言 "zh-CN" -> 退出登录清除缓存重新登录

# 核心概念

## Realm

默认情况下，Keycloack 提供了一个叫 Master 的 realm，这个 Master 不承担具体应用和用户的管理，它只用来管理其它 realm 的生命周期

## User

管理 -> 用户 -> 添加用户 -> 写入唯一用户名 -> 开启邮箱认证 -> 点击凭据 -> 输入初始化密码，开启临时凭据（第一次登录时需要修改密码）

## authentication

识别和验证用户的过程

## authorization

可以使用什么功能

## credentials

证明用户身份的凭证。可能是密码、一次性密码、数字证书以及指纹

## roles

角色是 RBAC 的重要概念，用于表明用户的身份类型。

## user role mapping

用户角色映射关系。通常一个用户可能有多个角色，一个角色也可以对应不同的人

## composite roles

复合角色，角色的从属关系或者说继承关系。B 角色从属于 A 角色，那么你拥有了 A 角色就一定拥有 B 角色的权限。

## groups

用户组，你可以将一系列的角色赋予定义好的用户组，一旦某用户属于该用户组，那么该用户将获得对应组的所有角色权限

## clients

通常指一些需要向 keycloak 请求以认证一个用户的应用或者服务，甚至可以说寻求 keycloak 保护并在 keycloak 上注册的请求实体都是客户端

## client adapters

keycloak 为了支持多语言和跨平台而设计的适配器，比如适配 Java 的、适配 Python 的。有些是内置的实现，有些需要我们按照 keycloak 的抽象定义来实现。后续我们主要和Spring Boot Adapter打交道

## identity provider

用来认证用户的服务，简称 IDP 。keycloak 本身就是一个 IDP。这个类似 Spring Security 中的 AuthenticationProvider 接口

