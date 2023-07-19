package com.samin.auth.vo;

import com.samin.auth.entity.Menu;
import com.samin.auth.entity.Resource;
import com.samin.auth.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class BaseInfoVo {

    private List<Role> roles;

    private List<Menu> menus;

    private List<Resource> resources;
}
