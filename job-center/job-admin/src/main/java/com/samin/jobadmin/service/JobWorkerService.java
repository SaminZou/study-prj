package com.samin.jobadmin.service;

import com.samin.jobadmin.bean.JobWorkerVo;
import com.samin.jobadmin.bean.PageReq;
import com.samin.jobadmin.bean.PageResp;
import com.samin.jobadmin.entity.JobWorker;
import com.samin.jobadmin.repository.JobWorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobWorkerService {

    private final JobWorkerRepository jobWorkerRepository;

    public PageResp<JobWorkerVo> page(PageReq<Void> req) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), sort);
        PageResp<JobWorker> jobWorkers = PageResp.success(jobWorkerRepository.findAll(pageable));

        return jobWorkers.map(JobWorkerVo::getInstance);
    }
}
