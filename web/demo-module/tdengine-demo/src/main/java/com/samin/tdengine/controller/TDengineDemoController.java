package com.samin.tdengine.controller;

import com.samin.tdengine.entity.IotDevice;
import com.samin.tdengine.service.TDengineDemoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
public class TDengineDemoController {

    private final TDengineDemoService tDengineDemoService;

    @PostMapping("insert_data")
    public void insertData() {
        tDengineDemoService.insertData();
    }

    @PostMapping("find_sub_table_data")
    public List<Map<String, Object>> findSubTableData() {
        return tDengineDemoService.findSubTableData();
    }

    @PostMapping("find_super_table_data")
    public List<IotDevice> findSuperTableData2Object() {
        return tDengineDemoService.findSuperTableData2Object();
    }
}
