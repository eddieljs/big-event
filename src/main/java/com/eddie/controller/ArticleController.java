package com.eddie.controller;

import com.eddie.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    @GetMapping("/list")
    public Result<String> list(){


        return Result.success("文章数据");
    }

}
