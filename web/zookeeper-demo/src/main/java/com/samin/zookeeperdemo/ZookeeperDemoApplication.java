package com.samin.zookeeperdemo;

import com.samin.zookeeperdemo.nodecache.ConfigNodeCache;
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

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        curatorFramework.start();
        configNodeCache.event();
    }
}
