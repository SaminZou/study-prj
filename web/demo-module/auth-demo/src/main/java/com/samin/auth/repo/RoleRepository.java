package com.samin.auth.repo;

import com.samin.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findByIdIn(List<Integer> ids);
}
