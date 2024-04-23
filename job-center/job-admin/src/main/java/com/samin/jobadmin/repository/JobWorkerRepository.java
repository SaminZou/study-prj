package com.samin.jobadmin.repository;

import com.samin.jobadmin.entity.JobWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobWorkerRepository extends JpaRepository<JobWorker, Integer> {

}