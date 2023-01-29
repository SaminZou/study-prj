package com.samin.usecase.repository;

import com.samin.usecase.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDO, Integer> {

}
