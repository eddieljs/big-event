package com.eddie.mapper;

import com.eddie.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    User getByUsername(String username);

    /**.
     * 注册
     * @param username
     * @param password
     */
    @Insert("insert into user(username,password,create_time,update_time) " +
            "values (#{username},#{password},now(),now())")
    void insert(String username, String password);

    /**
     * 更新用户信息
     * @param user
     */
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id = #{id}")
    void update(User user);

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id = #{id}")
    void updateAvatar(String avatarUrl,Integer id);
}
