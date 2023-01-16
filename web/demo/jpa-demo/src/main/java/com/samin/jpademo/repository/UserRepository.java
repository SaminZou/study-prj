package com.samin.jpademo.repository;

import com.samin.jpademo.entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户仓库类
 *
 * @author samin
 * @date 2022-11-23
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    List<User> findUserBySex(int sex);
}
