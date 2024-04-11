package com.samin.jobadmin.repository;

import com.samin.jobadmin.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Integer> {

    List<Job> findByIsDeleteAndIsEnable(Integer isDelete, Integer isEnable);

    Optional<Job> findByNameAndIsDelete(String name, Integer isDelete);
}