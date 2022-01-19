package com.xuanhan.vuedemo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuanhan.vuedemo.DAO.UserMapper;
import com.xuanhan.vuedemo.common.Result;
import com.xuanhan.vuedemo.entity.User;
import com.xuanhan.vuedemo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/user")

public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public Result<?> save(@RequestBody User user) {
        if (user.getPassword() == null) {
            user.setPassword("123456");
        }
        userService.insert(user);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        Page<User> userPage = userService.selectPage(pageNum, pageSize, search);
        return Result.success(userPage);
    }

    @PutMapping
    public Result<?> update(@RequestBody User user) {
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping(value = "/{id}")
    public Result<?> delete(@PathVariable Long id) {
        userService.delete(id);
        return Result.success();
    }

    @PostMapping(value = "/login")
    public Result<?> login(@RequestBody User user) {
        System.out.println(user);
        User res = userService.selectOne(user);
        if (res == null) {
            return Result.error("-1", "Username or Password incorrect!");
        }
        return Result.success(res);
    }

    @PostMapping(value = "/register")
    public Result<?> register(@RequestBody User user) {
        User res = userService.selectByUsername(user);
        if (res != null) {
            return Result.error("-1", "Username already exists");
        }
        userService.insert(user);
        return Result.success();
    }
}
