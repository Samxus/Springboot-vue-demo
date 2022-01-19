package com.xuanhan.vuedemo.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuanhan.vuedemo.DAO.BookMapper;
import com.xuanhan.vuedemo.entity.Book;
import com.xuanhan.vuedemo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public int insertSelective(Book record) {
        return bookMapper.insertSelective(record);
    }

    @Override
    public IPage<Book> selectByPage(Integer pageNum, Integer pageSize, String search) {
        if (StrUtil.isNotBlank(search)) {
            search = "%" + search + "%";
            return bookMapper.selectByPage(new Page<>(pageNum, pageSize), search);
        } else {
            return bookMapper.selectByPage2(new Page<>(pageNum, pageSize));
        }
    }

    @Override
    public void delete(Long id) {
        bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Book book) {
        bookMapper.updateByPrimaryKeySelective(book);
    }
}
