package com.samin.jobserver.repository;

import com.samin.jobserver.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByProcessTimeBefore(LocalDateTime localDateTime);

    Optional<Job> findByName(String name);
}