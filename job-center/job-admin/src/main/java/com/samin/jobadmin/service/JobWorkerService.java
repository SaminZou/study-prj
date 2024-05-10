package com.samin.jobadmin.service;

import com.samin.jobadmin.bean.JobWorkerVo;
import com.samin.jobadmin.entity.JobWorker;
import com.samin.jobadmin.repository.JobWorkerRepository;
import com.samin.jobsdk.bean.JobWorkerRegisterDto;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import com.samin.jobsdk.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public void register(JobWorkerRegisterDto dto) {
        Optional<JobWorker> optional = jobWorkerRepository.findByAddress(dto.getAddress());

        JobWorker jobWorker;
        if (optional.isPresent()) {
            jobWorker = optional.get();
            jobWorker.setGroupId(dto.getGroupId());
            jobWorker.setAddress(dto.getAddress());
            jobWorker.setStatus(StatusEnum.ONLINE.getCode());
            jobWorker.setUpdateTime(LocalDateTime.now());
        } else {
            jobWorker = new JobWorker();
            jobWorker.setGroupId(dto.getGroupId());
            jobWorker.setAddress(dto.getAddress());
            jobWorker.setStatus(StatusEnum.ONLINE.getCode());
            jobWorker.setCreateTime(LocalDateTime.now());
        }

        jobWorkerRepository.save(jobWorker);
    }
}
