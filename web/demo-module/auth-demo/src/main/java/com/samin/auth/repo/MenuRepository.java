package com.samin.auth.repo;

import com.samin.auth.entity.Menu;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByCodeIn(List<String> codes);

    /**
     * 带查询条件的分页声明方式
     *
     * @param page
     * @param name
     * @return
     */
    Page<Menu> findAllByNameLike(Pageable page, String name);
}
