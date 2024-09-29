package com.samin.auth.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.EasyExcel;
import com.samin.auth.entity.Role;
import com.samin.auth.entity.User;
import com.samin.auth.entity.UserRoleRelation;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.repo.RoleRepository;
import com.samin.auth.repo.UserRepository;
import com.samin.auth.repo.UserRoleRelationRepository;
import com.samin.auth.vo.excel.UserExcel;
import com.samin.auth.vo.req.PageReq;
import com.samin.auth.vo.req.UserSaveReq;
import com.samin.auth.vo.resp.PageResp;
import com.samin.auth.vo.resp.UserResp;
import com.samin.auth.vo.resp.UserSaveResp;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户服务类
 * <p>
 * Description: 用户服务类
 * <p>
 * Created By: Samin Created Date: 2023-08-16
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRelationRepository userRoleRelationRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 分页查询
     *
     * @param req 请求入参
     * @return 分页数据
     */
    public PageResp<UserResp> page(PageReq req) {
        Pageable pageable = PageRequest.of(req.getPage(), req.getSize(), Sort.by("createTime")
                .descending());

        PageResp<User> users = PageResp.success(userRepository.findAll(pageable));

        return users.map(user -> UserResp.getInstance(user, userRoleRelationRepository.findByUserId(user.getId())));
    }

    public void pageExport(PageReq req, HttpServletResponse response) throws IOException {
        List<UserResp> users = page(req).getContent();
        List<UserExcel> excels = users.stream()
                .map(UserExcel::getInstance)
                .collect(Collectors.toList());

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 为了解决中文导出异常，需要对中文进行 URL 编码后，配置好 Content-Disposition
        String fileName = URLEncoder.encode("用户列表", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        EasyExcel.write(response.getOutputStream(), UserExcel.class)
                .inMemory(true)
                .sheet(fileName)
                .doWrite(excels);
    }

    /**
     * 保存用户
     *
     * @param userSaveReq 用户信息
     * @return 回显信息
     */
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public UserSaveResp saveUser(UserSaveReq userSaveReq) {
        UserSaveResp resp = new UserSaveResp();

        User user;
        // update
        if (Objects.nonNull(userSaveReq.getId())) {
            Optional<User> userOptional = userRepository.findById(userSaveReq.getId());

            if (userOptional.isPresent()) {
                user = userOptional.get();
                CopyOptions options = CopyOptions.create()
                        .ignoreNullValue()
                        .setIgnoreProperties("mobile");
                BeanUtil.copyProperties(userSaveReq, user, options);

                if (StrUtil.isNotBlank(user.getPassword())) {
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                }

                userRepository.save(user);
                // 绑定角色
                setUserRoleRelations(user.getId(), userSaveReq.getRoles());

                resp.setId(user.getId());
            } else {
                ExceptionEnums.throwException(ExceptionEnums.USER_NOT_EXIST_ERROR);
            }

            // insert
        } else {
            Optional<User> userOptional = userRepository.findByMobile(userSaveReq.getMobile());

            if (userOptional.isPresent()) {
                ExceptionEnums.throwException(ExceptionEnums.USER_EXIST_ERROR);
            }

            user = new User();
            CopyOptions options = CopyOptions.create()
                    .ignoreNullValue();
            BeanUtil.copyProperties(userSaveReq, user, options);

            if (StrUtil.isNotBlank(user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }

            userRepository.save(user);
            // 绑定角色
            setUserRoleRelations(user.getId(), userSaveReq.getRoles());

            resp.setId(user.getId());
        }

        return resp;
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
        userRoleRelationRepository.deleteByUserId(id);
    }

    /**
     * 绑定角色
     *
     * @param userId 用户 id
     * @param roles  角色集合
     */
    public void setUserRoleRelations(Integer userId, List<String> roles) {
        // 过滤合法角色
        roles = validRoles(roles);

        // 删除历史绑定
        userRoleRelationRepository.deleteByUserId(userId);

        // 新增绑定
        if (!CollectionUtils.isEmpty(roles)) {
            List<UserRoleRelation> userRoleRelations = roles.stream()
                    .map(e -> UserRoleRelation.getInstance(userId, e))
                    .collect(Collectors.toList());

            userRoleRelationRepository.saveAll(userRoleRelations);
        }
    }

    public List<String> validRoles(List<String> roles) {
        if (CollectionUtils.isEmpty(roles)) {
            return new ArrayList<>(0);
        }

        // 过滤不存在的角色
        return roleRepository.findByCodeIn(roles)
                .stream()
                .map(Role::getCode)
                .collect(Collectors.toList());
    }
}