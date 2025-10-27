# Spring Boot VS Spring Cloud Alibaba

Spring Cloud Alibaba 基于 Spring boot 封装了微服务治理的逻辑，开箱配置即用，可以让开发者专注业务逻辑开发。

# OpenFeign vs RestTemplate

## OpenFeign

- 声明式 HTTP 客户端：通过接口 + 注解方式实现服务调用
- 基于 Ribbon 的负载均衡：内置客户端负载均衡能力
- 与 Spring Cloud 整合：无缝对接 Eureka/Nacos 等注册中心

```
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-starter-openfeign</artifactId>
  <version>4.1.2</version>
</dependency>

// 开启 Feign 客户端扫描
@EnableFeignClients
// 开启服务发现功能
@EnableDiscoveryClient 
```

## 区别

| 特性 | RestTemplate | OpenFeign       |
| -- |--------------|-----------------|
|代码复杂度	| 需手动拼接 URL 和参数	 | 接口声明直接映射远程 API  |
|维护	| 修改 URL 需多处调整	 | 集中管理接口定义        |
|扩展性| 	自定义拦截器      | 	支持全局 / 局部拦截器配置 |


