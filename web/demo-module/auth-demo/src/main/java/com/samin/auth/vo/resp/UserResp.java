package com.samin.auth.vo.resp;

import com.samin.auth.entity.User;
import com.samin.auth.entity.UserRoleRelation;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserResp {

    private Integer id;
    private String mobile;
    private String icon;
    private String email;
    private String nickName;
    private String note;
    private String createTime;
    private String updateTime;
    private String lastLoginTime;
    private List<Integer> roles;

    public static UserResp getInstance(User user, List<UserRoleRelation> list) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        UserResp resp = new UserResp();

        resp.setId(user.getId());
        resp.setMobile(user.getMobile());
        resp.setIcon(user.getIcon());
        resp.setEmail(user.getEmail());
        resp.setNickName(user.getNickName());
        resp.setNote(user.getNote());
        resp.setCreateTime(dtf.format(user.getCreateTime()));
        resp.setUpdateTime(Objects.nonNull(user.getUpdateTime()) ? dtf.format(user.getUpdateTime()) : "");
        resp.setLastLoginTime(Objects.nonNull(user.getLastLoginTime()) ? dtf.format(user.getLastLoginTime()) : "");
        resp.setRoles(list.stream()
                .map(UserRoleRelation::getRoleId)
                .collect(Collectors.toList()));

        return resp;
    }
}
