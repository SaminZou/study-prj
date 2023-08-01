package com.samin.auth.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.samin.auth.entity.User;
import com.samin.auth.entity.UserRoleRelation;
import com.samin.auth.exception.ExceptionEnums;
import com.samin.auth.repo.UserRepository;
import com.samin.auth.repo.UserRoleRelationRepository;
import com.samin.auth.vo.UserSaveResp;
import com.samin.auth.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleRelationRepository userRoleRelationRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 保存用户
     *
     * @param userVo 用户信息
     * @return 回显信息
     */
    @Transactional(rollbackOn = Exception.class)
    public UserSaveResp saveUser(UserVo userVo) {
        UserSaveResp resp = new UserSaveResp();

        User user;
        // update
        if (Objects.nonNull(userVo.getId())) {
            Optional<User> userOptional = userRepository.findById(userVo.getId());

            if (userOptional.isPresent()) {
                user = userOptional.get();
                CopyOptions options = CopyOptions.create()
                        .ignoreNullValue()
                        .setIgnoreProperties("nickName");
                BeanUtil.copyProperties(userVo, user, options);

                if (StrUtil.isNotBlank(user.getPassword())) {
                    user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
                }

                userRepository.save(user);
                // 绑定角色
                setUserRoleRelations(user.getId(), userVo.getRoles());

                resp.setId(user.getId());
            } else {
                ExceptionEnums.throwException(ExceptionEnums.USER_NOT_EXIST_ERROR);
            }

            // insert
        } else {
            Optional<User> userOptional = userRepository.findByNickName(userVo.getNickName());

            if (userOptional.isPresent()) {
                ExceptionEnums.throwException(ExceptionEnums.USER_EXIST_ERROR);
            }

            user = new User();
            CopyOptions options = CopyOptions.create()
                    .ignoreNullValue();
            BeanUtil.copyProperties(userVo, user, options);

            if (StrUtil.isNotBlank(user.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            }

            userRepository.save(user);
            // 绑定角色
            setUserRoleRelations(user.getId(), userVo.getRoles());

            resp.setId(user.getId());
        }

        return resp;
    }

    /**
     * 绑定角色
     *
     * @param userId 用户 id
     * @param roles  角色集合
     */
    public void setUserRoleRelations(Integer userId, List<Integer> roles) {
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

    public List<Integer> validRoles(List<Integer> roles) {
        // TODO
        return roles;
    }
}
