package com.eddie.anno;

import com.eddie.Validation.StateValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented//元注解
@Constraint(validatedBy = {StateValidation.class})//指定谁为该注解提供规则
@Target({FIELD})//元注解 标识该注解可以用在哪里 FIELD：属性
@Retention(RUNTIME)//元注解 标识该注解哪个阶段保留 RUNTIME：运行阶段

public @interface State {
    //提供校验失败的提示信息
    String message() default "state的值只能为已发布或草稿";
    //指定分组
    Class<?>[] groups() default { };
    //负载 获取到State的附加信息
    Class<? extends Payload>[] payload() default { };

}
