package com.samin.jpa.service;

import com.samin.jpa.entity.UserDO;
import com.samin.jpa.entity.UserVO;
import com.samin.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Integer> distinct() {
        String startTimeStr = "2024-01-01 00:00:00";
        String endTimeStr = "2024-01-31 23:59:59";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, dtf);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, dtf);
        OffsetDateTime now = OffsetDateTime.now();

        return userRepository.findDistinctBySex(String.valueOf(startTime.atZone(now.getOffset())
                .toEpochSecond()), String.valueOf(endTime.atZone(now.getOffset())
                .toEpochSecond()));
    }

    public List<UserVO> between() {
        List<UserVO> result = new ArrayList<>();

        String startTimeStr = "2024-01-01 00:00:00";
        String endTimeStr = "2024-01-31 23:59:59";
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime = LocalDateTime.parse(startTimeStr, dtf);
        LocalDateTime endTime = LocalDateTime.parse(endTimeStr, dtf);
        OffsetDateTime now = OffsetDateTime.now();
        List<UserDO> users = userRepository.findByCreateTimeBetween(String.valueOf(startTime.atZone(now.getOffset())
                .toEpochSecond()), String.valueOf(endTime.atZone(now.getOffset())
                .toEpochSecond()));

        users.forEach(e -> {
            UserVO model = new UserVO();
            BeanUtils.copyProperties(e, model);
            result.add(model);
        });

        return result;
    }

    public List<Long> count() {
        List<Long> result = new ArrayList<>();
        result.add(userRepository.count());
        result.add(userRepository.countBySex(1));
        return result;
    }

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

    public UserVO detail(Integer id) {
        Optional<UserDO> opt = userRepository.findUserDOById(id);
        UserDO userDO = opt.orElse(new UserDO());

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        return userVO;
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
