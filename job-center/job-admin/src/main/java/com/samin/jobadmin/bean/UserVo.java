package com.samin.jobadmin.bean;

import com.samin.jobadmin.entity.User;
import lombok.Data;

import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Data
public class UserVo {

    public static UserVo getInstance(User user) {
        UserVo ins = new UserVo();

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        ins.setId(user.getId());
        ins.setLoginName(user.getLoginName());
        ins.setPassword(user.getPassword());
        ins.setIsEnable(user.getIsEnable());
        ins.setCreateTime(Objects.nonNull(user.getCreateTime()) ? dtf.format(user.getCreateTime()) : "");
        ins.setUpdateTime(Objects.nonNull(user.getUpdateTime()) ? dtf.format(user.getUpdateTime()) : "");

        return ins;
    }

    private Integer id;

    private String loginName;

    private String password;

    private Integer isEnable;

    private String createTime;

    private String updateTime;
}