package com.samin.jobsdk.api;

import com.samin.jobsdk.bean.BaseResp;
import com.samin.jobsdk.bean.JobWorkerRegisterDto;

// TODO 注册工作节点
public interface JobWorkerApi {

    BaseResp<Boolean> register(JobWorkerRegisterDto dto);
}
