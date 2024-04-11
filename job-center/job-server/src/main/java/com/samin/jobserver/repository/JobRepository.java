package com.samin.jobserver.repository;

import com.samin.jobserver.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByProcessTimeBeforeAndIsDeleteAndIsEnable(LocalDateTime localDateTime, Integer isDelete, Integer isEnable);
}