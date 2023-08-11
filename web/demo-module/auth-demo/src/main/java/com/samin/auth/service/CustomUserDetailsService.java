package com.samin.auth.service;


import cn.hutool.core.collection.CollectionUtil;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.entity.*;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.repo.UserRepository;
import com.samin.auth.repo.UserRoleRelationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 自定义用户信息加载服务类
 *
 * @author samin
 * @date 2022-08-08
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRoleRelationRepository userRoleRelationRepository;
    private final UserRepository userRepository;
    private final RedissonClient redissonClient;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByMobile(username);
        if (!userOptional.isPresent()) {
            // 用这个的原因是因为可以被 AuthenticationEntryPoint 实现方法感知处理返回自定义报错
            throw new UsernameNotFoundException(ExceptionEnums.USER_NOT_EXIST_ERROR.getValue());
        }

        User user = userOptional.get();
        List<UserRoleRelation> userRoleRelations = userRoleRelationRepository.findByUserId(user.getId());

        // 缓存中全量获取数据
        RList<Role> roleRedisList = redissonClient.getList("Roles");
        List<Role> roles = roleRedisList.readAll();
        RList<Menu> menuRedisList = redissonClient.getList("Menus");
        List<Menu> menus = menuRedisList.readAll();
        RList<Resource> resourceRedisList = redissonClient.getList("Resources");
        List<Resource> resources = resourceRedisList.readAll();
        RMap<Integer, List<Integer>> roleMenuRedisMap = redissonClient.getMap("RoleMenuMap");
        Map<Integer, List<Integer>> roleMenuMap = roleMenuRedisMap.readAllMap();
        RMap<Integer, Set<Integer>> menuResourceRedisMap = redissonClient.getMap("MenuResourceMap");
        Map<Integer, Set<Integer>> menuResourceMap = menuResourceRedisMap.readAllMap();
        // 过滤用户实际数据
        List<Role> userRoles = new ArrayList<>();
        List<Menu> userMenus = new ArrayList<>();
        List<Resource> userResources = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(userRoleRelations)) {
            userRoleRelations.forEach(userRoleRelation -> {
                // 从缓存中获取 Role
                List<Role> roleRels = roles.stream().filter(e -> e.getId().equals(userRoleRelation.getRoleId())).collect(Collectors.toList());
                if (CollectionUtil.isNotEmpty(roleRels)) {
                    Role role = roleRels.get(0);
                    userRoles.add(role);

                    List<Integer> menuRelIndexs = roleMenuMap.get(role.getId());
                    if (CollectionUtil.isNotEmpty(menuRelIndexs)) {
                        List<Menu> menuRels = menus.stream().filter(e -> menuRelIndexs.contains(e.getId())).collect(Collectors.toList());
                        if (CollectionUtil.isNotEmpty(menuRels)) {
                            userMenus.addAll(menuRels);

                            menuRels.forEach(menu -> {
                                Set<Integer> resourceRelIndexs = menuResourceMap.get(menu.getId());
                                List<Resource> resourceRels = resources.stream().filter(e -> resourceRelIndexs.contains(e.getId())).collect(Collectors.toList());
                                if (CollectionUtil.isNotEmpty(resourceRels)) {
                                    userResources.addAll(resourceRels);
                                }
                            });
                        }
                    }
                }
            });
        }

        return CustomUserDetails.getInstance(user, userRoles, userMenus, userResources);
    }
}
