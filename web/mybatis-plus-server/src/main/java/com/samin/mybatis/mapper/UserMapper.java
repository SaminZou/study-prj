package com.samin.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.samin.mybatis.model.UserQueryVO;
import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.po.UserPO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<UserPO> {

    List<UserVO> queryByName(@Param("name") String name);

    List<UserVO> queryList(@Param("req") UserQueryVO req);

    Page<UserVO> pageBySql(@Param("page")Page<?> page, @Param("req") UserQueryVO req);

    List<UserVO> selectUserVO();
}
