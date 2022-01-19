package com.xuanhan.vuedemo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.xuanhan.vuedemo.common.Result;
import com.xuanhan.vuedemo.entity.Book;
import com.xuanhan.vuedemo.entity.User;
import com.xuanhan.vuedemo.service.BookService;
import com.xuanhan.vuedemo.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/book")

public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping
    public Result<?> save(@RequestBody Book book) {
        bookService.insertSelective(book);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize, @RequestParam(defaultValue = "") String search) {
        Page<Book> page = (Page<Book>) bookService.selectByPage(pageNum, pageSize, search);
        return Result.success(page);
    }

    @PutMapping
    public Result<?> update(@RequestBody Book book) {
        bookService.update(book);
        return Result.success();
    }

    @DeleteMapping(value = "/{id}")
    public Result<?> delete(@PathVariable Long id) {
        bookService.delete(id);
        return Result.success();
    }
}
