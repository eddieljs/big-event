package com.eddie.service.serviceImpl;

import com.eddie.mapper.UserMapper;
import com.eddie.pojo.User;
import com.eddie.service.Userservice;
import com.eddie.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserserviceImpl implements Userservice {

    @Autowired
    private UserMapper userMapper;
    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User getByUsername(String username) {
        User user = userMapper.getByUsername(username);
        return user;
    }

    /**
     * 注册
     * @param username
     * @param password
     */
    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);
        userMapper.insert(username,md5String);
    }
}
