package com.samin.auth.repo;

import com.samin.auth.entity.RoleMenuRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleMenuRelationRepository extends JpaRepository<RoleMenuRelation, Integer> {

    List<RoleMenuRelation> findByRoleId(Integer roleId);
}