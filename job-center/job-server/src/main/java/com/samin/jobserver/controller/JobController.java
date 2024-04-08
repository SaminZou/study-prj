package com.samin.jobserver.controller;

import com.samin.jobserver.bean.BaseResp;
import com.samin.jobserver.bean.JobSaveVo;
import com.samin.jobserver.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/save")
    public BaseResp<JobSaveVo> save(@RequestBody JobSaveVo req) {
        return BaseResp.success(jobService.save(req));
    }

    @PostMapping("/disable/{id}")
    public BaseResp<Void> disable(@PathVariable Integer id) {
        jobService.disable(id);
        return BaseResp.success();
    }

    @PostMapping("/delete/{id}")
    public BaseResp<Void> delete(@PathVariable Integer id) {
        jobService.delete(id);
        return BaseResp.success();
    }
}
