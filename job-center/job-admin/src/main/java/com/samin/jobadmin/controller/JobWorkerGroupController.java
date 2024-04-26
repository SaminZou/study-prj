package com.samin.jobadmin.controller;

import com.samin.jobadmin.bean.*;
import com.samin.jobadmin.service.JobWorkerGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobWorkerGroup")
@RequiredArgsConstructor
public class JobWorkerGroupController {

    private final JobWorkerGroupService jobWorkerGroupService;

    @GetMapping("/page")
    public BaseResp<PageResp<JobWorkerGroupVo>> page(PageReq<Void> req) {
        return BaseResp.success(jobWorkerGroupService.page(req));
    }

    @PostMapping("/save")
    public BaseResp<JobWorkerGroupSaveVo> save(@RequestBody JobWorkerGroupSaveVo req) {
        return BaseResp.success(jobWorkerGroupService.save(req));
    }
}
