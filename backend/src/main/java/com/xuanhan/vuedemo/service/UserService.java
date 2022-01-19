package com.xuanhan.vuedemo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xuanhan.vuedemo.entity.User;
import org.springframework.stereotype.Service;


public interface UserService {
    int insert(User user);

    Page<User> selectPage(Integer pageNum, Integer pageSize, String search);

    void update(User user);

    void delete(Long id);

    User selectOne(User user);

    User selectByUsername(User user);
}
