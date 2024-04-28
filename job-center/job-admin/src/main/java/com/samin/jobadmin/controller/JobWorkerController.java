package com.samin.jobadmin.controller;

import com.samin.jobadmin.bean.BaseResp;
import com.samin.jobadmin.bean.JobWorkerGroupVo;
import com.samin.jobadmin.bean.PageReq;
import com.samin.jobadmin.bean.PageResp;
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
    public BaseResp<PageResp<JobWorkerGroupVo>> page(PageReq<Void> req) {
        return BaseResp.success(jobWorkerService.page(req));
    }
}
