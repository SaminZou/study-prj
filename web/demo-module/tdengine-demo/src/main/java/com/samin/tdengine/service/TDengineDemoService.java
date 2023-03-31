package com.samin.tdengine.service;

import com.samin.tdengine.entity.IotDevice;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TDengineDemoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createSuperTable() {
        // 新增超级表
        jdbcTemplate.execute(
                "create stable if not exists iot_original(ts timestamp, val double) tags (tcode binary(64), pcode binary(64), m int, f int, flag int )");
    }

    public void insert() {
        // 新增子表
        jdbcTemplate.execute(
                "create table if not exists iot_original_d001 using iot_original(tcode,pcode,m,f,flag) tags ('bibt','jinyu',1001,1001,1)");
        jdbcTemplate.execute(
                "create table if not exists iot_original_d002 using iot_original(tcode,pcode,m,f,flag) tags ('bibt','jinyu',1002,1002,1) "
                        + "if not exists iot_original_d003 using iot_original(tcode,pcode,m,f,flag) tags ('bibt','jinyu',1003,1003,1)");
    }

    public void insertData() {
        // 插入数据
        jdbcTemplate.execute("insert into iot_original_d001(ts,val) values(now, 10.1)");
        jdbcTemplate.execute("insert into iot_original_d001(ts,val) values(now, 10.2)");
        jdbcTemplate.execute("insert into iot_original_d002 values(now, 10.4)");
        jdbcTemplate.execute("insert into iot_original_d003(ts,val) values(now, 10.5)");
        jdbcTemplate.execute("insert into iot_original_d003(ts,val) values(now, 10.6)");
    }

    public List<Map<String, Object>> findSubTableData() {
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from iot_original_d001");
        result.forEach(e -> log.info("{}", e));
        return result;
    }

    public List<Map<String, Object>> findSuperTableData() {
        // 查找数据
        List<Map<String, Object>> result = jdbcTemplate.queryForList("select * from iot_original");
        result.forEach(e -> log.info("{}", e));
        return result;
    }

    public List<IotDevice> findSuperTableData2Object() {
        // 查找数据返回实体类列表
        List<IotDevice> iotDevices = jdbcTemplate.query("select * from iot_original",
                new BeanPropertyRowMapper<>(IotDevice.class));
        log.info("{}", iotDevices);
        return iotDevices;
    }
}
