package com.eddie.controller;

import com.eddie.pojo.Article;
import com.eddie.pojo.PageBean;
import com.eddie.pojo.Result;
import com.eddie.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 新增文章
     * @param article
     * @return
     */
    @PostMapping
    public Result add(@RequestBody @Validated Article article){
        articleService.add(article);
        return Result.success("新增文章成功");
    }

    /**
     * 文章分页查询
     * @param pageNum
     * @param pageSize
     * @param categoryId
     * @param state
     * @return
     */
    @GetMapping
    public Result<PageBean> list(Integer pageNum, Integer pageSize,
                                          @RequestParam(required = false) String categoryId,
                                          @RequestParam(required = false)String state){
        PageBean pageBean = articleService.pageQuery(pageNum, pageSize, categoryId, state);
        return Result.success(pageBean);
    }



}
