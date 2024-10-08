package com.eddie.controller;


import com.eddie.pojo.Result;
import com.eddie.pojo.User;
import com.eddie.service.Userservice;
import com.eddie.utils.JwtUtil;
import com.eddie.utils.Md5Util;
import com.eddie.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
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
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",username);
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
            return Result.error("密码错误");
    }

//    @GetMapping("/userInfo")
//    public Result<User> getUserInfo(@RequestHeader(name = "Authorization") String token){
//        Map<String, Object> stringObjectMap = JwtUtil.parseToken(token);
//        String username = (String) stringObjectMap.get("username");
//        log.info("获取用户详细信息，{}",username);
//        User user = userservice.getByUsername(username);
//        return Result.success(user);
//    }

    /**
     * 获取用户信息
     * @param
     * @return
     */
    @GetMapping("/userInfo")
    public Result<User> getUserInfo(){
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userservice.getByUsername(username);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        log.info("更新用户信息：{}",user.getUsername());
        userservice.update(user);
        return Result.success("更新用户信息成功");
    }

    /**
     * 更新用户头像
     * @param avatarUrl
     * @return
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam String avatarUrl){
        userservice.updateAvatar(avatarUrl);
        return Result.success("头像更新成功");
    }

    /**
     * 更新密码
     * @param params
     * @return
     */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //"old_pwd":"123456",
        // "new_pwd":"234567",
        // "re_pwd":"234567"
        String OldString = params.get("old_pwd");
        String NewString = params.get("new_pwd");
        String ReString = params.get("re_pwd");
        //1检查密码是否为空
        if (!StringUtils.hasLength(OldString) || !StringUtils.hasLength(NewString) || !StringUtils.hasLength(ReString)){
            return Result.error("密码不能为空");
        }
        //2检查密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        User user = userservice.getByUsername(map.get("username").toString());
        if(!Md5Util.getMD5String(OldString).equals(user.getPassword())){
            return Result.error("原密码错误");
        }
        //3检查新密码和重复密码是否相同
        if (!NewString.equals(ReString)){
            return Result.error("两次密码不相同");
        }
        //4新密码不能与旧密码相同
        if(Md5Util.getMD5String(NewString).equals(user.getPassword())){
            return Result.error("新密码不能与旧密码相同");
        }
        //4更新密码
        Integer id = (Integer) map.get("id");
        userservice.updatePwd(Md5Util.getMD5String(NewString),id);
        return Result.success("密码更新成功");
    }
}
