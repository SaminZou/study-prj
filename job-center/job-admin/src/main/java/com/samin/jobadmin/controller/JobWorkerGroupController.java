package com.samin.jobadmin.controller;

import com.samin.jobadmin.bean.BaseResp;
import com.samin.jobadmin.bean.JobWorkerGroupSaveVo;
import com.samin.jobadmin.service.JobWorkerGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobWorkerGroup")
@RequiredArgsConstructor
public class JobWorkerGroupController {

    private final JobWorkerGroupService jobWorkerGroupService;

    @PostMapping("/save")
    public BaseResp<JobWorkerGroupSaveVo> save(@RequestBody JobWorkerGroupSaveVo req) {
        return BaseResp.success(jobWorkerGroupService.save(req));
    }
}
