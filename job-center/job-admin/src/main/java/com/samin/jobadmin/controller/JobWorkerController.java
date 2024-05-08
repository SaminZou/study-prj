package com.samin.jobadmin.controller;

import com.samin.jobsdk.bean.BaseResp;
import com.samin.jobadmin.bean.JobWorkerVo;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import com.samin.jobadmin.service.JobWorkerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobWorker")
@RequiredArgsConstructor
public class JobWorkerController {

    private final JobWorkerService jobWorkerService;

    @GetMapping("/page")
    public BaseResp<PageResp<JobWorkerVo>> page(PageReq<Void> req) {
        return BaseResp.success(jobWorkerService.page(req));
    }
}
