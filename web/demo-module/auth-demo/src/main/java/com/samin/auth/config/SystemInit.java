package com.samin.auth.config;

import com.samin.auth.entity.Menu;
import com.samin.auth.entity.Resource;
import com.samin.auth.entity.Role;
import com.samin.auth.repo.MenuRepository;
import com.samin.auth.repo.ResourceRepository;
import com.samin.auth.repo.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SystemInit implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final MenuRepository menuRepository;
    private final ResourceRepository resourceRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO redis 缓存所有的 role，menu，resource
        List<Role> roles = roleRepository.findAll();
        List<Menu> menus = menuRepository.findAll();
        List<Resource> resources = resourceRepository.findAll();
    }
}
