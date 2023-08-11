package com.samin.auth.vo.resp;

import com.samin.auth.entity.User;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

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
    private Integer status;

    public static UserResp getInstance(User user) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        UserResp userResp = new UserResp();

        userResp.id = user.getId();
        userResp.mobile = user.getMobile();
        userResp.icon = user.getIcon();
        userResp.email = user.getEmail();
        userResp.nickName = user.getNickName();
        userResp.note = user.getNote();
        userResp.createTime = dtf.format(user.getCreateTime());
        userResp.updateTime = Objects.nonNull(user.getUpdateTime()) ? dtf.format(user.getUpdateTime()) : "";
        userResp.lastLoginTime = Objects.nonNull(user.getLastLoginTime()) ? dtf.format(user.getLastLoginTime()) : "";
        userResp.status = user.getStatus();

        return userResp;
    }
}
