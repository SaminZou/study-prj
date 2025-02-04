package com.samin.zookeeperdemo.nodecache;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConfigNodeCache {

    @Autowired
    private CuratorFramework curatorFramework;

    public void event() throws Exception {
        NodeCache nodeCache = new NodeCache(curatorFramework, "/samin-config");
        nodeCache.start();
        nodeCache.getListenable()
                 .addListener(() -> {
                     log.info("{} path nodeChanged", "/samin-config");
                     byte[] newData = nodeCache.getCurrentData()
                                               .getData();
                     log.info("{} path new value: {}", "/samin-config", new String(newData));
                 });
    }
}