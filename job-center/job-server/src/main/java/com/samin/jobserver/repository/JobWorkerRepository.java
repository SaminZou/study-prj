package com.samin.jobserver.repository;

import com.samin.jobserver.entity.JobWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobWorkerRepository extends JpaRepository<JobWorker, Integer> {

}