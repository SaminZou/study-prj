package com.samin.zookeeperdemo.service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnowflakeIdWorker {

    @Autowired
    private CuratorFramework curatorFramework;

    //工作节点的路径
    private String pathPrefix = "/samin/idmaker/worker-";
    private String pathRegistered = null;

    /**
     * 在zookeeper中创建临时节点并写入信息
     */
    public void initData() throws Exception {

        // 创建一个 ZNode 节点
        // 节点的 payload 为当前 worker 实例
        byte[] payload = pathPrefix.getBytes();
        pathRegistered = curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath(pathPrefix, payload);
    }

    public long getId() {
        String sid = null;
        if (null == pathRegistered) {
            throw new RuntimeException("节点注册失败");
        }
        int index = pathRegistered.lastIndexOf(pathPrefix);
        if (index >= 0) {
            index += pathPrefix.length();
            sid = index <= pathRegistered.length() ? pathRegistered.substring(index) : null;
        }

        if (null == sid) {
            throw new RuntimeException("节点ID生成失败");
        }

        return Long.parseLong(sid);
    }
}