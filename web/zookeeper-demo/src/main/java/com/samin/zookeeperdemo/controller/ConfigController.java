package com.samin.zookeeperdemo.controller;

import com.samin.zookeeperdemo.bean.ConfigVO;
import com.samin.zookeeperdemo.service.SnowflakeIdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    private CuratorFramework curatorFramework;
    @Autowired
    private SnowflakeIdService snowflakeIdService;

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

    /**
     * 获取分布式 ID
     *
     * @return
     * @throws Exception
     */
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

        return "fail";
    }

    /**
     * 获取雪花 ID
     *
     * @return
     * @throws Exception
     */
    @GetMapping("/getSnowflakeId")
    public String getSnowflakeId() throws Exception {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        final HashSet idSet = new HashSet();
        Collections.synchronizedCollection(idSet);
        long start = System.currentTimeMillis();
        log.info(" start generate id *");
        int threadCount = 10;
        int turn = 50000;
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++)
            threadPool.execute(() -> {
                for (long j = 0; j < turn; j++) {
                    long id = snowflakeIdService.nextId();
                    synchronized (idSet) {
                        if (j % 10000 == 0) {
                            log.info("线程 {} 生成第 {} 个 id 为: {}", Thread.currentThread()
                                    .getName(), j, id);
                        }
                        idSet.add(id);
                    }
                }
                countDownLatch.countDown();
            });
        countDownLatch.await(50000, TimeUnit.MICROSECONDS);
        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
        long end = System.currentTimeMillis();
        log.info(" end generate id ");
        log.info("* cost " + (end - start) + " ms!");

        return "success";
    }
}