package com.samin.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.samin.mybatis.mapper.UserMapper;
import com.samin.mybatis.mapper.struct.UserMapperStruct;
import com.samin.mybatis.model.PageReq;
import com.samin.mybatis.model.UserQueryVO;
import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.po.UserPO;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserMapperStruct userMapperStruct;

    /**
     * 根据 ID 删除用户
     *
     * @param id 用户 ID
     */
    public void delete(Integer id) {
        log.info("删除用户，用户ID：{}", id);
        userMapper.deleteById(id);
        log.info("删除用户成功，用户ID：{}", id);
    }

    /**
     * 新增用户
     *
     * @param req 用户新增请求体
     * @return 用户实体
     */
    public UserPO insert(UserVO req) {
        log.info("新增用户，请求参数：{}", req);
        // UserVO to UserPO using MapStruct
        UserPO po = userMapperStruct.toUserPO(req);
        userMapper.insert(po);
        log.info("新增用户成功，用户ID：{}", po.getId());
        return po;
    }

    /**
     * 更新用户
     *
     * @param req 用户更新请求体
     * @return 用户实体
     */
    public UserPO update(UserVO req) {
        log.info("更新用户，请求参数：{}", req);
        UserPO po = userMapper.selectById(req.getId());
        if (po == null) {
            log.warn("更新用户失败，用户不存在，用户ID：{}", req.getId());
            return null;
        }
        // Update fields using MapStruct
        UserPO updatedPO = userMapperStruct.toUserPO(req);
        updatedPO.setId(po.getId()); // Preserve original ID
        userMapper.updateById(updatedPO);
        log.info("更新用户成功，用户ID：{}", updatedPO.getId());
        return updatedPO;
    }

    /**
     * 构建用户查询条件
     *
     * @param req 查询条件
     * @return 查询包装器
     */
    private QueryWrapper<UserPO> buildUserQueryWrapper(Object req) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        
        if (req instanceof PageReq) {
            PageReq pageReq = (PageReq) req;
            log.debug("构建分页查询条件，请求参数：{}", pageReq);
            if (StringUtils.hasText(pageReq.getName())) {
                wrapper.like("name", "%" + pageReq.getName() + "%");
            }
            if (Objects.nonNull(pageReq.getSex())) {
                wrapper.eq("sex", pageReq.getSex());
            }
        } else if (req instanceof UserQueryVO) {
            UserQueryVO userQueryVO = (UserQueryVO) req;
            log.debug("构建用户查询条件，请求参数：{}", userQueryVO);
            if (StringUtils.hasText(userQueryVO.getName())) {
                wrapper.like("name", "%" + userQueryVO.getName() + "%");
            }
            if (Objects.nonNull(userQueryVO.getSex())) {
                wrapper.eq("sex", userQueryVO.getSex());
            }
        }
        
        return wrapper;
    }

    /**
     * 用户查询计数
     *
     * @param req 用户查询请求体
     * @return 用户计数
     */
    public Integer count(UserQueryVO req) {
        log.info("查询用户数量，请求参数：{}", req);
        QueryWrapper<UserPO> wrapper = buildUserQueryWrapper(req);
        Integer count = userMapper.selectCount(wrapper).intValue();
        log.info("查询用户数量成功，结果：{}", count);
        return count;
    }

    /**
     * Lambda 表达式使用
     *
     * @param req 分页查询请求体
     * @return 用户分页结果
     */
    public Page<UserPO> pageByLambda(PageReq req) {
        log.info("Lambda分页查询用户，请求参数：{}", req);
        QueryWrapper<UserPO> wrapper = buildUserQueryWrapper(req);
        wrapper.orderByDesc("id");
        Page<UserPO> pageResult = userMapper.selectPage(new Page<>(req.getPage(), req.getSize()), wrapper);
        log.info("Lambda分页查询用户成功，结果：{}", pageResult);
        return pageResult;
    }

    public Page<UserVO> pageBySql(PageReq req) {
        log.info("SQL分页查询用户，请求参数：{}", req);
        Page<UserVO> pageResult = userMapper.pageBySql(new Page<>(req.getPage(), req.getSize()), req);
        log.info("SQL分页查询用户成功，结果：{}", pageResult);
        return pageResult;
    }

    public Page<UserPO> page(PageReq req) {
        log.info("分页查询用户，请求参数：{}", req);
        QueryWrapper<UserPO> wrapper = buildUserQueryWrapper(req);
        Page<UserPO> pageResult = userMapper.selectPage(new Page<>(req.getPage(), req.getSize()), wrapper);
        log.info("分页查询用户成功，结果：{}", pageResult);
        return pageResult;
    }

    public List<UserVO> findAll() {
        log.info("查询所有用户");
        List<UserPO> users = userMapper.selectList(null);
        List<UserVO> userVOList = userMapperStruct.toUserVOList(users);
        log.info("查询所有用户成功，结果数量：{}", userVOList.size());
        return userVOList;
    }

    public List<UserVO> queryByName(String name) {
        return userMapper.queryByName(name);
    }

    public List<String> names() {
        return userMapper.selectObjs(new QueryWrapper<UserPO>().select("name"))
                         .stream()
                         .map(Object::toString)
                         .collect(Collectors.toList());
    }

    public List<UserVO> queryList(UserQueryVO req) {
        return userMapper.queryList(req);
    }

    public List<UserVO> directList() {
        return userMapper.selectUserVO();
    }

    public List<UserVO> customList() {
        QueryWrapper<UserPO> qw = new QueryWrapper<>();
        qw.eq("sex", 1);
        List<UserPO> users = userMapper.selectList(qw);
        return userMapperStruct.toUserVOList(users);
    }
}
