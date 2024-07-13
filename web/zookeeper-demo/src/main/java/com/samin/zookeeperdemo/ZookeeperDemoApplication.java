package com.samin.zookeeperdemo;

import com.samin.zookeeperdemo.nodecache.ConfigNodeCache;
import com.samin.zookeeperdemo.service.SnowflakeIdService;
import com.samin.zookeeperdemo.service.SnowflakeIdWorker;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ZookeeperDemoApplication implements CommandLineRunner {

    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private ConfigNodeCache configNodeCache;
    @Autowired
    private SnowflakeIdWorker snowflakeIdWorker;
    @Autowired
    private SnowflakeIdService snowflakeIdService;

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        curatorFramework.start();
        configNodeCache.event();
        snowflakeIdWorker.initData();
        snowflakeIdService.init(snowflakeIdWorker.getId());
    }
}
