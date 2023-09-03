package com.samin.auth.repo;

import com.samin.auth.entity.RoleMenuRelation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleMenuRelationRepository extends JpaRepository<RoleMenuRelation, Integer> {

    void deleteByRoleCode(String roleCode);

    List<RoleMenuRelation> findByRoleCode(String roleCode);
}