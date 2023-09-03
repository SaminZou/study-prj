package com.samin.auth.repo;

import com.samin.auth.entity.Menu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByCodeIn(List<String> codes);
}
