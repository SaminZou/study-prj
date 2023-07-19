package com.samin.auth.repo;

import com.samin.auth.entity.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelation, Integer> {

    List<UserRoleRelation> findByUserId(Integer id);
}
