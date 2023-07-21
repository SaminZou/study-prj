package com.samin.auth.config;

import com.samin.auth.entity.*;
import com.samin.auth.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SystemInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final MenuRepository menuRepository;
    private final ResourceRepository resourceRepository;
    private final RoleMenuRelationRepository roleMenuRelationRepository;
    private final MenuResourceRelationRepository menuResourceRelationRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO redis 缓存所有的 role，menu，resource
        List<Role> roles = roleRepository.findAll();
        List<Menu> menus = menuRepository.findAll();
        List<Resource> resources = resourceRepository.findAll();

        // TODO 生成 Map<Integer, List<Integer>>
        List<RoleMenuRelation> roleMenus = roleMenuRelationRepository.findAll();
        Map<Integer, List<Integer>> roleMenuMap = roleMenus.stream().collect(Collectors.groupingBy(RoleMenuRelation::getRoleId, Collectors.mapping(RoleMenuRelation::getMenuId, Collectors.toList())));

        // TODO 生成 Map<Integer, Set<Integer>>
        List<MenuResourceRelation> menuResources = menuResourceRelationRepository.findAll();
        Map<Integer, Set<Integer>> menuResourceMap = menuResources.stream().collect(Collectors.groupingBy(MenuResourceRelation::getMenuId, Collectors.mapping(MenuResourceRelation::getResourceId, Collectors.toSet())));
    }
}
