package com.samin.jobadmin.controller;

import com.samin.jobsdk.bean.BaseResp;
import com.samin.jobadmin.bean.JobLogVo;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import com.samin.jobadmin.service.JobLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobLog")
@RequiredArgsConstructor
public class JobLogController {

    private final JobLogService jobLogService;

    @GetMapping("/page")
    public BaseResp<PageResp<JobLogVo>> page(PageReq<Void> req) {
        return BaseResp.success(jobLogService.page(req));
    }
}
