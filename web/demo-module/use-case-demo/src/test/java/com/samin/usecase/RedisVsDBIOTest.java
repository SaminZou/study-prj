package com.samin.usecase;


import com.samin.usecase.repo.entity.TableUser;
import com.samin.usecase.repo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class RedisVsDBIOTest {

    @Autowired
    private RedisTemplate<String, TableUser> redisTemplate;
    @Autowired
    private UserRepository userRepository;

    private List<TableUser> getDataSet() {
        List<TableUser> set = new ArrayList<>();

        // 测试结论,花费时间比： Redis < DB batch < DB
        for (int i = 0; i < 100000; i++) {
            TableUser tableUser = new TableUser();
            tableUser.setUserName("test" + i);
            tableUser.setUserDesc("test");

            set.add(tableUser);
        }

        return set;
    }

    @Test
    public void redisTest() {
        List<TableUser> set = getDataSet();
        long start = System.currentTimeMillis();
        set.forEach(e -> {
            redisTemplate.opsForValue()
                    .set(e.getUserName(), e);
        });
        System.out.println("Redis 花费：" + ((System.currentTimeMillis() - start) / 1000) + " 秒");
    }

    @Test
    public void dbTest() {
        List<TableUser> set = getDataSet();
        long start = System.currentTimeMillis();
        set.forEach(e -> userRepository.save(e));
        System.out.println("DB 花费：" + ((System.currentTimeMillis() - start) / 1000) + " 秒");
    }

    // 缺点是只要有一组数据异常，比如格式或者长度问题，会导致整个插入失败
    @Test
    public void dbBatchTest() {
        List<TableUser> set = getDataSet();
        long start = System.currentTimeMillis();

        userRepository.saveAll(set);

        System.out.println("DB batch 花费：" + ((System.currentTimeMillis() - start) / 1000) + " 秒");
    }

    @Test
    public void test() {
        redisTest();
        dbTest();
        dbBatchTest();
    }
}
