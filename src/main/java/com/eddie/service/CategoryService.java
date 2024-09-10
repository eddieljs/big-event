package com.eddie.service;

import com.eddie.pojo.Category;

import java.util.List;

public interface CategoryService {
    /**
     * 新增分类
     * @param category
     */
    void add(Category category);

    /**
     * 查询分类列表
     * @return
     */
    List<Category> list();

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    Category getById(Integer id);

    /**
     * 更新文章分类
     * @param category
     */
    void update(Category category);

    /**
     * 删除文章
     * @param id
     */
    void deleteById(Integer id);
}
