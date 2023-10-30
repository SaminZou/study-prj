package com.samin.auth.repo;

import com.samin.auth.entity.UserRoleRelation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRelationRepository extends JpaRepository<UserRoleRelation, Integer> {

    List<UserRoleRelation> findByUserId(Integer id);

    void deleteByUserId(Integer userId);
}
