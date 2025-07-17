package com.samin.mybatis.service;

import com.samin.mybatis.mapper.UserMapper;
import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.po.UserPO;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

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
}
