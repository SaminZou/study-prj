package com.samin.auth.repo;

import com.samin.auth.entity.MenuResourceRelation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuResourceRelationRepository extends JpaRepository<MenuResourceRelation, Integer> {

    List<MenuResourceRelation> findByMenuId(Integer menuId);
}
