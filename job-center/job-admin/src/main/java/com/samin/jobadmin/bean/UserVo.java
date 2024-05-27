package com.samin.jobadmin.bean;

import com.samin.jobadmin.entity.User;
import lombok.Data;

@Data
public class UserVo {

    public static UserVo getInstance(User user) {
      UserVo ins = new UserVo();

      return ins;
    }

    private Integer id;

    private String loginName;

    private String password;

    private Integer isEnable;

    private String createTime;

    private String updateTime;
}