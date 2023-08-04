package com.samin.auth.repo;

import com.samin.auth.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByIdIn(List<Integer> ids);
}
