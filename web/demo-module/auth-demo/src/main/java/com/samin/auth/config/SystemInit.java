package com.samin.auth.config;

import com.samin.auth.entity.*;
import com.samin.auth.repo.*;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
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
    private final RedissonClient redissonClient;

    @Override
    public void run(String... args) throws Exception {
        // redis 缓存所有的 role，menu，resource
        List<Role> roles = roleRepository.findAll();
        RList<Role> roleRedisList = redissonClient.getList("Roles");
        roleRedisList.clear();
        roleRedisList.addAll(roles);

        List<Menu> menus = menuRepository.findAll();
        RList<Menu> menuRedisList = redissonClient.getList("Menus");
        menuRedisList.clear();
        menuRedisList.addAll(menus);

        List<Resource> resources = resourceRepository.findAll();
        RList<Resource> resourceRedisList = redissonClient.getList("Resources");
        resourceRedisList.clear();
        resourceRedisList.addAll(resources);

        // 生成 Map<Integer, List<Integer>>
        List<RoleMenuRelation> roleMenus = roleMenuRelationRepository.findAll();
        Map<Integer, List<Integer>> roleMenuMap = roleMenus.stream().collect(Collectors.groupingBy(RoleMenuRelation::getRoleId, Collectors.mapping(RoleMenuRelation::getMenuId, Collectors.toList())));
        RMap<Integer, List<Integer>> roleMenuRedisMap = redissonClient.getMap("RoleMenuMap");
        roleMenuRedisMap.clear();
        roleMenuRedisMap.putAll(roleMenuMap);

        // 生成 Map<Integer, Set<Integer>>
        List<MenuResourceRelation> menuResources = menuResourceRelationRepository.findAll();
        Map<Integer, Set<Integer>> menuResourceMap = menuResources.stream().collect(Collectors.groupingBy(MenuResourceRelation::getMenuId, Collectors.mapping(MenuResourceRelation::getResourceId, Collectors.toSet())));
        RMap<Integer, Set<Integer>> menuResourceRedisMap = redissonClient.getMap("MenuResourceMap");
        menuResourceRedisMap.clear();
        menuResourceRedisMap.putAll(menuResourceMap);
    }
}
