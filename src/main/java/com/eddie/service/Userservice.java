package com.eddie.service;

import com.eddie.pojo.User;

public interface Userservice {
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 更新用户信息
     * @param user
     */
    void update(User user);

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    void updateAvatar(String avatarUrl);

    /**
     * 更新密码
     * @param password
     */
    void updatePwd(String password,Integer id);
}
