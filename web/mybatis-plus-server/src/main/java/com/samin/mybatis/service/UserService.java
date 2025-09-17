package com.samin.mybatis.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.samin.mybatis.mapper.UserMapper;
import com.samin.mybatis.model.PageReq;
import com.samin.mybatis.model.UserQueryVO;
import com.samin.mybatis.model.UserVO;
import com.samin.mybatis.po.UserPO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    /**
     * 根据 ID 删除用户
     *
     * @param id 用户 ID
     */
    public void delete(Integer id) {
        userMapper.deleteById(id);
    }

    /**
     * 新增用户
     *
     * @param req 用户新增请求体
     * @return 用户实体
     */
    public UserPO insert(UserVO req) {
        // UserVO to UserPO
        UserPO po = new UserPO();
        BeanUtils.copyProperties(req, po);
        userMapper.insert(po);
        return po;
    }

    /**
     * 更新用户
     *
     * @param req 用户更新请求体
     * @return 用户实体
     */
    public UserPO update(UserVO req) {
        UserPO po = userMapper.selectById(req.getId());
        po.setName(req.getName());
        po.setSex(req.getSex());
        po.setMobile(req.getMobile());
        userMapper.updateById(po);
        return po;
    }

    /**
     * 用户查询计数
     *
     * @param req 用户查询请求体
     * @return 用户计数
     */
    public Integer count(UserQueryVO req) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(req.getName())) {
            // 需要增加 %
            wrapper.like("name", "%" + req.getName() + "%");
        }
        if (Objects.nonNull(req.getSex())) {
            wrapper.eq("sex", req.getSex());
        }
        return userMapper.selectCount(wrapper)
                         .intValue();
    }

    /**
     * Lambda 表达式使用
     *
     * @param req 分页查询请求体
     * @return 用户分页结果
     */
    public Page<UserPO> pageByLambda(PageReq req) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        if (StringUtils.hasText(req.getName())) {
            // 需要增加 %
            wrapper.like("name", "%" + req.getName() + "%");
        }
        if (Objects.nonNull(req.getSex())) {
            wrapper.eq("sex", req.getSex());
        }
        wrapper.orderByDesc("id");
        return userMapper.selectPage(new Page<>(req.getPage(), req.getSize()), wrapper);
    }

    public Page<UserVO> pageBySql(PageReq req) {
        return userMapper.pageBySql(new Page<>(req.getPage(), req.getSize()), req);
    }

    public Page<UserPO> page(PageReq req) {
        QueryWrapper<UserPO> wrapper = new QueryWrapper<>();
        if (StringUtils.hasLength(req.getName())) {
            wrapper.like("name", req.getName());
        }
        return userMapper.selectPage(new Page<>(req.getPage(), req.getSize()), wrapper);
    }

    public List<UserVO> findAll() {
        List<UserVO> result = new ArrayList<>();
        List<UserPO> users = userMapper.selectList(null);

        users.forEach(e -> {
            UserVO model = new UserVO();
            BeanUtils.copyProperties(e, model);
            result.add(model);
        });

        return result;
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
        List<UserVO> result = new ArrayList<>();
        List<UserPO> users = userMapper.selectList(qw);

        users.forEach(e -> {
            UserVO model = new UserVO();
            BeanUtils.copyProperties(e, model);
            result.add(model);
        });

        return result;
    }
}
