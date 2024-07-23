package com.samin.jobadmin.repository;

import com.samin.jobadmin.entity.JobLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobLogRepository extends JpaRepository<JobLog, Integer> {

}