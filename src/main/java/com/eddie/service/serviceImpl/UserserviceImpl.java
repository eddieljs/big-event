package com.eddie.service.serviceImpl;

import com.eddie.mapper.UserMapper;
import com.eddie.pojo.User;
import com.eddie.service.Userservice;
import com.eddie.utils.Md5Util;
import com.eddie.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

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

    /**
     * 更新用户信息
     * @param user
     */
    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    /**
     * 更新用户头像
     * @param avatarUrl
     */
    @Override
    public void updateAvatar(@URL String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }
}
