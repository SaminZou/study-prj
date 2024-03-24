package com.samin.jobcenter.repository;

import com.samin.jobcenter.entity.JobLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobLogRepository extends JpaRepository<JobLog, Integer> {

}