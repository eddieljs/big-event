package com.eddie.exception;

import com.eddie.pojo.Result;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 */
@ControllerAdvice(annotations = {RestController.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 对控制台抛出的SQLIntegrityConstraintViolationException异常进行处理
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result<String> exceptionHandler(SQLIntegrityConstraintViolationException ex){
        String message = ex.getMessage();
        if (message.contains("Duplicate entry")){//消息包含此语句 即为用户名重复
            String[] split = message.split(" ");//把消息按照空格划分为数组
            String username = split[2];//提取用户名
            String msg = username+ "已存在";
            return Result.error(msg);
        }else {
            return Result.error("未知错误");
        }
    }

    /**
     * 对控制台抛出的ConstraintViolationException异常进行处理
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> exceptionHandler(ConstraintViolationException e) {
        String message = e.getMessage();
        return Result.error(StringUtils.hasLength(message) ? message : "操作失败");
    }
    }