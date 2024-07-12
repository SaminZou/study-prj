package com.samin.zookeeperdemo.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZookeeperConfig {

    @Value("${zookeeper.host}")
    private String host;

    @Bean
    public CuratorFramework curatorFramework() {
        return CuratorFrameworkFactory.builder()
                .connectString(host)
                // 会话超时时间
                .sessionTimeoutMs(5000)
                // 连接超时时间
                .connectionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                // .namespace("demo-app")
                .build();
    }
}