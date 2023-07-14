package com.samin.auth.repo;

import com.samin.auth.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemLogRepository extends JpaRepository<SystemLog, Integer> {

}
