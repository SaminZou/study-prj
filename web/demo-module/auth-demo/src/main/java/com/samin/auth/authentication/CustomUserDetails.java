package com.samin.auth.authentication;

import com.samin.auth.entity.Menu;
import com.samin.auth.entity.Resource;
import com.samin.auth.entity.Role;
import com.samin.auth.entity.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetails implements UserDetails, Serializable {

    private User user;
    private List<Role> roles;
    private List<Menu> menus;
    private List<Resource> resources;

    public static CustomUserDetails getInstance(User user, List<Role> roles, List<Menu> menus, List<Resource> resources) {
        CustomUserDetails ins = new CustomUserDetails();

        ins.setRoles(roles);
        ins.setUser(user);
        ins.setMenus(menus);
        ins.setResources(resources);

        return ins;
    }

    /**
     * 用来绑定角色结合 @PreAuthorize("hasRole('ROLE_ADMIN')") 来使用
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getNickName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus() == 1;
    }
}
