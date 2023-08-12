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
        UserResp resp = new UserResp();

        resp.id = user.getId();
        resp.mobile = user.getMobile();
        resp.icon = user.getIcon();
        resp.email = user.getEmail();
        resp.nickName = user.getNickName();
        resp.note = user.getNote();
        resp.createTime = dtf.format(user.getCreateTime());
        resp.updateTime = Objects.nonNull(user.getUpdateTime()) ? dtf.format(user.getUpdateTime()) : "";
        resp.lastLoginTime = Objects.nonNull(user.getLastLoginTime()) ? dtf.format(user.getLastLoginTime()) : "";
        resp.status = user.getStatus();

        return resp;
    }
}
