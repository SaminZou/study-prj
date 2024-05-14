package com.samin.jobadmin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.samin.jobadmin.bean.JobWorkerGroupSaveVo;
import com.samin.jobadmin.bean.JobWorkerGroupVo;
import com.samin.jobadmin.entity.JobWorker;
import com.samin.jobadmin.entity.JobWorkerGroup;
import com.samin.jobadmin.exception.BusException;
import com.samin.jobadmin.exception.ExceptionEnums;
import com.samin.jobadmin.repository.JobWorkerGroupRepository;
import com.samin.jobadmin.repository.JobWorkerRepository;
import com.samin.jobsdk.bean.PageReq;
import com.samin.jobsdk.bean.PageResp;
import com.samin.jobsdk.enums.EnableEnum;
import com.samin.jobsdk.enums.StatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobWorkerGroupService {

    private final JobWorkerGroupRepository jobWorkerGroupRepository;
    private final JobWorkerRepository jobWorkerRepository;

    public PageResp<JobWorkerGroupVo> page(PageReq<Void> req) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), sort);
        PageResp<JobWorkerGroup> jobWorkerGroups = PageResp.success(jobWorkerGroupRepository.findAll(pageable));

        return jobWorkerGroups.map(JobWorkerGroupVo::getInstance);
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public JobWorkerGroupSaveVo save(JobWorkerGroupSaveVo req) {
        LocalDateTime now = LocalDateTime.now();

        JobWorkerGroup jobWorkerGroup;
        // update
        if (Objects.nonNull(req.getId())) {
            Optional<JobWorkerGroup> jobOptional = getJobWorkerGroup(req.getId());
            if (jobOptional.isPresent()) {
                jobWorkerGroup = jobOptional.get();
                CopyOptions options = CopyOptions.create()
                        .ignoreNullValue();
                BeanUtil.copyProperties(req, jobWorkerGroup, options);
                jobWorkerGroup.setUpdateTime(now);

                jobWorkerGroupRepository.save(jobWorkerGroup);

                // isEnable 等于 0 时，下线所有 Job Worker
                if (EnableEnum.parseByCode(jobWorkerGroup.getIsEnable()) == EnableEnum.DISABLE) {
                    List<JobWorker> jobWorkers = jobWorkerRepository.findByGroupId(jobWorkerGroup.getId());
                    jobWorkers.forEach(jobWorker -> {
                        jobWorker.setStatus(StatusEnum.OFFLINE.getCode());
                    });
                    jobWorkerRepository.saveAll(jobWorkers);
                }

                req.setId(jobWorkerGroup.getId());
            } else {
                ExceptionEnums.throwException(ExceptionEnums.JOB_WORKER_GROUP_NOT_EXIST_ERROR);
            }

            // insert
        } else {
            Optional<JobWorkerGroup> jobWorkerGroupOptional = jobWorkerGroupRepository.findByAppCode(req.getAppCode());

            if (jobWorkerGroupOptional.isPresent()) {
                ExceptionEnums.throwException(ExceptionEnums.JOB_WORKER_GROUP_EXIST_ERROR);
            }

            jobWorkerGroup = new JobWorkerGroup();
            CopyOptions options = CopyOptions.create()
                    .ignoreNullValue();
            BeanUtil.copyProperties(req, jobWorkerGroup, options);
            jobWorkerGroup.setCreateTime(now);

            jobWorkerGroupRepository.save(jobWorkerGroup);
            req.setId(jobWorkerGroup.getId());
        }

        return req;
    }

    public void disable(Integer id) {
        Optional<JobWorkerGroup> optional = getJobWorkerGroup(id);
        if (optional.isPresent()) {
            JobWorkerGroup jobWorkerGroup = optional.get();
            jobWorkerGroup.setIsEnable(0);
            jobWorkerGroup.setUpdateTime(LocalDateTime.now());
            jobWorkerGroupRepository.save(jobWorkerGroup);
        } else {
            ExceptionEnums.throwException(ExceptionEnums.JOB_WORKER_GROUP_NOT_EXIST_ERROR);
        }
    }

    public void delete(Integer id) {
        Optional<JobWorkerGroup> optional = getJobWorkerGroup(id);
        if (optional.isPresent()) {
            JobWorkerGroup jobWorkerGroup = optional.get();
            jobWorkerGroup.setIsDelete(1);
            jobWorkerGroup.setUpdateTime(LocalDateTime.now());
            jobWorkerGroupRepository.save(jobWorkerGroup);
        } else {
            ExceptionEnums.throwException(ExceptionEnums.JOB_WORKER_GROUP_NOT_EXIST_ERROR);
        }
    }

    private Optional<JobWorkerGroup> getJobWorkerGroup(Integer id) throws BusException {
        return jobWorkerGroupRepository.findById(id);
    }
}
