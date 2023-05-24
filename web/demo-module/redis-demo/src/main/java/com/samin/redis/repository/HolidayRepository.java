package com.samin.redis.repository;

import com.samin.redis.entity.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 用户仓库类
 *
 * @author samin
 * @date 2022-11-23
 */
public interface HolidayRepository extends JpaRepository<Holiday, Integer> {

    /**
     * 根据 id 获取假期记录
     *
     * @param id
     * @return
     */
    Holiday findHolidayById(Integer id);

    /**
     * 获取假期列表记录
     *
     * @param enabled
     * @return
     */
    List<Holiday> findAllByEnabled(boolean enabled);

    /**
     * 查询包含某天的假期记录
     *
     * @param specTime
     * @return
     */
    @Query(value = "select * from holiday  where ?1 = any(workdays)", nativeQuery = true)
    List<Holiday> findByWorkdays(String specTime);

    /**
     * 查询包含某天的假期记录
     *
     * @param specTime
     * @return
     */
    @Query(value = "select * from holiday  where ?1 = any(holidays)", nativeQuery = true)
    List<Holiday> findByHolidays(String specTime);
}
