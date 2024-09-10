package com.eddie.mapper;

import com.eddie.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 新增分类
     * @param category
     */
    @Insert("insert into category(category_name,category_alias,create_user,create_time,update_time)" +
            "values (#{categoryName},#{categoryAlias},#{createUser},now(),now())")

    void insert(Category category);

    /**
     *查询分类列表
     * @param id
     * @return
     */
    @Select("select * from category where create_user = #{id}")
    List<Category> list(Integer id);

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category getById(Integer id);

    /**
     * 更新文章分类
     * @param category
     */
    @Update("update category set category_name = #{categoryName}, " +
            "category_alias = #{categoryAlias}, update_time = now() where id = #{id}")
    void update(Category category);

    /**
     *
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void deleteById(Integer id);
}
