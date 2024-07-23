package com.samin.jobserver.repository;

import com.samin.jobserver.entity.JobWorkerGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobWorkerGroupRepository extends JpaRepository<JobWorkerGroup, Integer> {

}