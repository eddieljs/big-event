package com.eddie.mapper;

import com.eddie.pojo.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {
    /**
     * 新增文章
     * @param article
     */
    @Insert("insert into article(title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
            "values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void insert(Article article);

    /**
     * 文章分页查询
     * @param categoryId
     * @param state
     * @return
     */
    Page<Article> pageQuery(String categoryId, String state, Integer userId);
}
