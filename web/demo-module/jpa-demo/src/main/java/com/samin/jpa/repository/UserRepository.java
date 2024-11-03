package com.samin.jpa.repository;

import com.samin.jpa.entity.UserDO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

  long countBySex(Integer sex);

  @Query(
      value = "SELECT DISTINCT sex FROM user WHERE create_time between ?1 and ?2",
      nativeQuery = true)
  List<Integer> findDistinctBySex(String startTime, String endTime);

  List<UserDO> findByCreateTimeBetween(String startDate, String endDate);

  // 直接这样声明只会调用 select 语句，Jpa 的安全设定
  // void deleteBySex(Integer sex);

  // 这种方式先调用 select，然后再根据结果一条条记录删除
  // @Modifying
  // @Transactional
  // void deleteBySex(Integer sex);

  // 最佳实践
  @Modifying
  @Transactional
  @Query("DELETE FROM UserDO d WHERE d.sex = :sex")
  void deleteBySex(Integer sex);
}
