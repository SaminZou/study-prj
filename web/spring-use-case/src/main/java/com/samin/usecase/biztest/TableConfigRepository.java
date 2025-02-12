package com.samin.usecase.biztest;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableConfigRepository extends JpaRepository<TableConfig, Integer> {

    Optional<TableConfig> findFirstByCode(String code);
}
