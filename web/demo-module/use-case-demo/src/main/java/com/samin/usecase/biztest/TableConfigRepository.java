package com.samin.usecase.biztest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TableConfigRepository extends JpaRepository<TableConfig, Integer> {

    Optional<TableConfig> findFirstByCode(String code);
}
