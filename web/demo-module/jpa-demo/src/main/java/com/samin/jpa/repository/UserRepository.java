package com.samin.jpa.repository;

import com.samin.jpa.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * 用户仓库类
 *
 * @author samin
 * @date 2022-11-23
 */
public interface UserRepository extends JpaRepository<UserDO, Integer> {

    /**
     * 根据性别获取用户记录
     *
     * @param sex
     * @return
     */
    List<UserDO> findUserBySex(int sex);

    Optional<UserDO> findUserDOById(Integer id);
}