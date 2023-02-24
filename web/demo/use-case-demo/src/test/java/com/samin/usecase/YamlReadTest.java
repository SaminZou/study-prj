package com.samin.usecase;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * 读取 yaml 文件，数组类型
 *
 * @author samin
 * @date 2022-11-18
 */
@Slf4j
@SpringBootTest
@ActiveProfiles(value = {"dev", "test"})
public class YamlReadTest {

    @Value("${use_case.values}")
    private List<String> arr;

    @Value("${use_case.values2}")
    private List<String> arr2;

    @Value("${use_case.values3:value7,value8,value9}")
    private List<String> arr3;

    @Value("${use_case.values4:1,2,3}")
    private List<Integer> arr4;

    @Test
    public void test() {
        arr.forEach(System.out::println);
        arr2.forEach(System.out::println);
        arr3.forEach(System.out::println);
        arr4.forEach(System.out::println);
    }
}
