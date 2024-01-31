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
public class AuthModuleInitializer implements CommandLineRunner {

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

        // 生成 Map<String, List<String>>
        List<RoleMenuRelation> roleMenus = roleMenuRelationRepository.findAll();
        Map<String, List<String>> roleMenuMap = roleMenus.stream()
                .collect(Collectors.groupingBy(RoleMenuRelation::getRoleCode,
                        Collectors.mapping(
                                RoleMenuRelation::getMenuCode,
                                Collectors.toList())));
        RMap<String, List<String>> roleMenuRedisMap = redissonClient.getMap("RoleMenuMap");
        roleMenuRedisMap.clear();
        roleMenuRedisMap.putAll(roleMenuMap);

        // 生成 Map<String, Set<String>>
        List<MenuResourceRelation> menuResources = menuResourceRelationRepository.findAll();
        Map<String, Set<String>> menuResourceMap = menuResources.stream()
                .collect(Collectors.groupingBy(
                        MenuResourceRelation::getMenuCode,
                        Collectors.mapping(
                                MenuResourceRelation::getResourceUrn,
                                Collectors.toSet())));
        RMap<String, Set<String>> menuResourceRedisMap = redissonClient.getMap("MenuResourceMap");
        menuResourceRedisMap.clear();
        menuResourceRedisMap.putAll(menuResourceMap);
    }
}
