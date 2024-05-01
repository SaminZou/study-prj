package com.samin.jobserver.service;

import com.samin.jobserver.entity.JobWorker;
import com.samin.jobserver.repository.JobWorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobWorkerService {

    private final JobWorkerRepository jobWorkerRepository;

    public void save(JobWorker jobWorker) {
        LocalDateTime now = LocalDateTime.now();

        // insert
        Optional<JobWorker> jobWorkerOptional = jobWorkerRepository.findByAddress(jobWorker.getAddress());

        if (jobWorkerOptional.isPresent()) {
            // update status
            jobWorker.setStatus(1);
            jobWorker.setUpdateTime(now);

            jobWorkerRepository.save(jobWorker);
        } else {
            // add job worker
            jobWorker.setStatus(1);
            jobWorker.setCreateTime(now);

            jobWorkerRepository.save(jobWorker);
        }
    }
}
