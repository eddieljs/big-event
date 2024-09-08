package com.eddie.controller;


import com.eddie.pojo.Result;
import com.eddie.pojo.User;
import com.eddie.service.Userservice;
import com.eddie.utils.Md5Util;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private Userservice userservice;

    /**
     * 用户注册
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        log.info("用户注册：{}",username);

        //查询用户
        User user = userservice.getByUsername(username);
        //没查到则注册
        if(user==null){
            userservice.register(username,password);
            return Result.success("注册成功");
        }else {
            return Result.success("用户名已存在");
        }

    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password) {
        log.info("用户登录：{}", username);
        //根据用户名查User
        User user = userservice.getByUsername(username);
        //判断是否查到
        if(user==null){
            return Result.error("用户不存在");
        }
        String md5String = Md5Util.getMD5String(password);
        //判断密码是否正确
        if (md5String.equals(user.getPassword()) ){
            return Result.success("jwt");
        }
            return Result.error("密码错误");


    }
}
