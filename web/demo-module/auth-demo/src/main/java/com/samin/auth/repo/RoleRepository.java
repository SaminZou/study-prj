package com.samin.auth.repo;

import com.samin.auth.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    List<Role> findByCodeIn(List<String> codes);

    Optional<Role> findByCode(String code);

    void deleteByCode(String code);
}
