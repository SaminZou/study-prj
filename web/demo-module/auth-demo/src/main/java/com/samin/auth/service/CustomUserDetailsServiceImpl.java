package com.samin.auth.service;


import cn.hutool.core.collection.CollectionUtil;
import com.samin.auth.authentication.CustomUserDetails;
import com.samin.auth.entity.*;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.repo.MenuResourceRelationRepository;
import com.samin.auth.repo.RoleMenuRelationRepository;
import com.samin.auth.repo.UserRepository;
import com.samin.auth.repo.UserRoleRelationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 自定义用户信息加载服务类
 *
 * @author samin
 * @date 2022-08-08
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final UserRoleRelationRepository userRoleRelationRepository;
    private final RoleMenuRelationRepository roleMenuRelationRepository;
    private final MenuResourceRelationRepository menuResourceRelationRepository;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByNickName(username);
        if (!userOptional.isPresent()) {
            // 用这个的原因是因为可以被 AuthenticationEntryPoint 实现方法感知处理返回自定义报错
            throw new UsernameNotFoundException(ExceptionEnums.USER_NOT_EXIST_ERROR.getValue());
        }

        User user = userOptional.get();
        List<UserRoleRelation> userRoleRelations = userRoleRelationRepository.findByUserId(user.getId());

        List<Role> roles = new ArrayList<>();
        List<Menu> menus = new ArrayList<>();
        List<Resource> resources = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(userRoleRelations)) {
            userRoleRelations.forEach(userRoleRelation -> {
                // TODO 从缓存中获取 Role
                Role role = null;
                roles.add(role);

                List<RoleMenuRelation> roleMenuRelations = roleMenuRelationRepository.findByRoleId(role.getId());
                roleMenuRelations.forEach(roleMenuRelation -> {
                    // TODO 从缓存中获取 Menu
                    Menu menu = null;
                    menus.add(menu);

                    List<MenuResourceRelation> menuResourceRelations = menuResourceRelationRepository.findByMenuId(menu.getId());
                    menuResourceRelations.forEach(menuResourceRelation -> {
                        // TODO 从缓存中获取 resource
                        Resource resource = null;
                        resources.add(resource);
                    });
                });
            });
        }

        return CustomUserDetails.getInstance(user, roles, menus, resources);
    }
}
