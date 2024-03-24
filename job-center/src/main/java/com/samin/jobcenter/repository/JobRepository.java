package com.samin.jobcenter.repository;

import com.samin.jobcenter.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Integer> {

}