package com.samin.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.samin.mybatis.mapper.UserMapper;
import com.samin.mybatis.model.PageReq;
import com.samin.mybatis.model.UserQueryVO;
import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.po.UserPO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public Page<UserPO> page(PageReq req) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(req.getName())) {
            wrapper.like("name", req.getName());
        }
        return userMapper.selectPage(new Page<>(req.getPage(), req.getSize()), wrapper);
    }

    public List<UserVO> findAll() {
        List<UserVO> result = new ArrayList<>();
        List<UserPO> users = userMapper.selectList(null);

        users.forEach(e -> {
            UserVO model = new UserVO();
            BeanUtils.copyProperties(e, model);
            result.add(model);
        });

        return result;
    }

    public List<UserVO> queryByName(String name) {
        return userMapper.queryByName(name);
    }

    public List<UserVO> queryList(UserQueryVO req) {
        return userMapper.queryList(req);
    }

    public List<UserVO> directList() {
        return userMapper.selectUserVO();
    }

    public List<UserVO> customList() {
        QueryWrapper<UserPO> qw = new QueryWrapper<>();
        qw.eq("sex", 1);
        List<UserVO> result = new ArrayList<>();
        List<UserPO> users = userMapper.selectList(qw);

        users.forEach(e -> {
            UserVO model = new UserVO();
            BeanUtils.copyProperties(e, model);
            result.add(model);
        });

        return result;
    }
}
