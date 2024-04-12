package com.samin.jobadmin.controller;

import com.samin.jobadmin.bean.BaseResp;
import com.samin.jobadmin.bean.JobSaveVo;
import com.samin.jobadmin.bean.JobVo;
import com.samin.jobadmin.bean.PageReq;
import com.samin.jobadmin.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/page")
    public BaseResp<Page<JobVo>> page(PageReq req) {
        return BaseResp.success(jobService.page(req));
    }

    @PostMapping("/save")
    public BaseResp<JobSaveVo> save(@RequestBody JobSaveVo req) {
        return BaseResp.success(jobService.save(req));
    }

    @PostMapping("/disable/{id}")
    public BaseResp<Void> disable(@PathVariable Integer id) {
        jobService.disable(id);
        return BaseResp.success();
    }

    @DeleteMapping("/delete/{id}")
    public BaseResp<Void> delete(@PathVariable Integer id) {
        jobService.delete(id);
        return BaseResp.success();
    }
}
