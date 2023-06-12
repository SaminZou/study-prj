package com.samin.usecase.repo.repository;

import com.samin.usecase.repo.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO, Integer> {

}
