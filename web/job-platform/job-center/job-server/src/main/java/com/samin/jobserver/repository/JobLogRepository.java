package com.samin.jobserver.repository;

import com.samin.jobserver.entity.JobLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobLogRepository extends JpaRepository<JobLog, Integer> {

}