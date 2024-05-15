package com.samin.jobadmin.service;

import com.samin.jobadmin.bean.JobWorkerVo;
import com.samin.jobadmin.entity.JobWorker;
import com.samin.jobadmin.entity.JobWorkerGroup;
import com.samin.jobadmin.repository.JobWorkerGroupRepository;
import com.samin.jobadmin.repository.JobWorkerRepository;
import com.samin.jobsdk.bean.JobWorkerRegisterDto;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import com.samin.jobsdk.enums.EnableEnum;
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

    private final JobWorkerGroupRepository jobWorkerGroupRepository;
    private final JobWorkerRepository jobWorkerRepository;

    public PageResp<JobWorkerVo> page(PageReq<Void> req) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), sort);
        PageResp<JobWorker> jobWorkers = PageResp.success(jobWorkerRepository.findAll(pageable));

        return jobWorkers.map(JobWorkerVo::getInstance);
    }

    public Boolean register(JobWorkerRegisterDto dto) {
        Optional<JobWorkerGroup> groupOptional = jobWorkerGroupRepository.findById(dto.getGroupId());
        // Job Worker Group 是否可用状态
        if (groupOptional.isEmpty()) {
            return Boolean.FALSE;
        } else {
            JobWorkerGroup jobWorkerGroup = groupOptional.get();
            if (EnableEnum.parseByCode(jobWorkerGroup.getIsEnable()) == EnableEnum.DISABLE) {
                return Boolean.FALSE;
            }
        }

        Optional<JobWorker> workerOptional = jobWorkerRepository.findFirstByAddress(dto.getAddress());

        JobWorker jobWorker;
        if (workerOptional.isPresent()) {
            jobWorker = workerOptional.get();
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

        return Boolean.TRUE;
    }
}
