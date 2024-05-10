package com.samin.jobadmin.controller;

import com.samin.jobadmin.bean.JobWorkerVo;
import com.samin.jobadmin.service.JobWorkerService;
import com.samin.jobsdk.bean.BaseResp;
import com.samin.jobsdk.bean.JobWorkerRegisterDto;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jobWorker")
@RequiredArgsConstructor
public class JobWorkerController {

    private final JobWorkerService jobWorkerService;

    @GetMapping("/page")
    public BaseResp<PageResp<JobWorkerVo>> page(PageReq<Void> req) {
        return BaseResp.success(jobWorkerService.page(req));
    }

    @PostMapping("/register")
    public BaseResp<Void> register(@RequestBody JobWorkerRegisterDto dto) {
        jobWorkerService.register(dto);
        return BaseResp.success();
    }
}
