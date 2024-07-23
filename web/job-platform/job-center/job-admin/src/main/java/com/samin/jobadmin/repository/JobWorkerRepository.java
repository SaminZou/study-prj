package com.samin.jobadmin.repository;

import com.samin.jobadmin.entity.JobWorker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobWorkerRepository extends JpaRepository<JobWorker, Integer> {

    Optional<JobWorker> findFirstByAddress(String address);

    List<JobWorker> findByGroupId(Integer groupId);
}