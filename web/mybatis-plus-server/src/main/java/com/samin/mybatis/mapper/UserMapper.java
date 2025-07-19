package com.samin.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.po.UserPO;
import java.util.List;

public interface UserMapper extends BaseMapper<UserPO> {

    List<UserVO> selectUserVO();
}
