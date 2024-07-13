package com.samin.zookeeperdemo.controller;

import com.samin.zookeeperdemo.bean.ConfigVO;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

    @GetMapping("/getGlobalId")
    public String getGlobalId() throws Exception {
        String path = "/samin-global/id-";
        String destPath = curatorFramework.create()
                .creatingParentsIfNeeded()
                .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                .forPath(path);

        if (Objects.nonNull(destPath)) {
            // 获取末尾的序号
            int index = destPath.lastIndexOf(path);
            if (index >= 0) {
                index += path.length();
                return index <= destPath.length() ? destPath.substring(index) : "";
            }
        }

        return "success";
    }
}