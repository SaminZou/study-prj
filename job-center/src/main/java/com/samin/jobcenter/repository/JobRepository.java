package com.samin.jobcenter.repository;

import com.samin.jobcenter.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByProcessTimeAfter(LocalDateTime localDateTime);
}