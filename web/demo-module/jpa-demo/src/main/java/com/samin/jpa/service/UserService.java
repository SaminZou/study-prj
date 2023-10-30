package com.samin.jpa.service;

import com.samin.jpa.entity.UserDO;
import com.samin.jpa.entity.UserVO;
import com.samin.jpa.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务类
 *
 * @author samin
 * @date 2022-11-22
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserVO> findAll() {
        List<UserVO> result = new ArrayList<>();
        List<UserDO> users = userRepository.findAll();

        users.forEach(e -> {
            UserVO model = new UserVO();
            BeanUtils.copyProperties(e, model);
            result.add(model);
        });

        return result;
    }

    public List<UserVO> findBySex(int sex) {
        List<UserVO> result = new ArrayList<>();
        List<UserDO> users = userRepository.findUserBySex(sex);

        users.forEach(e -> {
            UserVO model = new UserVO();
            BeanUtils.copyProperties(e, model);
            result.add(model);
        });

        return result;
    }

    public UserVO saveUser(UserVO req) {
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(req, userDO);

        userDO = userRepository.save(userDO);
        req.setId(userDO.getId());

        return req;
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    // TODO
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void transaction() {

    }
}
