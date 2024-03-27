package com.samin.jobserver.controller;

import com.samin.jobserver.bean.BaseResp;
import com.samin.jobserver.bean.JobSaveVo;
import com.samin.jobserver.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/save")
    public BaseResp<JobSaveVo> save(@RequestBody JobSaveVo req) {
        return BaseResp.success(jobService.save(req));
    }
}
