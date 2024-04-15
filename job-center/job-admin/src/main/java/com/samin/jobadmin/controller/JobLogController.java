package com.samin.jobadmin.controller;

import com.samin.jobadmin.bean.BaseResp;
import com.samin.jobadmin.bean.JobLogVo;
import com.samin.jobadmin.bean.PageReq;
import com.samin.jobadmin.service.JobLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobLog")
@RequiredArgsConstructor
public class JobLogController {

    private final JobLogService jobLogService;

    @GetMapping("/page")
    public BaseResp<Page<JobLogVo>> page(PageReq<Void> req) {
        return BaseResp.success(jobLogService.page(req));
    }
}
