package com.samin.usecase.repo.repository;

import com.samin.usecase.repo.entity.TableUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<TableUser, Integer> {}
