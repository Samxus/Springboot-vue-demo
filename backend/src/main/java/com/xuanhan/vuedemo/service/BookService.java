package com.xuanhan.vuedemo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuanhan.vuedemo.DAO.BookMapper;
import com.xuanhan.vuedemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;

public interface BookService {
    int insertSelective(Book record);

    IPage<Book> selectByPage(Integer pageNum, Integer pageSize, String search);

    void delete(Long id);

    void update(Book book);
}