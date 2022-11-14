package com.samin.usecase;


import com.samin.usecase.entity.UserDO;
import com.samin.usecase.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@Slf4j
@SpringBootTest
public class RedisVsDBIOTest {

    @Autowired
    private RedisTemplate<String, UserDO> redisTemplate;
    @Autowired
    private UserRepository userRepository;

    private List<UserDO> getDataSet() {
        List<UserDO> set = new ArrayList<>();

        // 测试结论,花费时间比： Redis < DB batch < DB
        for (int i = 0; i < 100000; i++) {
            UserDO userDO = new UserDO();
            userDO.setUserName("test" + i);
            userDO.setUserDesc("test");

            set.add(userDO);
        }

        return set;
    }

    @Test
    public void redisTest() {
        List<UserDO> set = getDataSet();
        long start = System.currentTimeMillis();
        set.forEach(e -> {
            redisTemplate.opsForValue().set(e.getUserName(), e);
        });
        System.out.println("Redis 花费：" + ((System.currentTimeMillis() - start) / 1000) + " 秒");
    }

    @Test
    public void dbTest() {
        List<UserDO> set = getDataSet();
        long start = System.currentTimeMillis();
        set.forEach(e -> userRepository.save(e));
        System.out.println("DB 花费：" + ((System.currentTimeMillis() - start) / 1000) + " 秒");
    }

    @Test
    public void dbBatchTest() {
        List<UserDO> set = getDataSet();
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
