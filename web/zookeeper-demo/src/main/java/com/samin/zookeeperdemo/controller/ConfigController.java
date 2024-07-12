package com.samin.zookeeperdemo.controller;

import com.samin.zookeeperdemo.bean.ConfigVO;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private CuratorFramework curatorFramework;

    @PostMapping("/create")
    public String create(@RequestBody ConfigVO configVO) throws Exception {
        return curatorFramework.create()
                .withMode(CreateMode.PERSISTENT)
                .forPath("/samin-config", configVO.getValue()
                        .getBytes());
    }

    @PostMapping("/setValue")
    public String setValue(@RequestBody ConfigVO configVO) throws Exception {
        curatorFramework.setData()
                .forPath("/samin-config", configVO.getValue()
                        .getBytes());

        return "success";
    }
}