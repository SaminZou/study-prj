package com.samin.tdengine;

import java.util.List;
import java.util.Map;
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
        // 新增超级表
        jdbcTemplate.execute(
                "create stable if not exists iot_original(ts timestamp, val double) tags (tcode binary(64), pcode binary(64), m int, f int, flag int )");

        // 新增子表
        jdbcTemplate.execute(
                "create table if not exists iot_original_d001 using iot_original(tcode,pcode,m,f,flag) tags ('bibt','jinyu',1001,1001,1)");

        // 插入数据
        jdbcTemplate.execute("insert into iot_original_d001(ts,val) values(now, 10.1)");
        jdbcTemplate.execute("insert into iot_original_d001(ts,val) values(now, 10.2)");

        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from iot_original_d001");
        result.forEach(System.out::println);

        // 新增子表
        jdbcTemplate.execute(
                "create table if not exists iot_original_d002 using iot_original(tcode,pcode,m,f,flag) tags ('bibt','jinyu',1002,1002,1) "
                        + "if not exists iot_original_d003 using iot_original(tcode,pcode,m,f,flag) tags ('bibt','jinyu',1003,1003,1)");

        // 插入数据
        jdbcTemplate.execute("insert into iot_original_d002 values(now, 10.4)");
        jdbcTemplate.execute("insert into iot_original_d003(ts,val) values(now, 10.5)");
        jdbcTemplate.execute("insert into iot_original_d003(ts,val) values(now, 10.6)");

        result = jdbcTemplate.queryForList("select * from iot_original");
        result.forEach(System.out::println);
    }
}
