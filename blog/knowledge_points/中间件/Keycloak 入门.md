```yaml
title: Keycloak 入门
author: samin
date: 2022-02-22
```

# Keycloak 是什么

# 支持访问控制机制

- Attribute-based access control (ABAC)
- Role-based access control (RBAC)
- User-based access control (UBAC)
- Context-based access control (CBAC)
- Rule-based access control
- Time-based access control

# 授权处理流程

## 资源管理（Resource Management）

create resource server -> create resource -> create and associate scope

资源服务、资源、范围关系：

资源服务包含资源，资源包含范围

## 权限和策略管理（Permission and Policy Management）

策略是用来定义满足哪些条件才可以访问资源，策略本身不与资源关联，要想策略起作用就需要在相应资源配置权限，将资源及策略进行关联

## 策略执行（Policy Enforcement）

策略执行需要在资源服务内集成一个Policy Enforcement Point（PEP，资源执行点），以便与Keycloak服务进行通信获取相关的权限信息等，从而决定哪些资源可以被访问

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

