package com.eddie.service;

import com.eddie.pojo.Article;
import com.eddie.pojo.PageBean;

public interface ArticleService {
    /**
     * 新增文章
     * @param article
     */
    void add(Article article);

    /**
     * 文章分页查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    PageBean pageQuery(Integer pageNum, Integer pageSize, String categoryId, String state);
}
