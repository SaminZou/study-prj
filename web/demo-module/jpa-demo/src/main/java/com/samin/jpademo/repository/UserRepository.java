package com.samin.jpademo.repository;

import com.samin.jpademo.entity.UserDO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓库类
 *
 * @author samin
 * @date 2022-11-23
 */
public interface UserRepository extends JpaRepository<UserDO, Integer> {

    List<UserDO> findUserBySex(int sex);
}
