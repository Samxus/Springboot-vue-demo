package com.xuanhan.vuedemo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuanhan.vuedemo.DAO.UserMapper;
import com.xuanhan.vuedemo.entity.User;
import com.xuanhan.vuedemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public int insert(User user) {
        int status = userMapper.insert(user);
        return status;
    }

    public Page<User> selectPage(Integer pageNum, Integer pageSize, String search) {
        LambdaQueryWrapper<User> like = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(search)) {
            like.like(User::getNickname, search);
        }
        return userMapper.selectPage(new Page<>(pageNum, pageSize), like);
    }

    @Override
    public void update(User user) {
        userMapper.updateById(user);
    }

    @Override
    public void delete(Long id) {
        userMapper.deleteById(id);
    }

    @Override
    public User selectOne(User user) {
        User res = userMapper.selectOne(user);
        return res;
    }

    @Override
    public User selectByUsername(User user) {
        User res = userMapper.selectByUsername(user);
        return res;
    }
}
