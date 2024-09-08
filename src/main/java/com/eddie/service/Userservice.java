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
}
