package com.samin.jobsdk.api;

import com.samin.jobsdk.bean.BaseResp;

// TODO 注册工作节点
public interface JobWorkerApi {

    BaseResp<Void> register();
}
