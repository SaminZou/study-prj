package com.samin.mybatis.mapper.struct;

import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.po.UserPO;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapperStruct {
    UserMapperStruct INSTANCE = Mappers.getMapper(UserMapperStruct.class);

    UserPO toUserPO(UserVO userVO);

    UserVO toUserVO(UserPO userPO);

    List<UserVO> toUserVOList(List<UserPO> userPOList);
}