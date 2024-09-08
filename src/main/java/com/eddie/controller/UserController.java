package com.eddie.controller;


import com.eddie.pojo.Result;
import com.eddie.pojo.User;
import com.eddie.service.Userservice;
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
}
