package com.samin.tdengine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class TDengineDemoApplication implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(TDengineDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // 在 tdengine 中观测是否执行成功
        jdbcTemplate.execute(
                "create stable if not exists iot_original(ts timestamp, val double) tags (tcode binary(64), pcode binary(64), m int, f int, flag int )");
    }
}
