package com.samin.auth.repo;

import com.samin.auth.entity.UserRoleRelation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelation, Integer> {

}
