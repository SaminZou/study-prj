package com.samin.auth.vo.resp;

import com.samin.auth.entity.User;
import com.samin.auth.entity.UserRoleRelation;
import com.samin.auth.util.DateUtil;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResp {

    public static UserResp getInstance(User user, List<UserRoleRelation> list) {
        UserResp resp = new UserResp();

        resp.setId(user.getId());
        resp.setMobile(user.getMobile());
        resp.setIcon(user.getIcon());
        resp.setEmail(user.getEmail());
        resp.setNickName(user.getNickName());
        resp.setNote(user.getNote());
        resp.setCreateTime(DateUtil.getDisplayTime(user.getCreateTime()));
        resp.setUpdateTime(DateUtil.getDisplayTime(user.getUpdateTime()));
        resp.setLastLoginTime(DateUtil.getDisplayTime(user.getLastLoginTime()));
        resp.setRoles(list.stream()
                .map(UserRoleRelation::getRoleCode)
                .collect(Collectors.toList()));

        return resp;
    }

    private Integer id;

    private String mobile;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    private String createTime;

    private String updateTime;

    private String lastLoginTime;

    private List<String> roles;
}
