package com.samin.jobadmin.service;

import com.samin.jobadmin.bean.JobLogVo;
import com.samin.jobadmin.bean.PageReq;
import com.samin.jobadmin.entity.JobLog;
import com.samin.jobadmin.repository.JobLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobLogService {

    private final JobLogRepository jobLogRepository;

    public Page<JobLogVo> page(PageReq<Void> req) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), sort);
        Page<JobLog> jobLogs = jobLogRepository.findAll(pageable);
        return jobLogs.map(JobLogVo::getInstance);
    }
}
