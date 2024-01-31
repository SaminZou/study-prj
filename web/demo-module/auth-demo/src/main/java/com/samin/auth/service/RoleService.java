package com.samin.auth.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.samin.auth.entity.Menu;
import com.samin.auth.entity.Role;
import com.samin.auth.entity.RoleMenuRelation;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.repo.MenuRepository;
import com.samin.auth.repo.RoleMenuRelationRepository;
import com.samin.auth.repo.RoleRepository;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.req.RoleSaveReq;
import com.samin.auth.vo.resp.PageResp;
import com.samin.auth.vo.resp.RoleResp;
import com.samin.auth.vo.resp.RoleSaveResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 角色服务类
 *
 * @author samin
 * @date 2023-07-23
 */
@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final MenuRepository menuRepository;
    private final RoleMenuRelationRepository roleMenuRelationRepository;

    /**
     * 分页查询
     *
     * @param req 请求入参
     * @return 分页数据
     */
    public PageResp<RoleResp> page(PageReq req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), Sort.by("createTime")
                .descending());

        PageResp<Role> roles = PageResp.success(roleRepository.findAll(pageable));

        PageResp<RoleResp> resp = PageResp.baseOf(roles);
        resp.setContent(roles.getContent()
                .stream()
                .map(role -> RoleResp.getInstance(role,
                        roleMenuRelationRepository.findByRoleCode(role.getCode())))
                .collect(Collectors.toList()));

        return resp;
    }

    /**
     * 保存角色
     *
     * @param roleSaveReq 角色信息
     * @return 回显信息
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public RoleSaveResp saveRole(RoleSaveReq roleSaveReq) {
        RoleSaveResp resp = new RoleSaveResp();

        Role role;
        // update
        if (Objects.nonNull(roleSaveReq.getId())) {
            Optional<Role> roleOptional = roleRepository.findById(roleSaveReq.getId());

            if (roleOptional.isPresent()) {
                role = roleOptional.get();
                CopyOptions options = CopyOptions.create()
                        .ignoreNullValue()
                        .setIgnoreProperties("code");
                BeanUtil.copyProperties(roleSaveReq, role, options);

                roleRepository.save(role);
                // 绑定菜单
                setRoleMenuRelations(role.getCode(), roleSaveReq.getMenus());

                resp.setId(role.getId());
            } else {
                ExceptionEnums.throwException(ExceptionEnums.ROLE_NOT_EXIST_ERROR);
            }

            // insert
        } else {
            Optional<Role> userOptional = roleRepository.findByCode(roleSaveReq.getCode());

            if (userOptional.isPresent()) {
                ExceptionEnums.throwException(ExceptionEnums.USER_EXIST_ERROR);
            }

            role = new Role();
            CopyOptions options = CopyOptions.create()
                    .ignoreNullValue();
            BeanUtil.copyProperties(roleSaveReq, role, options);

            roleRepository.save(role);
            // 绑定菜单
            setRoleMenuRelations(role.getCode(), roleSaveReq.getMenus());

            resp.setId(role.getId());
        }

        return resp;
    }

    public void delete(String roleCode) {
        roleRepository.deleteByCode(roleCode);
        roleMenuRelationRepository.deleteByRoleCode(roleCode);
    }

    /**
     * 绑定菜单
     *
     * @param roleCode 角色 code
     * @param menus    菜单集合
     */
    public void setRoleMenuRelations(String roleCode, List<String> menus) {
        // 过滤合法菜单
        menus = validMenus(menus);

        // 删除历史绑定
        roleMenuRelationRepository.deleteByRoleCode(roleCode);

        // 新增绑定
        if (!CollectionUtils.isEmpty(menus)) {
            List<RoleMenuRelation> userRoleRelations = menus.stream()
                    .map(e -> RoleMenuRelation.getInstance(roleCode, e))
                    .collect(Collectors.toList());

            roleMenuRelationRepository.saveAll(userRoleRelations);
        }
    }

    public List<String> validMenus(List<String> menus) {
        // 过滤不存在的菜单
        return menuRepository.findByCodeIn(menus)
                .stream()
                .map(Menu::getCode)
                .collect(Collectors.toList());
    }
}
