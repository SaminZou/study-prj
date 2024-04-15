package com.samin.jobadmin.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.samin.jobadmin.bean.JobSaveVo;
import com.samin.jobadmin.bean.JobVo;
import com.samin.jobadmin.bean.PageReq;
import com.samin.jobadmin.entity.Job;
import com.samin.jobadmin.exception.BusException;
import com.samin.jobadmin.exception.ExceptionEnums;
import com.samin.jobadmin.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    public Page<JobVo> page(PageReq<Void> req) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), sort);
        Page<Job> jobs = jobRepository.findByIsDeleteAndIsEnable(pageable, 0, 1);
        return jobs.map(JobVo::getInstance);
    }

    public JobSaveVo save(JobSaveVo req) {
        LocalDateTime now = LocalDateTime.now();

        Job job;
        // update
        if (Objects.nonNull(req.getId())) {
            Optional<Job> jobOptional = getJob(req.getId());
            if (jobOptional.isPresent()) {
                job = jobOptional.get();
                CopyOptions options = CopyOptions.create()
                        .ignoreNullValue();
                BeanUtil.copyProperties(req, job, options);
                job.setUpdateTime(now);

                if (StrUtil.isNotBlank(req.getCron())) {
                    CronExpression exp = CronExpression.parse(job.getCron());
                    LocalDateTime next = exp.next(now);
                    job.setProcessTime(next);
                }

                jobRepository.save(job);

                req.setId(job.getId());
            } else {
                ExceptionEnums.throwException(ExceptionEnums.JOB_NOT_EXIST_ERROR);
            }

            // insert
        } else {
            Optional<Job> jobOptional = jobRepository.findByNameAndIsDelete(req.getName(), 0);

            if (jobOptional.isPresent()) {
                ExceptionEnums.throwException(ExceptionEnums.JOB_EXIST_ERROR);
            }

            job = new Job();
            CopyOptions options = CopyOptions.create()
                    .ignoreNullValue();
            BeanUtil.copyProperties(req, job, options);
            CronExpression exp = CronExpression.parse(job.getCron());
            LocalDateTime next = exp.next(now);
            job.setProcessTime(next);
            job.setCreateTime(now);

            jobRepository.save(job);
            req.setId(job.getId());
        }

        return req;
    }

    public void disable(Integer id) {
        Optional<Job> jobOptional = getJob(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setIsEnable(0);
            job.setUpdateTime(LocalDateTime.now());
            jobRepository.save(job);
        } else {
            ExceptionEnums.throwException(ExceptionEnums.JOB_NOT_EXIST_ERROR);
        }
    }

    public void delete(Integer id) {
        Optional<Job> jobOptional = getJob(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setIsDelete(1);
            job.setUpdateTime(LocalDateTime.now());
            jobRepository.save(job);
        } else {
            ExceptionEnums.throwException(ExceptionEnums.JOB_NOT_EXIST_ERROR);
        }
    }

    private Optional<Job> getJob(Integer id) throws BusException {
        return jobRepository.findById(id);
    }
}
