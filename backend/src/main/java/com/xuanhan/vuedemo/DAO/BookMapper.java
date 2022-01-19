package com.xuanhan.vuedemo.DAO;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuanhan.vuedemo.entity.Book;

public interface BookMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Book record);

    int insertSelective(Book record);

    Book selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    IPage<Book> selectByPage(Page<?> page, String search);

    IPage<Book> selectByPage2(Page<?> page);
}