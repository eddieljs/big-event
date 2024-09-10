package com.eddie.controller;

import com.eddie.pojo.Category;
import com.eddie.pojo.Result;
import com.eddie.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/article")
@Validated
public class ArticleController {

    @Autowired
    private ArticleService  articleService;

    @GetMapping("/list")
    public Result<String> list(){

        return Result.success("文章数据");
    }



}
